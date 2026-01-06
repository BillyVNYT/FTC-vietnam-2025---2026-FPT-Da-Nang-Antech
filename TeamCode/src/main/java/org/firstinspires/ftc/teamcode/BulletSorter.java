package org.firstinspires.ftc.teamcode;
import com.bylazar.telemetry.TelemetryManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulletSorter {
    public enum BallColor {
        GREEN,
        PURPLE,
        EMPTY
    }
    /**
     * @param currentLoad   Array of 3 strings (e.g., {"G", "P", "E"})
     * @param targetPattern Array of 3 strings (e.g., {"P", "G", "P"})
     * @return int The number of spins needed (0, 1, or 2).
     */
    public static int getBestSpin(BallColor[] currentLoad, BallColor[] targetPattern, TelemetryManager telemetry) {
        int bestSpin = 0;
        int maxMatches = -1;

        // Try all 3 possible rotation states
        for (int spins = 0; spins < 3; spins++) {
            // 1. COMPACT: Create a list of the actual firing sequence, ignoring "Empty" slot
            List<BallColor> firingStream = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                // (i + spins) % 3 simulates the rotation index
                BallColor bullet = currentLoad[(i + spins) % 3];
                if (bullet != BallColor.EMPTY) firingStream.add(bullet);
            }

            // 2. MATCH: Compare the firing stream against the target pattern
            int currentMatches = 0;
            int checkLimit = Math.min(firingStream.size(), targetPattern.length);

            for (int k = 0; k < checkLimit; k++) {
                if (firingStream.get(k).equals(targetPattern[k])) {
                    currentMatches++;
                }
            }

            // 3. EVALUATE: Is this result better?
            if (currentMatches > maxMatches) {
                maxMatches = currentMatches;
                bestSpin = spins;

            }
        }

        telemetry.addData("Intake:", Arrays.toString(currentLoad));
        telemetry.addData("Pattern:", Arrays.toString(targetPattern));
        telemetry.addData("Best Spin:", bestSpin);
        return bestSpin;
    }
}
