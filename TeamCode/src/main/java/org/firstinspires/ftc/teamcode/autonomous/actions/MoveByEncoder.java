package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class MoveByEncoder extends CoreAction {

    private double leftSpeed, rightSpeed;
    private double leftTime, rightTime;
    private double time;
    Telemetry telemetry;
    ElapsedTime runtime = new ElapsedTime();

    public MoveByEncoder(double time, double speed, int nextPos) {

        this.leftSpeed = speed;
        this.rightSpeed = speed;
        this.nextPos = nextPos;
        this.leftTime = time;
        this.rightTime = time;
    }

  /*  public MoveByEncoder(double leftDistance, double rightDistance, double speed, int nextPos) {

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
    */

    public MoveByEncoder(double time, double leftSpeed, double rightSpeed, int nextPos) {

        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.nextPos = nextPos;
        this.leftTime = time;
        this.rightTime = time;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;
        robot.init(hardwareMap);
        runtime.reset();

    }

    @Override
    public int run() {
        // Set motor power until finished
        if (runtime.seconds() < leftTime || runtime.seconds() < rightTime) {

            if (runtime.seconds() < leftTime) {
                robot.leftDrive.setPower(leftSpeed);

            } if (runtime.seconds() < rightTime) {
                robot.rightDrive.setPower(rightSpeed);
            }

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
