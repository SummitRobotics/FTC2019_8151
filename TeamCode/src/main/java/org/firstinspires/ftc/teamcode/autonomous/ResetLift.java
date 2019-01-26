package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.LiftMotorControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import java.util.ArrayList;

@Autonomous(name="ResetLift", group="LinearOpMode")
public class ResetLift extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new LiftMotorControl(-3.5, 1, END));


        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}