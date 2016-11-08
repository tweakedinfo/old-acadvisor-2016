
// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.9")

// Scala.js for client-side components written in Scala
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.13")

// Brings the compiled Scala.js JavaScript into the Play project's assets
addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.3")


addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")

// Use Less CSS
addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0")

addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.2")


// --- DEV TOOLS ---

// Means sbt can show a tree structure of our dependencies
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.5")
