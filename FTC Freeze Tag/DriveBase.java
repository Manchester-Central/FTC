package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DriveBase {

    DcMotor right;
    DcMotor left;
    
    public DriveBase (HardwareMap hardwareMap) {
        
        right = hardwareMap.get(DcMotor.class, "right_drive");
        left = hardwareMap.get(DcMotor.class, "left_drive");
        
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.FORWARD);
        
    }
    
    public void setSpeed (double leftSpeed, double rightSpeed) {
        
        right.setPower(rightSpeed);
        left.setPower (leftSpeed);
        
        
    }
    
}
