package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by SHS Robotics - Anne on 10/24/2018.
 */

@Autonomous(name="depotauto", group = "Linear Opmode")
public class depotauto extends LinearOpMode {

    //ColorSensor color_sensor;

    ElapsedTime runtime = new ElapsedTime();
    protected DcMotor leftDrive;
    protected DcMotor rightDrive;
    protected DcMotor liftArm;
    protected DcMotor intake;
    protected Servo markerDrop;

    private final double DRIVE_SPEED = 0.5;
    private final double TURN_SPEED = 0.5;
    private final double OUTPUT_SPEED = 1;

    public void runOpMode() {
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        liftArm = hardwareMap.get(DcMotor.class, "lift");

        markerDrop = hardwareMap.get(Servo.class, "outPut");
        //color_sensor = hardwareMap.colorSensor.get("rainbow_reader");


        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.REVERSE);

        markerDrop.setDirection(Servo.Direction.REVERSE);

        markerDrop.setPosition(1);

        int liftposition = liftArm.getCurrentPosition();
        int leftposition = leftDrive.getCurrentPosition();
        int rightposition = rightDrive.getCurrentPosition();
        int intakeposition = intake.getCurrentPosition();
        telemetry.addData("Encoder Position", liftposition);
        telemetry.addData("Encoder Position", intakeposition);
        telemetry.addData("Encoder Position", leftposition);
        telemetry.addData("Encoder Position", rightposition);

        waitForStart();
        runtime.reset();

        //rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //liftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //put instruction to stop recording encoder above this comment(put one for each motor)
        //Color.colorToHSV(colors.toColor(), hsvValues);
        //NormalizedRGBA colors = color_Sensor.getNormalizedColors();
        /*float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red   /= max;
        colors.green /= max;
        colors.blue  /= max;
        color = colors.toColor();*/  //this normalizes the colors and sets the max amount (I don't know either, but this is very important.)
        //this sends the color input to the driver station
        //Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsvValues);

        while(runtime.seconds() <= 8.8){
            liftArm.setPower(1);
        }
        liftArm.setPower(0);

        //code for adding encoder data goes inside the brackets in the "while" loop.
        // Arm runs for 7.8 seconds

        while (runtime.seconds() >= 8.8 && runtime.seconds() <= 9.1) {
            turnRight();
        }

        while (runtime.seconds() >= 9.1 && runtime.seconds() <= 10.1){
            goReverse();
        }


        while (runtime.seconds() >= 10.1 && runtime.seconds() <= 10.8){
            turnLeft();
        }

        while (runtime.seconds() >= 10.8 && runtime.seconds() <= 12.5){
            goReverse();
        }

        while (runtime.seconds() >= 12.5 && runtime.seconds() <= 16){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            outPut(0);
        }



        while (runtime.seconds() >= 16 && runtime.seconds() <= 18.5){
            turnLeft();
            outPut(1);
        }

        while (runtime.seconds() >= 18.5 && runtime.seconds() <= 26){
            goReverse();
        }



        robotStop();
    }


    public void moveFwd() {
        leftDrive.setPower(DRIVE_SPEED);
        rightDrive.setPower(DRIVE_SPEED);
    }

    public void robotStop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        liftArm.setPower(0);
    }

    public void turnLeft() {
        leftDrive.setPower(-TURN_SPEED);
        rightDrive.setPower(TURN_SPEED);
    }

    public void turnRight() {
        leftDrive.setPower(TURN_SPEED);
        rightDrive.setPower(-TURN_SPEED);
    }

    public void goReverse() {
        leftDrive.setPower(-DRIVE_SPEED);
        rightDrive.setPower(-DRIVE_SPEED);
    }

    public void outPut(double amount) {
        markerDrop.setPosition(amount);
    }
}

