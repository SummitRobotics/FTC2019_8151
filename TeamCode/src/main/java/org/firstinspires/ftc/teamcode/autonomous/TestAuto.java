package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.LiftMotorControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByTime;
import org.firstinspires.ftc.teamcode.autonomous.actions.TurnByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.CalabrateGyro;
import org.firstinspires.ftc.teamcode.autonomous.actions.TurnByGyro;

import java.util.ArrayList;

@Autonomous(name="TestAuto", group="LinearOpMode")
public class TestAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous

      // landing off lander and resetting pos

        path.add(new CalabrateGyro(1));
        path.add(new LiftMotorControl(2.2,1,1));
        path.add(new TurnByEncoder(-.6,.4,1));
        path.add(new MoveByTime(1,-.5,1));
        path.add(new TurnByGyro(0,1));


        // doing other things
        path.add(new MoveByTime(0,-1,-1,END));


        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);


    }
}