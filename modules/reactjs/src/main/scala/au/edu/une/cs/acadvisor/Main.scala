package au.edu.une.cs.acadvisor

import info.tweaked.preview.ui.{MainRouter, Headers}
import japgolly.scalajs.react.{ReactDOM}
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
    ReactDOM.render(MainRouter.router(), renderNode)
  }

}
