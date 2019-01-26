package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.main.Hardware;

@TeleOp(name="POVTeleOp", group="Iterative Opmode")
public class POVTeleOp extends OpMode{

    private Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();
    DigitalChannel digitalTouch;

    @Override
    public void init() {
        // Initialize all hardware
        robot.init(hardwareMap);
        digitalTouch =hardwareMap.get(DigitalChannel.class, "liftButton");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;
        double liftPower;

        // Get gamepad inputs
        double drive = gamepad1.right_trigger - gamepad1.left_trigger;
        drive = expPower(drive);
        double turn = gamepad1.right_stick_x;

        // Set power variables for driving
        leftPower = Range.clip((drive + turn), -1.0, 1.0);
        rightPower = Range.clip((drive - turn), -1.0, 1.0);

        liftPower = Range.clip(-gamepad1.left_stick_y, -1, 1);

        //intake things
        if (gamepad1.right_bumper) {
            robot.frontIntakeServo.setPower(0.9);
            robot.backIntakeServo.setPower(0.9);

        } else if (gamepad1.left_bumper) {
            robot.frontIntakeServo.setPower(-0.9);
            robot.backIntakeServo.setPower(-0.9);

        } else {
            robot.frontIntakeServo.setPower(0);
            robot.backIntakeServo.setPower(0);
        }

        if (gamepad1.dpad_up) {
            robot.leftLiftServo.setPower(-0.4);
            robot.rightLiftServo.setPower(0.4);

        } else if (gamepad1.dpad_down) {
            robot.leftLiftServo.setPower(0.3);
            robot.rightLiftServo.setPower(-0.3);

        } else {
            robot.leftLiftServo.setPower(0.05);
            robot.rightLiftServo.setPower(0.05);
        }

        // lift power negative when going up positive going down.
        // digital touch state is `true` when not pressed - `false` when pressed.
        if (!digitalTouch.getState() && liftPower < 0.0) {
            liftPower = 0;
        }


        // Send calculated power to hardware
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);
        robot.liftMotor.setPower(liftPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "Left: (%.2f), Right: (%.2f)", leftPower, rightPower);
        telemetry.addData("Lift", robot.liftMotor.getCurrentPosition() / robot.LIFT_COUNTS_PER_ROT);

    }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }

    private double logPower(double power) {
        if (power >= 0) {
            return 0.96 * Math.log10(power + 0.1) + 0.96;
        } else {
            return -0.96 * Math.log10(-power + 0.1) - 0.96;
        }
    }

    private double expPower(double power) {
        if (power == 0) {
            return 0;
        }
        return (power * power) * (Math.abs(power) / power);
    }

}