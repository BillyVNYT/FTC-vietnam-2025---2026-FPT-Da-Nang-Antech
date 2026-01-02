package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Motor test")
public class MotorTest extends LinearOpMode {
    public DcMotor motor1;
    public DcMotor motor2;
    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.get(DcMotor.class, "0");
        motor2 = hardwareMap.get(DcMotor.class, "1");
        waitForStart();
        while (opModeIsActive()){
            motor1.setPower(1);
            motor2.setPower(1);
        }
    }
}
