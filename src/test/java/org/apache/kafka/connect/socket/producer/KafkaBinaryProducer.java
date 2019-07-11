package org.apache.kafka.connect.socket.producer;

import com.legstar.coxb.transform.HostTransformException;
import org.apache.kafka.connect.socket.model.Record;
import org.apache.kafka.connect.socket.model.Response;
import org.apache.kafka.connect.socket.transportation.Transport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KafkaBinaryProducer {

    static int HEADER=8;
    static int RECORDSIZE=27;

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }

    public static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

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

    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    public static void main(String[] args) throws IOException, InterruptedException, HostTransformException {
        byte[] buffer = new byte[RECORDSIZE];
        List<Record> records = new ArrayList<>();
        Transport transport = new Transport("localhost",12345);

        FileInputStream fileInputStream = new FileInputStream(new File("src/resources/DTAR020.bin"));
        while(fileInputStream.read(buffer) > 0) {
            Record record = new Record();
            record.setSize(RECORDSIZE);
            record.setRequest(Response.NOT_REQUIRED);
            record.setMessage(buffer);
            System.out.println(encodeHexString(buffer));
            records.add(record);
        }

        int numOfThreads = 5;
        final int numberOfMessage = 5;
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i<numOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j=0; j< numberOfMessage; j++) {
                    int index = (int) (Math.random() * 370);
                    try {
                        transport.send(records.get(index));
                    } catch (IOException e) {
                        e.printStackTrace();
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
