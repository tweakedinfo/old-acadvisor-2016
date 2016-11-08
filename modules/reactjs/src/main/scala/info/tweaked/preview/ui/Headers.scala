package info.tweaked.preview.ui

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

object Headers {


  def uneLogo(heightClass:String = "") = <.img(^.src := "http://www.une.edu.au/__data/assets/image/0011/344/une-logo.png?v=0.1.5", ^.`class` := "corp-logo " + heightClass)

  def picHeader(img:String, within:ReactElement, bottomLine:ReactElement):ReactElement = {
    <.div(^.cls := "pic-header",
      <.div(^.cls := "pic-header-img", ^.backgroundImage := "url(" + img + ")", within),
      <.div(^.`class` := "up-from-bottom", bottomLine)
    )
  }

  def banner(title:String):ReactElement = <.div(
    <.div(^.cls := "header", ^.role := "banner",
      <.div(^.cls := "container",
        <.a(^.href := "http://www.une.edu.au", ^.cls := "logo",
          <.img(^.src := "http://www.une.edu.au/__data/assets/image/0011/344/une-logo.png?v=0.1.5", ^.alt := "University of New England Home"),
          <.span(^.cls := "site-title lightitalic", title)
        )
      )
    )
  )

  def sepStrip(title:String):ReactElement = {
    <.div(^.cls := "separator-strip",
      <.div(^.cls := "container",
        <.h1(title)
      )
    )
  }

  def wideTitledCard(title:String, onHeaderClick: () => Callback, t:TagMod):TagMod = {
    <.div(^.cls := "col-md-12",
      <.div(^.cls := "card",
        <.a(^.onClick --> onHeaderClick(),
          <.div(^.cls := "card-header text-xs-center",
            "Resources"
          )
        ), t
      )
    )
  }


  type ExpanderState = Boolean
  class ExpanderBackend($: BackendScope[(ExpanderBackend => ReactElement, ExpanderBackend => ReactElement), ExpanderState]) {

    def toggle():Callback = $.modState(s => !s)

    def render(s:ExpanderState, p:(ExpanderBackend => ReactElement, ExpanderBackend => ReactElement)):ReactElement = {
      if (s) {
        p._2(this)
      } else {
        p._1(this)
      }
    }
  }

  val expander = ReactComponentB[(ExpanderBackend => ReactElement, ExpanderBackend => ReactElement)]("TileCard")
    .initialState(false)
    .backend(new ExpanderBackend(_))
    .renderBackend
    .build



  def expandableCard(
    shrunkTitle:String,
    shrunkClasses:String,
    shrunkContent:ReactElement,
    expandedTitle:String,
    expandedClasses:String,
    expandedContent:ReactElement
  ):ReactElement = {

    def drawShrunk(ebe:ExpanderBackend):ReactElement = <.div(^.cls := shrunkClasses,
      <.div(^.cls := "card",
        <.a(^.onClick --> ebe.toggle(),
          <.div(^.cls := "card-header text-xs-center", shrunkTitle)
        ),
        <.a(^.onClick --> ebe.toggle(),
          shrunkContent
        )
      )
    )

    def drawExpanded(ebe:ExpanderBackend):ReactElement = <.div(^.cls := expandedClasses,
      <.div(^.cls := "card",
        <.a(^.onClick --> ebe.toggle(),
          <.div(^.cls := "card-header text-xs-center", expandedTitle)
        ),
        expandedContent
      )
    )

    expander.apply((drawShrunk, drawExpanded))

  }


}

