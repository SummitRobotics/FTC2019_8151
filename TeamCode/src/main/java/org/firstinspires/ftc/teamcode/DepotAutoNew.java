package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="DepotAutoNew - Crater Alignment",group = "LinearOpMode")
public class DepotAutoNew extends LinearOpMode {
    Action action = new Action();
    @Override
    public void runOpMode() {
        action.robot.init(hardwareMap);
        waitForStart();
        action.robot.runtime.reset();

        while(opModeIsActive()){
            action.detach(9.0);
            action.turnRight(0.3);
            action.goReverse(1.0);
            action.turnLeft(0.3);
            //spin
            action.turnLeft(2.8);
            action.moveFwd(5.5);
            action.dropMarker(2.5);
            action.turnRight(0.6);
            action.goReverse(7.0);

        }

    }
}
