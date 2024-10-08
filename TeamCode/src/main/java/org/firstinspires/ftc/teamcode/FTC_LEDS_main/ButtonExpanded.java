package org.firstinspires.ftc.teamcode.FTC_LEDS_main;

public class ButtonExpanded {
    private boolean prevValue = false;
    private boolean toggled  = false;

    public void updateButtonValue(boolean newValue){
        if (newValue && isChanged(newValue)) {
            toggled = !toggled;
        }

        prevValue = newValue;
    }

    public boolean hasChangedToTrue(boolean buttonValue){
        return buttonValue && isChanged(buttonValue);
    }
    public boolean hasChangedToFalse(boolean buttonValue){
        return !buttonValue && isChanged(buttonValue);
    }
    public boolean isChanged(boolean buttonValue){
        return buttonValue != prevValue;
    }
    public boolean isToggled(){
        return toggled;
    }
}
