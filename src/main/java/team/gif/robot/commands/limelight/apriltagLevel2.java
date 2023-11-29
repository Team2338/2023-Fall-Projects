package team.gif.robot.commands.limelight;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

public class apriltagLevel2 extends CommandBase {
    double txOffSet;
    double tyOffSet;
    double taOffSet;

    public apriltagLevel2() {
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.limelight.setPipeline(0); //limelight pipeline

        //just driving the robot forward.
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0.01,0);
        SwerveModuleState[] moduleStates = Constants.Drivetrain.DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);
        Robot.swervetrain.setModuleStates(moduleStates);
    }

    // Called every time the scheduler runs (~20ms) while the command is scheduled
    @Override
    public void execute() {
        txOffSet = Robot.limelight.getXOffset();
        tyOffSet = Robot.limelight.getYOffset();
        taOffSet = Robot.limelight.getArea();

        if (Robot.limelight.hasTarget()) {
            if (taOffSet == 0.00 || taOffSet <= 2.0 && txOffSet == 0.0 && tyOffSet == 0.0) {
                ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 0, 0);
                SwerveModuleState[] moduleStates = Constants.Drivetrain.DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);
                Robot.swervetrain.setModuleStates(moduleStates);
            } else {
                ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 0.02, 0);
                SwerveModuleState[] moduleStates = Constants.Drivetrain.DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);
                Robot.swervetrain.setModuleStates(moduleStates);
            }
        }
    }

    // Return true when the command should end, false if it should continue. Runs every ~20ms.
    @Override
    public boolean isFinished() {
        if( Robot.oi.driver.getHID().getXButtonPressed()) // need a kill switch
            return true;
        return false;
    }

    // Called when the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.limelight.setLEDOff();
        ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0.01, 0, 0);
        SwerveModuleState[] moduleStates = Constants.Drivetrain.DRIVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);
        Robot.swervetrain.setModuleStates(moduleStates);
    }
}
