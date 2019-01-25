package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class MoveByEncoder extends CoreAction {

    private double leftSpeed, rightSpeed;
    private int leftTicks, rightTicks;
    private int leftTarget, rightTarget;
    Telemetry telemetry;

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
        this.telemetry = telemetry;

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
        if (robot.rightDrive.isBusy() && robot.leftDrive.isBusy()) {
            robot.leftDrive.setPower(leftSpeed);
            robot.rightDrive.setPower(rightSpeed);

            /*telemetry.addData("MoveByEncoder To", "Left: (%2f), Right (%2f)",
                    leftTarget, rightTarget);
            telemetry.addData("MoveByEncoder At", "Left: (%2f), Right (%2f)",
                    robot.leftDrive.getCurrentPosition(), robot.rightDrive.getCurrentPosition());
            telemetry.update();*/

            telemetry.addData("Left Pos", robot.leftDrive.getCurrentPosition());
            telemetry.addData("Right Pos", robot.rightDrive.getCurrentPosition());
            telemetry.update();

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
