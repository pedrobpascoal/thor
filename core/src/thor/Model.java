// Copyright 2013 Pedro B. Pascoal
package thor; 

import java.util.ArrayList;
import java.util.List;

import thor.graphics.Point3D;
import thor.model.geoset.Bone;
import thor.model.geoset.Mesh;

/**
 * The abstract class Model is the superclass of all classes that represent graphical 3d model.
 * The model must be obtained in a platform-specific manner.
 * 
 * @author Pedro B. Pascoal
 */
public abstract class Model {
	private String _name = null;
	private String _extension = null;
	
	protected List<Mesh> _meshes = new ArrayList<Mesh>();	// list of meshes/geosets
	protected List<Bone> _bones = new ArrayList<Bone>();
	//TODO: Add Attachments
	//TODO: Add Events
	//TODO: Add ParticleEmitters
	/**
	 * 
	 * @param name
	 * the name used to represent the model
	 * @param extension
	 * the extension of the file from which the model was read
	 */
	public Model(String name, String extension) {
		_name = name;
		_extension = extension;
	}
	/**
	 * @return
	 * the name used to represent the model
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @return
	 * the name of the Model
	 */
	public String getModelName() {
		return _name.replaceAll("."+_extension, "");
	}
	/**
	 * @return
	 * the extension of the file from which the model was read
	 */
	public String getModelFormat() {
		return _extension;
	}
	/**
	 * @return
	 * the list of polygonal meshes that make this model
	 */
	public List<Mesh> getMeshes() {
		return _meshes;
	}
	/**
	 * @return
	 * the list of topological bones that belong to this model. 
	 */
	public List<Bone> getBones() {
		return _bones;
	}
	
	/**
	 * Moves every point a constant distance in a specified (x, y, z) direction
	 * @param x
	 * the X value for the translation in the X-axis direction
	 * @param y
	 * the Y value for the translation in the Y-axis direction
	 * @param z
	 * the Z value for the translation in the Z-axis direction
	 */
	public void translate(double x, double y, double z){
		for(Mesh mesh : _meshes) {
			mesh.translate(x, y, z);
		}
		for(Bone bone : _bones) {
			bone.translate(x, y, z);
		}
	}
	/**
	 * Enlarges or shrinks the Model by a scale factor in all direction
	 * @param value
	 * the scale factor
	 */
	public void scale(double value) {
		for(Mesh mesh : _meshes) {
			mesh.scale(value);
		}
		for(Bone bone : _bones) {
			bone.scale(value);
		}
	}
	public void rotate() {
	}

	public Point3D getMaxVertex() {
		return _meshes.get(0).getMaxVertex();
	}
	public Point3D getMinVertex() {
		return _meshes.get(0).getMinVertex();
	}
	public Point3D getBarycenter() {
		return _meshes.get(0).getBarycenter();
	}
	
	/**
	 * 
	 * @return
	 * the total number of Vertices of the Model
	 */
	public int countVertices() {
		int count = 0;
		for(Mesh mesh : _meshes) {
			count += mesh.countVertices();
		}
		return count;
	}
	/**
	 * 
	 * @return
	 * the total number of Faces of the Model 
	 */
	public int countFaces() {
		int count = 0;
		for(Mesh mesh : _meshes) {
			count += mesh.countFaces();
		}
		return count;
	}
	public int countEdges() {
		int count = 0;
		for(Mesh mesh : _meshes) {
			count += mesh.countEdges();
		}
		return count;
	}
	
	public boolean isManifold() {
		return _meshes.get(0).isManifold();
	}
	public double getSurfaceArea() {
		return _meshes.get(0).getSurfaceArea();
	}

	/*
	public void draw(GLAutoDrawable drawable) {
		for(Mesh mesh : _meshes) {
			mesh.draw(drawable);
		}
		
		for(Bone bone : _bones) {
			bone.draw(drawable);
		}
	}
	*/
}
