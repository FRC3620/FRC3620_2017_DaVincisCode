package org.usfirst.frc3620.vision;
import java.io.*;
import java.math.BigInteger;
import java.net.*;

import org.slf4j.Logger;
import org.usfirst.frc3620.logger.EventLogging;
import org.usfirst.frc3620.logger.EventLogging.Level;

import com.google.gson.Gson;

public class UDPReceiver extends Thread {
	Logger logger = EventLogging.getLogger(getClass(), Level.INFO);

	public static String lastDataReceived = "";
	public static VisionData visionData;
	Gson gson = new Gson();
	
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    public UDPReceiver() throws IOException {
    this("udpReciever");
    }
 
    public UDPReceiver(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(3620);
    }
    
    public void run() {
    	logger.info("thread start");
    	byte[] buf = new byte[256];
    	DatagramPacket packet = new DatagramPacket(buf, buf.length);
    	InetAddress lastSender  = null;
        while (moreQuotes) {
            try {
 
                // receive request
            	packet.setLength(buf.length);
                socket.receive(packet);
                InetAddress sender = packet.getAddress();
                if (lastSender == null || ! lastSender.equals(sender)) {
                	logger.info ("Kangaru is at {}", sender);
                }
                lastSender = sender;
                byte[] data = packet.getData();
                lastDataReceived = new String(data, 0, packet.getLength());
                //SmartDashboard.putString("last Data Received", lastDataReceived);
               // System.out.println ("'" + lastDataReceived + "'");
                // figure out response
                visionData = gson.fromJson(lastDataReceived, VisionData.class);
                visionData.whenRecieved = System.currentTimeMillis();
        		//System.out.println(visionData);
            } catch (IOException e) {
                e.printStackTrace();
        moreQuotes = false;
            }
        }
        socket.close();
    }
    
    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }
 
}