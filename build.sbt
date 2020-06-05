import java.nio.charset.Charset

ThisBuild / organization := "com.hansblackcat"
ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.1.0"

scalacOptions += "-deprecation"
scalacOptions += "-feature"



lazy val root = (project in file("src"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "com.lihaoyi" %%% "utest" % "0.7.4" % "test",
      "be.doeraene" %%% "scalajs-jquery" % "1.0.0",
    )
    
  )

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)
// This is an application with a main method
scalaJSUseMainModuleInitializer := true
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
testFrameworks += new TestFramework("utest.runner.Framework")

scalaJSUseMainModuleInitializer := true

// ------------------------------------------------------------------------------------------------
// Key Setting
// ------------------------------------------------------------------------------------------------

val electronMainPath = SettingKey[File]("electron-main-path", "The absolute path where to write the Electron application's main.")
val electronMain = TaskKey[File]("electron-main", "Generate Electron application's main file.")

electronMainPath := {
  baseDirectory.value / ".." / "electron-scalajs" / "main.js"
}

electronMain := {
  // TODO here we rely on the files written on disk but it would be better to be able to get the actual content directly
  // from the tasks. I am not sure it's possible just yet though.
  val jsCode: String = IO.read((fastOptJS in Compile).value.data, Charset.forName("UTF-8"))
  val launchCode = IO.read((packageScalaJSLauncher in Compile).value.data, Charset.forName("UTF-8"))
  // we don't need jsDeps here but want it to be generated anyway so that we can start the Electron app right away
  val jsDeps = (packageJSDependencies in Compile).value

  // hack to get require and __dirname to work in the main process
  // see https://gitter.im/scala-js/scala-js/archives/2015/04/25
  val hack = """
  var addGlobalProps = function(obj) {
    obj.require = require;
    obj.__dirname = __dirname;
  }
  if((typeof __ScalaJSEnv === "object") && typeof __ScalaJSEnv.global === "object") {
    addGlobalProps(__ScalaJSEnv.global);
  } else if(typeof  global === "object") {
    addGlobalProps(global);
  } else if(typeof __ScalaJSEnv === "object") {
    __ScalaJSEnv.global = {};
    addGlobalProps(__ScalaJSEnv.global);
  } else {
    var __ScalaJSEnv = { global: {} };
    addGlobalProps(__ScalaJSEnv.global)
  }
  """

  val dest = electronMainPath.value
  IO.write(dest, hack + jsCode + launchCode, Charset.forName("UTF-8"))
  dest
}