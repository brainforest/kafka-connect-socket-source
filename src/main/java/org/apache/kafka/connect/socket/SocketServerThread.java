package org.apache.kafka.connect.socket;

import org.apache.kafka.connect.errors.ConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SocketServerThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SocketServerThread.class);
    protected ConcurrentLinkedQueue<Packet> messages;

    private ServerSocket serverSocket;
    private Socket clientSocket;


    public SocketServerThread(Integer port) {
        this.messages = new ConcurrentLinkedQueue<>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new ConnectException("Impossible to open socket on port " + port);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // waits for connections
                clientSocket = serverSocket.accept();
                // when new client connected, start a new thread to handle it
                new SocketThread(clientSocket, messages).start();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
