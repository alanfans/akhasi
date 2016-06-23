package com.luosl.akhasi.domain.base;

/**
 * Created by Administrator on 2016/5/23.
 */
public class EntryExeption extends RuntimeException{
    public EntryExeption() {
        super();
    }

    public EntryExeption(String message) {
        super(message);
    }

    public EntryExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public EntryExeption(Throwable cause) {
        super(cause);
    }

    protected EntryExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
