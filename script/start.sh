#!/bin/bash
# some folder settings
KAFKA_HOME=/Users/gokcerbelgusen/Downloads/kafka-2.0.0
ZOOKEEPER="./bin/zookeeper-server-start.sh config/zookeeper.properties"
KAFKA="./bin/kafka-server-start.sh config/server.properties"
CONNECT="./bin/connect-standalone.sh config/custom-standalone.properties config/tcpip-source.properties"
LOG="/Users/gokcerbelgusen/Downloads/Projects/kafka-connect-socket-source/log"
#
cd $KAFKA_HOME
echo Starting zookeeper...
nohup $ZOOKEEPER > $LOG/zookeeper.log 2>&1 &
sleep 10
echo Starting Kafka Server...
nohup $KAFKA > $LOG/kafka.log 2>&1 &
echo Starting Kafka Connect Standalone...
sleep 10
nohup $CONNECT > $LOG/connect.log 2>&1 &
