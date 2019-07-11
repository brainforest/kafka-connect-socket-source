package org.apache.kafka.connect.socket.transportation;




import org.apache.kafka.connect.socket.model.Record;
import org.apache.kafka.connect.socket.model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Transport {

    private Socket socket;
    protected DataOutputStream dataWriter ;
    protected DataInputStream dataReader;

    public Transport(String hostName, int port) throws IOException {
        Socket socket = new Socket(hostName, port);
        dataWriter = new DataOutputStream(socket.getOutputStream());
        dataReader = new DataInputStream(socket.getInputStream());
    }

    public void send(Record record) throws IOException {
          final byte[] response = new byte[1];
          dataWriter.write(record.build());
          if (record.getRequest() == Response.REQUIRED) {
              dataReader.read(response);
          }
    }


}
