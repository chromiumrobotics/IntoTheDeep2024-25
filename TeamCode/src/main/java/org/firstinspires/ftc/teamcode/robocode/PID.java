package org.firstinspires.ftc.teamcode.robocode;

public class PID {
    private double kp, ki, kd;
    private double setpoint;
    private double integral;
    private double previousError;

    public PID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.integral = 0;
        this.previousError = 0;
    }

    public void setPoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double calculate(double input) {
        double error = setpoint - input;
        integral += error;
        double derivative = error - previousError;
        previousError = error;
        return kp * error + ki * integral + kd * derivative;
    }
}