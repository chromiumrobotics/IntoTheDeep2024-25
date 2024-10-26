package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {

    private DcMotor leftFrontDrive;
    private DcMotor leftBackDrive;
    private DcMotor rightFrontDrive;
    private DcMotor rightBackDrive;

    public DriveTrain(HardwareMap hwmp){

        this.leftFrontDrive = hwmp.get(DcMotor.class, "leftFrontDrive");
        this.leftBackDrive = hwmp.get(DcMotor.class, "leftBackDrive");
        this.rightFrontDrive = hwmp.get(DcMotor.class, "rightFrontDrive");
        this.rightBackDrive = hwmp.get(DcMotor.class, "rightBackDrive");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void drive(Gamepad gamepad){
        double max;

        double axial = -gamepad.left_stick_y;
        double lateral = gamepad.left_stick_x;
        double yaw = gamepad.right_stick_x;
        //intakeRotator.setPosition(0);
        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;

        }
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
    }

    public void doTelemetry(Telemetry telemetry){
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontDrive.getPower(), rightFrontDrive.getPower());
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackDrive.getPower(), rightBackDrive.getPower());
    }
}
