package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardwareMap {
    protected DcMotor leftDrive;
    protected DcMotor rightDrive;
    protected DcMotor liftArm;
    //protected ColorSensor colorSensor;
    protected Servo marker;

    protected HardwareMap hwMap;

    ElapsedTime runtime = new ElapsedTime();

    public hardwareMap(){

    }

    public void init(HardwareMap aHwMap){
        hwMap = aHwMap;

        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        leftDrive = hwMap.get(DcMotor.class, "left_drive");
        liftArm = hwMap.get(DcMotor.class, "lift");
        //colorSensor = hwMap.get(ColorSensor.class, "rainbow_reader");
        marker = hwMap.get(Servo.class, "markerDrop");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.REVERSE);

        liftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        marker.setPosition(0);

    }
}
