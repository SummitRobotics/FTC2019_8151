package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Aidan's Auto", group = "Linear Opmode")
@Disabled
public class sampleAuto extends LinearOpMode {/*
    Action action = new Action();
    boolean isFinished;*/

    @Override
    public void runOpMode() throws InterruptedException {/*
        action.robot.init(hardwareMap);

        waitForStart();
        action.robot.runtime.reset();

        while (opModeIsActive()) {
            action.detach(8.8);
            action.turnRight(0.3);
            action.goReverse(1.0);
            action.turnLeft(0.7);
            action.goReverse(4.5);
            isFinished = true;
            while(isFinished && opModeIsActive()){
                action.robotStop();
                action.robot.liftArm.setPower(0);
            }

        }*/
    }
}