package com.hsb.adam.common;

import lombok.Getter;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:55
 */
@Getter
public class CarParkException extends RuntimeException {

    private static final long serialVersionUID = 1173708142635041616L;

    private String error;
    private String message;

    public CarParkException(String error,String message) {
        this.error = error;
        this.message = message;
    }

}
