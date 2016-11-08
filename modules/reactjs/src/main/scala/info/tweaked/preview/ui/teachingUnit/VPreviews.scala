package info.tweaked.preview.ui.teachingUnit

import au.edu.une.cs.cstweaked.CoscUnits
import info.tweaked.model.content.TopicSection
import info.tweaked.model.unit.Preview
import info.tweaked.preview.ui.Headers
import info.tweaked.preview.ui.MainRouter.{PreviewP, TeachingUnitP}
import info.tweaked.preview.ui.contentItem.VContentItem
import japgolly.scalajs.react.ReactElement
import japgolly.scalajs.react.vdom.prefix_<^._

object VPreviews {

  def topicHome(path:PreviewP):ReactElement = {
    // FIXME: breaks if unit not present
    val unit = CoscUnits.units(path.unitId)

    // FIXME: breaks if preview item not present
    val previewItem = unit.preview.get.topics(path.previewIdx)

    <.div(
      Headers.picHeader(previewItem.wideImage.getOrElse("/assets/images/wheatfields.jpeg"),
        <.div(
          Headers.banner(unit.name getOrElse path.unitId),
          <.div(^.cls := "container",

            <.div(^.cls := "inset-space",
              <.div(^.cls := "fh"

              )
            )
          ),
          unit.preview.map(_ => Headers.sepStrip(previewItem.title))
        ),
        <.div()
      )
    )
  }


  def topicContent(ts:TopicSection):ReactElement = <.div(
      ts.wideImage.map({ img =>
        <.img(^.src := img, ^.cls := "card-img tile-image")
      }),
      <.div(^.cls := "card-block",
        ts.content.map({ item =>
          VContentItem.renderContents(item)

        })
      )
  )

}
