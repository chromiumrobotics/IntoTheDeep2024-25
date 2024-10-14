package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "BasicOpMode", group = "LinearOpMode")
public class IntoTheDeepBasic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor slideExtender = null;
    //private Servo intakeRotator = null;
    private CRServo intakeSpinner = null;
    private Servo armRotator = null;


    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        intakeSpinner = hardwareMap.get(CRServo.class, "slideExtender");
        //intakeRotator = hardwareMap.get(Servo.class, "intakeRotator");
        intakeSpinner = hardwareMap.get(CRServo.class, "intakeSpinner");
        armRotator = hardwareMap.get(Servo.class, "armRotator");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        slideExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {
            double max;

            double axial = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
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

            if (gamepad1.y) {
                slideExtender.setPower(0.5);
            }
            else if (gamepad1.x) {
                slideExtender.setPower(-0.5);
            }
            else {
                slideExtender.setPower(0.0);
            }

            //if (gamepad1.right_bumper) {
                //intakeRotator.setPosition(0.25);
            //}
            //if (gamepad1.left_bumper) {
                //intakeRotator.setPosition(0);
            //}

            if (gamepad1.right_trigger > 0){
                intakeSpinner.setPower(1.0);
            }
            else{
                intakeSpinner.setPower(0.0);
            }
            if (gamepad1.a){
                armRotator.setPosition(0.5);
            }
            if (gamepad1.b){
                armRotator.setPosition(0.0);
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("intake spinner power", intakeSpinner.getPower());
            //telemetry.addData("intake rotator position", intakeRotator.getPosition());
            telemetry.addData("arm rotator position", armRotator.getPosition());
            telemetry.update();


        }
    }
}
