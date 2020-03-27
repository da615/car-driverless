package com.hsb.adam;

import com.hsb.adam.common.CarDirectionEnum;
import com.hsb.adam.common.CarError;
import com.hsb.adam.common.CarParkException;
import com.hsb.adam.entity.CarPark;
import com.hsb.adam.entity.CarPosition;
import com.hsb.adam.service.ICar;
import com.hsb.adam.service.impl.CarServiceImpl;

import java.util.Scanner;

/**
 * @author daxing.li
 * @Data 2020/03/27/22:40
 */
public class CarMainApp {

    public static void main(String[] args) {
        System.out.println("pls set the car park height above 0:");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int height = 0;
            while (in.hasNext()) {
                try {
                    height = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Illegal input,must enter integer");
                    continue;
                }
            }

            System.out.println("pls set the car park width above 0");
            int width = 0;
            while (in.hasNext()) {
                try {
                    width = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("illegal input,pls enter integer");
                    continue;
                }
            }
            CarPark carPark = new CarPark(height, width);

            String orientation = setOrientation(in);
            int positionX = setPositionX(in, height);
            int positionY = setPositionY(in, width);

            CarPosition car = new CarPosition(positionX, positionY, orientation);
            System.out.println(car.toString());
            moveCarMethod(in, carPark, car);
        }
    }

    private static void moveCarMethod(Scanner in, CarPark carPark, CarPosition carPosition) {
        if (carPosition == null && carPark == null) {
            throw new CarParkException(CarError.PARAMTERS_CANNOT_NULL.getErrorCode(), CarError.PARAMTERS_CANNOT_NULL.getErrorMessage());
        }
        ICar service = new CarServiceImpl(carPosition, carPark);
        System.out.println("input \"TURN\" or integer");

        while (in.hasNext()) {
            String command = in.next().trim();
            try {
                service.move(command);
            } catch (NumberFormatException e) {
                System.out.println("illegal command,pls input \"TURN\" or integer");
                continue;
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                break;
            }

            System.out.println(carPosition.toString());
            System.out.println("input your command,input \"TURN\" or integer");
        }
    }

    private static int setPositionY(Scanner in, int width) {
        System.out.println("Set init position-Y of car,above 0 and less than parking width:");
        int positionY = 0;
        while (in.hasNext()) {
            try {
                positionY = Integer.parseInt(in.next());
                if (positionY > width || positionY <= 0) {
                    System.out.println("illegal input,must above 0 and less than parking width:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("illegal input,pls enter or integer");
                continue;
            }
        }
        return positionY;
    }

    private static int setPositionX(Scanner in, int height) {
        System.out.println("Set init position-X of car,above 0 and less than parking height:");
        int positionX = 0;
        while (in.hasNext()) {
            try {
                positionX = Integer.parseInt(in.next());
                if (positionX > height || positionX <= 0) {
                    System.out.println("illegal input,pls again,above 0 and less than parking height:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("illegal input,pls enter or integer");
                continue;
            }
        }
        return positionX;
    }

    private static String setOrientation(Scanner in) {
        String orientation = null;
        System.out.println("pls input car of orientation [choice: NORTH,EAST,SOUTH or WEST] :");
        while (in.hasNext()) {
            orientation = in.next().trim().toUpperCase();
            try {
                CarDirectionEnum carDirection = CarDirectionEnum.valueOf(orientation);
                orientation = carDirection.getDirection();
                return orientation;
            } catch (IllegalArgumentException e) {
                System.out.println("illegal input,pls input NORTH,EAST,SOUTH or WEST.");
            }
        }
        return null;
    }

}
