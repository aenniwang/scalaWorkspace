package spark.tests


import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/**
 * @author ${user.name}
 */

class testSGDLR(appName:String, master:String){
  /* Prepare the spark context
  *  Create SparkConext and SparkConf
  *  Do this in first constructor */
  val conf = new SparkConf().setAppName(appName)
  val sc = new SparkContext(conf)

  def trainWithSGD(sourceFile:String): Unit ={
    val data=MLUtils.loadLibSVMFile(sc, sourceFile)
    val splits = data.randomSplit(Array(0.7,0.3),92)
    val tranning = splits(0).cache
    val test = splits(1)

    val SGDModule = new LogisticRegressionWithSGD()
      .run(tranning)

    val predictionAndLabels = test.map{
      case LabeledPoint(label, features) =>
        val prediction = SGDModule.predict(features)
        (prediction, label)
    }
    
    val metrics = new MulticlassMetrics(predictionAndLabels)
    val precision = metrics.precision
    println("SGD LR precision = " + precision)
   }
}

object testSGDLR {
  def main(args: Array[String]) {
    println("Spark LR started")
    var sgdLR = new testSGDLR("SparkLRSGDTest","thisMaster")
    sgdLR.trainWithSGD("sample_binary_classification_data.txt")
  }
}