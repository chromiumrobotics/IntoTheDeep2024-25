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

    public DriveTrain(HardwareMap hwmp) {

        this.leftFrontDrive = hwmp.get(DcMotor.class, "leftFrontDrive");
        this.leftBackDrive = hwmp.get(DcMotor.class, "leftBackDrive");
        this.rightFrontDrive = hwmp.get(DcMotor.class, "rightFrontDrive");
        this.rightBackDrive = hwmp.get(DcMotor.class, "rightBackDrive");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void drive(Gamepad gamepad) {
        double max;
        double x = gamepad.left_stick_x;
        double y = -gamepad.left_stick_y;
        double r = gamepad.right_stick_x;
        double leftFrontPower = y + x + r;
        double rightFrontPower = y - x - r;
        double leftBackPower = y - x + r;
        double rightBackPower = y + x - r;
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;

        }
        double scalar = .6;

        if (gamepad.right_trigger > 0) {
            scalar = 1.0;
        }

        leftFrontDrive.setPower(scalar * leftFrontPower);
        rightFrontDrive.setPower(scalar * rightFrontPower);
        leftBackDrive.setPower(scalar * leftBackPower);
        rightBackDrive.setPower(scalar * rightBackPower);


    }

    public void doTelemetry(Telemetry telemetry) {
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontDrive.getPower(), rightFrontDrive.getPower());
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackDrive.getPower(), rightBackDrive.getPower());
    }
}
