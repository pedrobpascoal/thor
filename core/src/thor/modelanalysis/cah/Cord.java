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

import thor.graphics.Point3D;
import thor.graphics.Vector3D;
import thor.modelanalysis.common.Feature;

/**
 * A cord is defined as a ray segment which joins the barycenter of the mesh with a triangle center.
 * This is used as a feature when generating the <i>Cord-and-Angle-Histogram</i> shape descriptor.
 * @author Pedro B. Pascoal, pmbp@tecnico.ulisboa.pt
 */
class Cord implements Feature {
	Point3D barycenter = null;
	Point3D triangleCenter = null;
	Vector3D ray = null;

	double Lenght = 0;
	double Angle_XY = 0.0;
	double Angle_XZ = 0.0;
	double Angle_YZ = 0.0;
	
	Cord(Point3D barycenter, Point3D triangleCenter) {
		this.barycenter = barycenter;
		this.triangleCenter = triangleCenter;
		this.setCordInfo();
	}
	
	//Calculate other fields of cord according to implicit baryCenter and triangleCenter
	private void setCordInfo() {
		this.ray = Vector3D.sub(this.triangleCenter, this.barycenter);

		this.Lenght = this.ray.lenght();
		this.Angle_XY = this.ray.getAngleXY();
		this.Angle_XZ = this.ray.getAngleXZ();
		this.Angle_YZ = this.ray.getAngleYZ();
	}
}
