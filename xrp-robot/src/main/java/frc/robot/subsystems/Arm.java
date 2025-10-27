// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;


public class Arm extends SubsystemBase {
  // Create a Mechanism2d with a 3x3 field
  Mechanism2d armMech = new Mechanism2d(3, 3);
  // Set the root of the arm at (2, 0)
  MechanismRoot2d root = armMech.getRoot("arm", 1.5, 1.5);

  // Define the arm ligament with length 1.0, width 6, and a gold color
  private final MechanismLigament2d armLigament = 
    root.append(new MechanismLigament2d("Arm1", 0.7, 0, 6, new Color8Bit(Color.kGold)));


  private XRPServo m_armServo = new XRPServo(4);

  /** Creates a new Arm. */
  public Arm() {
    
  armLigament.append(new MechanismLigament2d("Hook1", 0.1, 90, 6, new Color8Bit(Color.kGold)));

  armLigament.append(new MechanismLigament2d("Arm2", 0.3, 0, 6, new Color8Bit(Color.kGold)))
  .append(new MechanismLigament2d("Hook2", 0.1, 90, 6, new Color8Bit(Color.kGold)));


    // Put the Mechanism2d on the SmartDashboard
    SmartDashboard.putData("Arm Mechanism", armMech);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // Update the arm angle on the SmartDashboard for general debugging
    SmartDashboard.putNumber("Arm Angle", m_armServo.getAngle());
    
    // Update the angle of the arm ligament on the Mechanism2d
    armLigament.setAngle(m_armServo.getAngle());
  }

  public void setAngle(double angle) {
    m_armServo.setAngle(angle);
  }
}