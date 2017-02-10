package org.usfirst.frc3620.vision;

import java.util.Date;

public class VisionData {
		double height, width, x, y, rt;
		int count;
		long whenRecieved;

		public double getImageWidth() {
			return width;
		}

		public long getWhenRecieved() {
			return whenRecieved;
		}

		public double getX() {
			return x;
		}
		
		public double getY() {
			return y;
		}

		@Override
		public String toString() {
			return "VisionData [height=" + height + ", width=" + width + ", x="
					+ x + ", y=" + y + ", rt=" + rt + ", count=" + count
					+ ", whenRecieved=" + whenRecieved + "]";
		}

		public int getCount() {
			if (getAge() > 500) {
				return 0;
			}
			return count;
			
			
		}
		
		public double getAge(){
			return System.currentTimeMillis() - whenRecieved;
		}
	}