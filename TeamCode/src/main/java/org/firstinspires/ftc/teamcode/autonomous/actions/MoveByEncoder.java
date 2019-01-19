package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class MoveByEncoder extends CoreAction {

    private double leftSpeed, rightSpeed;
    private int leftTicks, rightTicks;
    private int leftTarget, rightTarget;

    public MoveByEncoder(double distance, double speed, int nextPos) {

        this.leftSpeed = speed;
        this.rightSpeed = speed;
        this.nextPos = nextPos;
        this.leftTicks = (int) (distance * robot.DRIVE_COUNTS_PER_INCH);
        this.rightTicks = (int) (distance * robot.DRIVE_COUNTS_PER_INCH);
    }

    public MoveByEncoder(double leftDistance, double rightDistance, double speed, int nextPos) {

        if (leftDistance > rightDistance) {
            this.leftSpeed = speed;
            this.rightSpeed = speed * (rightDistance / leftDistance);
        } else {
            this.rightSpeed = speed;
            this.leftSpeed = speed * (leftDistance / rightDistance);
        }

        this.nextPos = nextPos;
        this.leftTicks = (int) (leftDistance * robot.DRIVE_COUNTS_PER_INCH);
        this.rightTicks = (int) (rightDistance * robot.DRIVE_COUNTS_PER_INCH);
    }

    public MoveByEncoder(double leftDistance, double rightDistance, double leftSpeed, double rightSpeed, int nextPos) {

        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.nextPos = nextPos;
        this.leftTicks = (int) (leftDistance * robot.DRIVE_COUNTS_PER_INCH);
        this.rightTicks = (int) (rightDistance * robot.DRIVE_COUNTS_PER_INCH);
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        leftTarget = robot.leftDrive.getCurrentPosition() + leftTicks;
        rightTarget = robot.rightDrive.getCurrentPosition() + rightTicks;

        robot.leftDrive.setTargetPosition(leftTarget);
        robot.rightDrive.setTargetPosition(rightTarget);

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public int run() {
        // Set motor power until finished
        if (robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {
            robot.leftDrive.setPower(leftSpeed);
            robot.rightDrive.setPower(rightSpeed);
            return 0;

        } else if (robot.leftDrive.isBusy()) {
            robot.leftDrive.setPower(leftSpeed);
            return 0;

        } else if (robot.rightDrive.isBusy()) {
            robot.rightDrive.setPower(rightSpeed);
            return 0;
        }

        return nextPos;
    }

    @Override
    public void actionEnd() {

        // Set power to 0
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
