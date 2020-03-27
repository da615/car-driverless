package com.hsb.adam.common;

import lombok.Getter;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:15
 */

@Getter
public enum CarDirectionEnum {

    NORTH("NORTH"),
    EAST("EAST"),
    SOUTH("SOUTH"),
    WEST("WEST");
    private String direction;

    CarDirectionEnum(String direction) {
        this.direction = direction;
    }

}
