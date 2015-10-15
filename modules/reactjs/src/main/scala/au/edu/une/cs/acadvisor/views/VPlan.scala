package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{SelUnitChoice, SelUnit, Selection}
import info.tweaked._
import japgolly.scalajs.react.vdom.prefix_<^._

object VPlan {


  def unitChoice(u:UnitChoice) = {
    def selected = if (Selection.selected(u)) " selected " else ""
    def selectable = if (Selection.selectable(u)) " selectable " else " unselectable "
    def validSel = Selection.selection match {
      case SelUnit(_) => if (Selection.validSel(u)) " valid-sel " else " invalid-sel "
      case _ => ""
    }
    
    <.div(^.cls := "unit-choice " + Selection.rules.coloringRule(u.unit) + selected + selectable + validSel,
      ^.onClick --> Selection.select(u),
      <.div(^.cls := "unit-code", u.unit.code),
      <.div(^.cls := "unit-name", u.unit.name)
    )
  }

  def termful(t:Termful) = {
    <.div(^.cls := "col-sm-4",
      <.div(^.cls:="termful",
        <.div(^.cls := "term-name", t.term.name),
        t.unitChoices.map(unitChoice)
      )
    )
  }


  def unchosenBtn = {
    val enabled = Selection.selection match {
      case SelUnitChoice(uc) if uc.unit != TUnit.Unchosen => true
      case _ => false
    }

    <.button(
      ^.disabled := !enabled,
      ^.onClick --> Selection.setSelectedUC(TUnit.Unchosen),
      ^.cls := "btn btn-default",
      "Clear"
    )
  }

  def electiveBtn = {
    val enabled = Selection.selection match {
      case SelUnitChoice(uc) => true
      case _ => false
    }

    <.button(
      ^.disabled := !enabled,
      ^.onClick --> Selection.setSelectedUC(TUnit.elective),
      ^.cls := "btn btn-default",
      "Elective"
    )
  }

  def packBtn = {
    <.button(
      ^.onClick --> Selection.pack(Selection.rules.units),
      ^.cls := "btn btn-default",
      "Pack"
    )
  }

  def year(yr:(Seq[Termful], Int)) = {
    <.div(^.cls := "year",
      <.div(^.cls := "year-number", "Year " + (yr._2 + 1)),
      <.div(^.cls := "row",
        yr._1.map(termful)
      ),
      <.div(
        unchosenBtn,
        electiveBtn,
        packBtn
      )
    )
  }



  def years(terms:Seq[Termful]) = terms.grouped(Selection.terms.size)


  def standingUnit(u:TUnit) = {
    <.div(
      <.div(^.cls := "unit-choice ",
        <.div(^.cls := "unit-code", u.code),
        <.div(^.cls := "unit-name", u.name)
      )
    )
  }

  def standingUnitBunch(s:Seq[TUnit]) = {
    <.div(^.cls := "row",
      for {
        col <- s.grouped((s.size - 1) / 4 + 1)
      } yield <.div(^.cls := "col-sm-3",
        col.map(standingUnit)
      )
    )
  }

  def standing(standing:Seq[TUnit]) = {
    <.div(
      if (standing.nonEmpty) <.div(
        <.h3("Deemed to have completed from previous study"),
        standingUnitBunch(standing)
      ) else EmptyTag
    )
  }


  def plan(plan:Plan) = {
    <.div(
      standing(Selection.standing),
      <.h3(plan.name),
      years(plan.terms).zipWithIndex.map(year)
    )
  }


}
