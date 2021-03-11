package com.epam.tsk.sixth;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cashiers {
    private static final AtomicReference<Cashiers> instance = new AtomicReference<>();
    private final static int CASHIER_NUMBER = 5;
    private List<Cashier> cashiers = new ArrayList<>();
    private final static Lock LOCK = new ReentrantLock();
    private PriorityQueue<Caller> queue = new PriorityQueue<>(Comparator.comparing(Caller::isNotPreOrder));

    public void process(Caller caller) throws InterruptedException {
        initializeCashiers();
        queue.add(caller);
        Semaphore semaphore = new Semaphore(CASHIER_NUMBER);
        semaphore.acquire();
        LOCK.lock();
        try {
            Random random = new Random();
            int index = random.nextInt(CASHIER_NUMBER);
            Cashier cashier = cashiers.get(index);
            Caller callerToServe = queue.remove();
            cashier.process(callerToServe);
        } finally {
            semaphore.release();
            LOCK.unlock();
        }
    }

    private void initializeCashiers() {
        for (int i = 1; i <= CASHIER_NUMBER; ++i) {
            Cashier cashier = new Cashier(i);
            cashiers.add(cashier);
        }
    }

    public static Cashiers getInstance() {
        Cashiers localInstance = instance.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = instance.get();
                if (localInstance == null) {
                    Cashiers cashiers = new Cashiers();
                    instance.set(cashiers);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance.get();
    }
}
