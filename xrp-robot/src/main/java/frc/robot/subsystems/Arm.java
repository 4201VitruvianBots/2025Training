// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ARM.ARM_ANGLE;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  public Arm() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private final XRPServo m_armServo = new XRPServo(4);

  public void setAngle(double angleDeg){
    m_armServo.setAngle(angleDeg);
  }
  public double getAngle(){
    return m_armServo.getAngle();
  }

public DoubleSupplier getAngleSupplier(){
  return() -> m_armServo.getAngle();
}

  public double currentAngle = m_armServo.getAngle();


@Override
public void simulationPeriodic(){
  SmartDashboard.putNumber("Arm Angle", m_armServo.getAngle());
}
}


