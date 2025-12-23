package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShooterLogic {
    private ElapsedTime timer = new ElapsedTime();
    DcMotor MShooter;
    Servo SLift;
    private enum State {
        IDLE,
        SORT,
        LAUNCH,
        RESET
    }
    State state = State.IDLE;
    double remainingShot = 0;

    private void init(HardwareMap hmap) {
      MShooter = hmap.get(DcMotor.class, "shooter");
      // tune shooter code
    }

    private void update() {
        switch (state) {
            case IDLE:
                if(remainingShot > 0) {
                    MShooter.setPower(1);
                    timer.reset();
                    state = State.SORT;
                }
                break;
            case SORT:

                break;
            case LAUNCH:
                break;
            case RESET:
                break;
        }
    }

}
