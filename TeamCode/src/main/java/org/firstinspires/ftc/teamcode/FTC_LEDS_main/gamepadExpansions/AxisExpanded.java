package org.firstinspires.ftc.teamcode.FTC_LEDS_main.gamepadExpansions;

public class AxisExpanded {
    public float x;
    public float y;
    public AnalogExpanded magnitude = new AnalogExpanded();
    public double angle;



    public void update(float x, float y){
        this.magnitude.update((float) Math.hypot(x, y));
        this.angle = Math.atan2(y,x);

        float m = magnitude.getValue();
        this.x = (float) (m * Math.cos(angle));
        this.y = (float) (m * Math.sin(angle));
    }

    public void setFilter(AnalogExpanded.MODE m){
        magnitude.setMode(m);
    }


}
