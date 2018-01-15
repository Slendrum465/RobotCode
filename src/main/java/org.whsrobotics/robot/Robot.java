/**
* This is Sean Lendrum's original code
* Based off of the code written by Lawrence Tseng
* TimeStamp: Last edited: Jan 15 2018
 */

package org.whsrobotics.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID;

import static org.whsrobotics.robot.RobotMap.*;


//The class robot is an extention of IterativeRobot

public class Robot extends IterativeRobot {


    /*
    These properties declare the talon ports
     which control the motors of the robot wheels
     */




    // Create WPI_TalonSRXs

    WPI_TalonSRX leftFront;
    WPI_TalonSRX rightFront;
    WPI_TalonSRX leftBack;
    WPI_TalonSRX rightBack;

    // Create SpeedControllerGroups

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    // Create DifferentialDrive

    DifferentialDrive differentialDrive;

    //Create XboxController

    XboxController xboxController;

    // Init

    /**
     * Robot-wide initialization code should go here.
     * <p>
     * <p>Users should override this method for default Robot-wide initialization which will be called
     * when the robot is first powered on. It will be called exactly one time.
     * <p>
     * <p>Warning: the Driver Station "Robot Code" light and FMS "Robot Ready" indicators will be off
     * until RobotInit() exits. Code in RobotInit() that waits for enable will cause the robot to
     * never indicate that the code is ready, causing the robot to be bypassed in a match.
     */
    @Override
    public void robotInit() {


        //Initializes talons and objects

        leftFront = new WPI_TalonSRX(LEFT_FRONT_TALON_PORT);
        rightFront = new WPI_TalonSRX(RIGHT_FRONT_TALON_PORT);
        leftBack = new WPI_TalonSRX(LEFT_BACK_TALON_PORT);
        rightBack = new WPI_TalonSRX(RIGHT_BACK_TALON_PORT);


        /*
        Objects that group other objects
        Groups the right and left wheels together
         */

        leftDrive = new SpeedControllerGroup(leftFront, leftBack);
        rightDrive = new SpeedControllerGroup(rightFront, rightBack);


        //Groups all driving abilities together

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);


        //Initializes control with xbox controller

        xboxController = new XboxController(0);

    }

        //talonSRX = new TalonSRX(4);

        //talonSRX.setInverted(true);


    // Periodic Functions

    /**
     * Initialization code for teleop mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters teleop mode.
     */
    @Override
    public void teleopInit() {

    }

    /**
     * Periodic code for teleop mode should go here.
     */
    @Override
    public void teleopPeriodic() {

        if (isOperatorControl() && isEnabled()) {
            differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kRight));

            xboxController.getYButtonPressed();

            if (xboxController.getYButtonPressed()){

                leftFront.setNeutralMode(NeutralMode.Brake);
                rightFront.setNeutralMode(NeutralMode.Brake);
                leftBack.setNeutralMode(NeutralMode.Brake);
                rightBack.setNeutralMode(NeutralMode.Brake);


            }


        }

        //Scheduler.getInstance().run();
        //talonSRX.set(ControlMode.PercentOutput, .00);
    }
}