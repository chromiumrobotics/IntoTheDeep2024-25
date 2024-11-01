package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions.AnalogExpanded;
import org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions.AxisExpanded;
import org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions.GamepadExpanded;
import org.firstinspires.ftc.teamcode.robocode.Arm;
import org.firstinspires.ftc.teamcode.robocode.Slides;

@TeleOp(name = "BasicOpMode", group = "LinearOpMode")
public class IntoTheDeepBasic extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Slides slides;
    private Arm arm;
    private DriveTrain driveTrain;
    GamepadExpanded gpex1 = new GamepadExpanded(gamepad1);

    @Override
    public void runOpMode() {
        gpex1.left_stick_axis.setFilter(AnalogExpanded.MODE.INSENSITIVE);
        gpex1.right_stick_axis.setFilter(AnalogExpanded.MODE.INSENSITIVE);

        driveTrain = new DriveTrain(hardwareMap);
        slides = new Slides(hardwareMap);
        arm = new Arm(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {
            gpex1.update(gamepad1);

            driveTrain.drive(gpex1.left_stick_axis.x, -gpex1.left_stick_axis.y, gpex1.right_stick_axis.x);

            slides.move(gamepad2);

            arm.rotate(gamepad2);

            arm.spin(gamepad2);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            driveTrain.doTelemetry(telemetry);
            arm.doTelemetry(telemetry);
            telemetry.update();


            }
        }
    }
