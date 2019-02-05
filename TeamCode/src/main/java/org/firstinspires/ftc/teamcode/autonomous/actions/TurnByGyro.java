package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import com.qualcomm.robotcore.util.ElapsedTime;

public class TurnByGyro extends CoreAction {

    int target;
    ElapsedTime runtime = new ElapsedTime();

    public TurnByGyro(int target, int nextPos) {

        // compairs the curent position (as last calibrated) to the target angle
        this.target = target;
        this.nextPos = nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {
        runtime.reset();

    }

    @Override
    public int run() {


        if (target > robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle){

            while (target > robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + 2){


                robot.leftDrive.setPower(-.4);
                robot.rightDrive.setPower(.4);

               return 0;
            }


        }

        if (target < robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle){

            while (target < robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - 2){

                robot.rightDrive.setPower(-.4);
                robot.leftDrive.setPower(.4);

               return 0;
            }

        }





        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        return 1;
    }

    @Override
    public void actionEnd() {

    }
}
