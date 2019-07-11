package org.apache.kafka.connect.socket.consumer;

import com.legstar.coxb.transform.HostTransformException;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.connect.socket.legstar.Dfhcommarea;
import org.apache.kafka.connect.socket.legstar.bind.DfhcommareaTransformers;
import org.codehaus.jackson.map.deser.StdDeserializer;

import java.util.Collections;
import java.util.Properties;

public class SampleKafkaConsumer {

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

    public interface IKafkaConstants {
        public static String KAFKA_BROKERS = "localhost:9092";
        public static Integer MESSAGE_COUNT=1000;
        public static String CLIENT_ID="client1";
        public static String TOPIC_NAME="connect-test";
        public static String GROUP_ID_CONFIG="consumerGroup1";
        public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
        public static String OFFSET_RESET_LATEST="latest";
        public static String OFFSET_RESET_EARLIER="earliest";
        public static Integer MAX_POLL_RECORDS=1;
    }

    public static Consumer<Long, byte[]> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);
        Consumer<Long, byte[]> consumer = new KafkaConsumer<Long, byte[]>(props);
        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_NAME));
        return consumer;
    }

    public static void main(String[] args) {
        Consumer<Long, byte[]> consumer = createConsumer();
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<Long, byte[]> consumerRecords = consumer.poll(1000);
            // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
            if (consumerRecords.count() == 0) {
                noMessageFound++;
                if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    // If no message found count is reached to threshold exit loop.
                    break;
                else
                    continue;
            }
            DfhcommareaTransformers transformers = new DfhcommareaTransformers();
            //print each record.
            consumerRecords.forEach(record -> {
                try {
                    Dfhcommarea dfhcommarea = transformers.toJava(record.value());
                    System.out.println(dfhcommarea.toString());
                } catch (HostTransformException e) {
                    e.printStackTrace();
                }
            });
            // commits the offset of record to broker.
            consumer.commitAsync();
        }
        consumer.close();
    }
}
