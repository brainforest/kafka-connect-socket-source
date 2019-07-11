package org.apache.kafka.connect.socket.legstar;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.kafka.common.protocol.types.Field;

public class Test {

    public static void main(String[] args) {

        String hello = "Hello World!";
        HashCode code = Hashing.sha256().hashBytes(hello.getBytes());
        System.out.println("String : " + hello + " HashCode: " + code);
        byte[] asBtye = code.asBytes();
        System.out.println(HashCode.fromBytes(asBtye));

    }
}
