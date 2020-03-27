package com.hsb.adam.service.impl;

import com.hsb.adam.common.CarDirectionEnum;
import com.hsb.adam.common.CarError;
import com.hsb.adam.common.CarParkException;
import com.hsb.adam.entity.CarPark;
import com.hsb.adam.entity.CarPosition;
import com.hsb.adam.service.ICar;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:59
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CarServiceImpl implements ICar {
    private static final String TURN = "TURN";

    private CarPosition carPosition;

    private CarPark carPark;

    @Override
    public void move(String command) {
        if (TURN.equals(command.toUpperCase())) {
            carPosition.clockWiseTurn();
        } else {
            int distance = Integer.parseInt(command);
            switch (CarDirectionEnum.valueOf(getOrientation())) {
                case NORTH:
                    carPosition.setPositionY(getCarPositionY() + distance);
                    break;
                case EAST:
                    carPosition.setPositionX(getCarPositionX() + distance);
                    break;
                case WEST:
                    carPosition.setPositionX(getCarPositionX() - distance);
                    break;
                case SOUTH:
                    carPosition.setPositionY(getCarPositionY() - distance);
                    break;
            }
            if (outOfLines()) {
                throw new CarParkException(CarError.DISTANCE_IS_NOT_ENOUGH.getErrorCode(),CarError.DISTANCE_IS_NOT_ENOUGH.getErrorMessage());
            }
        }
    }

    private String getOrientation() {
        return carPosition.getOrientation();
    }

    private boolean outOfLines() {

        boolean flagX = carPark.getWidth() < getCarPositionX() || getCarPositionX() < 0;
        boolean flagY = carPark.getHeight() < getCarPositionY() || getCarPositionY() < 0;
        if ( flagX && flagY) {
            log.error("The car X and Y is out");
            return true;
        }
        if (flagX) {
            log.error("The car X is out");
            return true;
        }
        if (flagY) {
            log.error("The car Y is out");
            return true;
        }
        return false;
    }

    @Override
    public int getCarPositionX() {
        return carPosition.getPositionX();
    }

    @Override
    public int getCarPositionY() {
        return carPosition.getPositionY();
    }

    @Override
    public String getCarOrientation() {
        return carPosition.getOrientation();
    }
}
