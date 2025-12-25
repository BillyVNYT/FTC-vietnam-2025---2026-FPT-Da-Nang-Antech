package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Shooter extends LinearOpMode {
    public DcMotorEx Mshooter1;
    public DcMotorEx Mshooter2;
    public Servo Sdegree;
    double P = 15.1;
    double F = 0.0112;
    double[] stepsServo = {0.8492, 0.6389, 0};
    int stepIdx = 0;
    public ElapsedTime timer = new ElapsedTime();
    int rpm =2000;
    private LimelightHardware limelightHardware = new LimelightHardware(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        Mshooter1 = hardwareMap.get(DcMotorEx.class, "0");
        Mshooter2 = hardwareMap.get(DcMotorEx.class, "1");
        Mshooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Mshooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        Mshooter1.setDirection(DcMotorSimple.Direction.REVERSE);
        Mshooter2.setDirection(DcMotorSimple.Direction.REVERSE);

        Sdegree = hardwareMap.get(Servo.class, "s0");

        PIDFCoefficients pidf = new PIDFCoefficients(P, 0, 0, F);
        Mshooter1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);

        waitForStart();
        Sdegree.setPosition(0.8492);
        timer.reset();
        while (opModeIsActive()){
//            if(gamepad1.dpadUpWasPressed()) {
//                stepIdx++;
//                Sdegree.setPosition(stepsServo[stepIdx]);
//            } else if(gamepad1.dpadDownWasPressed()) {
//                stepIdx--;
//                Sdegree.setPosition(stepsServo[stepIdx]);
//            }
            if(gamepad1.dpadUpWasPressed()) {
                rpm += 100;
            } else if(gamepad1.dpadDownWasPressed()) {
                rpm -= 100;
            }
            if(timer.milliseconds() > 500 && gamepad1.a){
                Sdegree.setPosition(Sdegree.getPosition()+0.01);
                timer.reset();
            } else if (timer.milliseconds() > 500 && gamepad1.b){
                Sdegree.setPosition(Sdegree.getPosition()-0.01);
                timer.reset();
            }
            setMshooter(rpm);
            telemetry.addData("s0", Sdegree.getPosition());
            telemetry.addData("dis", limelightHardware.getDistance());
            telemetry.update();
        }
    }

    public void setMshooter(int rpm){
        double velocity = rpm*28/60;
        Mshooter1.setVelocity(velocity);
        Mshooter2.setVelocity(velocity);

        double curVelocity = Mshooter1.getVelocity();
        double error = velocity - curVelocity;

        telemetry.addData("curTargetVelocity", velocity);
        telemetry.addData("curVelocity", curVelocity);
        telemetry.addData("error", error);
        telemetry.addLine("---------------------------");
        telemetry.addData("F", "%.4f (dpad L/R)", F);
        telemetry.addData("P", "%.4f (dpad U/D)", P);
        telemetry.addData("Step size", "%.4f (B button)", stepsServo[stepIdx]);
    }
}
