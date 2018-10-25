package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Crater Auto - Front", group="Linear Opmode")
public class CraterAutoNew extends LinearOpMode {

    //Declare instance variables. These are objects and variables which can be used by any method in the class and are persistent throughout the class.
    ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor liftArm;

    final double TICKS_PER_ROTATION = 1680;
    final double WHEEL_CIRCUMFERENCE = 6.0 * Math.PI;
    //todo - find gear reduction
    final double GEAR_REDUCTION = 1;
    final double TICKS_PER_INCH = (TICKS_PER_ROTATION * GEAR_REDUCTION) / (WHEEL_CIRCUMFERENCE);

    public void runOpMode(){

        //Assigns a motor port to all of the DcMotor objects we created
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        liftArm = hardwareMap.get(DcMotor.class, "liftArm");

        //Tells these motors to run using their encoders
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Zeros these encoders
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Reverses direction of motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        liftArm.setDirection(DcMotor.Direction.FORWARD);

        //Tells motors to resist inertia when power = 0
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Waits for the "play" button to be pressed on the driver station
        waitForStart();
        //Resets the game time to zero when the game starts
        runtime.reset();
        //Zeros encoders again
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while(opModeIsActive()){

            drive(4,4,0.7);
        }
    }


    //This is the method we use to drive the robot given a certain amount of left and right inches and a speed to go at.
    public void drive(int leftInches, int rightInches, double power){
        int newLeftTarget, newRightTarget;
        //Quick sanity check to make sure the robot should still be running
        if(opModeIsActive()){
            //Adds the new number of ticks to the old position, so that the target is updated.
            //To do this, we convert inches to ticks.
            newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * TICKS_PER_INCH);
            newRightTarget = rightDrive.getCurrentPosition() + (int)(leftInches * TICKS_PER_INCH);

            //Tell the motors that their target distance is equal to the targets set above
            leftDrive.setTargetPosition(newLeftTarget);
            rightDrive.setTargetPosition(newRightTarget);

            //Run the motors until the target is reached
            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Run towards the target at the given power
            leftDrive.setPower(power);
            rightDrive.setPower(power);

            //When the target is reached, set power to 0 and disable run-to-position.
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }



    }
    /*
    public double ticksToInch(double ticks){
        TODO: Find gear reduction
        inches = ticks / (1680 * gearReduction)
    }*/
}
