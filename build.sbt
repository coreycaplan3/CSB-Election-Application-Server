name := "csb-voting-app"

version := "1.0"

lazy val `csb-voting-app` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  jdbc,
  cache,
  javaWs,
  specs2 % Test,
  "com.google.code.gson" % "gson" % "2.8.0",
  "com.google.api-client" % "google-api-client" % "1.22.0"
)

libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1212.jre7"

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += Resolver.mavenLocal