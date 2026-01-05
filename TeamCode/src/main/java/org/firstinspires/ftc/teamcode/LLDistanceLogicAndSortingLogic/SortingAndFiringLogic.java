package org.firstinspires.ftc.teamcode.LLDistanceLogicAndSortingLogic;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import java.util.Arrays;
import java.util.Random;
/*
* Noting!!!:
* M=motor
*
* S=servo
*
* */
@TeleOp
public class SortingAndFiringLogic extends LinearOpMode{
    AnalogInput analog0;
    SoSanh logic = new SoSanh(telemetry);
    public int[] mang = new int[3];
    Random random = new Random();

//    public char CheckColor(){
//        if(red==36&&green==36&&blue==36)
//            //neu la mau tim purple
//            return 'p';
//        else if(red==69&&green==69&&blue==69)
//             //neu la mau xanh green
//            return 'g';
//
//
//    }
    @Override
    public void runOpMode() throws InterruptedException {
        for (int i = 0; i < mang.length; i++) {
            mang[i] = random.nextInt(2);
        }
        analog0 = hardwareMap.get(AnalogInput.class, "analog0");
        DcMotor Mshooter = hardwareMap.get(DcMotor.class, "shooter");

        waitForStart();

        while(opModeIsActive()) {
            double volt = analog0.getVoltage();    // giá trị voltage 0–3.3V
            double vmax = analog0.getMaxVoltage();   // thường 3.3V
            int raw = (int) ((analog0.getVoltage() / analog0.getMaxVoltage()) * 4095);
            String Array = Arrays.toString(mang);
            String cleanString;
            if (Array.startsWith("[") && Array.endsWith("]")) {
                // Lấy chuỗi con từ vị trí 1 đến vị trí length - 1
                cleanString = Array.substring(1, Array.length() - 1);
            } else {
                // Nếu không có ngoặc thì dùng chuỗi gốc, nhưng cẩn thận lỗi
                cleanString = Array;
            }
            String[] parts = cleanString.split(",");
            int a = Integer.parseInt(parts[0].trim());
            int b = Integer.parseInt(parts[1].trim());
            int c = Integer.parseInt(parts[2].trim());
            telemetry.addData("Type: ", Arrays.toString(mang));
            telemetry.addData("Analog 0 Voltage", volt);
            telemetry.addData("Analog 0 Vmax", vmax);
            telemetry.addData("Analog 0 Raw", raw);
            telemetry.addData("A", a);
            telemetry.addData("B", b);
            telemetry.addData("C", c);

            logic.processAllVariables(a, b, c, raw);

            telemetry.addData("Status A", logic.statusA);
            telemetry.addData("Status B", logic.statusB);
            telemetry.addData("Status C", logic.statusC);
            telemetry.update();

        }

    }
}
