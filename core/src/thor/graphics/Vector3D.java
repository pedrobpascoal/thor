// Copyright 2013 Pedro B. Pascoal
package thor.graphics; 

/**
 * The Vector3D class defines a spacial vector expressed numerically as ordered list of tuples (x,y,z).
 * All vectors have origin on (0, 0, 0).
 * 
 * @author Pedro B. Pascoal
 */
public class Vector3D extends Point3D {
	/**the X coordinate of the Vector*/
	private double x;
	/**the Y coordinate of the Vector*/
	private double y;
	/**the Z coordinate of the Vector*/
	private double z;
	
	/** Constructs and initializes a Vector3D with coordinates (0, 0, 0).*/
	public Vector3D() {
		this(0.0);
	}
	/**
	 * Constructs and initializes a Vector3D with coordinates (value, value, value).
	 * @param value
	 * the specific value in which the vector is initialized
	 */
	protected Vector3D(double value) {
		this(value, value, value);
	}
	/**
	 * Constructs and initializes a Vector3D with the specified coordinates.
	 * @param x
	 * the X coordinate of the newly constructed Vector3D.
	 * @param y
	 * the Y coordinate of the newly constructed Vector3D.
	 * @param z
	 * the Z coordinate of the newly constructed Vector3D.
	 */
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Normalizes the vector preserves the direction, but scales dimension to one unit.
	 */
	public void normalize() {
		float length = (float) this.lenght();
		if (length > 0) {
			x /= length;
			y /= length;
			z /= length;
		}
	}
	/**
	 *  
	 * @param vt
	 * the specified Vector3D to be calculated the dot product against this Vector3D
	 * @return
	 * the dot product of vt and this object and return it.
	 */
	public double DotProduct3(Vector3D vt)
	{
	   return this.x * vt.x + this.y * vt.y + this.z * vt.z;
	}
	/**
	 * 
	 * @param v1
	 * the first Vector3D to be used.
	 * @param v2
	 * the second Vector3D to be used.
	 * @return
	 * the vector of the cross product of the two vectors
	 */
	public static Vector3D product(Vector3D v1, Vector3D v2) {
		Vector3D result = new Vector3D(	(v1.y * v2.z) - (v1.z * v2.y),
										(v1.z * v2.x) - (v1.x * v2.z),
										(v1.x * v2.y) - (v1.y * v2.x));
		return result;
	}
	/**
	 * 
	 * @return
	 * the length of a vector.
	 */
	public double lenght() {
		return Math.sqrt(this.x * this.x +
						 this.y * this.y +
						 this.z * this.z);
	}
	
	/**
	 * Vector3D Addition. Sum of each of their coordinates. 
	 * @param v1
	 * the first Vector3D to be used.
	 * @param v2
	 * the second Vector3D to be used.
	 * @return
	 * a Vector3D of the resulting addition
	 */
	public static Vector3D add(Vector3D v1, Vector3D v2) {
		Vector3D result = new Vector3D( v1.getX() + v2.getX(),
										v1.getY() + v2.getY(),
										v1.getZ() + v2.getZ());
		return result;
	}
	
	/**
	 * Vector3D Subtraction. Subs in each of their coordinates. 
	 * @param p1
	 * the first Point3D to be used.
	 * @param p2
	 * the second Point3D to be used.
	 * @return
	 * a Vector3D of the resulting substration.
	 */
	public static Vector3D sub(Point3D p1, Point3D p2) {
		Vector3D result = new Vector3D( p1.getX() - p2.getX(),
										p1.getY() - p2.getY(),
										p1.getZ() - p2.getZ());
		return result;
	}
	
	/**
	 * 
	 * @return
	 * the angle of a vector to the XOY plan.
	 */
	public double getAngleXY() {
		double l = this.lenght();
		if (l==0)
			return 0;
		return Math.asin(this.z/l);
	}
	/**
	 * @return
	 * the angle of a vector to the XOZ plan.
	 */
	public double getAngleXZ() {
		double l = this.lenght();
		if (l==0)
			return 0;
		return Math.asin(this.y/l);
	}
	/**
	 * 
	 * @return
	 * the angle of a vector to the YOZ plan.
	 */
	public double getAngleYZ() {
		double l = this.lenght();
		if (l==0)
			return 0;
		return Math.asin(this.x/l);
	}
	/**
	 *  
	 * @param v
	 * vector to calculate the angle to.
	 * @return
	 * the angle between one vector to another.
	 */
	public double angleBetweenVectors(Vector3D v) {
		//calculate the lengths of two vectors	
		double a = this.lenght();
		double b = v.lenght();
		
		if(a == 0 || b == 0) {
			return 0;
		}
		else {
			return Math.acos(this.DotProduct3(v));
		}
	}
	@Override
	public double getX() { return this.x; }
	@Override
	public double getY() { return this.y; }
	@Override
	public double getZ() { return this.z; }
	@Override
	public void setLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
