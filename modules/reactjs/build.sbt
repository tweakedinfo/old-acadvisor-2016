// core = essentials only. No bells or whistles.
libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "core" % "0.11.2",
  "com.github.japgolly.scalajs-react" %%% "extra" % "0.11.2"
)

// React.JS itself
// Note the JS filename. Can also be "react/0.12.2/react.js", "react.min.js", or "react-with-addons.min.js".
jsDependencies ++= Seq(

  "org.webjars.bower" % "react" % "15.3.2"
    /        "react-with-addons.js"
    minified "react-with-addons.min.js"
    commonJSName "React",

  "org.webjars.bower" % "react" % "15.3.2"
    /         "react-dom.js"
    minified  "react-dom.min.js"
    dependsOn "react-with-addons.js"
    commonJSName "ReactDOM",

  "org.webjars.bower" % "react" % "15.3.2"
    /         "react-dom-server.js"
    minified  "react-dom-server.min.js"
    dependsOn "react-dom.js"
    commonJSName "ReactDOMServer"
)