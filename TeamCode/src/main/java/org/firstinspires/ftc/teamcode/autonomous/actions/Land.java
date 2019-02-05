package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


import static android.os.SystemClock.sleep;

public class Land extends CoreAction {

    private double speed;
    private int ticks;
    private int target;
    Telemetry telemetry;
    private double leftSpeed, rightSpeed;
    private int leftTicks, rightTicks;
    private int leftTarget, rightTarget;
    ElapsedTime runtime = new ElapsedTime();
    double time;
    int done = 0;
    public int lastAngles;
    Orientation             firstAngle = new Orientation();





    public Land(HardwareMap hardwareMap, Telemetry telemetry) {
        //landing
        ticks = (int) (4 * robot.LIFT_COUNTS_PER_ROT);

        //turning
        double radians = -0.60;
        double speed = .4;
        this.leftSpeed = speed;
        this.rightSpeed = -speed;
        this.leftTicks = (int) (radians * robot.DRIVE_COUNTS_PER_RADIAN);
        this.rightTicks = (int) (radians * robot.DRIVE_COUNTS_PER_RADIAN);

        //backup
        this.time = 1;





        actionInit(hardwareMap, telemetry);

        nextPos = 1;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {



        this.telemetry = telemetry;

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        target = robot.liftMotor.getCurrentPosition() + ticks;

        robot.liftMotor.setTargetPosition(target);

        // Turn On RUN_TO_POSITION
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        // State used for updating telemetry
        Orientation angles;
        Acceleration gravity;


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".



        robot.gyro.initialize(parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        sleep(200);




        //inshlises drive motors for turning


        runtime.reset();

        telemetry.addData("Finished", false);
        telemetry.update();



    }

    @Override
    public int run() {

        // decends

        SG0();

        if (done == -1) {lower();}



        //drives back after it has decended
        if (done == 1){turn();}

       if (done == 2){backup();}

        // does next thing when all have completed
       if (done == 3) {corectangle();}


       if (done == 4){return 1;}

       return 0;
    }

    // gets the current angle of the robot
    public void SG0(){



        firstAngle = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        done = -1;
    }

    public void lower(){

        // lift motor control
        while (robot.liftMotor.isBusy()) {
            while ((!robot.liftButton.getState()) && (robot.liftMotor.getPower() < 0)){
                telemetry.addData("Finished",true);
                telemetry.update();
                robot.liftMotor.setPower(0);

            }
            robot.liftMotor.setPower(1);

        }
        done = 1;

    }

    public void turn(){

        // Prepare motors for encoder movement
        leftTarget = robot.leftDrive.getCurrentPosition() + leftTicks;
        rightTarget = robot.rightDrive.getCurrentPosition() - rightTicks;

        robot.leftDrive.setTargetPosition(leftTarget);
        robot.rightDrive.setTargetPosition(rightTarget);

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (robot.leftDrive.isBusy() && robot.rightDrive.isBusy()) {
            telemetry.addData("turning", "ttttttttttttt");
            telemetry.addData("Left Current Pos", robot.leftDrive.getCurrentPosition());
            telemetry.addData("Right Current Pos", robot.rightDrive.getCurrentPosition());
            telemetry.addData("Left Expected Pos", robot.leftDrive.getTargetPosition());
            telemetry.addData("Right Expected Pos", robot.rightDrive.getTargetPosition());
            telemetry.update();

            robot.leftDrive.setPower(leftSpeed);
            robot.rightDrive.setPower(rightSpeed);
        }

        done = 2;
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        telemetry.addData("turned", "rererererer");
        telemetry.update();
    }

    public void backup(){

        // Set motor power until finished

        runtime.reset();

            while(runtime.seconds() < time){
                telemetry.addData("backing", "nnnnnnnnn");
                telemetry.update();
                robot.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.leftDrive.setPower(-.5);
                robot.rightDrive.setPower(-.5);
            }

        done = 3;
        telemetry.addData("backuped", "aaaaaaaaaaaa");
        telemetry.update();
    }


    // fix red lines
    public void corectangle(){

        telemetry.addData("firstangle", firstAngle.firstAngle);
        telemetry.addData("curentangle", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
        telemetry.update();


        if (firstAngle.firstAngle > robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle){

            while (firstAngle.firstAngle > robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + 2){


                robot.leftDrive.setPower(-.4);
                robot.rightDrive.setPower(.4);

                telemetry.addData("firstangle", firstAngle.firstAngle);
                telemetry.addData("curentangle", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
                telemetry.update();
            }


        }

        if (firstAngle.firstAngle < robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle){

            while (firstAngle.firstAngle < robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - 2){

                robot.rightDrive.setPower(-.4);
                robot.leftDrive.setPower(.4);

                telemetry.addData("firstangle", firstAngle.firstAngle);
                telemetry.addData("curentangle", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
                telemetry.update();
            }

        }

        telemetry.addData("firstangle", firstAngle.firstAngle);
        telemetry.addData("curentangle", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
        telemetry.update();



        robot.leftDrive.setPower(0);
        done = 4;
    }




    @Override
    public void actionEnd() {
        robot.liftMotor.setPower(0);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
