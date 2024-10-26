package org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions;

public class ButtonExpanded {
    private boolean value = false;
    private boolean prevValue = false;
    private boolean toggled  = false;

    public boolean isPressed(){
        return value;
    }

    public void updateButtonValue(boolean newValue){
        value = newValue;
        if (newValue && isChanged(newValue)) {
            toggled = !toggled;
        }

        prevValue = newValue;
    }


    public ButtonExpanded(){

    }
    public boolean hasJustBeenPressed(boolean buttonValue){
        return hasChangedToTrue(buttonValue);
    }

    public boolean hasJustBeenReleased(boolean buttonValue){
        return hasChangedToFalse(buttonValue);
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
