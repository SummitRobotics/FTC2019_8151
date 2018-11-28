package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by SHS Robotics - Anne on 10/24/2018.
 */

@Autonomous(name="depotauto", group = "Linear Opmode")
public class depotauto extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();
    protected DcMotor leftDrive;
    protected DcMotor rightDrive;
    protected DcMotor liftArm;
    protected DcMotor intake;

    private final double DRIVE_SPEED = 0.5;
    private final double TURN_SPEED = 0.5;

    public void runOpMode() {
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        liftArm = hardwareMap.get(DcMotor.class, "lift");
        intake = hardwareMap.get(DcMotor.class, "uptake");


        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while(runtime.seconds() <= 6.8){
            liftArm.setPower(1);
        }
        liftArm.setPower(0);


        // Arm runs for 5.8 seconds

        while (runtime.seconds() >= 6.8 && runtime.seconds() <= 7.4) {
            turnRight();
        }

        while (runtime.seconds() >= 7.4 && runtime.seconds() <= 8.9){
            moveFwd();
        }
        /*
        while (runtime.seconds() >= 8.9 && runtime.seconds() <= 9.6){
            turnLeft();
        }

        while (runtime.seconds() >= 9.6 && runtime.seconds() <= 11.1){
            moveFwd();
        }

        while (runtime.seconds() >= 11.1 && runtime.seconds() <= 14) {
            intake.setPower(1);
        }
        intake.setPower(0);

      while (runtime.seconds() >= 14 && runtime.seconds() <= 15){
          turnLeft();
      }

      while (runtime.seconds() >= 15 && runtime.seconds() <= 5){
          goReverse();
      }

        */
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
}

