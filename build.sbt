import _root_.playscalajs.ScalaJSPlay
import _root_.playscalajs.ScalaJSPlay.autoImport._
import _root_.playscalajs.ScalaJSPlay.autoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

lazy val commonSettings = Seq(
  scalaVersion := "2.11.7",
  organization := "info.tweaked",
  version := "0.1-SNAPSHOT",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  resolvers ++= Seq(
    Resolver.file("Local repo", file("/home/user/.ivy2/local")),
    "sonatype snaps" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "typesafe snaps" at "http://repo.typesafe.com/typesafe/snapshots/",
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
    "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
    DefaultMavenRepository
  )
)

lazy val core = (crossProject.crossType(CrossType.Pure) in file("modules/core"))
  .settings(commonSettings:_*)
  .settings(
    libraryDependencies ++= Seq(
    )
  )
  .jsConfigure(_ enablePlugins ScalaJSPlugin)

lazy val coreJvm = core.jvm
lazy val coreJs = core.js


lazy val cs = (crossProject.crossType(CrossType.Pure) in file("modules/cs"))
  .settings(commonSettings:_*)
  .dependsOn(core)
  .settings(
    libraryDependencies ++= Seq(
    )
  )
  .jsConfigure(_ enablePlugins ScalaJSPlugin)

lazy val csJvm = cs.jvm
lazy val csJs = cs.js

lazy val reactjs = project.in(file("modules/reactjs"))
  .settings(commonSettings:_*)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false
  )

lazy val aggregate = project.in(file("."))
  .settings(commonSettings:_*)
  .aggregate(coreJvm, csJvm, reactjs)

scalaJSStage in Global := FastOptStage
