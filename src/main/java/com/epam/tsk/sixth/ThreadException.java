package com.epam.tsk.sixth;

public class ThreadException extends Exception{
    public ThreadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThreadException(String message) {
        super(message);
    }
}
