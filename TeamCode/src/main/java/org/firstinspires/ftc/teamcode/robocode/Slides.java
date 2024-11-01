package org.firstinspires.ftc.teamcode.robocode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides {

    private DcMotor slideExtender;

    public Slides(HardwareMap hwmp){

        this.slideExtender = hwmp.get(DcMotor.class, "slideExtender");

        slideExtender.setDirection(DcMotorSimple.Direction.REVERSE);

        slideExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void move(Gamepad gamepad){

        if (gamepad.right_bumper) {
            slideExtender.setPower(1);
        }

        else if (gamepad.left_bumper) {
            slideExtender.setPower(-0.1);
        }

        else if (gamepad.dpad_down) {
            slideExtender.setPower(-1);
        }

        else {
            slideExtender.setPower(0.1);
        }

    }

    public void doTelemetry(Telemetry telemetry){


    }
}
