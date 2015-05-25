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
 * (c) 2013-2015 by Pedro B. Pascoal (pmbp@tecnico.ulisboa.pt)
 * http://3dorus.ist.utl.pt/tools/thor, https://web.ist.utl.pt/pmbp/projects/thor 
 * 
 * Updated: 2015.05.18
 */
package thor.modelanalysis.utils;

import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import thor.Model;
import thor.graphics.Point3D;
import thor.model.geoset.Bone;
import thor.model.geoset.Face;
import thor.model.geoset.Mesh;
import thor.model.geoset.Vertex;

/**
 * 
 * 
 * @author Pedro B. Pascoal
 */
public class GLPainter {
	
	/**
	 * 
	 * @param drawable
	 * @param model
	 */
	public static void draw(GLAutoDrawable drawable, Model model) {
		for(Mesh mesh : model.getMeshes()) {
			GLPainter.draw(drawable, mesh);
		}
		for(Bone bone : model.getBones()) {
			GLPainter.draw(drawable, bone);
		}
	}

	/**
	 * 
	 * @param drawable
	 * @param mesh
	 */
	public static void draw(GLAutoDrawable drawable, Mesh mesh) {
		GL2 gl = drawable.getGL().getGL2();
		List<Vertex> vertices = mesh.getVertices();
		List<Point3D> verticesColor = mesh.getVerticesColor();
		
        for (Face face : mesh.getFaces()) {
        	if(face.Vertices.size() == 4) {
        		gl.glBegin(GL2.GL_QUADS);
        	} else { // default = 3
        		gl.glBegin(GL2.GL_TRIANGLES);
        	}
        	for(int i = 0; i < face.Vertices.size(); i++) {
        		//prepare the color for painting the vertex
        		gl.glColor3d(verticesColor.get(face.Vertices.get(i)).getX(),
        					 verticesColor.get(face.Vertices.get(i)).getY(),
        					 verticesColor.get(face.Vertices.get(i)).getZ());
        		//draw the vertex
        		gl.glVertex3d(	vertices.get(face.Vertices.get(i)).getX(),
        						vertices.get(face.Vertices.get(i)).getY(),
        						vertices.get(face.Vertices.get(i)).getZ());
        	}
            gl.glEnd();
		}
	}
	
	/**
	 * 
	 * @param drawable
	 * @param bone
	 */
	public static void draw(GLAutoDrawable drawable, Bone bone) {
		Point3D pivotPoint = bone.getPivotPoint();

        // GL Settings 
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = new GLU();
		
		GLUquadric quadric = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
        glu.gluQuadricNormals(quadric, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(quadric, GLU.GLU_OUTSIDE);

		// Set drawable sizes
        final float radius = 0.02f;
        final int slices = 8;
        final int stacks = 8;
        
        // Draw the bone node
        gl.glPushMatrix();
		gl.glColor3f(0.3f, 0.5f, 1.0f);
        gl.glTranslated(pivotPoint.getX(), pivotPoint.getY(), pivotPoint.getZ());
        glu.gluSphere(quadric, radius, slices, stacks);
        glu.gluDeleteQuadric(quadric);
        gl.glPopMatrix();
        
        // draw the bone connection between parent-child
        if(bone.getParentBone() != null) {
	        Point3D origin = bone.getParentBone().getPivotPoint();
	        
	        gl.glPushMatrix();
			gl.glColor3f(1.0f, 1.0f, 0.2f);
			
			gl.glLineWidth(2.0f);
			gl.glBegin(GL2.GL_LINES);
			gl.glVertex3d(origin.getX(), origin.getY(), origin.getZ());
			gl.glVertex3d(pivotPoint.getX(), pivotPoint.getY(), pivotPoint.getZ());
			gl.glEnd();

			gl.glLineWidth(1.0f);
	        gl.glPopMatrix();
        }
	}
}