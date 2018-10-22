
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by SHS Robotics - Anne on 10/10/2018.
 */

@TeleOp(name="FirstAutonomous", group="Linear Opmode")
public class FirstAutonomous extends LinearOpMode {

    double power;
    ElapsedTime runtime = new ElapsedTime();
    DcMotor rightDrive;
    DcMotor leftDrive;
    DcMotor liftArm;

    public void runOpMode() {


        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        liftArm = hardwareMap.get(DcMotor.class, "lift");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            while (runtime.seconds() < 2.5) {

                leftDrive.setPower(.5);
                rightDrive.setPower(.5);
            }

            while (runtime.seconds() > 2.5 && runtime.seconds() < 5) {

                leftDrive.setPower(.5);
                rightDrive.setPower(-.5);
            }

            while (runtime.seconds()> 2.5 && runtime.seconds() < 5 && runtime.seconds() < 8) {

                leftDrive.setPower(.5);
                rightDrive.setPower(.5);
            }

            while (runtime.seconds()> 2.5 && runtime.seconds() <5 && runtime.seconds() <8 && runtime.seconds() < 1.25)
        }
    }
}