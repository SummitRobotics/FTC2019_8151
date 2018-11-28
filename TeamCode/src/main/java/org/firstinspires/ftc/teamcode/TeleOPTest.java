
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SHS Robotics - Anne on 10/10/2018.
 */

@TeleOp(name="TeleOPTest", group="Linear Opmode")
public class TeleOPTest extends LinearOpMode {

    DigitalChannel digitalTouch;
    double power;
    final double POWER_MULTIPLIER = 0.25;
    ElapsedTime runtime = new ElapsedTime();
    //This creates a new hardware map "Robot", which creates all our hardware objects (DcMotors, etc.)
    DcMotor rightDrive;
    DcMotor leftDrive;
    DcMotor liftArm;
    DcMotor intake;
    DcMotor takerHead1;
    DcMotor takerHead2;

    double n_one = 0;
    double n_two = 0;

    //Example of how to use a Hardwaremap
    //This line runs the "Init" method in the "Robot" class, setting up all of our motors.
    public void runOpMode() {

        digitalTouch =hardwareMap.get(DigitalChannel.class, "sensor_digital");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        intake = hardwareMap.get(DcMotor.class, "uptake");

        /* "Taker Heads" bot and top Servo */
        takerHead1 = hardwareMap.get(DcMotor.class, "top_head_drive");
        takerHead2 = hardwareMap.get(DcMotor.class, "bottom_head_drive");

        /* These need to be flipped possible */
        takerHead1.setDirection(DcMotor.Direction.REVERSE);
        takerHead2.setDirection(DcMotor.Direction.FORWARD);

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        liftArm = hardwareMap.get(DcMotor.class, "lift");

        liftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        takerHead1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        takerHead2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        liftArm.setDirection(DcMotor.Direction.FORWARD);
        intake = hardwareMap.get(DcMotor.class, "uptake");

        intake.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;
            double liftPower;
            double intakePower;


            double drive = gamepad1.right_trigger;
            // Controller Right Joystick
            double turn = gamepad1.right_stick_x;
            double takerHead = gamepad1.right_stick_y;
            double takerPower = 0.0;

            // Controller Left Joystick
            double lift = gamepad1.left_stick_y;
            double goBack = gamepad1.left_trigger;

            boolean intakeButton = gamepad1.dpad_up;
            boolean quarterSpeed = gamepad1.right_stick_button;
            boolean outakeButton = gamepad1.dpad_down;


            leftPower = Range.clip((drive-goBack) + turn, -1.0, 1.0);
            rightPower = Range.clip((drive-goBack) - turn, -1.0, 1.0);
            liftPower = Range.clip(lift, -1.0, 1.0);

            takerPower = Range.clip(takerHead, -1.0, 1.0);

            takerHead1.setPower(takerPower);
            takerHead2.setPower(takerPower);

            if (quarterSpeed){
                leftPower *= POWER_MULTIPLIER;
                rightPower *= POWER_MULTIPLIER;
            }
            // lift power negative when going up positive going down.
            // digital touch state is `true` when not pressed - `false` when pressed.
            if (!digitalTouch.getState() && liftPower > 0.0) {
                liftPower = 0;
            }

            //intake();
            //outake();

            if(intakeButton){
                intake.setPower(1);
            }
            else if (outakeButton){
                intake.setPower(-1);
            }
            else{
                intake.setPower(0);
            }

            //Set power to the motors defined in the Robot class. actually, there is no robot class.
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            liftArm.setPower(liftPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left(%.2f),right(%.2f)", leftPower, rightPower, liftPower);
            telemetry.addData( "Button", "Button: " + digitalTouch.getState());
            telemetry.addData( "Taker", "Taker Position: (%.2f), Taker Power (%.2f) ", takerHead, takerPower);
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
}
