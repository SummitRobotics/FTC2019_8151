package actions;

import org.firstinspires.ftc.teamcode.Robot;

public class Drive extends ActionTemplate {
    int leftTargetInches, rightTargetInches;
    double power;
    final double DEFAULT_POWER = 0.65;
    Robot robot = new Robot();

    public Drive(int leftInches, int rightInches){
        leftTargetInches = leftInches;
        rightTargetInches = rightInches;
        power = DEFAULT_POWER;
    }

    public Drive(int leftInches, int rightInches, double driveSpeed){
        leftTargetInches = leftInches;
        rightTargetInches = rightInches;
        power = driveSpeed;
    }

    @Override
    public void init(){
        
    }

    @Override
    public void run(){

    }

}
