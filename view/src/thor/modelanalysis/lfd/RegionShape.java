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
 */
package thor.modelanalysis.lfd;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.lang.Math;

/**
 * 
 * @author Pedro B. Pascoal
 */
class RegionShape {
	
	// ART - Angular Radial Transformation
	public static final int ART_ANGULAR = 12;
	public static final int ART_RADIAL = 3; //12*3 = 36, the first will be 1, and will only be used for normalization
	//private static final int ART_COEF = 35;
	private static final int ART_LUT_RADIUS = 50; // Zernike basis function radius 
	private static final int ART_LUT_SIZE = 101; // (ART_LUT_RADIUS*2+1)
	
	//Variables:
	double[][][][] m_pBasisR = new double[ART_ANGULAR][ART_RADIAL][ART_LUT_SIZE][ART_LUT_SIZE];
	double[][][][] m_pBasisI = new double[ART_ANGULAR][ART_RADIAL][ART_LUT_SIZE][ART_LUT_SIZE];
	
	double m_radius;
	double r_radius;
	
	/**
	 * Generates look-up-table
	 */
	void generateBasisLUT() {
		double angle, temp, radius;
		int maxradius = ART_LUT_RADIUS;
		for(int y = 0; y < ART_LUT_SIZE; y++) {
			for(int x = 0; x < ART_LUT_SIZE; x++) {
				radius = Math.hypot(x - maxradius, y - maxradius);
				if(radius < maxradius) {
					angle = Math.atan2((double)y - maxradius, (double)x - maxradius);
					for(int p = 0; p < ART_ANGULAR; p++) {
						for(int r = 0; r < ART_RADIAL; r++) {
							temp = Math.cos(radius*Math.PI*r/maxradius);
							m_pBasisR[p][r][x][y] = temp*Math.cos(angle*p);
							m_pBasisI[p][r][x][y] = temp*Math.sin(angle*p);
						}
					}
				}
				else {
					for(int p = 0; p < ART_ANGULAR; p++) {
						for(int r = 0; r < ART_RADIAL; r++) {
							m_pBasisR[p][r][x][y] = 0;
							m_pBasisI[p][r][x][y] = 0;
						}
					}
				}
			}
		}
	}
	
	public double[][] extractCoefficients(BufferedImage image) {
		double[][] m_Coeff = new double[ART_ANGULAR][ART_RADIAL];
		
		double[][] m_pCoeffR = new double[ART_ANGULAR][ART_RADIAL]; // real part (x)
		double[][] m_pCoeffI = new double[ART_ANGULAR][ART_RADIAL]; // imag part (y)
		
		double dx, dy, tx, ty, x1, x2;
		int ix, iy; // complex numbers
		
		int count = 0;
		Raster data = image.getData();
		double[] center = findCenter(image);
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				int[] pixel = new int[3]; // because each pixel has 3 positions Red, Green, Blue. Question?: no alpha?
				pixel = data.getPixel(x, y, pixel);
				if(pixel[0] < 127) { // we will only need the red since we are using silhouettes
					// map image coordinate (x, y) to basis function coordinate (tx,ty)
					dx = x - center[0];
					dy = y - center[1];

					tx = dx * r_radius + ART_LUT_RADIUS;
					ty = dy * r_radius + ART_LUT_RADIUS;
					
					ix = (int)tx;
					iy = (int)ty;
					
					dx = tx - ix;
					dy = ty - iy;
					
					// summation of basis function
					for(int p = 0; p < ART_ANGULAR; p++) {
						for(int r = 0; r < ART_RADIAL; r++) {
							// real part
							x1 = m_pBasisR[p][r][ix][iy] + (m_pBasisR[p][r][ix+1][iy] - m_pBasisR[p][r][ix][iy]) * dx;
							x2 = m_pBasisR[p][r][ix][iy+1] + (m_pBasisR[p][r][ix+1][iy+1] - m_pBasisR[p][r][ix][iy+1]) * dx;
							m_pCoeffR[p][r] += (x1 + (x2-x1) * dy);
							
							// imaginary part
							x1 = m_pBasisI[p][r][ix][iy] + (m_pBasisI[p][r][ix+1][iy] - m_pBasisI[p][r][ix][iy]) * dx;
							x2 = m_pBasisI[p][r][ix][iy+1] + (m_pBasisI[p][r][ix+1][iy+1] - m_pBasisI[p][r][ix][iy+1]) * dx;
							m_pCoeffI[p][r] -= (x1 + (x2-x1) * dy);
						}
					}
					count++; //how many pixels
				}
			}
		}
		if( count > 0 ) {
			for(int p = 0; p < ART_ANGULAR; p++) {
				for(int r = 0; r < ART_RADIAL; r++) {
					m_Coeff[p][r] = Math.hypot(m_pCoeffR[p][r]/count, m_pCoeffI[p][r]/count);
				}
			}
			for(int p = 0; p < ART_ANGULAR; p++) {
				for(int r = 0; r < ART_RADIAL; r++) {
					m_Coeff[p][r] /= m_Coeff[0][0]; // normalization
				}
			}
		}
		else {// if the 3D model is flat, some camera will render nothing, so count=0 in this case
			// if didn't add this, the result will also be saved as 0
			for(int p = 0; p < ART_ANGULAR; p++) {
				for(int r = 0; r < ART_RADIAL; r++) {
					m_Coeff[p][r] = 0.0;
				}
			}
		}
		return m_Coeff;
	}
	
	void findRadius(BufferedImage[] images) {
		double t_radius = 0; // temp radius
		
		BufferedImage image;
		Raster data;
		double[] center;
		
		for (int i = 0; i < images.length; i++) {
			image = images[i];
			data = image.getData();
			center = findCenter(image);
			for(int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					int[] pixel = new int[3];
					pixel = data.getPixel(x, y, pixel);
					if(pixel[0] < 255) {
						t_radius = Math.hypot(x - center[0], y - center[1]);
						if(t_radius > m_radius) m_radius = t_radius;
					}
				}
			}
		}
		r_radius = ART_LUT_RADIUS / m_radius;
	}
	
	double[] findCenter(BufferedImage image) {
		double[] center = new double[2];
		center[0] = center[1] = -1;
		
		int max_x, min_x, max_y, min_y;
		Raster data = image.getData();
		
		max_x = max_y = -1;
		min_x = image.getWidth() + 1;
		min_y = image.getHeight() + 1; 
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				int[] pixel = new int[3];
				pixel = data.getPixel(x, y, pixel);
				
				if(pixel[0] < 255) {
					if(x > max_x) max_x = x;
					if(x < min_x) min_x = x;
					if(y > max_y) max_y = y;
					if(y < min_y) min_y = y;
				}
			}
		}
		
		if(max_x > 0) {
			center[0] = (max_x + min_x) / 2.0;
			center[1] = (max_y + min_y) / 2.0;
		}
		return center;
	}
}

