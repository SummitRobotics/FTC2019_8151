
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by SHS Robotics - Anne on 10/10/2018.
 */

@TeleOp(name="TeleOPTest", group="Linear Opmode")
public class TeleOPTest extends LinearOpMode {

    DigitalChannel digitalTouch;
    double power;
    final double QUARTER_POWER = 0.25;
    double FULL_POWER = 1;
    boolean powerThrottled = false;
    //This creates a new hardware map "Robot", which creates all our hardware objects (DcMotors, etc.)
    hardwareMap robot = new hardwareMap();

    double n_one = 0;

    boolean toggleSpeed = false;

    //Example of how to use a Hardwaremap
    //This line runs the "Init" method in the "Robot" class, setting up all of our motors.
    public void runOpMode() {
        
        robot.init(hardwareMap);

        digitalTouch =hardwareMap.get(DigitalChannel.class, "sensor_digital");

        /* "Taker Heads" bot and top Servo */

        /* These need to be flipped possible */


        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        //robot.liftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        robot.runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;
            double liftPower;
            //double intakePower;


            double drive = gamepad1.right_trigger;
            // Controller Right Joystick
            double turn = gamepad1.right_stick_x;
            //double takerPower = isTakerPower();
            //double takerTilt = gamepad1.right_stick_y;

            // Controller Left Joystick
            double lift = gamepad1.left_stick_y;
            double goBack = gamepad1.left_trigger;

            //boolean intakeButton = gamepad1.dpad_up;
            boolean quarterSpeed = gamepad1.x;
            //boolean outakeButton = gamepad1.dpad_down;

            leftPower = Range.clip((drive-goBack) + turn, -1.0, 1.0);
            rightPower = Range.clip((drive-goBack) - turn, -1.0, 1.0);
            liftPower = Range.clip(-lift, -1.0, 1.0);

            if (quarterSpeed && !toggleSpeed) {

                toggleSpeed = true;
                powerThrottled = !powerThrottled;
                
            } else if (!quarterSpeed) {

                toggleSpeed = false;
            }


            // lift power negative when going up positive going down.
            // digital touch state is `true` when not pressed - `false` when pressed.
            if (!digitalTouch.getState() && liftPower > 0.0) {
                liftPower = 0;
            }

            //double tiltPower = takerTilt;
            //if (tiltPower == 0) {
            //    tiltPower = 0.15;
            //}

            if (powerThrottled){
                leftPower *= QUARTER_POWER;
                rightPower *= QUARTER_POWER;
                liftPower *= QUARTER_POWER;
                //tiltPower *= QUARTER_POWER;
            }

            //Set power to the motors defined in the Robot class. actually, there is no robot class.
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);
            robot.liftArm.setPower(liftPower);



            telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
            telemetry.addData("Motors", "left" ,"right", "lift", leftPower, rightPower, liftPower);
            telemetry.addData( "Button", "Button: " + digitalTouch.getState());
            //telemetry.addData( "Intake Power", takerPower);
            telemetry.update();

        }
    }
   /* public void intake(){
        boolean intakeButton = gamepad1.dpad_up;

        if (intakeButton){
            n_one = 1-n_one;
        }

        if(n_one == 1){
            intake.setPower(1);
        }
        else{
            intake.setPower(0);
        }
    }
    public void outake(){
        boolean outakeButton = gamepad1.dpad_down;

        if (outakeButton){
            n_two = 1-n_two;
        }

        if (n_two == 1){
            intake.setPower(-1);
        }
        else{
            intake.setPower(0);
        }
    }*/

    public double isTakerPower(){

        if(gamepad1.dpad_up){
            return 1;
        }
        else if (gamepad1.dpad_down){
            return -1;
        }
        else{
            return 0;
        }
    }
    public double holdArm(double value){
        if (value == 0){
            return 0.15;
        }
        else{
            return value;
        }
    }
}
