/** Copyright 2013 Pedro B. Pascoal **/ 
package thor.model.geoset; 

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
*/

import thor.graphics.Vector3D;
import thor.graphics.Point3D;

/**
 * The abstract class Mesh is the superclass of all classes that represent polygon mesh.
 * The mesh must be obtained in a platform-specific manner.
 * @author Pedro B. Pascoal
 */
public abstract class Mesh extends Object {
	protected List<Vertex> _vertices = new ArrayList<Vertex>();		// list of vertices
	protected List<Point3D> _verticesColor = new ArrayList<Point3D>();// list of colors of each vertice
	
	protected List<Point2D> _textCoord = new ArrayList<Point2D>();	// list of texture coordinates
	protected List<Vector3D> _normals = new ArrayList<Vector3D>();	// list of normals
	protected List<Face> _faces = new ArrayList<Face>(); 			// list of faces (triangles)

	protected Point3D _maxVertex;
	protected Point3D _minVertex;
	
	public Mesh() {
		_maxVertex = new Point3D.Float(-9999, -9999, -9999);
		_minVertex = new Point3D.Float(9999, 9999, 9999);
	}
	/**
	 * @return
	 * A list of all the vertices of the mesh.
	 */
	public List<Vertex> getVertices() { return _vertices; }
	/**
	 * @return
	 * A list of colors of each vertex of the mesh.
	 */
	public List<Point3D> getVerticesColor() { return _verticesColor; }
	/**
	 * @return
	 * A list of Point2D with the texture coordinates for each of the vertices of the mesh.
	 */
	public List<Point2D> getTextCoord() { return _textCoord; }
	/**
	 * @return
	 * A list of Vector3D that represent the normals of each of the vertices of the mesh.
	 */
	public List<Vector3D> getNormals() { return _normals; }
	/**
	 * @return
	 * A list of Face with all the faces of the mesh.
	 */
	public List<Face> getFaces() { return _faces; }
	/**
	 * @return
	 * Return the total number of vertices in the mesh.
	 */
	public int countVertices() { return _vertices.size(); }
	/**
	 * @return
	 * Return the total number of faces in the mesh.
	 */
	public int countFaces() { return _faces.size(); }
	/**
	 * @return
	 * Return the total number of edges in the mesh.
	 */
	public int countEdges() {
		Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();
		int count = 0;

		// calculate the edges
		for(Face face : _faces) {
			int first = -1;
			int previous = -1;
			for(int i = 0; i < face.Vertices.size(); i++) {
				int current = face.Vertices.get(i);

				if(previous == -1) {
					first = current;
				} else {
					int max = (previous > current) ? previous : current;
					int min = (previous > current) ? current : previous;
					
					if(!edges.containsKey(min))
						edges.put(min, new ArrayList<Integer>());
					
					if(!edges.get(min).contains(max))
						edges.get(min).add(max);
				}
				previous = current;
			}
			if(first != previous && first != -1) {
				int max = (previous > first) ? previous : first;
				int min = (previous > first) ? first : previous;
				
				if(!edges.containsKey(min))
					edges.put(min, new ArrayList<Integer>());
				
				if(!edges.get(min).contains(max))
					edges.get(min).add(max);
			}
		}

		for(int index : edges.keySet()) {
			count += edges.get(index).size();
		}
		
		return count;
	}
	/**
	 * Moves every point of the mesh a (x, y, z) distance.
	 * @param x - the X coordinate to add.
	 * @param y - the Y coordinate to add.
	 * @param z - the Z coordinate to add.
	 */
	public void translate(double x, double y, double z) {
		for (Vertex vertex : _vertices) {
			vertex.add(new Point3D.Double(x, y, z));
		}
	}
	/**
	 * Change the dimension of object by a scaling factor, i.e. enlarging or shrinking. 
	 * @param value - the scale factor
	 */
	public void scale(double value) {
		for (Vertex vertex : _vertices) {
			vertex.mul(value);
		}
	}
	public void rotateX(float angle) {
		for (Vertex vertex : _vertices) {
			double rangle = Math.atan(vertex.getY() / vertex.getX()) * (float) (Math.PI /180);
			vertex.setLocation( (float) Math.cos(angle + rangle),
								(float) Math.asin(angle + rangle),
								vertex.getZ());
		}
	}
	public void rotateZ(float angle) {
		for (Vertex vertex : _vertices) {
			double rangle = Math.atan(vertex.getY()/vertex.getX()) * (float) (Math.PI /180);
			vertex.setLocation( (float) Math.cos(angle + rangle),
								(float) Math.asin(angle + rangle),
								vertex.getZ());
		}
	}
	
	public void calculateNormals() {
		//TODO: ok... kinda of tired, so calculate only for 3 vertices, screw when there are 4...
		// i'll do it later, maybe calculate each face normal
		for(Face face : _faces) {
			Point3D p1 = _vertices.get(face.Vertices.get(0));
			Point3D p2 = _vertices.get(face.Vertices.get(1));
			Point3D p3 = _vertices.get(face.Vertices.get(2));

			Vector3D v1 = Vector3D.sub(p2, p1);
			Vector3D v2 = Vector3D.sub(p3, p1);
			
			// Calc the weight of the normal
			face.Normal = Vector3D.product(v1, v2);
			face.Normal.normalize();

			// TODO: remove the signal, keep only value: square(point.x * point.x)
			
			// TODO: need to decide the direction using the barycenter: vector = point2 - barycenter
			// basically keep only the signal: point.x/ (square (point.x * point.x))
			// all normals must be pointing outside
			
			for(int i : face.Vertices) {
				Vertex v = _vertices.get(face.Vertices.get(i));
				v.Normal.add(face.Normal);
			}
		}
		for(Vertex v : _vertices) {
			v.Normal.normalize();
		}
	}
	
	public Point3D getMaxVertex() {
		return _maxVertex;
	}
	public Point3D getMinVertex() {
		return _minVertex;
	}
	

	public Point3D getFaceCenter(Face face) {
		// its being used by the CAH
		// TODO: must find a better placement for this method
		Point3D center = new Point3D.Double();
		
		for(int i : face.Vertices) {
			Vertex v = _vertices.get(i);
			center.add(v);
		}
		center.div(face.Vertices.size());
		return center;
	}
	
	/**
	 * Assigns a color to a specific vertex
	 * @param index
	 * The index of the vertex that will be painted. Index of vertices start in 0.
	 * @param color
	 * The color to paint the vertex. Colors are in RGB format. E.g: (0, 0, 0) will be black.
	 */
	public void paintVertex(int index, Point3D color) {
		if(_verticesColor.size() > index)
			_verticesColor.set(index, color);
	}
	/*
	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
        for (Face face : _faces) {
        	if(face.Vertices.size() == 4) {
        		gl.glBegin(GL2.GL_QUADS);
        	} else { // default = 3
        		gl.glBegin(GL2.GL_TRIANGLES);
        	}
        	for(int i = 0; i < face.Vertices.size(); i++) {
        		//prepare the color for painting the vertex
        		gl.glColor3d(_verticesColor.get(face.Vertices.get(i)).getX(),
        					 _verticesColor.get(face.Vertices.get(i)).getY(),
        					 _verticesColor.get(face.Vertices.get(i)).getZ());
        		//draw the vertex
        		gl.glVertex3d(	_vertices.get(face.Vertices.get(i)).getX(),
        						_vertices.get(face.Vertices.get(i)).getY(),
        						_vertices.get(face.Vertices.get(i)).getZ());
        	}
            gl.glEnd();
		}
	}*/
	
	public Point3D getBarycenter() {
		return new Point3D.Double((_maxVertex.getX() + _minVertex.getX())/2,
								  (_maxVertex.getY() + _minVertex.getY())/2,
								  (_maxVertex.getZ() + _minVertex.getZ())/2);
	}
	
	public boolean isManifold() {
		Integer verticesSize = _vertices.size();
		
		/* Stores how many times a vertex is used across all the faces composing the Mesh */
		ArrayList<Integer> vertexList = new ArrayList<Integer>(Collections.nCopies(verticesSize, 0));
		/* Stores how many times an edge is used across all the faces composing the Mesh */
		HashMap<Integer,Integer> edgeMap = new HashMap<Integer,Integer>();

		for(Face face: _faces){
			
			for(int index=0; index < face.Vertices.size(); index++) {
				
				int index0 = index;
				int index1 = (index + 1) % face.Vertices.size();
				
				/* The Map is initially empty */
				if(edgeMap.containsKey(face.Vertices.get(index0) * verticesSize + face.Vertices.get(index1)) == false) {
					
					edgeMap.put(face.Vertices.get(index0) * verticesSize + face.Vertices.get(index1), 0);
					edgeMap.put(face.Vertices.get(index1) * verticesSize + face.Vertices.get(index0), 0);
				}

				Integer edgeCounter = edgeMap.get(face.Vertices.get(index0) * verticesSize + face.Vertices.get(index1));
				edgeCounter++;
				
				/* If an edge is shared by more than 2 faces then the Mesh is not manifold */
				if(edgeCounter > 2)
					return false;
				
				/* Update the number of occurences of the edge */
				edgeMap.put(face.Vertices.get(index0) * verticesSize + face.Vertices.get(index1), edgeCounter);
				edgeMap.put(face.Vertices.get(index1) * verticesSize + face.Vertices.get(index0), edgeCounter);
				
				Integer vertexCounter0 = vertexList.get(face.Vertices.get(index0));
				vertexCounter0++;				
				
				/* Update the number of occurences of vertex 0 */
				vertexList.set(face.Vertices.get(index0),vertexCounter0);
				
				Integer vertexCounter1 = vertexList.get(face.Vertices.get(index1));
				vertexCounter1++;				
				
				/* Update the number of occurences of vertex 1 */
				vertexList.set(face.Vertices.get(index1),vertexCounter1);				
			}	
		}
		for(Integer vertexCounter: vertexList) {
			/* If a vertex is shared by only 2 edges then the Mesh is not manifold */			
			if(vertexCounter == 2)
				return false;
		}
		return true;
	}

	public double getSurfaceArea() {
		double area = 0.0;
		for (Face face : _faces) {
			area += getFaceArea(face);
		}
		return area;
	}
	
	public double getFaceArea(Face face) {
		double area = 0.0;
		switch (face.Vertices.size()) {
			case 3: area = triangleArea(face); break;
			case 4: area = convexQuadArea(face); break;
			default: break;
		}
		return area;
	}
	
	private double triangleArea(final Face face) {
		final Vertex v1 = _vertices.get(face.Vertices.get(0));
		final Vertex v2 = _vertices.get(face.Vertices.get(1));
		final Vertex v3 = _vertices.get(face.Vertices.get(2));
		return heronFormula(v1, v2, v3);
	}
	private double convexQuadArea(final Face face) {
		final Vertex v1 = _vertices.get(face.Vertices.get(0));
		final Vertex v2 = _vertices.get(face.Vertices.get(1));
		final Vertex v3 = _vertices.get(face.Vertices.get(2));
		final Vertex v4 = _vertices.get(face.Vertices.get(3));
		return heronFormula(v1, v2, v3) + heronFormula(v3, v4, v1);
	}
	private double heronFormula(final Vertex v1, final Vertex v2, final Vertex v3) {
		final double a =  Vector3D.distance(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z);
		final double b =  Vector3D.distance(v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
		final double c =  Vector3D.distance(v3.x, v3.y, v3.z, v1.x, v1.y, v1.z);
		final double s = (a + b + c) * 0.5; // semiperimeter
		final double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
		return area;
	}

	public Mesh getConvexHull() {
		/* PO3D Pratica 1 - create and return the convex hull
		 * return a new mesh that is the convex hull of the original
		 */
		return null;
	}
}
