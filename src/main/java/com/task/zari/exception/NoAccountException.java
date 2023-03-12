package com.task.zari.exception;

public class NoAccountException extends Exception { //계정을 찾지 못했을 경우 발생

    public NoAccountException(String msg) {
        super(msg);
    }
}
