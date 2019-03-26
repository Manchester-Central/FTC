package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Lift {

    
    
    DcMotor left;
    DcMotor right;
    DigitalChannel liftSensor;
    
    public Lift(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, "left_lift");
        left.setDirection(DcMotor.Direction.REVERSE);
        right = hardwareMap.get(DcMotor.class, "right_lift");
    
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        liftSensor = hardwareMap.get(DigitalChannel.class, "lift_limit");

    }
    
    public int getEncoderPosition() {
        
        return left.getCurrentPosition();
        
    }
    
    public boolean getLiftSensor() {
        return liftSensor.getState();
    }
    
    
    public void setSpeed(double speed) {
        left.setPower(speed);
        right.setPower(speed);
    }
}
