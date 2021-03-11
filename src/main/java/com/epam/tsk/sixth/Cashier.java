package com.epam.tsk.sixth;
import org.apache.log4j.Logger;

public class Cashier {
    private final static Logger LOGGER = Logger.getLogger(Cashier.class);
    private final static String INFO_MESSAGE = "Visitor served! Visitor id: ";
    private final int number;

    public Cashier(int number) {
        this.number = number;
    }

    public void process(Caller caller){
        System.out.println(INFO_MESSAGE + caller.getId());
        LOGGER.info(INFO_MESSAGE + caller.getId());
    }
}
