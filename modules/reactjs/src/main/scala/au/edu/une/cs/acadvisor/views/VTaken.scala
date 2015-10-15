package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.Selection
import au.edu.une.cs.cstweaked.CompUnits
import info.tweaked.TUnit
import japgolly.scalajs.react.vdom.prefix_<^._

object VTaken {


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
        <.h4("MATH units"),  oldUnitBunch(CompUnits.oldMathUnits)
      ),
      <.div(
        <.h4("COMP units"), oldUnitBunch(CompUnits.oldCompUnits)
      )
    )
  }

}
