package org.firstinspires.ftc.teamcode.robocode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides {
    public boolean hangTime = true;
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

        if (gamepad.left_bumper) {
            hangTime = false;
        }
        if (gamepad.right_bumper){
            hangTime = true;
        }
        if (hangTime){
            if (gamepad.dpad_up) {
                hangExtenderLeft.setPower(1.0);
                hangExtenderRight.setPower(1.0);
            }
            else if (gamepad.dpad_down){
                hangExtenderLeft.setPower(-1.0);
                hangExtenderRight.setPower(-1.0);
            }
            else {
                hangExtenderLeft.setPower(0.0);
                hangExtenderRight.setPower(0.0);
            }
        }
        else {
            hangExtenderLeft.setPower(-.5);
            hangExtenderRight.setPower(-.5);
        }

    }

    public double tickstomms(int ticks){
        double revoluions = ticks/384.5;
        double diameter = 38.2;
        double circumference = diameter * Math.PI;
        return(circumference * revoluions);
    }

    public int mmsToTicks(double milimiteres){
       double revolutions = milimiteres/(38.2 * Math.PI);
       return (int) (revolutions * 384.5);
    }

    public void goToPosition(DcMotor slide, double posMms){

        if (slide.equals(slideExtender)){
            if (posMms > 970){
                posMms = 970;
            }
        }
        else{
            if (posMms > 690){
                posMms = 690;
            }
        }

        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setTargetPosition(mmsToTicks(posMms));

    }

    public void doTelemetry(Telemetry telemetry){
        telemetry.addData("slideExtender Target", tickstomms(slideExtender.getTargetPosition()));
        telemetry.addData("hangExtenderLeft Target", tickstomms(hangExtenderLeft.getTargetPosition()));
        telemetry.addData("hangExtenderRight Target", tickstomms(hangExtenderRight.getTargetPosition()));

        telemetry.addData("slideExtender CurrentPos", tickstomms(slideExtender.getCurrentPosition()));
        telemetry.addData("hangExtenderLeft CurrentPos", tickstomms(hangExtenderLeft.getCurrentPosition()));
        telemetry.addData("hangExtenderRight CurrentPos", tickstomms(hangExtenderRight.getCurrentPosition()));
    }
}
