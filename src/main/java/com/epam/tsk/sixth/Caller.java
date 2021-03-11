package com.epam.tsk.sixth;
import org.apache.log4j.Logger;

public class Caller implements Runnable{
    private final static Logger LOGGER = Logger.getLogger(Caller.class);
    private static final String EXCEPTION_MESSAGE = "Thread exception";
    private int id;
    private boolean preOrder;

    public int getId() {
        return id;
    }

    public boolean isNotPreOrder() {
        return !preOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    @Override
    public void run() {
        Cashiers cashiers = Cashiers.getInstance();
        try {
            cashiers.process(this);
        } catch (InterruptedException e) {
            LOGGER.error(EXCEPTION_MESSAGE, e);
        }
    }
}
