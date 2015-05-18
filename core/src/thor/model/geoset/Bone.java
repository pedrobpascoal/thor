package thor.model.geoset;
/*
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
*/
import thor.graphics.Point3D;

/**
 * Each Bone is a represented by a line that start at it's Parent pivot point and end at his own pivot point.
 * 
 * @author Pedro B. Pascoal
 */
public class Bone {
	int _objectId = 0;
	int _parentId = -1;
	
	Bone _parentBone = null;
	
	String _name = "";
	Point3D _pivotPoint = null;
	
	Point3D _barycenter = new Point3D.Double();
	
	/**
	 * 
	 * @param name - the name of the Bone
	 * @param uniqueId - the Bone *unique* identifier
	 * @param parentId - the parent Bone identifier
	 * @param pivotPoint - the pivot point of the Bone
	 */
	public Bone(String name, int uniqueId, int parentId, Point3D pivotPoint) {
		_name = name;
		_objectId = uniqueId;
		_parentId = parentId;
		_pivotPoint = pivotPoint;
	}
	/**
	 * 
	 * @param name - the name of the Bone
	 * @param uniqueId - the Bone *unique* identifier
	 * @param parent - the parent Bone
	 * @param pivotPoint - the pivot point of the Bone
	 */
	public Bone(String name, int uniqueId, Bone parent, Point3D pivotPoint) {
		_name = name;
		_objectId = uniqueId;
		_parentId = parent.getObjectId();
		_parentBone = parent;
		_pivotPoint = pivotPoint;
	}
	
	/**
	 * @return
	 * the Bone's *unique* identifier
	 */
	public int getObjectId() {
		return _objectId;
	}
	/**
	 * @return
	 * the parent Bone's identifier
	 */
	public int getParentId() {
		return _parentId;
	}
	/**
	 * @return
	 * the parent Bone of the corresponding Bone
	 */
	public Bone getParentBone() {
		return _parentBone;
	}
	/**
	 * Assign a new parent Bone to the corresponding Bone 
	 * @param bone - the bone to be assigned as parent
	 */
	public void setParent(Bone bone) {
		_parentBone = bone;
	}
	/**
	 * @return
	 * the name of the Bone
	 */
	public String getName() {
		return _name;
	}
	/**
	 * Move the pivot point to a new position
	 * @param point - the new position of the Bone's pivot point
	 */
	public void setPivotPoint(Point3D point) {
		_pivotPoint = point;
	}
	/**
	 * @return
	 * the pivot point's position
	 */
	public Point3D getPivotPoint() {
		return _pivotPoint;
	}
	/*
	public void draw(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
        final float radius = 0.02f;
        final int slices = 8;
        final int stacks = 8;
        
		GLUquadric quadric = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadric, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quadric, GLU.GLU_OUTSIDE);
        
        gl.glPushMatrix();
		gl.glColor3f(0.3f, 0.5f, 1.0f);
        gl.glTranslated(_pivotPoint.getX(), _pivotPoint.getY(), _pivotPoint.getZ());
        glu.gluSphere(quadric, radius, slices, stacks);
        glu.gluDeleteQuadric(quadric);
        gl.glPopMatrix();
        

        // draw the bone connection between parent-child
        if(_parentBone != null) {
	        Point3D origin = _parentBone.getPivotPoint();
	        //Point3D medianPoint = new Point3D.Double();

	        //medianPoint.add(origin);
	        //medianPoint.add(_pivotPoint);
	        //medianPoint.div(2);
	        
	        gl.glPushMatrix();
			gl.glColor3f(1.0f, 1.0f, 0.2f);
			//gl.glColor3f(0.3f, 0.5f, 1.0f);
			
			gl.glLineWidth(2.0f);
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(origin.getX(),origin.getY(),origin.getZ());
			gl.glVertex3d(_pivotPoint.getX(),_pivotPoint.getY(),_pivotPoint.getZ());
			gl.glEnd();

			gl.glLineWidth(1.0f);
	        gl.glPopMatrix();
        }
	}*/

	public void translate(double x, double y, double z) {
		_pivotPoint.add(new Point3D.Double(x, y, z));
		//_barycenter = new Point3D.Double(x, y, z);
	}
	public void scale(double value) {
		_pivotPoint.mul(value);
	}
}