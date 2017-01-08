package pretty_simple

import org.apache.log4j.{Logger, Level}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{StreamingContext, Seconds}

object Twitter_Stream_Recorder {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("TwitterStreaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(1))
    val twitterDStream = TwitterDStream(ssc)
    	.map(s => String.format("%s,%s,%s,%s,%s,%s,%s", s.getUser.getScreenName, s.getCreatedAt.getYear.toString, s.getCreatedAt.getMonth.toString, s.getCreatedAt.getDay.toString, s.getCreatedAt.getHours.toString, s.getCreatedAt.getMinutes.toString, s.getCreatedAt.getSeconds.toString))
    	
    args match {
      case Array() =>
        // Disable logging to make messages more clear
        Logger.getLogger("org").setLevel(Level.OFF)
        Logger.getLogger("akka").setLevel(Level.OFF)
        twitterDStream.count().print()
        twitterDStream.saveAsTextFiles("file:///home/gregoire/twitter/DAU", "txt")
      case _ => throw new IllegalArgumentException("Expecting at most one argument");
    }
    ssc.start()
    ssc.awaitTermination()
  }
}
