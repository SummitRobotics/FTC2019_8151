package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public static DcMotor leftDrive, rightDrive, liftArm, intake;
    HardwareMap hwMap;

    public Robot(){

    }

    public void init(HardwareMap hardware){
        hwMap = hardware;

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        liftArm = hwMap.get(DcMotor.class, "liftArm");
        intake = hwMap.get(DcMotor.class, "uptake");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection((DcMotor.Direction.REVERSE));

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
}
