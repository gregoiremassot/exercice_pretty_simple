package pretty_simple

/**
  * Illustrates flatMap + countByValue for wordcount.
  */

import org.apache.spark.sql.SparkSession

object DAU_MAU
{
  def main(args: Array[String]): Unit =
  {
    val spark = SparkSession
      .builder.master("local[*]")
      .appName("DAU and MAU")
      .getOrCreate()

    val df = spark.read.csv("/home/gregoire/twitter/*/*")
    val df_filtered_dau = df.filter(df("_c6")===10)
    val DAU = df_filtered_dau.select("_c0").distinct().count()
    println("DAU: %s", DAU)

    val df_filtered_mau = df.filter(df("_c5")===4)
    val MAU = df_filtered_mau.select("_c0").distinct().count()

    println("Daily Active Users : ", DAU)
    println("Monthly Active Users: ", MAU)


  }
}
