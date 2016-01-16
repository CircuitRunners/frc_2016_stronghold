
package com.github.circuitrunners;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    RobotDrive drive;

    Joystick joystick;

    AnalogGyro gyro;

    @Override
    public void robotInit() {

        drive = new RobotDrive(0, 3, 4, 5);

        joystick = new Joystick(0);

        gyro = new AnalogGyro(0);
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
    }

    private boolean isGyroControlEnabled;
    @Override
    public void teleopPeriodic() {
        double moveVal = joystick.getY();
        double rotateVal = joystick.getTwist();

        double gyroVal = gyro.getAngle();
        if (joystick.getRawButton(1)) gyro.reset();

        if (joystick.getRawButton(2)) isGyroControlEnabled = !isGyroControlEnabled;
        if (isGyroControlEnabled){
            if (gyroVal > 3){
                rotateVal = -0.15;
            } else if (gyroVal < 177) {
                rotateVal = 0.15;
            }
        }

        drive.arcadeDrive(moveVal, rotateVal);

        SmartDashboard.putNumber("moveVal", moveVal);
        SmartDashboard.putNumber("rotateVal", rotateVal);
        SmartDashboard.putNumber("gyroVal", gyroVal);

        SmartDashboard.putData("test", gyro);
    }

    @Override
    public void testPeriodic() {

    }

}
