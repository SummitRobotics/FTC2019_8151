package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
        path.add(new LiftMotorControl(3.5, 1, 1));
        path.add(new TurnByEncoder(-0.2222 * Math.PI, .5,1));
        path.add(new MoveByEncoder(-3,1,1));
        path.add(new TurnByEncoder(0.2222 * Math.PI,.5,1));
        path.add(new MoveByEncoder(-3,1,1));
        path.add(new TurnByEncoder(0.5555 * Math.PI,.5,1));
        path.add(new MoveByEncoder(132,1,END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}