package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name="2018 auto", group="Autonomous")

public class Auto extends OpMode {

    Lift lift;
    Mechanum drive;
    
    Long startTime;
    int liftEngaged = 0;

    //private SensorManager sensor;

    boolean started;

    public static final float dropDistanceInTicks = 21717f; 
    public static final long forwardTimeInMilliseconds = 150l;
    public static final long leftTimeInMilliseconds = 1000l;

    @Override
    public void init() {
        
        startTime = -1L;
        started = false;
        lift = new Lift (hardwareMap);
        drive = new Mechanum (hardwareMap);
        
        
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
       
       if (liftEngaged == 0) {
           
           lift.setSpeed (1f);
           
           if (lift.getLiftSensor() == false) {
               liftEngaged++;
           }
           
           if (!started) {
               
               startTime = System.currentTimeMillis();
             
               telemetry.addData ("start time", startTime);
               telemetry.addData ("lift sensor", lift.getLiftSensor());
           }
       }
           
           
        if (liftEngaged >= 1) {
           lift.setSpeed (0f);
           
           telemetry.addData ("asdf",  System.currentTimeMillis());
           
           if (startTime + forwardTimeInMilliseconds > System.currentTimeMillis()) {
               
               drive.mechanumDrive (-1f, 0f, 0f, false, false, telemetry);
               
               telemetry.addData ("stop time 1", System.currentTimeMillis());
               
           } else if (startTime + forwardTimeInMilliseconds + leftTimeInMilliseconds > System.currentTimeMillis()) {
               
               drive.mechanumDrive (0f, -1f, 0f, false, false, telemetry);
               
               telemetry.addData ("stop time 2", System.currentTimeMillis());
           } else {
               
               drive.mechanumDrive (0f, 0f, 0f, false, false, telemetry);
               
           }
           
       }

       
       
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        
    }
    
}
