// Copyright 2013 Pedro B. Pascoal
package thor.model;

import thor.Model;
import thor.model.geoset.Bone;
import thor.model.geoset.Mesh;

/**
 * The BufferedModel subclass describes a Model with an accessible buffer of model data.
 * 
 * @author Pedro B. Pascoal
 */
public class BufferedModel extends Model {

	public BufferedModel(String name, String extension) {
		super(name, extension);
	}

	/**
	 * Adds a mesh to the list of Meshes of the Model 
	 * @param mesh - polygon mesh that is to be added to the list of the Model
	 */
	public void addMesh(Mesh mesh) {
		_meshes.add(mesh);
	}
	
	/**
	 * Adds a bone to the list of Bones of the Model 
	 * @param bone - bone to be added to the list of the Model
	 */
	public void addBone(Bone bone) {
		_bones.add(bone);
		System.out.println("added bone:" + bone.getName() );
	}
}

