package info.tweaked.preview.ui.teachingUnit

import au.edu.une.cs.cstweaked.CoscUnits
import info.tweaked.model.unit.Resources.RRR
import info.tweaked.model.unit.{Resources, ReadyQuiz, AssessmentDescription, TeachingUnit}
import info.tweaked.preview.ui.Headers
import info.tweaked.preview.ui.MainRouter.TeachingUnitP
import info.tweaked.preview.ui.contentItem.VContentItem
import japgolly.scalajs.react.{Callback, BackendScope, ReactElement, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._


/**
  * Views associated with a teaching unit
  */
object VTeachingUnit {

  def unitHome(unitPath:TeachingUnitP):ReactElement = {
    // FIXME: breaks if unit not present
    val unit = CoscUnits.units(unitPath.id)

    <.div(
      Headers.picHeader(unit.details.backgroundImg.getOrElse("/assets/images/wheatfields.jpeg"),
        <.div(
          Headers.banner(unit.name getOrElse unitPath.id),
          <.div(^.cls := "container",
            welcome(unit),

            <.div(^.cls := "inset-space",
              <.div(^.cls := "row",

                expander((
                  (exp:ExpanderBackend) => fourCol(tileCard("Ready for this?", cb = exp.toggle)),
                  (exp:ExpanderBackend) => readyForThisCard(unit, cb = exp.toggle)
                  )),
                expander((
                  (exp:ExpanderBackend) => fourCol(tileCard("Outcomes & assessment", cb = exp.toggle)),
                  (exp:ExpanderBackend) => assessmentCard(unit, exp.toggle)
                  )),
                expander((
                  (exp:ExpanderBackend) => fourCol(tileCard("Resources", cb = exp.toggle)),
                  (exp:ExpanderBackend) => resourcesCard(unit, exp.toggle)
                  ))
              )
            )
          ),
          unit.preview.map(_ => Headers.sepStrip("Preview"))
        ),
        <.div()
      ),
      unit.preview.map(_ =>
        tilesPanel(unit)
      )
    )
  }

  def welcome(u:TeachingUnit):ReactElement = <.div(^.cls := "row",
    u.details.welcome.map(item =>
      <.div(^.cls := "col-md-6 col-md-offset-3 inset-space",
        <.div(^.cls := "card",

          for {
            title <- item.details.title
          } yield <.div(^.cls := "card-header text-xs-center", title),

          <.div(^.cls := "card-block", VContentItem.renderBody(item.body))
        )
      )
    )

  )

  def details(s:String):ReactElement = <.span(s)

  type ExpanderState = Boolean
  class ExpanderBackend($: BackendScope[(ExpanderBackend => ReactElement, ExpanderBackend => ReactElement), ExpanderState]) {

    def toggle():Callback = $.modState(s => !s)

    def render(s:ExpanderState, p:(ExpanderBackend => ReactElement, ExpanderBackend => ReactElement)) = {
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


  def tileCard(title:String, img:Option[String] = None, cb: () => Callback = () => Callback {}):ReactElement = {
    <.div(^.cls := "card text-xs-center",
      <.a(^.onClick --> cb(),
        <.div(^.cls := "card-header", title)
      ),
      img.map(i => <.a(^.onClick --> cb(),
        <.img(^.src := img, ^.cls := "card-img tile-image hidden-sm-down")
      ))
    )
  }

  def fourCol(r:ReactElement):ReactElement = <.div(^.cls := "col-md-4 col-lg-3", r)


  def tileGroup(tiles:(String, String)*):ReactElement = {
    <.div(^.cls := "col-md-6 col-lg-4",

      <.div(^.cls := "card-group",

        for {
          (img, title) <- tiles
        } yield
          <.div(^.cls := "card text-xs-center",
            <.div(^.cls := "card-header",
              title
            ),
            <.img(^.src := img, ^.cls := "card-img tile-image hidden-sm-down")
          )

      )
    )
  }

  def assessmentCard(u:TeachingUnit, cb: () => Callback = () => Callback {}):ReactElement = {
    <.div(^.cls := "col-md-12",
      <.a(^.onClick --> cb(),
        <.div(^.cls := "card",
          <.div(^.cls := "card-header text-xs-center",
            "Outcomes and assessment"
          ),
          <.div(^.cls := "card-block",
            <.h4("Learning Outcomes"),
            <.p("At the end of this unit, you will be able to "),
            <.ol(
              u.outcomes.map(outcome => <.li(outcome))
            ),
            <.h4("Assessment"),
            <.table(^.cls := "table",
              <.thead(
                <.tr(
                  <.th(""), <.th("Item"), <.th("Weighting"), <.th("Learning Outcomes")
                )
              ),
              <.tbody(
                u.assessment.map(item =>
                  <.tr(
                    <.td(),
                    <.td(
                      <.b(item.title),
                      <.p(item.description)
                    ),
                    <.td(item.percentage),
                    <.td(
                      item.lo.map(lo => lo + " ")
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
  }

  def readyForThisCard(u:TeachingUnit, cb: () => Callback = () => Callback {}):ReactElement = {
    <.div(^.cls := "col-md-12",
      <.div(^.cls := "card",
        <.a(^.onClick --> cb(),
          <.div(^.cls := "card-header text-xs-center",
            "Are you ready for this?"
          )
        ),
        <.div(^.cls := "card-block",

          <.div(^.cls := "prerequisite",
            <.label("Pre-requisites for degree students: "),
            <.span(" " + u.prerequisite.stringify)
          ),

          u.readyQuiz.map(q => readyQuiz(q))
        )
      )
    )
  }




  def resourcesCard(unit:TeachingUnit, cb: () => Callback = () => Callback {}):ReactElement = {
    <.div(^.cls := "col-md-12",
      <.div(^.cls := "card",
        <.a(^.onClick --> cb(),
          <.div(^.cls := "card-header text-xs-center",
            "Resources"
          )
        ),
        <.div(^.cls := "card-block",

          <.table(^.cls := "table",
            <.tbody(
              interactivity(unit.resources.interactivity),
              tb(Resources.Required, unit.resources.textbooks),
              tb(Resources.Recommended, unit.resources.textbooks),
              tb(Resources.Referenced, unit.resources.textbooks)
            )
          )
        )
      )
    )
  }

  def interactivity(is:Seq[Resources.Interactivity]):TagMod = {
    <.tr(
      <.td("Interactivity"),
      <.td(
        is.map({
          case Resources.FacebookPage => <.p(<.i(^.cls := "fa fa-facebook"), " Facebook page")
          case Resources.Slack => <.p(<.i(^.cls := "fa fa-slack"), " Slack group")
          case Resources.Forum => <.p(<.i(^.cls := "fa fa-comments"), " Forums")
          case Resources.GitHub => <.p(<.i(^.cls := "fa fa-github"), " GitHub")
          case Resources.GroupWork => <.p(<.i(^.cls := "fa fa-users"), " Online group work")
          case Resources.Quizzes => <.p(<.i(^.cls := "fa fa-check-square-o"), " Quizzes")
          case Resources.ProgrammingEnvironments => <.p(<.i(^.cls := "fa fa-code"), " Programming environments")
          case Resources.VideoRecordings => <.p(<.i(^.cls := "fa fa-film"), " Video content")
        })
      )
    )
  }

  def tb(rrr:Resources.RRR, s:Seq[Resources.Textbook]):TagMod = {
    val filtered = s.filter(_.rrr == rrr)

    def leftText(rrr:RRR) = rrr match {
      case Resources.Required => <.div("Required textbooks")
      case Resources.Recommended => <.div("Recommended textbooks (optional)")
      case Resources.Referenced => <.div("Referenced textbooks (optional)")
    }

    if (filtered.nonEmpty) {
      <.tr(
        <.td(leftText(rrr)),
        <.td(
          filtered.map(tb =>
            <.div(^.cls := "textbook",
              <.div(^.cls := "title", tb.title),
              <.div(^.cls := "authors", tb.authors),
              tb.edition.map(e => <.div(^.cls := "edition", e)),
              tb.isbn13.map(e =>
                <.div(^.cls := "isbn", "ISBN: ", e, ". ",
                  <.a(^.target := "_blank", ^.href := s"http://orders.ucb.net.au/ViewBook.aspx?acc=$e",
                    "Get it at UCB >"
                  )
                )
              )
            )
          )
        )
      )
    } else EmptyTag
  }


  def tileImage(img:String):ReactElement =
    <.img(^.src := img, ^.cls := "card-img tile-image hidden-sm-down")


  def tilesPanel(unit:TeachingUnit):ReactElement = {
    <.div(^.cls := "container inset-space",
      <.div(^.cls := "row",

        for {
          p <- unit.preview.toSeq
          ts <- p.topics
        } yield {
          Headers.expandableCard(
            ts.title, "col-md-4", tileImage(ts.wideImage getOrElse "/assets/images/assessment.jpeg"),
            ts.title, "col-md-12", VPreviews.topicContent(ts)
          )

        }

      )
    )

  }

  case class ReadyQuizState(indx:Int, ans:Option[Int])
  class ReadyQuizBackend($: BackendScope[ReadyQuiz, ReadyQuizState]) {

    def answer(i:Option[Int]):Callback = $.modState { s => ReadyQuizState(s.indx, i) }

    def goto(q:Int):Callback = $.modState(s => ReadyQuizState(q, None))

    def render(s:ReadyQuizState, rq:ReadyQuiz):ReactElement = {
      val q = rq.questions(s.indx)
      val first = s.indx == 0
      val last = s.indx >= rq.questions.size - 1


      def prnControls:ReactElement = <.div(
        <.button(^.`type` := "button", ^.cls := "btn btn-sm btn-secondary", ^.disabled := first, ^.onClick --> this.goto(s.indx - 1), "Previous"),
        " ",
        s.ans.map(_ => <.button(^.`type` := "button", ^.cls := "btn btn-sm btn-secondary", ^.onClick --> this.answer(None), "Reset")),
        " ",
        <.button(^.`type` := "button", ^.cls := "btn btn-sm btn-secondary", ^.disabled := last, ^.onClick --> this.goto(s.indx + 1), "Next")
      )

      <.form(^.cls := "ready-quiz",
        <.div(^.cls := "rqmcq-header", s"Question ${s.indx + 1} of ${rq.questions.length}"),
        <.p(q.text),
        <.div(^.cls := "form-group",
          q.options.zipWithIndex.map { case (o, idx) => <.div(
            if (s.ans.isEmpty || s.ans.contains(idx)) {
              <.div(^.cls := "form-check",
                <.label(^.cls := "form-check-label",
                  <.input(^.`type` := "radio", ^.cls := "form-check-input", ^.checked := s.ans.contains(idx), ^.onClick --> answer(Some(idx))),
                  " ", o.text
                )
              )
            } else { <.div() },
            if (s.ans.contains(idx)) {
              <.p(o.response)
            } else <.div()
          )},
          prnControls
        )
      )
    }
  }

  val readyQuiz = ReactComponentB[ReadyQuiz]("ReadyQuiz")
    .initialState(ReadyQuizState(0, None))
    .backend(new ReadyQuizBackend(_))
    .renderBackend
    .build


}
