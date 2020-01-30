name := "newrelic_slack"

version := "0.1"

scalaVersion := "2.12.10"

enablePlugins(JavaAgent)

libraryDependencies += "com.github.slack-scala-client" %% "slack-scala-client" % "0.2.6"

javaAgents += {
  val agentJar = baseDirectory.value / "agent" / "newrelic-agent-5.10.0.jar"
  val agentJarUrl = s"file://$agentJar"
  "com.newrelic.agent.java" % "newrelic-agent" % "5.10.0" % Runtime from agentJarUrl
}
javaOptions += {
  val configFile = baseDirectory.value / "agent" / "newrelic.yml"
  s"-Dnewrelic.config.file=$configFile"
}