package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="CraterAutoTest", group = "Linear Opmode")
public class CraterAuto extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();
    protected DcMotor leftDrive;
    protected DcMotor rightDrive;
    protected DcMotor liftArm;
    private final double DRIVE_SPEED = 0.65;
    private final double TURN_SPEED = 0.5;

    public void runOpMode(){
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        liftArm = hardwareMap.get(DcMotor.class, "lift");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){

            while (runtime.seconds() <= 7){
                moveFwd();
            }
            robotStop();
        }
    }


    public void moveFwd(){
        leftDrive.setPower(DRIVE_SPEED);
        rightDrive.setPower(DRIVE_SPEED);
    }

    public void robotStop(){
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        liftArm.setPower(0);
    }
    public void turnLeft(){
        leftDrive.setPower(-TURN_SPEED);
        rightDrive.setPower(TURN_SPEED);
    }
    public void turnRight(){
        leftDrive.setPower(TURN_SPEED);
        rightDrive.setPower(-TURN_SPEED);
    }

    public void goReverse(){
        leftDrive.setPower(-DRIVE_SPEED);
        rightDrive.setPower(-DRIVE_SPEED);
    }






}
