import _root_.playscalajs.ScalaJSPlay
import _root_.playscalajs.ScalaJSPlay.autoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

organization in ThisBuild := "info.tweaked"

version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.6"

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

crossScalaVersions in ThisBuild := Seq("2.11.0")


lazy val reactjs = project.in(file("modules/reactjs"))
  .settings(commonSettings:_*)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false,
    libraryDependencies in ThisBuild ++= Seq(
      "org.specs2" %% "specs2" % "2.3.12" % "test",
      "junit" % "junit" % "4.7" % "test"
    )

  )
