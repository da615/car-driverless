package com.hsb.adam.entity;

import com.hsb.adam.common.CarDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daxing.li
 * @Data 2020/03/27/21:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPosition {

    private int positionX;

    private int positionY;

    private String orientation;

    public void clockWiseTurn(){
        switch (CarDirectionEnum.valueOf(getOrientation())) {
            case NORTH:
                orientation = CarDirectionEnum.EAST.getDirection();
                break;
            case EAST:
                orientation = CarDirectionEnum.SOUTH.getDirection();
                break;
            case SOUTH:
                orientation = CarDirectionEnum.WEST.getDirection();
                break;
            case WEST:
                orientation = CarDirectionEnum.NORTH.getDirection();
                break;
        }
    }

}
