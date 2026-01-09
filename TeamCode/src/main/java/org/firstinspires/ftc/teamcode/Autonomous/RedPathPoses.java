package org.firstinspires.ftc.teamcode.Autonomous;

import com.pedropathing.geometry.Pose;

public class RedPathPoses {

    public static Pose topZonePose = new Pose(78.82144335048448, 79.94421324671876);

    public static Pose pickupMidPose = new Pose(133.33410370324102, 54.67825604909545);

    public static Pose pickupGatePose = new Pose(132.739, 60.057);

    public static Pose pickupTopPose = new Pose(125.05071090047393, 83.943);

    public static Pose pickupLowPose = new Pose(135.4691943127962, 36.0);

    public static Pose lowZonePose = new Pose(84.45497630331753, 8.530805687203792);

    public static Pose leavePose = new Pose(122.67298578199052, 69.44075829383885);


    public static Pose[] topZoneToPickupMidPoses = {topZonePose, new Pose(85.308, 54.768), pickupMidPose};

    public static Pose[] pickupMidToTopZonePoses = {pickupMidPose, topZonePose};

    public static Pose[] topZoneToPickupGatePoses = {topZonePose, pickupGatePose};

    public static Pose[] pickupGateToTopZonePoses = {pickupGatePose, topZonePose};

    public static Pose[] topZoneToPickupTopPoses = {topZonePose, new Pose(101.82, 88.597), pickupTopPose};

    public static Pose[] pickupTopToTopZonePoses = {pickupTopPose, topZonePose};

    public static Pose[] topZoneToPickupLowPoses = {topZonePose, new Pose(80.701, 25.934), pickupLowPose};

    public static Pose[] pickupLowToLowZonePoses = {pickupLowPose, lowZonePose};

    public static Pose[] lowZoneToLeavePoses = {lowZonePose, leavePose};
}
