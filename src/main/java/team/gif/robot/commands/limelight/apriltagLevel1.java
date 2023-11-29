package team.gif.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;

public class apriltagLevel1 extends CommandBase {
    double txOffSet;
    double tyOffSet;
    double taOffSet;
    public apriltagLevel1() {
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        txOffSet = Robot.limelight.getXOffset();
        tyOffSet = Robot.limelight.getYOffset();
        taOffSet = Robot.limelight.getArea();
    }

    // Called every time the scheduler runs (~20ms) while the command is scheduled
    @Override
    public void execute() {
        System.out.println("x Offset:  " + txOffSet);
        System.out.println("y Offset:  " + tyOffSet);
        System.out.println("a offset: " + taOffSet);
    }

    // Return true when the command should end, false if it should continue. Runs every ~20ms.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called when the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
}
