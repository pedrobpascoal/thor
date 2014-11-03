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

	public void addMesh(Mesh mesh) {
		_meshes.add(mesh);
	}
	
	public void addBone(Bone bone) {
		_bones.add(bone);
		System.out.println("added bone:" + bone.getName() );
	}
}

