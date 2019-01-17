package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class TurnByEncoder extends CoreAction {

    private double leftSpeed, rightSpeed;
    private int leftTicks, rightTicks;
    private int nextPos, leftTarget, rightTarget;

    // Direction variables

    public TurnByEncoder(double radians, double speed, int nextPos) {

        this.leftSpeed = speed;
        this.rightSpeed = -speed;
        this.nextPos = nextPos;
        this.leftTicks = (int) (radians * robot.DRIVE_COUNTS_PER_RADIAN);
        this.rightTicks = (int) (radians * robot.DRIVE_COUNTS_PER_RADIAN);
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        leftTarget = robot.leftDrive.getCurrentPosition() + leftTicks;
        rightTarget = robot.rightDrive.getCurrentPosition() - rightTicks;

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
