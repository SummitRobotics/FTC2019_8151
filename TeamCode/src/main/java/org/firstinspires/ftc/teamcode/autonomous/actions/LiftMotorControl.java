package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftMotorControl extends CoreAction {

    private double speed;
    private int ticks;
    private int target;
    Telemetry telemetry;

    public LiftMotorControl(double rotations, double speed, int nextPos) {
        this.speed = speed;
        this.nextPos = nextPos;

    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        target = robot.liftMotor.getCurrentPosition() + ticks;

        robot.liftMotor.setTargetPosition(target);

        // Turn On RUN_TO_POSITION
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Finished", false);
        telemetry.update();
    }

    @Override
    public int run() {
        // Set motor power until finished
        if (robot.liftMotor.isBusy()) {
            if((!robot.liftButton.getState()) && (robot.liftMotor.getPower() < 0)){
                telemetry.addData("Finished",true);
                telemetry.update();
                robot.liftMotor.setPower(0);
                return nextPos;
            }
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
