# Kafka Connect Socket Source
The connector is used to load data to Kafka from a Socket. TCP/IP source connector is based on request/reply mechanism and sample Record Layout, containing size of payload.

# Building
You can build the connector with Maven using the standard lifecycle phases:
```
mvn clean
mvn package
```

# Sample Configuration
``` ini
name=socket-connector
connector.class=org.apache.kafka.connect.socket.SocketSourceConnector
tasks.max=1
topic=connect-test
port=12345
batch.size=100
```
# kafka-connect-socket-source
