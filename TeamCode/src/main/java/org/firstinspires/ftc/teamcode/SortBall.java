
package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
//import org.firstinspires.ftc.teamcode.ObeliskData;


public class SortBall {

    // ===== Hardware =====
    private Servo sortServo;
    private DcMotor pushBall;
    private AnalogInput analog0;

    // ===== Constants =====
    private static final double SLOT1_POS = 0.5;
    private static final double SLOT2_POS = 0.75;
    private static final double SLOT3_POS = 1.0;

    private static final int PURPLE = 3669;
    private static final int GREEN  = 3636;

    private static final int TAG_GPP = 36;
    private static final int TAG_PPG = 84;
    private static final int TAG_PGP = -48;

    // ===== State =====
    private boolean[] slotFull = new boolean[3];
    private char[] ballColor = new char[3];

    private int convertedAprilTagID = 36;

    // ===== Constructor =====
    public SortBall(HardwareMap hardwareMap) {
        sortServo = hardwareMap.get(Servo.class, "s3");
        pushBall  = hardwareMap.get(DcMotor.class, "m36");
        analog0   = hardwareMap.get(AnalogInput.class, "analog0");

        sortServo.setPosition(0);
    }

    // ===== Color detection =====
    private char detectColor(int raw) {
        if (raw == PURPLE) return 'p';
        if (raw == GREEN)  return 'g';
        return '0';
    }

    // ===== Slot update =====
    public void updateSlot(int slotIndex, int raw) {
        char color = detectColor(raw);

        if (color != '0') {
            slotFull[slotIndex] = true;
            ballColor[slotIndex] = color;
        }
    }

    private boolean allSlotsFull() {
        return slotFull[0] && slotFull[1] && slotFull[2];
    }

    // ===== Servo decision =====
    public void setNeededPosition() {
        if (!allSlotsFull()) return;
        boolean moved = false;
        switch (convertedAprilTagID) {

            case TAG_GPP:
                if (ballColor[1]=='p' && ballColor[2]=='p') {
                    sortServo.setPosition(SLOT1_POS);
                    moved = true;
                }
                else if (ballColor[0]=='p' && ballColor[2]=='p') {
                    sortServo.setPosition(SLOT2_POS);
                    moved = true;
                }
                else if (ballColor[0]=='p' && ballColor[1]=='p') {
                    sortServo.setPosition(SLOT3_POS);
                    moved = true;
                }
                break;

            case TAG_PPG:
                if (ballColor[0] == 'p' && ballColor[1] == 'p') {
                    sortServo.setPosition(SLOT1_POS);
                    moved = true;
                }
                else if (ballColor[1]=='p'&&ballColor[2]=='p') {
                    sortServo.setPosition(SLOT2_POS);
                    moved = true;
                }
                else if (ballColor[2]=='p'&&ballColor[0]=='p') {
                    sortServo.setPosition(SLOT3_POS);
                    moved = true;
                }
                break;


            case TAG_PGP:
                if (ballColor[0] == 'p' && ballColor[2] == 'p') {
                    sortServo.setPosition(SLOT1_POS);
                    moved = true;
                }
                else if (ballColor[1]=='p'&&ballColor[0]=='p') {
                    sortServo.setPosition(SLOT2_POS);
                    moved = true;
                }
                else if (ballColor[2]=='p'&&ballColor[1]=='p') {
                    sortServo.setPosition(SLOT3_POS);
                    moved = true;
                }
                break;
            default:
                return;
        }

        if(moved){
            resetSlots();
        }
    }



    private double getSlotPosition(int slot) {
        switch (slot) {
            case 0: return SLOT1_POS;
            case 1: return SLOT2_POS;
            case 2: return SLOT3_POS;
        }
        return 0;
    }

    private void resetSlots() {
        for (int i = 0; i < 3; i++) {
            slotFull[i] = false;
            ballColor[i] = '0';
        }
    }
}


