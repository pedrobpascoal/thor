// Copyright 2013 Pedro B. Pascoal
package thor.model.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import thor.Model;
import thor.model.geoset.Face;
import thor.model.geoset.Mesh;
import thor.model.geoset.Vertex;

//MyTODO: Javadoc
class ModelWriterObj extends ModelWriter {
	
	public void write(Model model) throws IOException {
		throw new IOException("Not yet implemented");
	}

	public void write(Model model, File output) throws IOException {
		FileWriter fstream = new FileWriter(output);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("# This file was saved using the ThOR framework."); out.newLine();
		out.write("#"); out.newLine();
		out.write("# Original File: " + model.getName()); out.newLine();
		out.write("# Original Format: " + model.getModelFormat()); out.newLine();
		out.write("#"); out.newLine();
		out.write("# Model Name: " + model.getModelName()); out.newLine();
		out.write("# Vertices: " + model.countVertices()); out.newLine();
		out.write("# Faces: " + model.countFaces()); out.newLine();
		out.write("#"); out.newLine();
		
		// Write all meshes of the model
		List<Mesh> meshes = model.getMeshes();
		for(int m=0; m < meshes.size(); m++ ) {
			// Write all vertices
			List<Vertex> vertices = meshes.get(m).getVertices();
			for(int v=0; v < vertices.size(); v++) {
				out.write("v");
				out.write(" " + vertices.get(v).getX());
				out.write(" " + vertices.get(v).getY());
				out.write(" " + vertices.get(v).getZ());
				out.newLine();
			}
			// Write all faces
			List<Face> faces = model.getMeshes().get(0).getFaces();
			for(int f=0; f < faces.size(); f++) {
				out.write("f");
				for(int i = 0; i < faces.get(f).Vertices.size(); i++) {
					out.write(" " + (faces.get(f).Vertices.get(i)+1));
				}
				out.newLine();
			}
		}
		out.close();
		fstream.close();
	}
}

