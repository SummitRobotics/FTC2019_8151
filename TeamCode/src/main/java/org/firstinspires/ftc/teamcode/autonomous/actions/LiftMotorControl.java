package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftMotorControl extends CoreAction {

    private double speed;
    private int ticks;
    private int target;

    public LiftMotorControl(double rotations, double speed, int nextPos) {
        this.speed = speed;
        this.nextPos = nextPos;
        this.ticks = (int) (rotations * robot.LIFT_COUNTS_PER_ROT);
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {
        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        target = robot.liftMotor.getCurrentPosition() + ticks;

        robot.liftMotor.setTargetPosition(target);

        // Turn On RUN_TO_POSITION
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public int run() {
        // Set motor power until finished
        if (robot.liftMotor.isBusy()) {
            robot.liftMotor.setPower(speed);
            return 0;
        }
        return nextPos;
    }

    @Override
    public void actionEnd() {
        robot.liftMotor.setPower(0);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
