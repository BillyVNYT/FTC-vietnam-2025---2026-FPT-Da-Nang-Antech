package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Shooter extends LinearOpMode {
    public DcMotorEx Mshooter;
    double maxTPS = Mshooter.getMotorType().getAchieveableMaxTicksPerSecond();
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        Mshooter = hardwareMap.get(DcMotorEx.class, "0");
        Mshooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Mshooter.setDirection(DcMotorSimple.Direction.REVERSE);
        Mshooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(0.0002, 0, 0.00001, 1/maxTPS));

        timer.reset();
        while (opModeIsActive()){

        }
    }
    public void setMshooter(int rpm){
        double velocity = rpm*28/60;
        Mshooter.setVelocity(velocity);
    }
}
