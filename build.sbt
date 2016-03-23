lazy val commonSettings = Seq(
  organization := "com.fredrouvier.poc",
  organizationName := "Frédéric Rouvier",
  organizationHomepage := Some(url("https://github.com/frouvier")),
  scalaVersion := "2.11.7",
  parallelExecution in Test := false,
  ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
)

lazy val configuration = file("src/main/resources")

lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "poc",

    libraryDependencies ++= {
      Seq(
        "org.apache.spark"  %  "spark-streaming_2.11"         % "1.6.1",
        "org.apache.spark"  %  "spark-streaming-kafka_2.11"   % "1.6.1"
      )
    }
  ).
  settings(
    unmanagedClasspath in Test += configuration,
    unmanagedClasspath in Runtime += configuration
  )