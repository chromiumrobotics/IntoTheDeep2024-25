package org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions;

public class AnalogExpanded {
    private float value = 0.0f;

    private MODE mode = MODE.NORMAL;
    private double factor = 2.7183;

    public enum MODE{
        NORMAL,
        INSENSITIVE,
        BANG,
        SENSITIVE;
    }

    /**
     * Sets the different modes an analog signal may be interpreted as.
     * @param mode AnalogExpanded.MODE to set.
     * <p>NORMAL makes no difference</p>
     *             <p>SENSITIVE amplifies according to tanh(factor*x)</p>
     *             <p>INSENSITIVE amplifies according to arctanh(x)/factor</p>
     *             <p>BANG outputs -1 <b><u>or</u></b> 0 <b><u>or</u></b> 1</p>
     */
    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public void update(float newValue){
        if (newValue < -1) {
            newValue = -1;
        } else if (newValue > 1) {
            newValue = 1;
        }

        if (mode == MODE.NORMAL){
            value = newValue;
        } else if (mode == MODE.SENSITIVE) {
            value = (float) Math.tanh(factor*newValue);
        } else if (mode == MODE.INSENSITIVE) {
            //this is arctanh(newValue).
            //idk why it casts twice but i get an error if i dont
            value = (float) ((float) (0.5 * Math.log((1 + newValue) / (1 - newValue))) / (factor));
        } else if (mode == MODE.BANG) {
            value = Math.signum(newValue);
        }
    }

    /**
     * @param f factor to set for Sensitivity/Insensitivity controls. the minimum/default value is PI, 3.14. graph tanh(pi*x) to see why (+-1, +-1)
     */
    public void setFactor(double f){
        f = (f < 0? 1 : f); //ensure f > 0 (force 1)
        this.factor = f;

    }



    public float getValue() {

        return value;
    }
}
