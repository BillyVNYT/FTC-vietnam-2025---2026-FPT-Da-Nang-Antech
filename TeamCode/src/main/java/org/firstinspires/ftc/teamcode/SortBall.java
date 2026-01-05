package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;

/*
* NOTING:
*
*
* */
public class SortBall {
    Servo SortBall;
    boolean slot3isfull=false;
    boolean slot2isfull=false;
    boolean slot1isfull=false;
    DcMotor PushBall;
    AnalogInput analog0;
    public char[] BallColor = new char[3];

    double volt = analog0.getVoltage();    // giá trị voltage 0–3.3V
    double vmax = analog0.getMaxVoltage();   // thường 3.3V
    int raw1 = (int) ((analog0.getVoltage() / analog0.getMaxVoltage()) * 4095);
    int raw2 = (int) ((analog0.getVoltage() / analog0.getMaxVoltage()) * 4095);
    int convertedApriltagID =36;//lime light docapril tag xong chuyen thanh ma

    final double Slot1pos=0.5;
    final double Slot2pos=0.75;

    final int purple=3669;//mau tim,sua lai sau
    final int green=3636;//mau xanh,sua lai sau
    ColorSensor slot1;
    ColorSensor slot2;
    ColorSensor slot3;
    private AutoTrackStartMatch autoTrackStartMatch;
    public SortBall(HardwareMap hardwareMap){
        autoTrackStartMatch = new AutoTrackStartMatch(hardwareMap);
        SortBall = hardwareMap.get(Servo.class, "s3");
        analog0 = hardwareMap.get(AnalogInput.class, "analog0");
        PushBall=hardwareMap.get(DcMotor.class,"m36");
        SortBall.setPosition(0);
//        obeliskData = new ObeliskData();
    }
    public char CheckColor(int color){
        if(color==purple)
            //neu la mau tim purple
            return 'p';
        else if(color==green)
             //neu la mau xanh green
            return 'g';
        return '0';
    }
    public void checkSlot1(int raw){
        if(CheckColor(raw)!='0'){
            if(CheckColor(raw)=='p'){
                BallColor[0]='p';
                slot1isfull=true;
            }
            if(CheckColor(raw)=='g'){
                BallColor[0]='g';
                slot1isfull=true;
            }

        }
        slot1isfull=false;
    }
    public void checkSlot2(int raw){
        if(CheckColor(raw)!='0'){
            if(CheckColor(raw)=='p'){
                BallColor[1]='p';
                slot2isfull=true;
            }
            if(CheckColor(raw)=='g'){
                BallColor[1]='g';
                slot2isfull=true;
            }
        }
        slot2isfull=false;
    }
    public void checkSlot3(int raw){
        if(CheckColor(raw)!='0'){
            if(CheckColor(raw)=='p'){
                BallColor[2]='p';
                slot3isfull=true;
            }
            if(CheckColor(raw)=='g'){
                BallColor[2]='g';
                slot3isfull=true;
            }
        }
        slot3isfull=false;
    }
    public void SetNeededPosition(){
        while(slot1isfull&&slot2isfull&&slot3isfull){//bo sung sau, ktra xem da full 3 bong chx
            if(convertedApriltagID == 36) {//de dai so vi ko nho(xu li trong truong hop la 'g','p','p')
                for (int i = 0; i < 3; i++) {//                 .

                    if (BallColor[i] == 'g') {
                        PushBall.setPower(1);//bat dau day bong len,luu y chinh lai huong motor sau
                    }
                    SortBall.setPosition(0.5);//quay toi o tiep theo, chinh lai pos sau
                }
            }else if (convertedApriltagID==84){//de dai so vi ko nho(xu li trong truong hop la 'p','p','g')
                if(BallColor[0]=='p'&&BallColor[1]=='p') {//     .
                    SortBall.setPosition(0.5);//quay toi slot 1
                    PushBall.setPower(1);//bat dau day bong len + xoay sorter qua trai
                } else if (BallColor[1]=='p'&&BallColor[2]=='p'){
                    SortBall.setPosition(0.75);//quay toi slot 2
                    PushBall.setPower(1);//bat dau day bong len + xoay sorter qua trai
                } else if (BallColor[2]=='p'&&BallColor[0]=='p') {
                    SortBall.setPosition(1);//quay toi slot 3
                    PushBall.setPower(1);//bat dau day bong len

                }
            } else if (convertedApriltagID==-48) {//de dai so vi ko nho(xu li trong truong hop la 'p','g','p')
                if(BallColor[0]=='p'&&BallColor[2]=='p') {//          .
                    SortBall.setPosition(0.5);//quay toi slot 1
                    PushBall.setPower(1);//bat dau day bong len + xoay sorter qua trai
                }
                else if(BallColor[1]=='p'&&BallColor[0]=='p'){
                    SortBall.setPosition(0.75);//quay toi slot 2
                    PushBall.setPower(1);//bat dau day bong len + xoay sorter qua trai
                }
                else if(BallColor[2]=='p'&&BallColor[1]=='p'){
                    SortBall.setPosition(1);//quay toi slot 3
                    PushBall.setPower(1);//bat dau day bong len + xoay sorter qua trai
                }
            }
        }
    }
//    public boolean checkBall(ColorSensor colorSensor){
////        if(colorSensor.blue() > colorSensor.red()){
////            return 0;
////        } else {
////            return 1;
////        }
//        return colorSensor.blue() > colorSensor.red();
//    }
}
