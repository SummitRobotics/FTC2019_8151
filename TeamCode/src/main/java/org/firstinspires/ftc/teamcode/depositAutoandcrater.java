package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by SHS Robotics - Anne on 10/24/2018.
 */

public class depositAutoandcrater {




    @Autonomous(name="DepositTeamMarker", group = "Linear Opmode")
    public class CraterAuto extends LinearOpMode {

        ElapsedTime runtime = new ElapsedTime();
        protected DcMotor leftDrive;
        protected DcMotor rightDrive;
        protected DcMotor liftArm;
        protected DcMotor intake;
        private final double DRIVE_SPEED = 0.65;
        private final double TURN_SPEED = 0.5;
        private final double INTAKE_OUT = 0.75;

        public void runOpMode() {
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
            leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
            liftArm = hardwareMap.get(DcMotor.class, "lift");
            intake = hardwareMap.get(DcMotor.class, "uptake");

            rightDrive.setDirection(DcMotor.Direction.REVERSE);
            leftDrive.setDirection(DcMotor.Direction.REVERSE);
            liftArm.setDirection(DcMotor.Direction.FORWARD);
            intake.setDirection(DcMotor.Direction.FORWARD);

            waitForStart();
            runtime.reset();

            while (opModeIsActive()) {

                while (runtime.seconds() <= 1.5) {
                    liftArm.setPower(-1);
                }

                while (runtime.seconds() <= 7) {
                    moveFwd();
                }

                while (runtime.seconds() <= 9) {
                    deposit();
                }

                robotStop();

            }

        }

        // moves robot forward for a given amount of time

        public void moveFwd(){
            leftDrive.setPower(DRIVE_SPEED);
            rightDrive.setPower(DRIVE_SPEED);
        }

        //stops the robot

        public void robotStop(){
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            liftArm.setPower(0);
        }

        //turns robot left for given amount of time

        public void turnLeft(){
            leftDrive.setPower(-TURN_SPEED);
            rightDrive.setPower(TURN_SPEED);
        }

        //turns robot right for given amount of time

        public void turnRight(){
            leftDrive.setPower(TURN_SPEED);
            rightDrive.setPower(-TURN_SPEED);
        }

        //moves robot backwards for a given amount of time

        public void goReverse(){
            leftDrive.setPower(-DRIVE_SPEED);
            rightDrive.setPower(-DRIVE_SPEED);
        }

        //makes intake go out for given amount of time

        public void deposit(){

            intake.setPower(-INTAKE_OUT);
        }
    }






    }
