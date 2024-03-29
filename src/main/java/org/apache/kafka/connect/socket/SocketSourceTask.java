package org.apache.kafka.connect.socket;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class SocketSourceTask extends SourceTask {
    private final static Logger log = LoggerFactory.getLogger(SocketSourceTask.class);

    private Integer port;
    private Integer batchSize = 100;
    private String topic;
    private SocketServerThread socketServerThread;


    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }


    @Override
    public String version() {
        return new SocketSourceConnector().version();
    }

    /**
     * Start the Task. Handles configuration parsing and one-time setup of the Task.
     *
     * @param map initial configuration
     */
    @Override
    public void start(Map<String, String> map) {
        try {
            port = Integer.parseInt(map.get(SocketSourceConnector.PORT));
        } catch (Exception e) {
            throw new ConnectException(SocketSourceConnector.PORT + " config should be an Integer");
        }

        try {
            batchSize = Integer.parseInt(map.get(SocketSourceConnector.BATCH_SIZE));
        } catch (Exception e) {
            throw new ConnectException(SocketSourceConnector.BATCH_SIZE + " config should be an Integer");
        }

        topic = map.get(SocketSourceConnector.TOPIC);

        log.trace("Opening Socket");
        socketServerThread = new SocketServerThread(port);
        new Thread(socketServerThread).start();
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        List<SourceRecord> records = new ArrayList<>(0);
        // while there are new messages in the socket queue
        while (!socketServerThread.messages.isEmpty() && records.size() < batchSize) {
            // get the message
            Packet p = socketServerThread.messages.poll();
            // creates the record
            if (p.getData() != null) {
                SourceRecord record = new SourceRecord(Collections.singletonMap("socket", 0),
                        Collections.singletonMap("0", 0),
                        p.getTopic(),
                        Schema.OPTIONAL_BYTES_SCHEMA,
                        UUID.randomUUID().toString().getBytes(),
                        Schema.OPTIONAL_BYTES_SCHEMA,
                        p.getData());
                records.add(record);
            } else Thread.sleep(1);
        }
        return records;
    }

    @Override
    public void stop() {
        socketServerThread.stop();
    }
}
