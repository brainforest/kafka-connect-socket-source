#!/bin/bash
KAFKA_HOME=/Users/gokcerbelgusen/Downloads/kafka-2.0.0
cd $KAFKA_HOME
echo Stopping Kafka Server...
./bin/kafka-server-stop.sh
echo Stopping zookeeper...
./bin/zookeeper-server-stop.sh
echo Stopipng Kafka Connect Standalone...
ps -ef | grep "[k]afka" | awk {'print $2'} | xargs kill -9

