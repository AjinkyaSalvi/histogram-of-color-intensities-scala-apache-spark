import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object Histogram
{

  def main (args: Array[String])
  {

    val conf = new SparkConf().setAppName("Histogram")
    val sc = new SparkContext(conf)
    var red=sc.textFile(args(0)).map(line=> {val a=line.split(",")
        ((1,a(0)),1)})
    var green=sc.textFile(args(0)).map(line=> {val a=line.split(",")
      ((2,a(1)),1)})
    var blue=sc.textFile(args(0)).map(line=> {
      val a=line.split(",")
      ((3,a(2)),1)})

    	val color=red++green++blue
    	val x=color.reduceByKey(_+_)
    	val y=x.collect()
    		y.map(x=>{x._1._1+"\t"+x._1._2+"\t"+x._2}).foreach(println)
     }
}
