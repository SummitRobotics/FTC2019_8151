package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.ArmControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
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

        //test action to be deleted
        //path.add(new LiftMotorControl(4, 1, 1));
        path.add(new TurnByEncoder(-0.75, 0.7, 1));
        path.add(new MoveByEncoder(1.5,-.5,-.55,1));
        path.add(new TurnByEncoder(0.75,-0.5, 1));
        path.add(new MoveByEncoder(5,-.5 ,-.55,END));
        //path.add(new LiftMotorControl(-2, 1, END));
        /*
        path.add(new MoveByEncoder(0.175,-0.5,-0.5,1));
        path.add(new MoveByEncoder(0.25,-0.5,0.5,1));
        path.add(new MoveByEncoder(1.5,-0.5,-0.5,1));
        path.add(new ArmControl(0.5, 0.7, 1));
        path.add(new ArmControl (0.5,-0.7,1));
        path.add(new MoveByEncoder(2.0,-0.5,0.5,1));
        path.add(new MoveByEncoder (4.5,-0.5,-0.5,END));
        */

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);


    }
}