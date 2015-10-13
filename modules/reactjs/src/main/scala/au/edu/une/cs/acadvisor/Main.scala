package au.edu.une.cs.acadvisor

import japgolly.scalajs.react.React
import org.scalajs.dom.{document, window}
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


object Main extends JSApp {

  def main():Unit = {
    window.alert("foo");
    println("Hello")
    React.render(<.div("boo"), document.getElementById("render-here"))
  }

}
