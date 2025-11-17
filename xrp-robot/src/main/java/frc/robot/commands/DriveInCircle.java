// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class DriveInCircle extends SequentialCommandGroup {
  public DriveInCircle(Drivetrain drivetrain) {
    addCommands(
        new DriveAndTurnTime(1.0, 1.0, 999999, drivetrain) // Drive in a circle forever
    );
  }
}
