package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.{Main, Selection}
import japgolly.scalajs.react.React
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

object Phases {

  def configure = {
    <.div(
      <.h2("Configure"),
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


  def structure = {
    <.div(
      configure,
      <.h2("Already taken (by T3)"),
      <.h2("Plan")
    )
  }
}
