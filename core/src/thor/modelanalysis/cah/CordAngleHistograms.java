/*
 * This file is part of the ThOR project: http://3dorus.ist.utl.pt/tools/thor
 * ThOR is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * ThOR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * 
 * We kindly ask you to refer the any or one of the following publications in
 * any publication mentioning or employing ThOR:
 * 
 * Pedro B. Pascoal and Alfredo Ferreira. 8th Eurographics Workshop on 3D Object Retrieval,
 * Zurich, Switzerland, May 2-3, 2015
 * 
 * Copyright statement:
 * ====================
 * (c) 2013-2015 by Pedro B. Pascoal (pmbp@tecnico.ulisboa.pt)
 * http://3dorus.ist.utl.pt/tools/thor, https://web.ist.utl.pt/pmbp/projects/thor 
 * 
 * Updated: 2015.05.18
 */
package thor.modelanalysis.cah;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import thor.Model;
import thor.model.geoset.Face;
import thor.model.geoset.Mesh;
import thor.modelanalysis.common.FeatureDescriptor;
import thor.modelanalysis.common.Histogram;
import thor.modelanalysis.common.Normalize;

/**
 * Uses a histogram of <i>cords</i> as feature to describe and compare 3D shapes. 
 * A cord is defined as a ray segment which joins the barycenter of the mesh with a triangle center.<br />
 * <br />
 * More information can be found in: Paquet, E.; Rioux, M., <i>Nefertiti: a query by content software for three-dimensional models databases management</i>,
 * 3-D Digital Imaging and Modeling, 1997. Proceedings., International Conference on Recent Advances in , vol., no., pp.345,352, 12-15 May 1997
 * doi: 10.1109/IM.1997.603886
 * 
 * @author Pedro B. Pascoal, pmbp@tecnico.ulisboa.pt
 */
public class CordAngleHistograms extends FeatureDescriptor {
	
	private final int LEVEL = 256;			// default
	
	private Histogram _lenghtHistogram;
	private Histogram _angleXYHistogram;
	private Histogram _angleXZHistogram;
	private Histogram _angleYZHistogram;
	
	public CordAngleHistograms() {
	}

	public void extract(Model model) {
		
		if(model.getMeshes().size() < 1)
			return; // check if there exists at least one mesh in the model
		Mesh mesh = model.getMeshes().get(0); // only calc for the main mesh
		int cord_count = mesh.getFaces().size(); // will be the number of faces 

		double[] lenght = new double[cord_count];
		double[] angleXY = new double[cord_count];
		double[] angleXZ = new double[cord_count];
		double[] angleYZ = new double[cord_count];
		
		// First step: normalizes the model
		Normalize.translation(model);
		Normalize.scale(model);
		
		int i=0;
		for (Face face : mesh.getFaces()) {
			
			// both barycenter and face-center must be normalized
			// the normalized barycenter is 0.0.0 so if correct can be removed from the input (using the original barycenter may be causing the problem)
			// but are the face-center also normalized?!
			Cord cord = new Cord(mesh.getBarycenter(), mesh.getFaceCenter(face));
			//Cord cord = new Cord(new Point3D.Double(), mesh.getFaceCenter(face));
			//_cordList.add(cord);
			lenght[i] = cord.Lenght;
			angleXY[i] = cord.Angle_XY;
			angleXZ[i] = cord.Angle_XZ;
			angleYZ[i] = cord.Angle_YZ;
			
			i++;
		}
		
		double max = MIN_PIXEL;
		double min = MAX_PIXEL; //TODO: if not necessary, then remove
		for (int j=0; j < lenght.length; j++) {
			if(lenght[j] > max) max=lenght[j];
			if(lenght[j] < min) min=lenght[j];
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
		bw.write(String.valueOf(LEVEL)); // number of features per histogram
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
	public double compare(FeatureDescriptor feature) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String printFeatureVector() {
		// TODO Auto-generated method stub
		return null;
	}

}