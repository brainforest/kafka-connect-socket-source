package org.apache.kafka.connect.socket.server;

import com.google.common.hash.Hashing;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketReader {
    static int CHUNKSIZE = 1000;
    public static char request;

    public static byte[] PacketReader(DataInputStream dataReader) throws IOException {
        long size;  ;
        try {
            size = dataReader.readLong();
            request = dataReader.readChar();
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

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        long counter = 1;
        while (true) {
            Socket clientSocket = server.accept(); //Server will be using this instance of Socket
            DataInputStream dataReader = new DataInputStream(clientSocket.getInputStream());
            byte[] packet;
            while ((packet = PacketReader(dataReader))!=null) {
                if ((counter % 1000) == 0) {
                    String data = new String(packet);
                    String format = String.format("Id=%05d Size=%04d Read=%s", counter, packet.length,  Hashing.sha256().hashBytes(data.getBytes()));
                    System.out.println(format);
                }
                counter++;
            }
       }
    }
}
