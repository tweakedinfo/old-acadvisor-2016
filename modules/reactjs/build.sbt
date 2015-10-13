// core = essentials only. No bells or whistles.
libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.9.2"

// React.JS itself
// Note the JS filename. Can also be "react/0.12.2/react.js", "react.min.js", or "react-with-addons.min.js".
jsDependencies +=
  "org.webjars" % "react" % "0.12.2" / "react-with-addons.js" commonJSName "React"