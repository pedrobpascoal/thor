// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.common;

import java.io.IOException;
import java.util.Map;

import thor.Model;

public abstract class ShapeDescriptor {
	protected Model _model;
	
	public ShapeDescriptor(Model model) {
		_model = model;
	}
	
	public void setup(Map<ShapeDescriptorSetting, String> settings) {
		for(ShapeDescriptorSetting setting : settings.keySet()) {
			System.err.println("Setting " + setting + " not recognized for the " + this.getClass().toString());
		}
	}
	
	public abstract void extract();
	public abstract float compare(FeatureVector feature);

	public abstract double[] getHistogram();
    
	public abstract void writeToFile(String filename) throws IOException;
	public abstract String toString();
}
