package org.apache.kafka.connect.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SocketThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SocketThread.class);
    private Socket clientSocket;
    private ConcurrentLinkedQueue<Packet> messages;
    private static char request;
    private static byte[] dynamicTopic = new byte[8];

    static int CHUNKSIZE = 1024;

    public SocketThread(Socket clientSocket, ConcurrentLinkedQueue<Packet> messages) {
        this.clientSocket = clientSocket;
        this.messages = messages;
    }

    public static byte[] PacketReader(DataInputStream dataReader) throws IOException {
        long size;
        try {
            size = dataReader.readLong();
            request = dataReader.readChar();
            dataReader.read(dynamicTopic);
        } catch (java.io.EOFException e) {
            return null;
        };
        long chunks = size / CHUNKSIZE;
        int lastChunk = (int)(size - (chunks*CHUNKSIZE ));
        byte[] buf = new byte[(int )size];
        int off = 0;
        for (long i = 0; i < chunks; i++) {
            int read = dataReader.read(buf,off,CHUNKSIZE);
            if (read == -1) return null;
            off+=CHUNKSIZE;
        }
        dataReader.read(buf, off, lastChunk);
        return buf;
    }

    public void run() {
        InputStream input;
        OutputStream output;
        try {
            // get the input stream
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
            return;
        }
        // while connected, reads a line and saves it in the queue
        byte[] data;
        DataInputStream dataReader = new DataInputStream(input);
        while (true) {
            try {
                data = PacketReader(dataReader);
                if (request == '1' ) {   /* response is waiting */
                    output.write(new byte[] { 'C' });
                }
                if (data == null) {
                    //clientSocket.close();
                    return;
                } else {
                    Packet p = new Packet(data,new String(dynamicTopic));
                    messages.add(p);
                }
            } catch (IOException e) {
                //log.error(e.getMessage());
                return;
            }
        }
    }
}
