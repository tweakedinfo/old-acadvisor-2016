package info.tweaked.preview.ui.degreeCourse

import au.edu.une.cs.cstweaked.{Courses, CoscUnits}
import info.tweaked.model.plan._
import info.tweaked.model.unit.{TeachingUnit, Term}
import info.tweaked.preview.ui.Headers
import info.tweaked.preview.ui.MainRouter.{PlannerP, TeachingUnitP}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLSelectElement

object VCourse {

  case class PlannerState(
    course: Course,
    majors: Seq[Major],
    intake: Option[Intake] = None,
    plan: Plan = Plan("dynamic"),
    selectedTerm: Option[Term] = None,
    selectedUnit: Option[TeachingUnit] = None
  ) {
    def rules = course.rules ++ majors.flatMap(_.rules)

    def terms = intake match {
      case Some(i) => Term.terms.filter(_.start.toEpochDays >= i.start.start.toEpochDays)
      case None => Seq.empty
    }
  }

  class PlannerBackend($: BackendScope[Course, PlannerState]) {

    def onMajorChange(e:SyntheticEvent[HTMLSelectElement]):Callback = {
      val v = e.target.value

      $.modState({ s: PlannerState =>
        val m = s.course.majors.find(_.name == v)
        s.copy(majors = m.toSeq)
      })
    }

    def onIntakeChange(e:SyntheticEvent[HTMLSelectElement]):Callback = {
      val v = e.target.value.toInt

      $.modState({ s: PlannerState =>
        if (v < 0) {
          s.copy(intake = None)
        } else {
          s.copy(intake = Some(Courses.intakes(s.course)(v)))
        }
      })
    }

    def addUnitToPlan(unit:TeachingUnit, t:Term, s:PlannerState):PlannerState = {
      val unitsInTerm = s.plan.selection.getOrElse(t, Seq.empty)
      s.copy(plan = s.plan.copy(
        selection = s.plan.selection.updated(t, unitsInTerm.+:(unit))
      ), selectedUnit = None)
    }


    def setSelUnit(i: Option[TeachingUnit]):Callback = $.modState({s =>
      (i, s.selectedTerm) match {
        case (Some(u), Some(t)) => addUnitToPlan(u, t, s)
        case _ => s.copy(selectedUnit = i)
      }
    })

    def setSelTerm(i: Option[Term]):Callback = $.modState({s =>
      (s.selectedUnit, i) match {
        case (Some(u), Some(t)) => addUnitToPlan(u, t, s)
        case _ => s.copy(selectedTerm = i)
      }
    })

    def setMajor(m: Major):Callback = $.modState(s => s.copy(majors = Seq(m)))

    def setIntake(i: Option[Intake]):Callback = $.modState(s => s.copy(intake = i))

    def rulesPanel(s:PlannerState, rs:Seq[NamedRule]):ReactElement = <.div(
      rs.map({ rule => ruleCard(s, rule) })
    )

    def configPanel(s:PlannerState):ReactElement = <.div(^.cls := "card",
      <.div(^.cls := "card-block",

        if (s.course.majors.nonEmpty) {
          <.div(^.cls := "form-group",
            <.label("Major"),
            <.select(^.cls := "form-control", ^.onChange ==> onMajorChange,
              s.course.majors.map { m =>
                <.option(m.name, ^.value := m.name)
              }
            )
          )
        } else "",

        <.div(^.cls := "form-group",
          <.label("Intake"),
          <.select(^.cls := "form-control", ^.onChange ==> onIntakeChange,

            <.option("", ^.value := "-1"),

            for {
              (intake, idx) <- Courses.intakes(s.course).zipWithIndex
            } yield <.option(^.value := idx.toString,
              s"${intake.start.name}; ${intake.ftpt}; ${intake.attendance}"
            )
          )
        )
      )
    )

    def canSelectUnit(u:TeachingUnit, s:PlannerState):Boolean = {
      val termOk = s.selectedTerm match {
        case Some(t) => Courses.offerings.getOrElse(u, Seq.empty).contains(t)
        case _ => true
      }
      termOk && !s.plan.unitInPlan(u)
    }

    def canSelectTerm(t:Term, s:PlannerState):Boolean = {
      val unitOk = s.selectedUnit match {
        case Some(u) => Courses.offerings.getOrElse(u, Seq.empty).contains(t)
        case _ => true
      }
      unitOk
    }

    def unitInRule(s:PlannerState, u:TeachingUnit):ReactElement = {
      <.li(^.cls := "list-group-item teaching-unit",
        if (s.selectedUnit.contains(u)) {
          <.button(^.cls := "btn btn-sm btn-inverse right", ^.onClick --> setSelUnit(None), "-")
        } else {
          <.button(
            ^.cls := "btn btn-sm btn-secondary right",
            ^.onClick --> setSelUnit(Some(u)),
            ^.disabled := !canSelectUnit(u, s),
            "+"
          )
        },
        <.div(^.cls := "code", u.code),
        <.span(^.cls := "name", u.name)
      )
    }

    def termsPanel(s:PlannerState, ts:Seq[Term]):ReactElement = <.div(
      ts.map({ term => termCard(s, term) })
    )


    def termCard(s:PlannerState, t:Term):ReactElement = <.div(^.cls := "card",
      <.div(^.cls := "card-header",
        if (s.selectedTerm.contains(t)) {
          <.button(^.cls := "btn btn-sm btn-inverse", ^.onClick --> setSelTerm(None), "-")
        } else {
          <.button(^.cls := "btn btn-sm btn-secondary",
            ^.onClick --> setSelTerm(Some(t)),
            ^.disabled := !canSelectTerm(t, s),
            "*"
          )
        },
        t.name
      ),
      <.ul(^.cls := "list-group list-group-flush",
        s.plan.selection.getOrElse(t, Seq.empty).map { unit =>
          <.li(^.cls := "list-group-item",
            unit.code
          )
        }
      )
    )


    def ruleMessage(p:Prerequisite):TagMod = p match {
      case Prerequisite.Contains(args @ _*) => "Complete the following units"
      case Prerequisite.NumFrom(i, args @ _*) => s"Complete $i from the following units"
      case _ => p.stringify
    }

    def ruleCard(s:PlannerState, r:NamedRule):ReactElement = <.div(^.cls := "card",
      <.div(^.cls := "card-header",
        if (r.rule.apply(s.plan.allUnits)) {
          <.span(<.i(^.cls := "fa fa-check-square"), " ")
        } else {
          ""
        },
        r.name
      ),
      <.div(^.cls := "card-block", ruleMessage(r.rule)),
      <.div(^.cls := "list-group list-group-flush",
        for { u <- r.rule.units } yield unitInRule(s, u)
      )
    )

    def render(s:PlannerState, course:Course):ReactElement = {
      <.div(^.cls := "container",
        <.div(^.cls := "row",
          <.div(^.cls := "col-xs-12",
            configPanel(s)
          )
        ),
        <.div(^.cls := "row",
          <.div(^.cls := "col-md-12",
            <.div(^.cls := "card",
              <.div(^.cls := "card-header text-xs-center", "Instructions"),
              <.div(^.cls := "card-block",
                """
                  | When you select a unit and a term, that unit will be moved into your plan.
                  | The course rules will check themselves against your plan as you go.
                  | If you'd like, you can also ask some rules to automatically populate your plan
                  | with their units.
                """.stripMargin)
            )
          )
        ),
        <.div(^.cls := "row",
          <.div(^.cls := "col-xs-6",
            rulesPanel(s, s.rules)
          ),
          <.div(^.cls := "col-xs-6",
            termsPanel(s, s.terms)
          )
        )
      )
    }
  }

  val plannerC = ReactComponentB[Course]("TileCard")
    .initialState_P( (p:Course) => PlannerState(p, Seq.empty) )
    .backend(new PlannerBackend(_))
    .renderBackend
    .build


  def planner(path:PlannerP):ReactElement = {
    // FIXME: breaks if course not present
    val course = Courses.courses(path.course)

    <.div(
      Headers.picHeader("/assets/images/wheatfields.jpeg",
        <.div(
          Headers.banner(course.name),
          <.div(^.cls := "container", ""
          )
        ),
        <.div()
      ),
      plannerC(course)
    )
  }


}

/*


    def onFtptChange(e:SyntheticEvent[HTMLSelectElement]):Callback = {
      val v = e.target.value

      if (v == FullTime.toString)
        $.modState((s:PlannerState) => s.copy(ftptFilter = FullTime))
      else
        $.modState((s:PlannerState) => s.copy(ftptFilter = PartTime))
    }

    def onAttendanceChange(e:SyntheticEvent[HTMLSelectElement]):Callback = {
      val v = e.target.value

      if (v == OnCampus.toString)
        $.modState((s:PlannerState) => s.copy(attendanceFilter = OnCampus))
      else
        $.modState((s:PlannerState) => s.copy(attendanceFilter = OffCampus))
    }


        <.div(^.cls := "form-group",
          <.label("Mode"),
          <.select(^.cls := "form-control", ^.defaultValue := s.attendanceFilter.toString, ^.onChange ==> onAttendanceChange,
            <.option("On campus", ^.value := OnCampus.toString),
            <.option("Online", ^.value := OffCampus.toString)
          )
        ),

        <.div(^.cls := "form-group",
          <.label("Basis"),
          <.select(^.cls := "form-control", ^.defaultValue := s.ftptFilter.toString, ^.onChange ==> onFtptChange,
            <.option("Full-time", ^.value := FullTime.toString),
            <.option("Part-time", ^.value := PartTime.toString)
          )
        ),


 */