// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Histogram {
	private final static int LEVEL = 256;			// default
	private final static double MAX_PIXEL = 1000;	// default
	private final static double MIN_PIXEL = -1000;	// default
	
	private int _level;			// default = 256
	private double _maxPixel;	// default = 1000
	private double _minPixel;	// default = -1000

	double[] _histLevel;		// the value of bins
	double[] _histBin;			// the count of elements in each bin
	
	//TODO: remove comments
	//List<Integer> _histIndex;	//indicate which bin the corresponding element is in
	//List<Double[]> _binElements[];	//indicate all the indexes of elements in the corresponding bin
	//IndexVector bin_elements[HISTOGRAM_LEVEL];	//indicate all the indexes of elements in the corresponding bin
	
	/*
	public Histogram() {
		this(LEVEL); //default
	}*/

	public Histogram() {
		this(LEVEL); //default
	}
	public Histogram(int level) {
		this(level, MAX_PIXEL, MIN_PIXEL);
	}
	public Histogram(int level, double max, double min) {
		this._level = level; 
		this._maxPixel = max; 
		this._minPixel = min; 
		
		_histLevel = new double[level];
		_histBin = new double[level];
		Arrays.fill(_histLevel, 0); // TODO: remove this cause in java all arrays are init with 0...
		Arrays.fill(_histBin, 0);
		//_histIndex = new ArrayList<>();
	}
	
	public void setHistogram(double[] values) {
		// ununiformed
		double interval=(_maxPixel - _minPixel) / _level;
		for(int i=0; i<values.length; i++) {
			//uniformed and ununiformed
			int index = (int) ((values[i] - _minPixel) / interval);
			
			if(index >= _level) index = _level-1;
			else if(index < 0) index = 0;

			_histBin[index]++;
		}
		//normalization	
		_histLevel[0] = _minPixel;
		_histBin[0] = _histBin[0]/values.length;
		for(int i=1; i<_level; i++) {
			_histLevel[i] = _histLevel[i-1] + interval;
			_histBin[i] = _histBin[i]/values.length;
		}
	}
	
	public double[] getHistogram() {
		return _histBin;
	}
	
	public double compareHistogram(Histogram histogram) {
		return Histogram.compare(this, histogram);
	}
	public static double compare(Histogram h1, Histogram h2) {
		//TODO: 
		return -1;
	}
	
	public void printHistogram(File file) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		if(file.isFile()) {
			FileWriter writer = new FileWriter(file);
			for (int i=0; i<LEVEL; i++) {
				writer.write(_histLevel[i] + ":" + _histBin[i] + System.getProperty("line.separator"));
			}
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();
		}
	}
	
}
