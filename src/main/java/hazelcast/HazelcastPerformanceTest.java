package hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelcastPerformanceTest {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hzInstance[] = new HazelcastInstance[3];
        Config config = new Config();
        config.getNetworkConfig().setPortAutoIncrement(true);
        config.getNetworkConfig().setPort(10555);
        config.getMapConfig("default").setBackupCount(2
        );
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getInterfaces().addInterface("127.0.0.1");
        config.getNetworkConfig().getInterfaces().setEnabled(true);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("127.0.0.1");
        config.getManagementCenterConfig().setEnabled(true);
        config.getManagementCenterConfig().setUrl("http://localhost:8080/mancenter");

        for (int i = 0; i < 3; i++) {
            config.setInstanceName("HazelcastService" + i);
            hzInstance[i] = Hazelcast.newHazelcastInstance(config);
            System.out.println("Creating instance " + (i + 1));
        }

        System.out.println("Creating Map...");
        IMap<String, String> myMap = hzInstance[0].getMap("MyMap");
        int threadNum = 50;
        int requestNum = 1000;
        System.out.println("Executing multi-threaded insert operations");

        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < requestNum; i1++) {
                    final String key = Thread.currentThread().getName() + (long) (Math.random() * 1000000);
                    Runnable worker = () -> myMap.set(key, "this is the sample value");
                    worker.run();
                }
            });

            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("Inserted Keys : " + myMap.keySet().stream().count());
        long timeCost = (System.currentTimeMillis() - startTime);
        String msg = String.format("call total-time-cost=%sms ,req/s=%s", timeCost, ((double) (requestNum *
                threadNum) / timeCost * 1000));
        System.out.println(msg);
    }
}


