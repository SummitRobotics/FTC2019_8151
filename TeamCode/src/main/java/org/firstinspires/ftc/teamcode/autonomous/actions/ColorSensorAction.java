package org.firstinspires.ftc.teamcode.autonomous.actions;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorAction extends CoreAction {
    Telemetry telemetry;

    public ColorSensorAction(int nextPos1, int nextPos2){
                                                                                        //public class butts_lol

    }
    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

    }

    @Override
    public int run() {
        double red = robot.colorSensor.red();
        double blue = robot.colorSensor.blue();
        double green = robot.colorSensor.green();

        telemetry.addData("Red", red);
        telemetry.addData("Blue", blue);
        telemetry.addData("Green", green);

        return 0;
    }

    @Override
    public void actionEnd() {

    }
}
