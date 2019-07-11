package org.apache.kafka.connect.socket.model;

import java.nio.ByteBuffer;



public class Record {


    protected long size;
    protected char request;
    protected byte[] message;

    public Record() {
    }

    public byte[] build() {
        ByteBuffer data =  ByteBuffer.allocate((int) (size + 10));
        data.putLong(size);
        data.putChar(request);
        data.put(message);
        return data.array();
    }

    public Record(long size, char request, byte[] message) {
        this.size = size;
        this.request = request;
        this.message = message;
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
}
