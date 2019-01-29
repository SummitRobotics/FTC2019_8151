package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmControl extends CoreAction {

    private double speed;
    private double time;
    ElapsedTime runtime = new ElapsedTime();

    public ArmControl(double time, double speed, int nextPos) {
        this.speed = speed;
        this.time = time;
        this.nextPos = nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);
        runtime.reset();
    }

    @Override
    public int run() {
        // Set motor power until finished
        if (runtime.seconds() < time) {
            robot.leftLiftServo.setPower(speed);
            robot.rightLiftServo.setPower(-speed);
            return 0;
        }
        return nextPos;
    }

    @Override
    public void actionEnd() {
        robot.leftLiftServo.setPower(0);
        robot.rightLiftServo.setPower(0);
    }

}