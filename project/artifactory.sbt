credentials in ThisBuild += {
  import scala.util.Try
  lazy val localCreds = Credentials(file(s"${sys.props("user.home")}/.artifactory-credentials"))
  lazy val circleCreds =
    Credentials(
      realm = "Artifactory Realm",
      host = "riskified.jfrog.io",
      userName = sys.env("ARTIFACTORY_USER"),
      passwd = sys.env("ARTIFACTORY_PASSWORD")
    )
  Try(circleCreds) getOrElse localCreds
}

val releaseResolver =
  "Artifactory Releases" at
    "https://riskified.jfrog.io/riskified/ivy-release-local"

val snapshotResolver =
  "Artifactory Snapshots" at
    "https://riskified.jfrog.io/riskified/ivy-dev-local"

resolvers in ThisBuild ++= Seq(releaseResolver, snapshotResolver)
