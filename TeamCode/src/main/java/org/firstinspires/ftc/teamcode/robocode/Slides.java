package org.firstinspires.ftc.teamcode.robocode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides {

    private DcMotor slideExtender;
    private DcMotor hangExtenderLeft;
    private DcMotor hangExtenderRight;

    public Slides(HardwareMap hwmp){

        this.slideExtender = hwmp.get(DcMotor.class, "slideExtender");
        this.hangExtenderLeft = hwmp.get(DcMotor.class, "hangExtenderLeft");
        this.hangExtenderRight = hwmp.get(DcMotor.class, "hangExtenderRight");

        slideExtender.setDirection(DcMotorSimple.Direction.REVERSE);
        hangExtenderLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        slideExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangExtenderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangExtenderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void move(Gamepad gamepad){

        if (gamepad.dpad_up) {
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

    public void hang(Gamepad gamepad){

        if (gamepad.dpad_up) {
            hangExtenderLeft.setPower(1.0);
            hangExtenderRight.setPower(1.0);
        }
        else if (gamepad.dpad_down){
            hangExtenderLeft.setPower(-1.0);
            hangExtenderRight.setPower(-1.0);
        }
        else {
            hangExtenderLeft.setPower(-.5);
            hangExtenderRight.setPower(-.5);
        }

    }

    public void doTelemetry(Telemetry telemetry){


    }
}
