package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robocode.Arm;
import org.firstinspires.ftc.teamcode.robocode.Slides;

@TeleOp(name = "BasicOpMode", group = "LinearOpMode")
public class IntoTheDeepBasic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Slides slides;
    private Arm arm;
    private DriveTrain driveTrain;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(hardwareMap);
        slides = new Slides(hardwareMap);
        arm = new Arm(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {

            driveTrain.drive(gamepad1);

            slides.move(gamepad1);

            arm.rotate(gamepad2);

            arm.spin(gamepad2);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            driveTrain.doTelemetry(telemetry);
            arm.doTelemetry(telemetry);
            telemetry.update();


            }
        }
    }
