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
 * (c) 2011-2015 by Pedro B. Pascoal (pmbp@tecnico.ulisboa.pt)
 * http://3dorus.ist.utl.pt/tools/thor, https://web.ist.utl.pt/pmbp/projects/thor 
 * 
 * Updated: 2015.05.20
 */
package thor.modelanalysis.lfd;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import thor.Model;
import thor.model.geoset.Vertex;
import thor.modelanalysis.common.FeatureDescriptor;
import thor.modelanalysis.common.Normalize;
import thor.modelanalysis.utils.GLScene;

/**
 * The general idea of a Light Field Descriptor is to compare 2D views of the 3D shape, 
 * obtained from different viewing angles equally distributed around the bounding sphere.<br />
 * <br />
 * More information can be found in: 
 * Ding-Yun Chen, Xiao-Pei Tian, Yu-Te Shen and Ming Ouhyoung, 
 * <i>On Visual Similarity Based 3D Model Retrieval</i>, 
 * Computer Graphics Forum (EUROGRAPHICS'03), Vol. 22, No. 3, pp. 223-232, Sept. 2003.
 * 
 * @author Pedro B. Pascoal, pmbp@tecnico.ulisboa.pt
 */
public class LightfieldDescriptor extends FeatureDescriptor {
	public static final int LFD_NUMBER_MAX = 10; // the max number of Lightfieds are equal to the number of dodecahedrons available
	public static final int CAM_NUMBER_MAX = 20; // number of vertices available in dodecahedrons
	
	//TODO: Make the Lightfiled Descriptor class only extract a single Lightfield
	//*** the LFD.java, shall provide the basic/default configuration, and generate the 10 lightfields, as depicted in the original paper 
	
	public enum Settings {
		NumberOfLightfields,
		NumberOfCameras,
		
		ImageOutputEnabled,
		ImageOutputSize,
		ImageOutputFormat,
		ImageOutputDirectory
	}
	
	//TODO: use a single camera
	private List<Model> _cameras = new ArrayList<Model>();
	
	private int _lfdNumberMax = 1;
	private int _camNumberMax = 10;
	
	private String _name = "";
	
	private boolean _imageOutputEnabled = false;
	private int _imageOutputSize = 512;
	private String _imageOutputFormat = "png";
	private String _imageOutputDirectory = "";

	private double[][][][] _regionShapeCoeff = null;
	//TODO: private double[][][][] _contourShapeCoeff = null;
	
	public LightfieldDescriptor() {
		// load all 10 lightfield camera sets
		_cameras.add(dodecahedron_0.createModel());
		_cameras.add(dodecahedron_1.createModel());
		_cameras.add(dodecahedron_2.createModel());
		_cameras.add(dodecahedron_3.createModel());
		_cameras.add(dodecahedron_4.createModel());
		_cameras.add(dodecahedron_5.createModel());
		_cameras.add(dodecahedron_6.createModel());
		_cameras.add(dodecahedron_7.createModel());
		_cameras.add(dodecahedron_8.createModel());
		_cameras.add(dodecahedron_9.createModel());
	}
	
	public void setup(Map<LightfieldDescriptor.Settings, String> settings) {
		for(LightfieldDescriptor.Settings setting : settings.keySet()) {
			if(setting == LightfieldDescriptor.Settings.NumberOfLightfields) {
				_lfdNumberMax = Integer.parseInt(settings.get(setting));
			} else if(setting == LightfieldDescriptor.Settings.NumberOfCameras) {
				_camNumberMax = Integer.parseInt(settings.get(setting));
			} else if(setting == LightfieldDescriptor.Settings.ImageOutputEnabled) {
				_imageOutputEnabled = Boolean.parseBoolean(settings.get(setting));
			} else if(setting == LightfieldDescriptor.Settings.ImageOutputSize) {
				_imageOutputSize = Integer.parseInt(settings.get(setting));
			} else if(setting == LightfieldDescriptor.Settings.ImageOutputFormat) {
				_imageOutputFormat = settings.get(setting);
			} else if(setting == LightfieldDescriptor.Settings.ImageOutputDirectory) {
				_imageOutputDirectory = settings.get(setting);
			} else {
				//super.setup(settings);
			}
		}
	}
	
	@Override
	public void extract(Model model) {
		_name = model.getModelName();
		
		// ===================================================================================
		// Step 1. Translation and scaling are applied first to ensure that 3D model is
		// entirely contained in the rendered images
		// ===================================================================================
		Normalize.translation(model);
		Normalize.scale(model);
		
		// ===================================================================================
		// Step 2-3. For a LightField Descriptor, 10 images are represented for 20 viewports,
		// and are in a pre-defined order for storage.LFD_NUM
		// ===================================================================================
		GLScene scene = new GLScene();
		scene.setDimensions(_imageOutputSize, _imageOutputSize);
		scene.addDrawable(model);
		
		_regionShapeCoeff = new double[_lfdNumberMax][][][];
		for(int lfd = 0; lfd < _lfdNumberMax; lfd++) {
			List<Vertex> camera = _cameras.get(lfd).getMeshes().get(0).getVertices();
			
			BufferedImage[] silhouettes = new BufferedImage[_camNumberMax];
			
			for(int cam = 0; cam < _camNumberMax; cam++) {
				scene.setCameraPosition(camera.get(cam).getX(), camera.get(cam).getY(), camera.get(cam).getZ());
				BufferedImage silhouette;
				//TODO: BufferedImage contour;
				if(_imageOutputEnabled) {
					String prefix = _imageOutputDirectory + model.getName() + "_" + lfd + "_" + cam;
					silhouette = scene.extractSilhouette(new File(prefix + "_silhouette." + _imageOutputFormat), _imageOutputFormat);
					silhouette = scene.extractSketch(new File(prefix + "_sketch." + _imageOutputFormat), _imageOutputFormat);
					//TODO: contour = scene.extractContourn(new File(prefix + "_contourn." + format), format);
				} else {
					silhouette = scene.extractSilhouette();
					//TODO: contour = scene.extractContourn();
				}
				silhouettes[cam] = silhouette;
			}
			
			// ===================================================================================
			// Step 4. Descriptors for a 3D model are extracted from the LFD_NUM * CAM_NUM images
			// ===================================================================================
			RegionShape regionShape = new RegionShape();
			regionShape.findRadius(silhouettes);
			regionShape.generateBasisLUT();
			
			_regionShapeCoeff[lfd] = new double[_camNumberMax][][]; 
			for(int cam = 0; cam < silhouettes.length; cam++) {
				_regionShapeCoeff[lfd][cam] = regionShape.extractCoefficients(silhouettes[cam]);
			}
		}
	}

	@Override
	public double compare(FeatureDescriptor descriptor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getDescriptor() {
		int radialNumberMax = RegionShape.ART_ANGULAR * RegionShape.ART_RADIAL - 1;
		//TODO: contour_num_coeff
		int descriptorNumberMax = radialNumberMax; // TODO: + contour_num_coeff;
		
		// create a holder for the whole descriptor
		double[] descriptor = new double[_lfdNumberMax * _camNumberMax * descriptorNumberMax];
		
		// iterate through each lightfield, and combines each view's descriptor into the descriptor holder to be returned
		for(int lfd = 0; lfd < _lfdNumberMax; lfd++) {
			for(int cam = 0; cam < _camNumberMax; cam++) {
				int iterator = (descriptorNumberMax * _lfdNumberMax) * lfd + (descriptorNumberMax * _camNumberMax) * cam;
				
				for(int radial_num = 1; radial_num < RegionShape.ART_RADIAL; radial_num++) {
					descriptor[iterator+radial_num-1] = _regionShapeCoeff[lfd][cam][0][radial_num];
				}
				for(int angular_num = 1; angular_num < RegionShape.ART_ANGULAR; angular_num++){
					for(int radial_num = 0; radial_num < RegionShape.ART_RADIAL; radial_num++) {
						descriptor[iterator+(angular_num * RegionShape.ART_RADIAL) + radial_num] = _regionShapeCoeff[lfd][cam][angular_num][radial_num];
					}
				}
				//TODO add the Fourier/Contour coeffs 
			}
		}
		return descriptor;
	}


	public void writeToFolder(String folder, String name, String extension) throws IOException {
		for(int lfd = 0; lfd < _lfdNumberMax; lfd++) {
			String filename = folder + name + "_" + lfd + extension;
			
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(name);
			bw.newLine();
			writeSingle(bw, lfd);
			bw.close();
			fw.close();
		}
	}
	
	@Override
	public void writeToFile(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(_name);
		bw.newLine();
		bw.write(_lfdNumberMax);
		bw.newLine();
		for(int lfd = 0; lfd < _lfdNumberMax; lfd++) {
			writeSingle(bw, lfd);
		}
		bw.close();
		fw.close();
	}

	private void writeSingle(BufferedWriter bw, int lfd) throws IOException {
		bw.write((RegionShape.ART_ANGULAR * RegionShape.ART_RADIAL - 1));
		bw.newLine();
		bw.write(_camNumberMax);
		bw.newLine();
		for(int cam = 0; cam < _camNumberMax; cam++) {
			for(int radial_num = 1; radial_num < RegionShape.ART_RADIAL; radial_num++) {
				bw.write(""+_regionShapeCoeff[lfd][cam][0][radial_num]);
				bw.newLine();
			}
			for(int angular_num = 1; angular_num < RegionShape.ART_ANGULAR; angular_num++){
				for(int radial_num = 0; radial_num < RegionShape.ART_RADIAL; radial_num++) {
					bw.write(""+_regionShapeCoeff[lfd][cam][angular_num][radial_num]);
					bw.newLine();
				}
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}