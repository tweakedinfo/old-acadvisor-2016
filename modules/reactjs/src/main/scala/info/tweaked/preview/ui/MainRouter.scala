package info.tweaked.preview.ui

import info.tweaked.model.plan.Course
import info.tweaked.preview.ui.degreeCourse.VCourse
import info.tweaked.preview.ui.teachingUnit.{VPreviews, VTeachingUnit}

import scala.language.implicitConversions
import japgolly.scalajs.react.extra.router._

object MainRouter {

  val base = "#!/"

  implicit val baseUrl:BaseUrl = BaseUrl.fromWindowOrigin / base


  sealed trait MyPages
  case object Home                extends MyPages {
    val path = base
  }

  case class CourseP(id:String)    extends MyPages
  object CourseP {
    def pathRel(c:String) = s"course/${c}"
    def path(c:String) = base + pathRel(c)
  }

  case class TeachingUnitP(id:String)    extends MyPages
  object TeachingUnitP {
    def pathRel(c:String) = s"unit/${c}"
    def path(c:String) = base + pathRel(c)
  }

  case class PreviewP(unitId:String, previewIdx:Int)    extends MyPages
  object PreviewP {
    def pathRel(c:String, idx:Int) = s"unit/$c/preview/$idx"
    def path(c:String, idx:Int) = base + pathRel(c, idx)
  }

  case class PlannerP(course:String)   extends MyPages
  object PlannerP {
    def pathRel(c:String) = s"course/$c/planner"
    def path(c:String) = base + pathRel(c)
  }

  val routerConfig = RouterConfigDsl[MyPages].buildConfig { dsl =>

    import dsl._

    val key = string("[a-z0-9]+")
    val camel = string("[a-zA-Z0-9]+")

    (
      emptyRule
      | staticRoute(root, Home) ~> render(Front.home)
      | dynamicRouteCT[TeachingUnitP]("unit" / key.caseClass[TeachingUnitP]) ~> dynRender(VTeachingUnit.unitHome)
      | dynamicRouteCT[PreviewP](("unit" / key / "preview" / int).caseClass[PreviewP]) ~> dynRender(VPreviews.topicHome)
      | dynamicRouteCT[PlannerP]("course" / camel.caseClass[PlannerP] / "planner") ~> dynRender(VCourse.planner)
    //  | dynamicRouteCT[CourseP]("course" / key.caseClass[CourseP]) ~> dynRender(CourseViews.courseFront(_))
    //  | dynamicRouteCT("task" / key.caseClass[TaskP]) ~> dynRender(TaskViews.taskFront(_))

    ).notFound(redirectToPage(Home)(Redirect.Replace))
  }

  // Set up the router component, assuming / to be the root URL
  val (router, logic) = Router.componentAndLogic(baseUrl, routerConfig)

  def goTo(p:MyPages) = logic.ctl.set(p).runNow()
}
