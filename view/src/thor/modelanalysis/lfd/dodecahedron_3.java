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

 * Original File: dodecahedron_3.off
 * Original Format: off
 * 
 * Model Name: dodecahedron_3
 * Vertices: 20
 * Faces: 20

 * @author ThOR-ModelConverter
 */
class dodecahedron_3 {

	@SuppressWarnings("serial")
	public static Model createModel() {
		BufferedModel model = new BufferedModel("dodecahedron_3", "java");
		BufferedMesh mesh = new BufferedMesh();

		// Add list of vertices:
		mesh.addVertex(0.079042f,-1.039571f,0.242783f);
		mesh.addVertex(-0.349675f,-0.671425f,0.756841f);
		mesh.addVertex(-0.935122f,-0.347966f,0.387767f);
		mesh.addVertex(-0.86823f,-0.516207f,-0.354398f);
		mesh.addVertex(-0.241448f,-0.943639f,-0.444012f);
		mesh.addVertex(0.249304f,-0.554266f,-0.881208f);
		mesh.addVertex(0.873115f,-0.409574f,-0.464646f);
		mesh.addVertex(0.76786f,-0.709484f,0.230031f);
		mesh.addVertex(0.76486f,-0.137338f,0.736223f);
		mesh.addVertex(0.074186f,-0.113812f,1.06181f);
		mesh.addVertex(-0.249304f,0.554266f,0.881208f);
		mesh.addVertex(-0.873115f,0.409574f,0.464646f);
		mesh.addVertex(-0.76786f,0.709484f,-0.230031f);
		mesh.addVertex(-0.76486f,0.137338f,-0.736223f);
		mesh.addVertex(-0.074186f,0.113812f,-1.06181f);
		mesh.addVertex(0.935122f,0.347966f,-0.387767f);
		mesh.addVertex(0.86823f,0.516207f,0.354398f);
		mesh.addVertex(0.241448f,0.943639f,0.444012f);
		mesh.addVertex(-0.079042f,1.039571f,-0.242783f);
		mesh.addVertex(0.349675f,0.671425f,-0.756841f);

		// Add list of faces:
		mesh.addFace(new ArrayList<Integer>() {{add(16);add(17);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(16);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(18);add(19);}});
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(19);add(14);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(15);add(14);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(14);add(5);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(18);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(12);add(13);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(19);add(13);}});
		mesh.addFace(new ArrayList<Integer>() {{add(17);add(10);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(17);add(11);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(18);add(17);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(17);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(10);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(16);add(8);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(8);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(16);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(7);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(13);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(14);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(3);add(4);}});
		mesh.addFace(new ArrayList<Integer>() {{add(12);add(11);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(12);add(2);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(13);add(12);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(11);add(10);add(9);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(11);add(9);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(9);add(1);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(9);add(8);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(8);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(0);add(1);}});
		mesh.addFace(new ArrayList<Integer>() {{add(4);add(0);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(4);add(7);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(4);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(1);add(0);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(2);add(0);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(0);add(4);}});

		// Add built mesh:
		model.addMesh(mesh);
		return model;
	}
}
