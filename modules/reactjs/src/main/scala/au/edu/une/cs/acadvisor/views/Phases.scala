package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{SelUnitChoice, SelUnit, Main, Selection}
import au.edu.une.cs.cstweaked.CompUnits
import info.tweaked._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, ReactEventI}
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

object Phases {


  def planSec = {
    <.div(
      <.h2("3. Plan your degree"),
      <.div(^.cls := "row",
        <.div(^.cls := "col-xs-3",
          VRules.degree(Selection.rules)
        ),
        <.div(^.cls := "col-xs-9",
          VPlan.plan(Selection.plan)
        )
      )
    )
  }




  def structure = {
    <.div(
      VConfig.configure,
      VTaken.alreadyTaken,
      planSec
    )
  }

}
