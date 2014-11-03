// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.cah;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import thor.Model;
import thor.graphics.Point3D;
import thor.model.geoset.Face;
import thor.model.geoset.Mesh;
import thor.modelanalysis.common.FeatureDescriptor;
import thor.modelanalysis.common.FeatureVector;
import thor.modelanalysis.common.Histogram;
import thor.modelanalysis.utils.Normalize;

public class CordAngleHistograms implements FeatureDescriptor {
	
	private final int LEVEL = 256;			// default
	private final double MAX_PIXEL = 1000;	// default
	private final double MIN_PIXEL = -1000;	// default
	
	private Histogram _lenghtHistogram;
	private Histogram _angleXYHistogram;
	private Histogram _angleXZHistogram;
	private Histogram _angleYZHistogram;
	
	public CordAngleHistograms() {
	}

	public void extract(Model model) {

		List<Double> lenght = new ArrayList<Double>();
		List<Double> angleXY = new ArrayList<Double>();
		List<Double> angleXZ = new ArrayList<Double>();
		List<Double> angleYZ = new ArrayList<Double>();
		
		// First: normalizes the model
		Normalize.translation(model);
		Normalize.scale(model);
		
		for (Mesh mesh : model.getMeshes()) {
			for (Face face : mesh.getFaces()) {
				
				// both barycenter and face-center must be normalized
				// the normalized barycenter is 0.0.0 so if correct can be removed from the input (using the original barycenter may be causing the problem)
				// but are the face-center also normalized?!
				//Cord cord = new Cord(mesh.getBarycenter(), mesh.getFaceCenter(face));
				Cord cord = new Cord(new Point3D.Double(), mesh.getFaceCenter(face));
				//_cordList.add(cord);
				lenght.add(cord.Lenght);
				angleXY.add(cord.Angle_XY);
				angleXZ.add(cord.Angle_XZ);
				angleYZ.add(cord.Angle_YZ);
			}
		}
		
		double max = MIN_PIXEL;
		double min = MAX_PIXEL;
		for (int i=0; i < lenght.size(); i++) {
			if(lenght.get(i) > max) max=lenght.get(i);
			if(lenght.get(i) < min) min=lenght.get(i);
		}
		_lenghtHistogram = new Histogram(LEVEL, max, 0);
		_lenghtHistogram.setHistogram(lenght);
		
		_angleXYHistogram = new Histogram(LEVEL, Math.PI/2,-Math.PI/2);
		_angleXYHistogram.setHistogram(angleXY);
		
		_angleXZHistogram = new Histogram(LEVEL, Math.PI/2,-Math.PI/2);
		_angleXZHistogram.setHistogram(angleXZ);
		
		_angleYZHistogram = new Histogram(LEVEL, Math.PI/2,-Math.PI/2);
		_angleYZHistogram.setHistogram(angleYZ);
	}

	public void writeToFile(String filename) throws IOException {
		
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(filename);
		bw.newLine();
		bw.write("4"); //number of histograms
		bw.newLine();
		bw.write(String.valueOf(LEVEL));
		bw.newLine();
		
		for(double feature : _lenghtHistogram.getHistogram()) {
			bw.write(String.valueOf(feature));
			bw.newLine();
		}
		for(double feature : _angleXYHistogram.getHistogram()) {
			bw.write(String.valueOf(feature));
			bw.newLine();
		}
		for(double feature : _angleXZHistogram.getHistogram()) {
			bw.write(String.valueOf(feature));
			bw.newLine();
		}
		for(double feature : _angleYZHistogram.getHistogram()) {
			bw.write(String.valueOf(feature));
			bw.newLine();
		}
		bw.close();
		fw.close();
	}

	@Override
	public double[] getHistogram() {
		
		double[] histogram = new double[LEVEL*4];
		
		int i = 0;
		// foreach histogram 
		for(double feature : _lenghtHistogram.getHistogram()) {
			histogram[i++] = feature;
		}
		for(double feature : _angleXYHistogram.getHistogram()) {
			histogram[i++] = feature;
		}
		for(double feature : _angleXZHistogram.getHistogram()) {
			histogram[i++] = feature;
		}
		for(double feature : _angleYZHistogram.getHistogram()) {
			histogram[i++] = feature;
		}
		return histogram;
	}

	@Override
	public float compare(FeatureVector feature) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String printFeatureVector() {
		// TODO Auto-generated method stub
		return null;
	}
}