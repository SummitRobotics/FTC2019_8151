
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by SHS Robotics - Anne on 10/10/2018.
 */

@TeleOp(name="TeleOPTest", group="Linear Opmode")
public class TeleOPTest extends LinearOpMode {

    double power;
    final double POWER_MULTIPLIER = 0.25;
    ElapsedTime runtime = new ElapsedTime();
    //This creates a new hardware map "Robot", which creates all our hardware objects (DcMotors, etc.)
    Robot Robot = new Robot();

    public void runOpMode() {

        //Example of how to use a Hardwaremap
        //This line runs the "Init" method in the "Robot" class, setting up all of our motors.
        Robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;
            double liftPower;


            double drive = gamepad1.right_trigger;
            double turn = gamepad1.right_stick_x;
            double lift = gamepad1.left_stick_y;
            double goBack = gamepad1.left_trigger;
            boolean quarterSpeed = gamepad1.right_stick_button;


            leftPower = Range.clip((drive-goBack) + turn, -1.0, 1.0);
            rightPower = Range.clip((drive-goBack) - turn, -1.0, 1.0);
            liftPower = Range.clip(lift, -1.0, 1.0);

            if (quarterSpeed){
                leftPower *= POWER_MULTIPLIER;
                rightPower *= POWER_MULTIPLIER;
                liftPower *= POWER_MULTIPLIER;
            }

            //Set power to the motors defined in the Robot class
            Robot.leftDrive.setPower(leftPower);
            Robot.rightDrive.setPower(rightPower);
            Robot.liftArm.setPower(liftPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left(%.2f),right(%.2f)", leftPower, rightPower, liftPower);
            telemetry.update();

        }
    }
}