package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ObjectHandler {

    DcMotor intake;
    
    public ObjectHandler (HardwareMap hardwareMap) {
        
        intake = hardwareMap.get(DcMotor.class, "intake");
        
    }
    
    public void manualIntake (double speed) {
        intake.setPower(speed);
    }

}
