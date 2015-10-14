package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{SelUnitChoice, SelUnit, Main, Selection}
import au.edu.une.cs.cstweaked.CompUnits
import info.tweaked._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, ReactEventI}
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

object Phases {

  def configure = {
    <.div(
      <.h2("1. Configure your program"),
      <.form(^.cls := "form-horizontal",
        <.div(^.cls := "form-group",
          <.label(^.`for` := "degree", ^.cls := "control-label col-sm-2", "Degree "),
          <.div(^.cls := "col-sm-6",
            <.select(^.cls := "form-control", ^.id := "degree", ^.value := Selection.rules.name,
              ^.onChange ==> { (e:ReactEventI) => Selection.setCourse(e.target.value) },
              for { course <- Selection.courses } yield <.option(
                ^.value := course.name,
                course.name
              )
            )
          )
        ),
        <.div(^.cls := "form-group",
          <.label(^.`for` := "units-per-term", ^.cls := "control-label col-sm-2", "Units per term "),
          <.div(^.cls := "col-sm-1",
            <.select(^.cls := "form-control", ^.id := "units-per-term",
              ^.value := Selection.unitsPerTerm,
              ^.onChange ==>  { (e:ReactEventI) => Selection.setUnitsPerTerm(e.target.value.toInt) },
              <.option(^.value := 2, 2), <.option(^.value := 3, 3), <.option(^.value := 4, 4)
            )
          )
        ),
        <.div(^.cls := "form-group",
          <.label(^.`for` := "units-per-term", ^.cls := "control-label col-sm-2", "Starting in term "),
          <.div(^.cls := "col-sm-1",
            <.select(^.cls := "form-control", ^.id := "units-per-term",
              ^.value := Selection.terms.indexOf(Selection.startingTerm),
              ^.onChange ==>  { (e:ReactEventI) => Selection.setStartingTerm(e.target.value.toInt) },
              for { (t, i) <- Selection.terms.zipWithIndex } yield <.option(
                ^.value := i,
                t.name
              )
            )
          )
        )

      )
    )
  }


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

  def year(yr:(Seq[Termful], Int)) = {
    <.div(^.cls := "year",
      <.div(^.cls := "year-number", "Year " + (yr._2 + 1)),
      <.div(^.cls := "row",
        yr._1.map(termful)
      )
    )
  }



  def years(terms:Seq[Termful]) = terms.grouped(Selection.terms.size)


  def unitInRules(u:TUnit) = {
    def selected = if (Selection.selection == SelUnit(u)) " selected " else ""
    def selectable = if (Selection.selectable(u)) " selectable " else " unselectable "
    def validSel = Selection.selection match {
      case SelUnitChoice(_) => if (Selection.validSel(u)) " valid-sel " else " invalid-sel "
      case _ => ""
    }

    <.div(^.cls := "unit-choice " + Selection.rules.coloringRule(u) + selected + selectable + validSel,
      ^.onClick --> Selection.select(u),
      <.div(^.cls := "unit-code", u.code),
      <.div(^.cls := "unit-name", u.name)
    )
  }


  def ContainsSelect(p:Prerequisite.Contains) = {
    <.div(
      <.p("Complete the following units"),
      p.required.map(unitInRules)
    )
  }

  def NumFromSelect(p:Prerequisite.NumFrom) = {
    <.div(
      <.p(s"Complete ${p.num} of the following units"),
      p.units.map(unitInRules)
    )
  }


  def otherRuleDetails(prereq:Prerequisite) = {
    <.div("(details not shown)")
  }


  class NRBackend($: BackendScope[NamedRule, Boolean]) {
    def toggle() = $.modState(!_)
  }
  val nr = ReactComponentB[NamedRule]("NamedRule")
    .getInitialState(_ => true)
    .backend(new NRBackend(_))
    .render({ (rule, state, b) =>
      if (state) {
        <.div(^.cls := "named-rule",
          <.div(^.cls := "rule-name",
            <.button(^.cls := "btn-link fright", ^.onClick --> b.toggle(), "hide"),
            rule.name
          ),
          namedRule(rule)
        )
      } else {
        <.div(^.cls := "named-rule",
          <.div(^.cls := "rule-name",
            <.button(^.cls := "btn-link fright", ^.onClick --> b.toggle(), "show"),
            rule.name
          )
        )
      }
    })
    .build



  def namedRule(rule:NamedRule) = {
    <.div(
      rule.rule match {
        case p:Prerequisite.Contains => ContainsSelect(p)
        case p:Prerequisite.NumFrom => NumFromSelect(p)
        case p => otherRuleDetails(p)
      },
      if (rule.extra.nonEmpty) {
        <.div(
          <.p("Helpful electives:"),
          rule.extra.map(unitInRules)
        )
      } else EmptyTag
    )
  }

  def plan(plan:Plan) = {
    <.div(
      <.h3(plan.name),
      years(plan.terms).zipWithIndex.map(year)
    )
  }

  def degree(course:Course) = {
    <.div(
      <.h3("Rules"),
      for { r <- course.rules } yield nr(r)
    )
  }



  def planSec = {
    <.div(
      <.h2("3. Plan your degree"),
      <.div(^.cls := "row",
        <.div(^.cls := "col-xs-3",
          degree(Selection.rules)
        ),
        <.div(^.cls := "col-xs-9",
          plan(Selection.plan)
        )
      )
    )
  }

  def oldUnitToggle(u:TUnit) = {
    def taken = if (Selection.taken.contains(u)) " taken " else ""

    <.div(
      <.div(^.cls := "unit-choice " + taken,
        ^.onClick --> Selection.toggleTaken(u),
        <.div(^.cls := "unit-code", u.code),
        <.div(^.cls := "unit-name", u.name)
      )
    )
  }

  def oldUnitBunch(s:Seq[TUnit]) = {
    <.div(^.cls := "row",
      for {
        col <- s.grouped((s.size - 1) / 4 + 1)
      } yield <.div(^.cls := "col-sm-3",
        col.map(oldUnitToggle)
      )
    )
  }

  def alreadyTaken = {
    <.div(
      <.h2("2. Select what you've taken before 2016"),
      <.div(
        "MATH units",  oldUnitBunch(CompUnits.oldMathUnits)
      ),
      <.div(
        "COMP units ", oldUnitBunch(CompUnits.oldCompUnits)
      )
    )
  }


  def structure = {
    <.div(
      configure,
      alreadyTaken,
      planSec
    )
  }

}
