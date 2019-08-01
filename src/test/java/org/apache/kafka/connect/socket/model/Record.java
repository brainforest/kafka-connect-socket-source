package org.apache.kafka.connect.socket.model;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Record {
    protected long size;
    protected char request;
    protected byte[] message;
    protected byte[] topic;

    public Record() {
    }


    public byte[] build() {
        ByteBuffer data =  ByteBuffer.allocate((int) (size + 18));
        data.putLong(size);
        data.putChar(request);
        data.put(topic);
        data.put(message);
        return data.array();
    }

    public Record(long size, char request, byte[] message, byte[] topic) {
        this.size =   size;
        this.request = request;
        this.message = message;
        this.topic = topic;
    }

    public byte[] getTopic() {
        return topic;
    }

    public void setTopic(byte[] topic) {
        this.topic = topic;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public char getRequest() {
        return request;
    }

    public void setRequest(char request) {
        this.request = request;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Record{" +
                "size=" + size +
                ", request=" + request +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
