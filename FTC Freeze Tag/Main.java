
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")

public class Main extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DriveBase drive;
    
    DigitalChannel sensor;
    boolean previousState;
    boolean currentState;
    
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        drive = new DriveBase(hardwareMap);
        sensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
        sensor.setMode(DigitalChannel.Mode.INPUT);
        previousState = false;
        currentState = true;
        
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
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
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
       
        telemetry.addData("sensor", sensor.getState());

        if (!sensor.getState() && !previousState)
            currentState = !currentState;
        if (currentState)
            drive.setSpeed ((gamepad1.left_stick_y*0.5), (gamepad1.right_stick_y*-0.5));
        else 
            drive.setSpeed (0, 0);
        previousState = !sensor.getState();

        // Show the elapsed game time and wheel power.
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        
    }

    
    
}
