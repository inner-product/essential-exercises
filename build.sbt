name := "essential-exercises"

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / useSuperShell := false

// ScalaFix configuration
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"

val catsVersion = "2.4.2"
val catsEffectVersion = "3.3.5"
val catsParseVersion = "0.3.3"
val circeVersion = "0.13.0"
val doodleVersion = "0.10.1"
val kantanVersion = "0.6.2"
val munitVersion = "0.7.22"
val scalaTestVersion = "3.2.9"

val build = taskKey[Unit]("Format, compile, and test")

val sharedSettings = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % catsVersion,
    "org.typelevel" %% "cats-effect" % catsEffectVersion,
    "org.typelevel" %% "cats-parse" % catsParseVersion,
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "org.creativescala" %% "doodle" % doodleVersion,
    "com.nrinaudo" %% "kantan.csv" % kantanVersion,
    "org.scalameta" %% "munit" % munitVersion % Test,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "org.scalatestplus" %% "scalacheck-1-15" % "3.2.5.0" % Test,
    "io.chrisdavenport" %% "cats-scalacheck" % "0.3.0" % Test
  ),
  // Turn on some compiler flags that scalafix needs
  scalacOptions ++= Seq(
    "-Yrangepos",
    "-Ymacro-annotations",
    "-Wunused:imports"
  ),
  // Don't warn on dead code---some of the exercises need this
  scalacOptions -= ("-Wdead-code"),
  testFrameworks += new TestFramework("munit.Framework"),
  addCompilerPlugin(scalafixSemanticdb)
)

lazy val root = project
  .in(file("."))
  .settings(
    sharedSettings,
    build := {
      Def.sequential(scalafixAll.toTask(""), scalafmtAll, Test / test).value
    }
  )

lazy val benchmarks = project
  .in(file("benchmarks"))
  .settings(
    sharedSettings,
    build := {
      Def.sequential(scalafixAll.toTask(""), scalafmtAll, Test / test).value
    }
  )
  .enablePlugins(JmhPlugin)
  .dependsOn(root)
