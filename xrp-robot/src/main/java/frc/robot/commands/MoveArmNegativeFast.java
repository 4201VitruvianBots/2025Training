// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveArmNegativeFast extends Command {


  private Arm m_Arm;
  public MoveArmNegativeFast(Arm arm) {
    m_Arm = arm;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Arm.getAngle();
    m_Arm.setAngle(m_Arm.getAngle());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Arm.setAngle(m_Arm.getAngle() - 2.5); 
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Arm.getAngle();
    m_Arm.setAngle(m_Arm.getAngle());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
