package actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import cakesolutions.kafka.akka.KafkaConsumerActor.{Confirm, Subscribe}
import cakesolutions.kafka.akka._
import cakesolutions.kafka.{KafkaConsumer, KafkaProducer}
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import cakesolutions.kafka.{KafkaProducer, KafkaProducerRecord}

import scala.concurrent.duration._

object Producer {
  def apply : ActorRef = {
    
    val producerConf = KafkaProducer.Conf(new StringSerializer, new StringSerializer).withConf(ConfigFactory.load().getConfig("producer"))

    val system = ActorSystem()
    system.actorOf(Props(new Producer(producerConf)))

  }
}

class Producer(
  producerConf: KafkaProducer.Conf[String, String]) extends Actor with ActorLogging {
  val producer = context.actorOf(KafkaProducerActor.props(producerConf))
//  producer ! ProducerRecords.fromKeyValues[String, String]("topic1", "Hello", Some(records.offsets), None)
  producer.send(
    KafkaProducerRecord("topic1", "1", "Hello")
}