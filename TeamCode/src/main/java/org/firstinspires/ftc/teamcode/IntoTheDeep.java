package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.robocode.Arm;
import org.firstinspires.ftc.teamcode.robocode.Slides;

@TeleOp(name = "IntoTheDeep")
public class IntoTheDeep extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        DriveTrain driveTrain = new DriveTrain(hardwareMap);
        Slides slides = new Slides(hardwareMap);
        Arm arm = new Arm(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {

            driveTrain.drive(gamepad1);

            slides.move(gamepad2);

            slides.hang(gamepad1);

            arm.rotate(gamepad2);

            arm.spin(gamepad2);

            telemetry.addData("Status", "Run Time: " + runtime);
            driveTrain.doTelemetry(telemetry);
            slides.doTelemetry(telemetry);
            arm.doTelemetry(telemetry);
            telemetry.update();


            }
        }
    }
