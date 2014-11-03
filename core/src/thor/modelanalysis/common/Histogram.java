// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import java.util.Arrays;
import java.util.List;

public class Histogram {
	private final static int LEVEL = 256;			// default
	private final static double MAX_PIXEL = 1000;	// default
	private final static double MIN_PIXEL = -1000;	// default
	
	private int _level;			// default = 256
	private double _maxPixel;	// default = 1000
	private double _minPixel;	// default = -1000

	double[] _histLevel;		//the value of bins
	double[] _histBin;			//the count of elements in each bin
	//List<Integer> _histIndex;	//indicate which bin the corresponding element is in
	//List<Double[]> _binElements[];	//indicate all the indexes of elements in the corresponding bin
	//IndexVector bin_elements[HISTOGRAM_LEVEL];	//indicate all the indexes of elements in the corresponding bin
	
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
		Arrays.fill(_histLevel, 0);
		Arrays.fill(_histBin, 0);
		//_histIndex = new ArrayList<>();
	}
	
	public void setHistogram(List<Double> featureVector) {
		
		double interval=(_maxPixel - _minPixel) / _level;
		
		for(int i=0; i<featureVector.size(); i++) {
			//uniformed and ununiformed
			int index = (int) ((featureVector.get(i) - _minPixel) / interval);
			
			if(index >= _level)
				index = _level-1;
			else if(index < 0)
				index = 0;

			_histBin[index]++;
		}
		//normalization	
		_histLevel[0] = _minPixel;
		_histBin[0] = _histBin[0]/featureVector.size();
		for(int i=1; i<_level; i++) {
			_histLevel[i] = _histLevel[i-1] + interval;
			_histBin[i] = _histBin[i]/featureVector.size();
		}
	}
	
	public double[] getHistogram() {
		return _histBin;
	}
	
}
