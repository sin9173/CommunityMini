package com.task.zari.exception;

public class NoDataException extends Exception { //데이터를 찾지 못했을 경우 발생

    public NoDataException(String msg) {
        super(msg);
    }
}
