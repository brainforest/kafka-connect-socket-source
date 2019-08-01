package org.apache.kafka.connect.socket;

public class Packet {
    byte[] data;
    String topic;

    public Packet(byte[] data, String topic) {
        this.data = data;
        this.topic = topic;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
