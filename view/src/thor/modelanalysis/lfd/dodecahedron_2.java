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

import java.util.ArrayList;

import thor.Model;
import thor.model.BufferedModel;
import thor.model.geoset.BufferedMesh;

/**
 * This file was autogenerated using the ModelConverter from the ThOR project.

 * Original File: dodecahedron_2.off
 * Original Format: off
 * 
 * Model Name: dodecahedron_2
 * Vertices: 20
 * Faces: 20

 * @author ThOR-ModelConverter
 */
class dodecahedron_2 {

	@SuppressWarnings("serial")
	public static Model createModel() {
		BufferedModel model = new BufferedModel("dodecahedron_2", "java");
		BufferedMesh mesh = new BufferedMesh();

		// Add list of vertices:
		mesh.addVertex(-0.265671f,-0.970876f,0.364304f);
		mesh.addVertex(-0.889128f,-0.551793f,0.225538f);
		mesh.addVertex(-0.87577f,-0.345058f,-0.509765f);
		mesh.addVertex(-0.24405f,-0.636375f,-0.825444f);
		mesh.addVertex(0.133016f,-1.023151f,-0.28525f);
		mesh.addVertex(0.807144f,-0.680583f,-0.176679f);
		mesh.addVertex(0.825125f,-0.416615f,0.539979f);
		mesh.addVertex(0.162066f,-0.596002f,0.874304f);
		mesh.addVertex(-0.197046f,0.054765f,1.050748f);
		mesh.addVertex(-0.846719f,0.082092f,0.649789f);
		mesh.addVertex(-0.807144f,0.680583f,0.176679f);
		mesh.addVertex(-0.825125f,0.416615f,-0.539979f);
		mesh.addVertex(-0.162066f,0.596002f,-0.874304f);
		mesh.addVertex(0.197046f,-0.054765f,-1.050748f);
		mesh.addVertex(0.846719f,-0.082092f,-0.649789f);
		mesh.addVertex(0.87577f,0.345058f,0.509765f);
		mesh.addVertex(0.24405f,0.636375f,0.825444f);
		mesh.addVertex(-0.133016f,1.023151f,0.28525f);
		mesh.addVertex(0.265671f,0.970876f,-0.364304f);
		mesh.addVertex(0.889128f,0.551793f,-0.225538f);

		// Add list of faces:
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(16);add(17);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(15);add(17);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(17);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(6);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(15);add(19);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(5);add(19);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(18);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(12);add(13);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(19);add(13);}});
		mesh.addFace(new ArrayList<Integer>() {{add(18);add(17);add(10);}});
		mesh.addFace(new ArrayList<Integer>() {{add(18);add(10);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(12);add(18);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(17);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(10);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(16);add(8);}});
		mesh.addFace(new ArrayList<Integer>() {{add(8);add(16);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(8);add(15);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(8);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(13);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(14);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(3);add(4);}});
		mesh.addFace(new ArrayList<Integer>() {{add(13);add(12);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(13);add(11);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(13);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(9);add(1);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(1);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(11);add(10);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(9);add(8);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(8);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(0);add(1);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(0);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(0);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(4);add(0);add(5);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(0);add(4);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(1);add(4);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(4);add(3);}});

		// Add built mesh:
		model.addMesh(mesh);
		return model;
	}
}
