package org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;

public class GamepadExpanded {

    Gamepad gp;

    public ButtonExpanded a;
    public ButtonExpanded b;
    public ButtonExpanded x;
    public ButtonExpanded y;
    public ButtonExpanded right_bumper;
    public ButtonExpanded left_bumper;
    public ButtonExpanded left_stick_button;
    public ButtonExpanded right_stick_button;
    public ButtonExpanded dpad_up;
    public ButtonExpanded dpad_left;
    public ButtonExpanded dpad_down;
    public ButtonExpanded dpad_right;
    public ButtonExpanded start;
    public ButtonExpanded back;
    public ButtonExpanded guide;
    public ButtonExpanded left_trigger_digital;
    public ButtonExpanded right_trigger_digital;

    public AnalogExpanded left_trigger_analog;
    public AnalogExpanded right_trigger_analog;

    public AxisExpanded right_stick_axis;
    public AxisExpanded left_stick_axis;

    public ArrayList<ButtonExpanded> buttons;


    /**
     * Each button of this class is a ButtonExpanded class.
     * <p>Implementation:
     * <p>GamepadExpanded gpex1 = new GamepadExpanded();
     * <p>gpex1.update(gamepad1);
     * <p>gpex.a.isPressed();
     * <p>gpex.b.hasChangedToTrue();
     * <p>etc..
     */
    public GamepadExpanded(){
        this.buttons.add(a);
        this.buttons.add(b);
        this.buttons.add(x);
        this.buttons.add(y);
        this.buttons.add(left_bumper);
        this.buttons.add(right_bumper);
        this.buttons.add(right_stick_button);
        this.buttons.add(left_stick_button);
        this.buttons.add(right_trigger_digital);
        this.buttons.add(left_trigger_digital);
        this.buttons.add(dpad_left);
        this.buttons.add(dpad_right);
        this.buttons.add(dpad_up);
        this.buttons.add(dpad_down);
        this.buttons.add(start);
        this.buttons.add(back);
        this.buttons.add(guide);


    }

    public GamepadExpanded(Gamepad gp){
        this.gp = gp;

        this.buttons.add(a);
        this.buttons.add(b);
        this.buttons.add(x);
        this.buttons.add(y);
        this.buttons.add(left_bumper);
        this.buttons.add(right_bumper);
        this.buttons.add(right_stick_button);
        this.buttons.add(left_stick_button);
        this.buttons.add(right_trigger_digital);
        this.buttons.add(left_trigger_digital);
        this.buttons.add(dpad_left);
        this.buttons.add(dpad_right);
        this.buttons.add(dpad_up);
        this.buttons.add(dpad_down);
        this.buttons.add(start);
        this.buttons.add(back);
        this.buttons.add(guide);
    }


    public void update(){
        if (this.gp != null){
            update(this.gp);
        } else {
            throw new RuntimeException("Gamepad object gp not found on " +
                    this + "\nDid you initialize it or call it in the update method?");
        }
    }

    public void update(Gamepad gp){
        if (this.gp == null) {
            this.gp = gp;
        }

        left_stick_axis.update(gp.left_stick_x, gp.left_stick_y);
        right_stick_axis.update(gp.right_stick_x, gp.right_stick_y);

        a.updateButtonValue(gp.a);
        b.updateButtonValue(gp.b);
        y.updateButtonValue(gp.y);
        x.updateButtonValue(gp.x);
        dpad_up.updateButtonValue(gp.dpad_up);
        dpad_down.updateButtonValue(gp.dpad_down);
        dpad_left.updateButtonValue(gp.dpad_left);
        dpad_right.updateButtonValue(gp.dpad_right);
        start.updateButtonValue(gp.start);
        guide.updateButtonValue(gp.guide);
        back.updateButtonValue(gp.back);
        left_bumper.updateButtonValue(gp.left_bumper);
        right_bumper.updateButtonValue(gp.right_bumper);
        right_stick_button.updateButtonValue(gp.right_stick_button);
        left_stick_button.updateButtonValue(gp.left_stick_button);
        left_trigger_digital.updateButtonValue(gp.left_trigger > 0);
        right_trigger_digital.updateButtonValue(gp.right_trigger > 0);

        right_trigger_analog.update(gp.right_trigger);
        left_trigger_analog.update(gp.left_trigger);



    }



}
