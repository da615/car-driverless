package com.hsb.adam.common;

import lombok.Getter;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:20
 */

@Getter
public enum CarError {
    DISTANCE_IS_NOT_ENOUGH("Car_E001", "Distance is not enough"),
    PARAMTERS_CANNOT_NULL("Car_E002", "Object parameters cannot null");

    private String errorCode;
    private String errorMessage;

    CarError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
