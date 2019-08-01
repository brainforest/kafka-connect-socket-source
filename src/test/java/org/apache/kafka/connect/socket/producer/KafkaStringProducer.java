package org.apache.kafka.connect.socket.producer;

import org.apache.kafka.connect.socket.model.Record;
import org.apache.kafka.connect.socket.model.Response;
import org.apache.kafka.connect.socket.transportation.Transport;

import java.io.IOException;

public class KafkaStringProducer {
    /* Max record layout that can be */
    static int PAYLOADSIZE = 256;

    public static String getAlphaNumericString(int n)  {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            if ((i % 5)==0 && i > 0 && i < (n-5)) sb.append("-");
            else sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        final Transport transport = new Transport("localhost",12345);
        int numOfThreads = 10;
        final int numberOfMessage = (int) (Math.random()*100 + 100);
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i<numOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int j=0; j< numberOfMessage; j++) {
                        try {
                            int size = (int) (Math.random() * PAYLOADSIZE + 100);
                            String message = getAlphaNumericString(size);
                            Record record = new Record();
                            record.setSize(size);
                            record.setRequest(Response.NOT_REQUIRED);
                            record.setMessage(message.getBytes());
                            record.setTopic("12345678".getBytes());
                            transport.send(record);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threads[i].start();
        }
        for (int i = 0; i<numOfThreads; i++) threads[i].join();
        long end = System.currentTimeMillis();
        String msg = String.format("%s Messages has been processed @ %s sec, avarege %s req/s",(numberOfMessage* numOfThreads), (end-start)/1000.0,(long )(numberOfMessage * numOfThreads * 1000)/(end-start));
        System.out.println(msg);
    }
}
