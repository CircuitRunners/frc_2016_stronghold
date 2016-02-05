package com.github.circuitrunners;

import java.util.Vector;

/**
 * Useful CalibMath, living creatures
 */
public class CalibMath {
/** Turns a -1 to 1 into 0 to 1 value */
    public static double throttleMath(double input){
        double output = (input+1)/2;
        return output;
    }
/**
 *  Adds deadband, then scales input by a factor linearly.
 *  Produces a straight line
 *  @param input: value to scale, -1 <= x <= 1
 *  @param min: deadband value, anything less will return 0, 0 < x <= 1
 *  @param scale: factor to scale down by, also max value, min < x < 1
 *  @return will max out at input = 1
 */
    public static double scaleLinear(double input, double min, double scale) {
        double output;
        if(Math.abs(input) < min) {
            output = 0;
        }
        else {
            output = scale * input;
        }
        return output;
    }

    /**
     * Adds deadband, then scales by a power, then scales by a constant, then sets a max value
     * Produces a radical-shaped curve
     * @param input: value to scale, -1 <= x <= 1
     * @param min: deadband value, anything less will return 0, 0 < x <= 1
     * @param scale factor to scale by, also the max value, min < x < 1
     * @param power power to scale by, 1 <= x
     * @return will max out at input = scale
     */
    public static double scaleDoubleFlat(double input, double min, double scale, double power){
        double output;
        if(Math.abs(input) < min) {
            output = 0;
        }
        else if(Math.abs(input) > scale){
            output = Math.signum(input) * scale;
        }
        else{
            output = scale * Math.signum(input) * Math.abs(Math.pow(input/scale,power));
        }
        return output;
    }

    /**
     * Adds deadband, then scales by a power, then scales by a constant
     * Produces an upwards concavity curve
     * @param input: value to scale, -1 <= x <= 1
     * @param min: deadband value, anything less will return 0, 0 < x <= 1
     * @param scale factor to scale by, also the max value, min < x < 1
     * @param power power to scale by, 1 <= x
     * @return will max out at input = 1
     */

    public static double scalePower(double input, double min, double scale, double power){
        double output;
        if(Math.abs(input) < min) {
            output = 0;
        }
        else {
            output = scale * Math.signum(input) * Math.abs(Math.pow(input,power));
        }
        return output;
    }

    /**
     * If input is smaller than a min value returns 0
     * @param input: value to deadband
     * @param min: minimum value to return > 0
     * @return
     */
    public static double deadband(double input, double min){
        double output;
        if(Math.abs(input) < min) {
            output = 0;
        }
        else {
            output = input;
        }
        return output;
    }

    /**
     * If input is smaller than a min value returns 0, if input is larger than max value returns max
     * @param input: value to deadband
     * @param min: minimum value to return > 0
     * @param max: maximum value to return
     * @return
     */
    public static double deadband(double input, double min, double max){
        double output;
        if(Math.abs(input) < min) {
            output = 0;
        }
        else if(Math.abs(input) > max ) {
            output = Math.signum(input) * max;
        }
        else {
            output = input;
        }
        return output;
    }

    /**
     * Finds the magnitude of input vector
     * @param input: double array of vector values
     * @return positive number
     */
    public static double magnitude(double[] input){
        double sum = 0;
        for (int i=0; i<input.length; i++){
            sum += input[i] * input[i];
        }
        return Math.sqrt(sum);
    }
}
