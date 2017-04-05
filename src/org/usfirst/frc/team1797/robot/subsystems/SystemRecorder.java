package org.usfirst.frc.team1797.robot.subsystems;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
 *
 */
public class SystemRecorder {

	public final static String DEFAULT_PATH = "/home/lvuser/";
	public final static String BASE_FILE_NAME = "auto";
	
	private class RecordableSystem {
		private Recordable rec;
		private int numDatas;
		private RecordableSystem(Recordable rec) { this.rec = rec; numDatas = rec.getLength(); }
	}

	private RecordableSystem[] list;
	private LinkedList<double[]> data;

	public SystemRecorder(Recordable...recordables) {
		RecordableSystem[] tmp = new RecordableSystem[recordables.length];
		for (int i = 0; i < tmp.length; ++i) tmp[i] = new RecordableSystem(recordables[i]);
		list = tmp;
		data = new LinkedList<double[]>();
	}
	
	public double[] collectData() {
		double[] buf = new double[]{};
		for (RecordableSystem r : list) {
			double[] newData = r.rec.getData();
			double[] tmp = new double[buf.length + newData.length];
			System.arraycopy(buf, 0, tmp, 0, buf.length);
			int old_length = buf.length;
			buf = tmp;
			System.arraycopy(newData, 0, buf, old_length, newData.length);
		}
		data.add(buf);
		return buf;
	}
	
	public void putData(double[] vals) {
		int start = 0;
		for (RecordableSystem r : list) {
			double[] data = new double[r.numDatas];
			System.arraycopy(vals, start, data, 0, r.numDatas);
			r.rec.putData(data);
			start += r.numDatas;
		}
	}

	public boolean writeData() {
		File toWrite = new File(DEFAULT_PATH + BASE_FILE_NAME + "-1" + ".csv");
		for (int i = 1; toWrite.exists(); ++i) {
			String tmp = DEFAULT_PATH + BASE_FILE_NAME + "-" + i;
			toWrite = new File(tmp+".csv");
		}
		try {
			toWrite.createNewFile();
			FileOutputStream fos = new FileOutputStream(toWrite);
			String out = "";
			for (double[] dat : data) {
				String[] textElements = new String[dat.length];
				for (int i = 0; i < dat.length; ++i) textElements[i] = String.valueOf(dat[i]);
				out += String.join(",", textElements) + "\n";
			}
			fos.write(out.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			data = new LinkedList<double[]>();
			return false;
		}
		data = new LinkedList<double[]>();
		return true;
	}
}
