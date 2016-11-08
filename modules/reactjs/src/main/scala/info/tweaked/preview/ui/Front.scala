package info.tweaked.preview.ui

import japgolly.scalajs.react.ReactElement
import japgolly.scalajs.react.vdom.prefix_<^._

/**
  * Views associated wiith the front page of the site
  */
object Front {

  def home:ReactElement = <.div(
    Headers.picHeader("/assets/images/wheatfields.jpeg",
      <.div(
        Headers.banner("preview.une.edu.au"),
        <.div(^.cls := "container", ^.minHeight := "100vh",
          msg
        )
      ),
      <.div()
    )
  )

  def msg:ReactElement = <.div(^.cls := "row",
    <.div(^.cls := "col-md-6 col-md-offset-3 inset-space",
      <.div(^.cls := "inset-tab",
        <.h1("A glance inside the box"),
        <.p(
          """
            | UNE Preview is a proof-of-concept for a course catalogue that lets you
            | take a look at what's inside our teaching units before you enrol.
          """.stripMargin
        )
      )
    )
  )

}
