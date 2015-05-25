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

 * Original File: dodecahedron_1.off
 * Original Format: off
 * 
 * Model Name: dodecahedron_1
 * Vertices: 20
 * Faces: 20

 * @author ThOR-ModelConverter
 */
class dodecahedron_1 {

	@SuppressWarnings("serial")
	public static Model createModel() {
		BufferedModel model = new BufferedModel("dodecahedron_1", "java");
		BufferedMesh mesh = new BufferedMesh();

		// Add list of vertices:
		mesh.addVertex(0.701802f,-0.421444f,0.689752f);
		mesh.addVertex(0.036426f,-0.795914f,0.714903f);
		mesh.addVertex(-0.109796f,-1.064716f,0.014936f);
		mesh.addVertex(0.465216f,-0.856372f,-0.442823f);
		mesh.addVertex(0.966813f,-0.45881f,-0.025774f);
		mesh.addVertex(0.994836f,0.251889f,-0.304541f);
		mesh.addVertex(0.747181f,0.728507f,0.238692f);
		mesh.addVertex(0.566046f,0.312347f,0.853184f);
		mesh.addVertex(-0.183238f,0.391383f,0.979358f);
		mesh.addVertex(-0.510564f,-0.293558f,0.893894f);
		mesh.addVertex(-0.994836f,-0.251889f,0.304541f);
		mesh.addVertex(-0.747181f,-0.728507f,-0.238692f);
		mesh.addVertex(-0.566046f,-0.312347f,-0.853184f);
		mesh.addVertex(0.183238f,-0.391383f,-0.979358f);
		mesh.addVertex(0.510564f,0.293558f,-0.893894f);
		mesh.addVertex(0.109796f,1.064716f,-0.014936f);
		mesh.addVertex(-0.465216f,0.856372f,0.442823f);
		mesh.addVertex(-0.966813f,0.45881f,0.025774f);
		mesh.addVertex(-0.701802f,0.421444f,-0.689752f);
		mesh.addVertex(-0.036426f,0.795914f,-0.714903f);

		// Add list of faces:
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(16);add(17);}});
		mesh.addFace(new ArrayList<Integer>() {{add(15);add(17);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(19);add(15);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(6);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(5);add(15);add(19);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(5);add(19);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(19);add(18);}});
		mesh.addFace(new ArrayList<Integer>() {{add(14);add(18);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(13);add(14);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(17);add(10);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(18);add(17);add(11);}});
		mesh.addFace(new ArrayList<Integer>() {{add(18);add(11);add(12);}});
		mesh.addFace(new ArrayList<Integer>() {{add(16);add(8);add(9);}});
		mesh.addFace(new ArrayList<Integer>() {{add(16);add(9);add(10);}});
		mesh.addFace(new ArrayList<Integer>() {{add(17);add(16);add(10);}});
		mesh.addFace(new ArrayList<Integer>() {{add(7);add(8);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(7);add(16);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(16);add(15);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(4);add(5);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(5);add(14);}});
		mesh.addFace(new ArrayList<Integer>() {{add(13);add(3);add(14);}});
		mesh.addFace(new ArrayList<Integer>() {{add(11);add(2);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(12);add(11);add(3);}});
		mesh.addFace(new ArrayList<Integer>() {{add(12);add(3);add(13);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(9);add(1);}});
		mesh.addFace(new ArrayList<Integer>() {{add(10);add(1);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(11);add(10);add(2);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(8);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(9);add(7);add(0);}});
		mesh.addFace(new ArrayList<Integer>() {{add(1);add(9);add(0);}});
		mesh.addFace(new ArrayList<Integer>() {{add(6);add(0);add(7);}});
		mesh.addFace(new ArrayList<Integer>() {{add(4);add(0);add(6);}});
		mesh.addFace(new ArrayList<Integer>() {{add(4);add(6);add(5);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(1);add(0);}});
		mesh.addFace(new ArrayList<Integer>() {{add(2);add(0);add(4);}});
		mesh.addFace(new ArrayList<Integer>() {{add(3);add(2);add(4);}});

		// Add built mesh:
		model.addMesh(mesh);
		return model;
	}
}
