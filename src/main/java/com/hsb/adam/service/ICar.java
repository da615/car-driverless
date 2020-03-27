package com.hsb.adam.service;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:45
 */
public interface ICar {
    void move(String command);

    int getCarPositionX();

    int getCarPositionY();

    String getCarOrientation();
}
