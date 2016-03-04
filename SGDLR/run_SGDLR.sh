#!/bin/sh

#
# Q: BinaryClassification.class or BinaryClassification
# A: BinaryClassification.class is not valid
#
# Q: is '--jars $SPARK_OTHER_JARS' required?
#

export HADOOP_HOME=/home/david/ml-env/hadoop-2.6.2
export YARN_CONF_DIR=/home/david/ml-env/hadoop-2.6.2/etc/hadoop/

export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"

SPARK_ROOT=/home/david/ml-env/spark-1.6.0
MAIN_EXAMPLE_CLASS=spark.tests.testSGDLR
SPARK_OTHER_JARS=

SPARK_MASTER_JAR=target/SGDLR-1.0-SNAPSHOT-jar-with-dependencies.jar
$SPARK_ROOT/bin/spark-submit  \
        --verbose             \
        --class $MAIN_EXAMPLE_CLASS \
        --master yarn-client \
        $SPARK_MASTER_JAR \


