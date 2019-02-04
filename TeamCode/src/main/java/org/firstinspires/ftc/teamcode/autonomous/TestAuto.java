package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.ArmControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.Land;
import org.firstinspires.ftc.teamcode.autonomous.actions.LiftMotorControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.TurnByEncoder;

import java.util.ArrayList;

@Autonomous(name="TestAuto", group="LinearOpMode")
public class TestAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous

      // landing off lander and reseting pos
        path.add(new Land(hardwareMap, telemetry));


       //path.add(new TurnByEncoder(-0.40, 0.4, 1));


        path.add(new MoveByEncoder(0,-1,-1,END));


        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);


    }
}