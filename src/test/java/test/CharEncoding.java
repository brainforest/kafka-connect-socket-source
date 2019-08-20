package test;

import java.io.UnsupportedEncodingException;

public class CharEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] bytes = new byte[] { 0x5c, 0x22} ;

        System.out.println(new String(bytes,"UTF-16"));

        System.out.println(String.format("CP1026 [Ü] %x", "Ü".getBytes("CP1026")[0]));
        System.out.println(String.format("UTF-8  [Ü] %x", "Ü".getBytes("UTF-8")[0]));
        System.out.println(String.format("UTF-8  [Ü] %x", "Ü".getBytes("UTF-8")[1]));

        System.out.println(String.format("1140   [Ü] %x", "Ü".getBytes("CP1140")[0]));

        System.out.println(String.format("CP857  [Ü] %x", "Ü".getBytes("CP857")[0]));
        System.out.println(String.format("UTF-16 [Ü] %x", "Ü".getBytes("UTF-16")[0]));
        System.out.println(String.format("UTF-16 [Ü] %x", "Ü".getBytes("UTF-16")[1]));

    }
}
