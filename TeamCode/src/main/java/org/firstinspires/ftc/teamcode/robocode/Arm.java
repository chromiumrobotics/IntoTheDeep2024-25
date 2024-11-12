package org.firstinspires.ftc.teamcode.robocode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    private Servo armRotator = null;
    private CRServo intakeSpinner;
    double armPos = 0.2;

    public Arm(HardwareMap hwmp){

        this.armRotator = hwmp.get(Servo.class, "armRotator");
        this.intakeSpinner = hwmp.get(CRServo.class, "intakeSpinner");

    }

    public void rotate(Gamepad gamepad){

        if (gamepad.y) {
            armPos = .2;
        }
        if (gamepad.b) {
            armPos = .42;
        }
        if (gamepad.a) {
            armPos = .67;
        }
        armRotator.setPosition(armPos);

    }

    public void spin(Gamepad gamepad) {

        if (gamepad.right_trigger > 0) {
            intakeSpinner.setPower(1.0);
        }
        else if (gamepad.left_trigger > 0) {
            intakeSpinner.setPower(-0.5);
        }
        else {
            intakeSpinner.setPower(0.0);
        }

    }

    public void doTelemetry(Telemetry telemetry){

        telemetry.addData("intake spinner power", intakeSpinner.getPower());
        telemetry.addData("arm rotator position", armRotator.getPosition());

    }
}
