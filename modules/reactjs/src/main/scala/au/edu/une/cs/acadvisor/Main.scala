package au.edu.une.cs.acadvisor

import au.edu.une.cs.acadvisor.views.Phases
import japgolly.scalajs.react.React
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


object Main extends JSApp {

  val renderNode =  document.getElementById("render-here")

  def main():Unit = {
    println("Javascript started")
    rerender()
  }

  def rerender():Unit = {
    React.render(Phases.structure, renderNode)
  }

}
