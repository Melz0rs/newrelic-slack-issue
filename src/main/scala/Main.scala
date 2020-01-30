import akka.actor.ActorSystem
import slack.api.SlackApiClient

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("slack")

  val token = sys.env.getOrElse("SLACK_APP_TOKEN", "asd")
  val slackApiClient: SlackApiClient = SlackApiClient(token)

  val channel = "@some.user"
  val text = "some text"

  val task = for {
    _ <- slackApiClient.postChatMessage(channel, text) recover {case _ => "failed"}
    terminating <- system.terminate()
  } yield terminating

  Await.result(task, 10.seconds)
}
