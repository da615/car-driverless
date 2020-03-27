package com.hsb.adam;

import com.hsb.adam.common.CarDirectionEnum;
import com.hsb.adam.common.CarError;
import com.hsb.adam.entity.CarPark;
import com.hsb.adam.entity.CarPosition;
import com.hsb.adam.service.ICar;
import com.hsb.adam.service.impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CarTest {

    private ICar car;

    //init Car data
    @Before
    public void setUp() {
        CarPosition carPosition = new CarPosition(1, 1, CarDirectionEnum.NORTH.getDirection());
        CarPark carPark = new CarPark(4, 4);
        car = new CarServiceImpl(carPosition, carPark);
    }

    @Test
    public void move() {
        //move 1
        car.move("1");
        assertEquals(car.getCarPositionX(), 1);
        assertEquals(car.getCarPositionY(), 2);
        assertEquals(car.getCarOrientation(), CarDirectionEnum.NORTH.getDirection());

        //TURN and 2
        car.move("TURN");
        car.move("2");
        assertEquals(car.getCarPositionX(), 3);
        assertEquals(car.getCarPositionY(), 2);
        assertEquals(car.getCarOrientation(), CarDirectionEnum.EAST.getDirection());

        //TURN and 1
        car.move("TURN");
        car.move("1");
        assertEquals(car.getCarPositionX(), 3);
        assertEquals(car.getCarPositionY(), 1);
        assertEquals(car.getCarOrientation(), CarDirectionEnum.SOUTH.getDirection());

        //  CarParkException Test
        try {
            car.move("5");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), CarError.DISTANCE_IS_NOT_ENOUGH.getErrorMessage());
        }
    }


}
