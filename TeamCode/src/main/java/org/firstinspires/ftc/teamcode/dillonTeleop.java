package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;

public class dillonTeleop extends LinearOpMode {
    hardwareMap robot = new hardwareMap();
    DigitalChannel digitalTouch;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.runtime.reset();


        while(opModeIsActive()){
            double leftPower, rightPower, liftPower;


            leftPower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -1, 1);
            rightPower = Range.clip(gamepad1.left_stick_y - gamepad1.left_stick_x, -1, 1);
            liftPower = Range.clip(gamepad1.right_trigger - gamepad1.left_trigger, -1, 1);

            if (!digitalTouch.getState() && liftPower > 0.0) {
                liftPower = 0;
            }

            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);
            robot.rightDrive.setPower(liftPower);
        }
    }
}
