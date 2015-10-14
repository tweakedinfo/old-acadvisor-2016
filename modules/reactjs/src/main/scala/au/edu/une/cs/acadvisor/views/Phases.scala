package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{Main, Selection}
import info.tweaked._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, React}
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

object Phases {

  def configure = {
    <.div(
      <.h2("1. Configure your program"),
      <.form(^.cls := "form-inline",
        <.div(^.cls := "form-group",
          <.label(^.`for` := "units-per-term", "Units per term"),
          <.select(^.cls := "form-control", ^.id := "units-per-term",
            ^.value := Selection.unitsPerTerm,
            ^.onChange ==> { (v:Int) => Selection.unitsPerTerm = v; Main.rerender() },
            <.option(^.value := 2, 2), <.option(^.value := 3, 3), <.option(^.value := 4, 4)
          )
        )
      )
    )
  }


  def unitChoice(u:UnitChoice) = {
    <.div(^.cls := "unit-choice " + Selection.rules.coloringRule(u.unit),
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


  // TODO: This is a hack for T1, T2, T3
  def years(terms:Seq[Termful]) = terms.grouped(3)


  def unitInRules(u:Unit) = {
    <.div(^.cls := "unit-choice " + Selection.rules.coloringRule(u),
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

  def alreadyTaken = {
    <.div(
      <.h2("2. Select what you've taken before 2016"),

      <.p("Under the new rules you would be deemed to have completed")
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
