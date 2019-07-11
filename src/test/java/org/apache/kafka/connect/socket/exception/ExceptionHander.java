package org.apache.kafka.connect.socket.exception;

public class ExceptionHander  implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            // Write the custom logic here
            System.out.println(e.getMessage());
            System.exit(-1);
        }
}
