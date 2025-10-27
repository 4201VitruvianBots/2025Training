// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID; 
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ARM.ARM_ANGLE;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoChal1;
import frc.robot.commands.AutoChal2;
import frc.robot.commands.AutoChal3;
import frc.robot.commands.AutonomousDistance;
import frc.robot.commands.AutonomousTime;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveArmNegative;
import frc.robot.commands.MoveArmNegativeFast;
import frc.robot.commands.MoveArmPositive;
import frc.robot.commands.MoveArmPositiveFast;
import frc.robot.commands.TurnDegrees;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPOnBoardIO;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final XRPOnBoardIO m_onboardIO = new XRPOnBoardIO();

  private final Arm m_Arm = new Arm();

  // Assumes a gamepad plugged into channel 0
  private final CommandXboxController m_controller = new CommandXboxController(0);

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default command is arcade drive. This will run unless another command
    // is scheduled over it.
    m_drivetrain.setDefaultCommand(getArcadeDriveCommand());

    // Example of how to use the onboard IO
    Trigger userButton = new Trigger(m_onboardIO::getUserButtonPressed);
    userButton
        .onTrue(new PrintCommand("USER Button Pressed"))
        .onFalse(new PrintCommand("USER Button Released"));

    // Setup SmartDashboard options
    m_chooser.setDefaultOption("Auto Routine Distance", new AutonomousDistance(m_drivetrain));
    m_chooser.addOption("Auto Routine Time", new AutonomousTime(m_drivetrain));
    m_chooser.addOption("AutoChal1", new AutoChal1(m_drivetrain));
    m_chooser.addOption("AutoChal2", new AutoChal2(m_drivetrain));
    m_chooser.addOption("AutoChal3", new AutoChal3(m_drivetrain));
    SmartDashboard.putData(m_chooser);
      
    m_controller.a().whileTrue(new MoveArm(m_Arm, ARM_ANGLE.HALF_EXTENDED.get()));
    m_controller.b().whileTrue(new MoveArm(m_Arm, ARM_ANGLE.FULLY_EXTENDED.get()));
    m_controller.rightTrigger().whileTrue(new DriveDistance(1, 50, m_drivetrain));
    m_controller.leftTrigger().whileTrue(new DriveDistance(-1, 50, m_drivetrain));
    m_controller.leftBumper().whileTrue(new TurnDegrees(1, 360, m_drivetrain));
    m_controller.rightBumper().whileTrue(new TurnDegrees(-1, 360, m_drivetrain));
    m_controller.x().whileTrue(new MoveArm(m_Arm, ARM_ANGLE.STOWED.get()));
    m_controller.povDown().whileTrue(new MoveArmPositiveFast(m_Arm));
    m_controller.povUp().whileTrue(new MoveArmNegativeFast(m_Arm));
    m_controller.pov(315).whileTrue(new MoveArmPositive(m_Arm));
    m_controller.pov(45).whileTrue(new MoveArmPositive(m_Arm));
    m_controller.pov(135).whileTrue(new MoveArmNegative(m_Arm));
    m_controller.pov(225).whileTrue(new MoveArmNegative(m_Arm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  /**
   * Use this to pass the teleop command to the main {@link Robot} class.
   *
   * @return the command to run in teleop
   */
  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
        m_drivetrain, () -> -m_controller.getRawAxis(0), () -> -m_controller.getRawAxis(1));
  }
}
