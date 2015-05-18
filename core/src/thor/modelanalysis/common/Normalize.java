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
package thor.modelanalysis.common;

import thor.graphics.Point3D;
import thor.Model;

/**
 * Translation, scaling and rotation methods to apply first when need.
 * E.g.: to ensure that 3D model entirely contained in the rendered images.
 * 
 * @author Pedro B. Pascoal
 */
public class Normalize {
	/**
	 * 
	 * @param model
	 * the model to apply the translation.
	 */
	public static void translation(Model model) {
		//find model barycenter
		Point3D barycenter = model.getBarycenter();
		
		//translate to origin
		model.translate(-barycenter.getX(), -barycenter.getY(), -barycenter.getZ());
	}

	/**
	 * 
	 * @param model
	 * the model to apply the scaling.
	 */
	public static void scale(Model model) {
		// find model bounding box
		Point3D max = model.getMaxVertex();
		Point3D min = model.getMinVertex();
		
		// scale to unitary value
		model.scale(1/Math.max(max.getX() - min.getX(), Math.max(max.getY() - min.getY() , max.getZ() - min.getZ())));
	}
	
	/**
	 * currently not implemented
	 * @deprecated
	 * @param model
	 * the model to apply the rotation.
	 */
	public static void rotation(Model model) {
		//TODO: PO3D Project - PCA/Rectiliniarity
	}
	
}

