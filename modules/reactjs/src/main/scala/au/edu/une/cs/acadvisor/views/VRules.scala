package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{SelUnitChoice, SelUnit, Selection}
import info.tweaked.{Course, NamedRule, Prerequisite, TUnit}
import japgolly.scalajs.react.{ReactComponentB, BackendScope}
import japgolly.scalajs.react.vdom.prefix_<^._

object VRules {

  def link(code:String) = <.a(^.target := "_cauc", ^.href := s"https://my.une.edu.au/courses/units/$code", "Unit catalogue")

  def require(r:Prerequisite) = {
    r match {
      case Prerequisite.Yes => <.span("None")
      case r => <.span(r.stringify)
    }
  }

  def details(u:TUnit) = <.div(^.cls := "details",
    if (u.description.nonEmpty) <.div(^.cls := "description", u.description) else EmptyTag,
    <.div(^.cls := "prereq",
      "requires: ",
      require(u.require)
    ),
    <.div(^.cls := "CAUC", link(u.code))
  )

  def unitInRules(u:TUnit) = {
    val selected = Selection.selected(u)
    def selectedC = if (Selection.selection == SelUnit(u)) " selected " else ""
    def selectable = if (Selection.selectable(u)) " selectable " else " unselectable "
    def validSel = Selection.selection match {
      case SelUnitChoice(_) => if (Selection.validSel(u)) " valid-sel " else " invalid-sel "
      case _ => ""
    }

    <.div(^.cls := "unit-choice " + Selection.rules.coloringRule(u) + selectedC + selectable + validSel,
      ^.onClick --> Selection.select(u),
      <.div(^.cls := "unit-code", u.code),
      <.div(^.cls := "unit-name", u.name),
      if (selected) details(u) else EmptyTag
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
      p.required.map(unitInRules)
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

    def valid = if (Selection.checkRuleAgainstPlan(rule.rule)) " valid " else " invalid "

    if (state) {
      <.div(^.cls := "named-rule" + valid,
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

  def degree(course:Course) = {
    <.div(
      <.h3("Rules"),
      for { r <- course.rules } yield nr(r)
    )
  }


}
