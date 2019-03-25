package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Mechanum {

    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor leftFront;
    DcMotor leftBack;
    
    public Mechanum(HardwareMap hardwareMap) {
        rightFront = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBack = hardwareMap.get(DcMotor.class, "right_back_drive");
        leftFront = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBack = hardwareMap.get(DcMotor.class, "left_back_drive");
        
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
    }
    
    //public double 
    
    public void setSpeed (double leftFacing, double rightFacing) {
        
        rightFront.setPower(rightFacing);
        leftBack.setPower(rightFacing);
        
        leftFront.setPower (leftFacing);
        rightBack.setPower (leftFacing);
        
    }
    
    public void rotate (double rotate) {
        
        rightFront.setPower(-rotate);
        leftBack.setPower(rotate);
        leftFront.setPower (rotate);
        rightBack.setPower (-rotate);
        
    }
    
    static double clamp (double value, double max, double min) {
        return Math.max(min, Math.min (max, value));
    }
    
    static double reduceAngle (double angleInDegrees) {

        if (angleInDegrees > 360)
            return (reduceAngle(angleInDegrees - 360));
        else if (angleInDegrees < 0)
            return (reduceAngle(angleInDegrees + 360));
        else
            return angleInDegrees;

    }
    
    public void mechanumDrive (double joystickY, double joystickX, double joystickRotate, boolean rotateCheck, boolean leftGoesLeft, Telemetry telemetry) {
        
        /*if (rotateCheck) {

            rotate (joystickRotate);

        } else {*/
            

            double leftBase = 0;
            double rightBase = 0;
            double magnitude =  Math.sqrt (Math.pow (joystickX, 2) + Math.pow (joystickY, 2));
            
            double biggerNumber =  (Math.abs(joystickY) > Math.abs(joystickX)) ? Math.abs(joystickY) : Math.abs(joystickX);
            
            double largestMagnitude =  Math.sqrt (Math.pow (joystickX / biggerNumber, 2) + Math.pow (joystickY / biggerNumber, 2));

            double proportionTo45 =  (Math.toDegrees(Math.atan2 (Math.abs(joystickY), Math.abs(joystickX))) - 45.0) / 45.0;

            boolean up = joystickY > 0;

            boolean directionCheck = (leftGoesLeft) ? (joystickY > 0 && joystickX > 0) || (joystickY < 0 && joystickX < 0) :
                                (joystickY > 0 && joystickX < 0) || (joystickY < 0 && joystickX > 0);

            if (joystickY == 0) {
                
                if (joystickX == 0) {
                    
                    rotate (joystickRotate);
                    
                    return;
                    
                }
                
                boolean check = (leftGoesLeft) ? joystickX > 0 : joystickX < 0;
                
                if (check) {
                    rightBase = 1.0;
                    leftBase = -1.0;
                } else {
                    rightBase = -1.0;
                    leftBase = 1.0;
                }
                    
                
            } else if (directionCheck) {
                
                rightBase = (up) ? 1.0 : -1.0;

                leftBase = (up) ? proportionTo45 : -proportionTo45;

            } else {

                leftBase = (up) ? 1.0 : -1.0;
            
                rightBase = (up) ? proportionTo45 : -proportionTo45;
        
            }
            
            telemetry.addData("right base", rightBase);
            telemetry.addData("left base", leftBase);
            
            rightFront.setPower(clamp((rightBase * magnitude / largestMagnitude) - joystickRotate, 1D, -1D));
            leftBack.setPower(clamp((rightBase * magnitude / largestMagnitude) + joystickRotate, 1D, -1D));
        
            leftFront.setPower (clamp((leftBase * magnitude / largestMagnitude) + joystickRotate, 1D, -1D));
            rightBack.setPower (clamp((leftBase * magnitude / largestMagnitude) - joystickRotate, 1D, -1D));

        //}
        
    }

    // angleEN - Angle East of North

    public void mechanumDriveWithAngle (double joystickY, double joystickX, double joystickRotate, boolean rotateCheck, boolean leftGoesLeft, double angleEN, Telemetry telemetry) {

        // joystick angle North of East
        double joystickAngle = reduceAngle(Math.toDegrees(Math.atan2(joystickY, joystickX)));
        double angleNE = reduceAngle(90 - angleEN);
        double resultantAngle = reduceAngle(angleNE - joystickAngle);

        mechanumDrive (Math.sin (Math.toRadians(resultantAngle)), Math.cos(Math.toRadians(resultantAngle)), joystickRotate, rotateCheck, leftGoesLeft, telemetry);         

    }



}
