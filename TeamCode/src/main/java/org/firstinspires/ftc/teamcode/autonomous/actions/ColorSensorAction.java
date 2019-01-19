package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorAction extends CoreAction {
    int yellowPos;
    public ColorSensorAction(int yellowPos, int whitePos) {
        this.yellowPos = yellowPos;
        this.nextPos = whitePos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {
        robot.init(hardwareMap);
    }

    @Override
    public int run() {
        if (isYellow((int) (robot.colorSensor.red() * 255.0),
                (int) (robot.colorSensor.green() * 255.0),
                (int) (robot.colorSensor.blue() * 255.0))) {
            return yellowPos;
        } else {
            return nextPos;
        }
    }

    public boolean isYellow(int r, int g, int b) {
        return true;
    }

    @Override
    public void actionEnd() {

    }
}
