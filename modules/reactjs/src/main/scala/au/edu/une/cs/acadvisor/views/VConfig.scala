package au.edu.une.cs.acadvisor.views

import au.edu.une.cs.acadvisor.Selection
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, ReactEventI}

object VConfig {

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

}
