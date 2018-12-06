package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
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
    protected CRServo takerHead1;
    protected CRServo takerHead2;

    private final double DRIVE_SPEED = 0.5;
    private final double TURN_SPEED = 0.5;
    private final double OUTPUT_SPEED = 1;

    public void runOpMode() {
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        liftArm = hardwareMap.get(DcMotor.class, "lift");
        intake = hardwareMap.get(DcMotor.class, "uptake");
        takerHead1 = hardwareMap.get(CRServo.class, "takerHead1");
        takerHead2 = hardwareMap.get(CRServo.class, "takerHead2");
        //color_sensor = hardwareMap.colorSensor.get("rainbow_reader");


        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        takerHead1.setDirection(DcMotor.Direction.REVERSE);
        takerHead2.setDirection(DcMotor.Direction.FORWARD);

        //int position = arm_motor.getCurrentPosition();
        //telemetry.addData("Encoder Position", position);

        waitForStart();
        runtime.reset();

        //arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //put instruction to stop recording encoder above this comment(put one for each motor)

        //color_sensor.red();   //Red channel value
        //color_sensor.green();  //Green channel value
        //color_sensor.blue();  // Blue channel value
        //color_sensor.alpha(); // Total brightness
        //color_sensor.argb();  // Combined color value/shading

        //NormalizedRGBA colors = color_Sensor.getNormalizedColors();
        /*float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red   /= max;
        colors.green /= max;
        colors.blue  /= max;
        color = colors.toColor();*/  //this normalizes the colors and sets the max amount (I don't know either, but this is very important.)
        //this sends the color input to the driver station

        while(runtime.seconds() <= 7.8){
            liftArm.setPower(1);
        }
        liftArm.setPower(0);

        //code for adding encoder data goes inside the brackets in the "while" loop.
        // Arm runs for 7.8 seconds

        while (runtime.seconds() >= 7.8 && runtime.seconds() <= 8.1) {
            turnRight();
        }

        while (runtime.seconds() >= 8.1 && runtime.seconds() <= 9.1){
            goReverse();
        }


        while (runtime.seconds() >= 9.1 && runtime.seconds() <= 9.8){
            turnLeft();
        }

        while (runtime.seconds() >= 9.8 && runtime.seconds() <= 11.5){
            goReverse();
        }

        while (runtime.seconds() >= 11.5 && runtime.seconds() <= 14){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            outPut();
        }

        while (runtime.seconds() >= 14 && runtime.seconds() <= 16.5){
            takerHead1.setPower(0);
            takerHead2.setPower(0);

            turnLeft();
        }

        while (runtime.seconds() >= 16.5 && runtime.seconds() <= 22){
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
    public void outPut() {
        takerHead1.setPower(-OUTPUT_SPEED);
        takerHead2.setPower(-OUTPUT_SPEED);
    }
}

