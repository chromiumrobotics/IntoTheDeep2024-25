package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "BasicOpMode", group = "LinearOpMode")
public class IntoTheDeepBasic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor slideExtender = null;
    //private Servo intakeRotator = null;
    private CRServo intakeSpinner = null;
    private Servo armRotator = null;
    private DriveTrain driveTrain;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(hardwareMap);
        slideExtender = hardwareMap.get(DcMotor.class, "slideExtender");
        //intakeRotator = hardwareMap.get(Servo.class, "intakeRotator");
        intakeSpinner = hardwareMap.get(CRServo.class, "intakeSpinner");
        armRotator = hardwareMap.get(Servo.class, "armRotator");

        double armPos = 0.28;

        slideExtender.setDirection(DcMotorSimple.Direction.REVERSE);

        slideExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {

            driveTrain.drive(gamepad1);

            if (gamepad1.y) {
                slideExtender.setPower(1);
            } else if (gamepad1.x) {
                slideExtender.setPower(-0.1);
            } else if (gamepad1.dpad_up) {
                slideExtender.setPower(-1);
            } else {
                slideExtender.setPower(0.1);
            }

            /*if (gamepad1.right_bumper) {
                for (int i = 100; i >= 0; i--) {
                    intakeRotator.setPosition((double) i / 100);
                }*/


                if (gamepad1.right_trigger > 0) {
                    intakeSpinner.setPower(1.0);
                } else if (gamepad1.left_trigger > 0) {
                    intakeSpinner.setPower(-0.5);
                } else {
                    intakeSpinner.setPower(0.0);
                }
                if (gamepad1.a) {
                    armPos = .28;
                }
                if (gamepad1.b) {
                    armPos = .5;
                }
                if (gamepad1.dpad_down) {
                    armPos = .7;
                }

                armRotator.setPosition(armPos);

                telemetry.addData("Status", "Run Time: " + runtime.toString());
                driveTrain.doTelemetry(telemetry);
                telemetry.addData("intake spinner power", intakeSpinner.getPower());
                //telemetry.addData("intake rotator position", intakeRotator.getPosition());
                telemetry.addData("arm rotator position", armRotator.getPosition());
                telemetry.update();


            }
        }
    }
