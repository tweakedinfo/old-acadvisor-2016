package info.tweaked.preview.ui.contentItem

import info.tweaked.model.content._
import japgolly.scalajs.react.ReactElement
import japgolly.scalajs.react.vdom.prefix_<^._

object VContentItem {

  def renderBody(body:ContentItemBody) = body match {

    case MarkdownText(text) => <.p(text)

    case YouTubeVideo(id) => <.div(^.cls := "videoWrapper",
      <.iframe(
        ^.src := "https://www.youtube.com/embed/" + id, ^.frameBorder := "0", ^.allowFullScreen := "yes"
      )
    )

    case KalturaVideo(wId, uiConfId, id) => <.div(^.cls := "videoWrapper",
      <.iframe(
        ^.src := s"https://cdnapisec.kaltura.com/html5/html5lib/v2.48.1/mwEmbedFrame.php/p/$wId/uiconf_id/$uiConfId/entry_id/$id?wid=_$wId&iframeembed=true", ^.frameBorder := "0", ^.allowFullScreen := "yes"
      )
    )



  }

  def ciIcon(ci:ContentItem) = ci.details.category match {
    case ContentItemCategory.Video => <.i(^.cls := "fa fa-film")
  }

  def renderContents(ci:ContentItem):ReactElement = ci.details.category match {
    case ContentItemCategory.Message => renderBody(ci.body)
    case ContentItemCategory.ActiveTutorial => <.p(
      <.i(^.cls := "fa fa-futbol-o"), " Active tutorial: ", ci.details.title, " "
    )
    case ContentItemCategory.ProgrammingTutorial => <.p(
      <.i(^.cls := "fa fa-code"), " Tech tutorial: ", ci.details.title, " "
    )
    case ContentItemCategory.Quiz => <.p(
      <.i(^.cls := "fa fa-check-square-o"), " Quiz: ", ci.details.title,
      ci.details.length.map(l => <.i(" (", l, ")"))
    )
    case ContentItemCategory.Tutorial => <.p(
      <.i(^.cls := "fa fa-code"), " Tutorial: ", ci.details.title
    )
    case ContentItemCategory.Video => <.p(
      <.i(^.cls := "fa fa-film"), " Video: ", ci.details.title,
      ci.details.length.map(l => <.i(" (", l, ")"))
    )
    case ContentItemCategory.Discussion => <.p(
      <.i(^.cls := "fa fa-comments"), " Discussion: ", ci.details.title
    )
    case ContentItemCategory.Slack => <.p(
      <.i(^.cls := "fa fa-slack"), " Slack: ", ci.details.title
    )
    case ContentItemCategory.Web => <.p(
      <.i(^.cls := "fa fa-globe"), " Web: ", ci.details.title
    )
    case ContentItemCategory.GitHub => <.p(
      <.i(^.cls := "fa fa-github"), " GitHub: ", ci.details.title
    )
  }





}
