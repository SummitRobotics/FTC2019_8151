package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare Hardware members.
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor liftMotor;
    public DcMotor sampleMotor;

    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    private final int MOTOR_REV_COUNTS = 1680; // 1120 for 40:1, 560 for 20:1
    private final double DRIVE_GEAR_RATIO = 1; // WheelGear / MotorGear
    private final double WHEEL_CIRCUMFERENCE = 6 * Math.PI;
    public final double ROBOT_WIDTH = 16;

    public final int DRIVE_COUNTS_PER_INCH = (int) (MOTOR_REV_COUNTS * DRIVE_GEAR_RATIO /
            WHEEL_CIRCUMFERENCE);

    public final int DRIVE_COUNTS_PER_RADIAN = (int) -(ROBOT_WIDTH / 2 * DRIVE_COUNTS_PER_INCH);

    private final double LIFT_GEAR_RATIO = 1/1; // WheelGear / MotorGear
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

        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }
}
