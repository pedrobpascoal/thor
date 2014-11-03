// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.cah;

import thor.graphics.Point3D;
import thor.graphics.Vector3D;
import thor.modelanalysis.common.Feature;

class Cord implements Feature {
	Point3D barycenter = null;
	Point3D triangleCenter = null;
	Vector3D ray = null;

	double Lenght;
	double Angle_XY;
	double Angle_XZ;
	double Angle_YZ;
	
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
