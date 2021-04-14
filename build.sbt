name := "essential-exercises"

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / useSuperShell := false

// ScalaFix configuration
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"

val catsVersion = "2.4.2"
val catsEffectVersion = "2.3.1"
val circeVersion = "0.13.0"
val doodleVersion = "0.9.23"
val munitVersion = "0.7.22"

val build = taskKey[Unit]("Format, compile, and test")

val sharedSettings = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel"     %% "cats-core"     % catsVersion,
    "org.typelevel"     %% "cats-effect"   % catsEffectVersion,
    "io.circe"          %% "circe-core"    % circeVersion,
    "io.circe"          %% "circe-generic" % circeVersion,
    "io.circe"          %% "circe-parser"  % circeVersion,
    "org.creativescala" %% "doodle"        % doodleVersion,
    "org.scalameta"     %% "munit"         % munitVersion % Test
  ),
  // Turn on some compiler flags that scalafix needs
  scalacOptions ++= Seq(
    "-Yrangepos",
    "-Ymacro-annotations",
    "-Wunused:imports",
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
    build := { Def.sequential(scalafixAll.toTask(""), scalafmtAll, Test / test).value }
  )
