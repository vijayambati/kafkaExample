package controllers

import play.api.mvc.{Action, Controller}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import cakesolutions.kafka.akka.KafkaConsumerActor.{Confirm, Subscribe}
import cakesolutions.kafka.akka._
import cakesolutions.kafka.{KafkaConsumer, KafkaProducer}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import scala.concurrent.duration._

/**
  * A very small controller that renders a home page.
  */
class HomeController extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }
  
  def testAttemptPost = Action { implicit request =>
    
    val producerConf = KafkaProducer.Conf(new StringSerializer, new StringSerializer).withConf(producerConfig)
    val producer = context.actorOf(KafkaProducerActor.props(producerConf))
    
    Ok("Done")
    
  }
}

