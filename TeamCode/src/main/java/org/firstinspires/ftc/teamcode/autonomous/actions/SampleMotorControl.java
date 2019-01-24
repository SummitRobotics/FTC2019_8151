package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SampleMotorControl extends CoreAction{

    private double speed;
    private int ticks;
    private int target;
    Telemetry telemetry;

    public SampleMotorControl(double radians, double speed, int nextPos) {

        this.speed = speed;
        this.nextPos = nextPos;
        this.ticks = (int) (radians * robot.SAMPLE_COUNTS_PER_RADIAN);
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);
        this.telemetry = telemetry;

        // Prepare motors for encoder movement
        target = robot.sampleMotor.getCurrentPosition() + ticks;

        if ((speed > 0 && ticks > robot.sampleMotor.getCurrentPosition()) ||
                (speed < 0 && ticks < robot.sampleMotor.getCurrentPosition())) {
            speed *= -1;
        }
    }

    @Override
    public int run() {
        // Set motor power until finished
        if (speed > 0) {
            if (robot.sampleMotor.getCurrentPosition() >= target) {
                robot.sampleMotor.setPower(speed);

                telemetry.addData("Sample Motor To", target);
                telemetry.addData("Sample Motor At", robot.sampleMotor.getCurrentPosition());
                telemetry.update();
                return 0;
            }
        } else {
            if (robot.sampleMotor.getCurrentPosition() <= target) {
                robot.sampleMotor.setPower(speed);

                telemetry.addData("Sample Motor To", target);
                telemetry.addData("Sample Motor At", robot.sampleMotor.getCurrentPosition());
                telemetry.update();
                return 0;
            }
        }

        return nextPos;
    }

    @Override
    public void actionEnd() {

        // Set power to 0
        robot.sampleMotor.setPower(0);
    }
}
