package org.usfirst.frc3620.FRC36202017DaVincisCode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.can.CANJNI;

public class CANDeviceFinder {
    ArrayList<String> deviceList = new ArrayList<String>();

    private boolean pdpIsPresent = false;
    private Set<Integer> srxs = new TreeSet<>();
    private Set<Integer> pcms = new TreeSet<>();

    public CANDeviceFinder() {
        super();
        find();
    }

    public boolean isPDPPresent() {
        return pdpIsPresent;
    }

    public boolean isSRXPresent(CANTalon srx) {
        return srxs.contains(srx.getDeviceID());
    }

    public boolean isPCMPresent(int i) {
        return pcms.contains(i);
    }

    /**
     * 
     * @return ArrayList of strings holding the names of devices we've found.
     */
    public List<String> getDeviceList() {
        return deviceList;
    }

    /**
     * polls for received framing to determine if a device is present. This is
     * meant to be used once initially (and not periodically) since this steals
     * cached messages from the robot API.
     */
    public void find() {
        deviceList.clear();
        pdpIsPresent = false;
        srxs.clear();
        pcms.clear();

        /* get timestamp0 for each device */
        long pdp0_timeStamp0; // only look for PDP at '0'
        long[] pcm_timeStamp0 = new long[63];
        long[] srx_timeStamp0 = new long[63];

        pdp0_timeStamp0 = checkMessage(0x08041400, 0);
        for (int i = 0; i < 63; ++i) {
            pcm_timeStamp0[i] = checkMessage(0x09041400, i);
            srx_timeStamp0[i] = checkMessage(0x02041400, i);
        }

        /* wait 200ms */
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* get timestamp1 for each device */
        long pdp0_timeStamp1; // only look for PDP at '0'
        long[] pcm_timeStamp1 = new long[63];
        long[] srx_timeStamp1 = new long[63];

        pdp0_timeStamp1 = checkMessage(0x08041400, 0);
        for (int i = 0; i < 63; ++i) {
            pcm_timeStamp1[i] = checkMessage(0x09041400, i);
            srx_timeStamp1[i] = checkMessage(0x02041400, i);
        }

        /*
         * compare, if timestamp0 is good and timestamp1 is good, and they are
         * different, device is healthy
         */
        if (pdp0_timeStamp0 >= 0 && pdp0_timeStamp1 >= 0
                && pdp0_timeStamp0 != pdp0_timeStamp1) {
            deviceList.add("PDP 0");
            pdpIsPresent = true;
        }

        for (int i = 0; i < 63; ++i) {
            if (pcm_timeStamp0[i] >= 0 && pcm_timeStamp1[i] >= 0
                    && pcm_timeStamp0[i] != pcm_timeStamp1[i]) {
                deviceList.add("PCM " + i);
                pcms.add(i);
            }
            if (srx_timeStamp0[i] >= 0 && srx_timeStamp1[i] >= 0
                    && srx_timeStamp0[i] != srx_timeStamp1[i]) {
                deviceList.add("SRX " + i);
                srxs.add(i);
            }
        }
    }

    private ByteBuffer targetID = ByteBuffer.allocateDirect(4);
    private ByteBuffer timeStamp = ByteBuffer.allocateDirect(4);

    /** helper routine to get last received message for a given ID */
    private long checkMessage(int fullId, int deviceID) {
        try {
            targetID.clear();
            targetID.order(ByteOrder.LITTLE_ENDIAN);
            targetID.asIntBuffer().put(0, fullId | deviceID);

            timeStamp.clear();
            timeStamp.order(ByteOrder.LITTLE_ENDIAN);
            timeStamp.asIntBuffer().put(0, 0x00000000);

            CANJNI.FRCNetCommCANSessionMuxReceiveMessage(
                    targetID.asIntBuffer(), 0x1fffffff, timeStamp);

            long retval = timeStamp.getInt();
            retval &= 0xFFFFFFFF; /* undo sign-extension */
            return retval;
        } catch (Exception e) {
            return -1;
        }
    }
}