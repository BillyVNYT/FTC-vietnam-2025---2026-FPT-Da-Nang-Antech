package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class mainRed extends LinearOpMode {
    private LimelightHardware limelightHardware = new LimelightHardware(hardwareMap);
    private DcMotor motorTurnOutTake;

    @Override
    public void runOpMode() throws InterruptedException {
        motorTurnOutTake = hardwareMap.get(DcMotor.class, "0");
    }
    public void holdApriltag(){
        double error = limelightHardware.getAprilTagData().x;
        if(error > 5){
            motorTurnOutTake.setPower(error*0.2);
        } else if(error < 5){
            motorTurnOutTake.setPower(-(error*0.2));
        } else {
            motorTurnOutTake.setPower(0);
        }
    }
}
