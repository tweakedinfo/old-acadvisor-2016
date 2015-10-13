// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")

// Scala.js for client-side components written in Scala
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.5")

// Brings the compiled Scala.js JavaScript into the Play project's assets
addSbtPlugin("com.vmunier" % "sbt-play-scalajs" % "0.2.8")


addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")

// Use Less CSS
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0")


// --- DEV TOOLS ---

// Means sbt can show a tree structure of our dependencies
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.5")
