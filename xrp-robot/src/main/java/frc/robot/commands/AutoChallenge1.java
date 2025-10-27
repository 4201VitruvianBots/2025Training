// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoChallenge1 extends SequentialCommandGroup {
  /** Creates a new AutoChallenge. */
  public AutoChallenge1(Drivetrain drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(        
      new TurnDegrees(-1, 45, drivetrain),
      new DriveDistance(1, 12 * 4, drivetrain),
      new TurnDegrees(1, 90, drivetrain),
      new DriveDistance(1, 12 * 2, drivetrain)
    );
  }
}
