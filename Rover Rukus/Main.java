package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="2018 teleop", group="Iterative Opmode")

public class Main extends OpMode
{
    // Declare OpMode members.
    //private ElapsedTime runtime = new ElapsedTime();
    //DriveBase drive;
    Mechanum drive;
    Lift lift;
    ObjectHandler objectHandler;
    
    double dpadModifier;
  
    
    //DigitalChannel sensor;
    //boolean previousState;
    //boolean currentState;
    
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        objectHandler = new ObjectHandler (hardwareMap);
        telemetry.addData("Status", "Initialized");
        //drive = new DriveBase(hardwareMap);
        //liftSensor = hardwareMap.get(DigitalChannel.class, "lift_limit");
        //sensor.setMode(DigitalChannel.Mode.INPUT);
        //previousState = false;
        //currentState = true;
        dpadModifier = 0.5;
        drive = new Mechanum(hardwareMap);
        lift = new Lift(hardwareMap);
   
        
        // Tell the driver that initialization is complete.
        //telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
       // runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
       telemetry.addData ("encoder ticks", lift.getEncoderPosition());
       lift.setSpeed(gamepad2.right_stick_y);
       if (gamepad2.left_bumper) {
           objectHandler.manualIntake(1D);
       } else if (gamepad2.right_bumper) {
           objectHandler.manualIntake(-1D);
       } else {
        objectHandler.manualIntake(gamepad2.left_stick_y);
       }
        //telemetry.addData("sensor", sensor.getState());
        if (gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_up || gamepad1.dpad_right) {
            
            double horizontal = (gamepad1.dpad_left) ? -dpadModifier : dpadModifier;
            horizontal = (gamepad1.dpad_right || gamepad1.dpad_left) ? horizontal : 0D;
            
            double vertical = (gamepad1.dpad_up) ? -dpadModifier : dpadModifier;
            vertical = (gamepad1.dpad_down || gamepad1.dpad_up) ? vertical : 0D;
            
            drive.mechanumDrive (vertical, horizontal, -gamepad1.left_stick_x, gamepad1.right_trigger > 0, false, telemetry);
        } else 
            drive.mechanumDrive (gamepad1.right_stick_y, gamepad1.right_stick_x, -gamepad1.left_stick_x, gamepad1.right_trigger > 0, false, telemetry);
        
        telemetry.addData("Lift Position in Ticks", lift.getEncoderPosition());
        
        /*if (!sensor.getState() && !previousState)
            currentState = !currentState;
        if (currentState)
            drive.setSpeed ((gamepad1.left_stick_y*0.3), (gamepad1.right_stick_y*0.3));
        else 
            drive.setSpeed (0, 0);
        previousState = !sensor.getState();
        */

        // Show the elapsed game time and wheel power.
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        
    }

    
    
}
