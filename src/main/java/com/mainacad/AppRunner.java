package com.mainacad;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.multithreads.MultithreadWrapper;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class AppRunner {

    private static final Logger LOG = Logger.getLogger(AppRunner.class.getName());
    private static final boolean[] PARKING_PLACES = new boolean[10];
    private static final Semaphore SEMAPHORE = new Semaphore(10, true);

    public static void main(String[] args) throws InterruptedException {



        List<String> connectionIpList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            ConnectionInfo connectionInfo =
                    ConnectionInfoHelper.getRandomConnectionInfo();
            MultithreadWrapper multithreadWrapper =
                    new MultithreadWrapper("therad " + i, connectionInfo, connectionIpList);
            threads.add(multithreadWrapper);

            multithreadWrapper.start();
        }

        while (threadsAlive(threads)) {
            try {
                LOG.info("\n-------------------------\nThreads still alive!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info(connectionIpList.size() + " connections were writed to file" + "\n");

        Map<String, String> users = new HashMap<>();
        users.put("111", "Alex1");
        users.put("112", "Alex2");
        users.put("113", "Alex3");
        users.put("114", "Alex4");

        users.put("111", "Nikita");

        users.put(null, "Gleb");

        for (String key : users.keySet()) {
            String value = users.get(key);
            LOG.info("Key \"" + key + "\" has value \"" + value + "\"");
        }
//        users.keySet().forEach(key -> LOG.info("Key \"" + key + "\" has value \""+ users.get(key) + "\""));
    }

    private static boolean threadsAlive(List<Thread> threads) {
        for (Thread thread : threads) {
            if (thread.isAlive() || thread.getState().equals(Thread.State.NEW)) {
                return true;
            }
        }
        return false;
    }

}

