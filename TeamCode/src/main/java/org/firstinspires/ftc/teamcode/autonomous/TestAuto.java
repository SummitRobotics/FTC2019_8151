package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.Land;
import org.firstinspires.ftc.teamcode.autonomous.actions.LiftMotorControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByTime;
import org.firstinspires.ftc.teamcode.autonomous.actions.TurnByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.CalabrateGyro;
import org.firstinspires.ftc.teamcode.autonomous.actions.TurnByGyro;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

@Autonomous(name="TestAuto", group="LinearOpMode")
public class TestAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous

      // landing off lander and resetting pos
       path.add(new Land(hardwareMap, telemetry));
        path.add(new MoveByTime(1.5,-.5,1));
        //TODO add intake control in auto
        path.add(new TurnByEncoder(3 ,.4,1));
        path.add(new MoveByTime(3,-.5,END));




        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);


    }
}