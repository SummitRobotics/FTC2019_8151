package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Hardware {
    // Declare Hardware members.
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor liftMotor;
    public DcMotor sampleMotor;
    public CRServo leftLiftServo;
    public CRServo rightLiftServo;
    public CRServo frontIntakeServo;
    public CRServo backIntakeServo;

    public DigitalChannel liftButton;
    public ColorSensor colorSensor;

    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    private final int MOTOR_REV_COUNTS = 1680; // 1120 for 40:1, 560 for 20:1
    private final double DRIVE_GEAR_RATIO = 1; // WheelGear / MotorGear
    private final double WHEEL_CIRCUMFERENCE = 6 * Math.PI;
    public final double ROBOT_WIDTH = 16;

    public final int DRIVE_COUNTS_PER_INCH = (int) (MOTOR_REV_COUNTS * DRIVE_GEAR_RATIO /
            WHEEL_CIRCUMFERENCE);

    public final int DRIVE_COUNTS_PER_RADIAN = (int) -(ROBOT_WIDTH / 2 * DRIVE_COUNTS_PER_INCH);

    private final double LIFT_GEAR_RATIO = 120/25; // WheelGear / MotorGear
    public final double LIFT_COUNTS_PER_ROT = (MOTOR_REV_COUNTS * LIFT_GEAR_RATIO);

    // Local opmode hardware map
    private HardwareMap hardwareMap = null;

    // Constructor
    public Hardware() {}

    public void init(HardwareMap hardwareMap) {
        // Save reference to Hardware map
        this.hardwareMap = hardwareMap;

        //Init hardware
        leftDrive = this.hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = this.hardwareMap.get(DcMotor.class, "rightDrive");
        liftMotor = this.hardwareMap.get(DcMotor.class, "liftMotor");
        sampleMotor = this.hardwareMap.get(DcMotor.class, "sampleMotor");
        leftLiftServo = this.hardwareMap.get(CRServo.class, "leftLiftServo");
        rightLiftServo = this.hardwareMap.get(CRServo.class, "rightLiftServo");
        frontIntakeServo = this.hardwareMap.get(CRServo.class, "frontIntakeServo");
        backIntakeServo = this.hardwareMap.get(CRServo.class, "backIntakeServo");

        liftButton = hardwareMap.get(DigitalChannel.class, "liftButton");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.REVERSE);
        sampleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
