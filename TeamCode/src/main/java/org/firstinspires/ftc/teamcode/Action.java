package org.firstinspires.ftc.teamcode;

public class Action {
    hardwareMap robot = new hardwareMap();

    private final double DRIVE_SPEED = 0.5;
    private final double TURN_SPEED = 0.5;
    private final double OUTPUT_SPEED = 1;

    protected void detach(double time){
        boolean isDone;
        double initTime = robot.runtime.seconds();
        while (robot.runtime.seconds() - initTime <= time){
            robot.liftArm.setPower(1);
        }
        robot.liftArm.setPower(0);
        isDone = true;
    }

    public void moveFwd(double time) {
        boolean isDone;
        double initTime = robot.runtime.seconds();
        while(robot.runtime.seconds() - initTime <= time) {
            robot.leftDrive.setPower(DRIVE_SPEED);
            robot.rightDrive.setPower(DRIVE_SPEED);
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        isDone = true;
    }

    public void robotStop() {
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);
            robot.liftArm.setPower(0);
    }

    public void turnLeft(double time ) {
        boolean isDone;
        double initTime = robot.runtime.seconds();
        while(robot.runtime.seconds() - initTime <= time) {
            robot.leftDrive.setPower(-TURN_SPEED);
            robot.rightDrive.setPower(TURN_SPEED);
        }
        isDone = true;
    }

    public void turnRight(double time) {
        boolean isDone;
        double initTime = robot.runtime.seconds();
        while(robot.runtime.seconds() - initTime <= time) {
            robot.leftDrive.setPower(TURN_SPEED);
            robot.rightDrive.setPower(-TURN_SPEED);
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        isDone = true;
    }


    public void goReverse(double time) {
        boolean isDone;
        double initTime = robot.runtime.seconds();
        while(robot.runtime.seconds() - initTime <= time) {
            robot.leftDrive.setPower(-DRIVE_SPEED);
            robot.rightDrive.setPower(-DRIVE_SPEED);
        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        isDone = true;
    }
        public void dropMarker(double time){
        double initTime = robot.runtime.seconds();
        while(robot.runtime.seconds() - initTime <= time) {
            robot.marker.setPosition(1);
        }
    }
}
