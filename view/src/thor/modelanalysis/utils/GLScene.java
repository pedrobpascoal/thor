// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLPbuffer;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.awt.Screenshot;

import thor.Model;
import thor.graphics.Point3D;
import thor.modelanalysis.utils.view.SceneRenderFactory;

/**
 * 
 * @author Pedro B. Pascoal
 */
public class GLScene {
	public enum Type {
		Silhouette,
		Contourn,
		Sketch,
		Default;
	}

	private int _width = 512;
	private int _height = 512;
	
	private Point3D _cameraPosition;
	private Point3D _cameraLookAt;
	private Point3D _cameraUp;

	private List<Model> _drawables = new ArrayList<Model>();
	
	public GLScene() {
		_width = 512;
		_height = 512;
		
		_cameraPosition = new Point3D.Double(1.0, 1.0, 1.0);
		_cameraLookAt = new Point3D.Double();
		_cameraUp = new Point3D.Double(0.0, 1.0, 0.0);
	}
	
	public BufferedImage extractView() {
		return extractSnapshot(GLScene.Type.Default);
	}
	public BufferedImage extractSketch() {
		return extractSnapshot(GLScene.Type.Sketch);
	}
	public BufferedImage extractSketch(File output, String formatName) {
		BufferedImage silhouette = extractSnapshot(GLScene.Type.Sketch);
		if(silhouette != null)
			writeToFile(silhouette, formatName, output);
		return silhouette;	
	}
	
	public BufferedImage extractContourn() {
		return extractSnapshot(GLScene.Type.Contourn);
	}
	
	public BufferedImage extractSilhouette() {
		return extractSnapshot(GLScene.Type.Silhouette);
	}
	public BufferedImage extractSilhouette(File output, String formatName) {
		BufferedImage silhouette = extractSnapshot(GLScene.Type.Silhouette);
		if(silhouette != null)
			writeToFile(silhouette, formatName, output);
		return silhouette;	
	}

	private BufferedImage extractSnapshot(GLScene.Type type) {
		BufferedImage screenshot = null;
		
		GLPbuffer buffer = null;  
		GLProfile glp = GLProfile.getDefault(GLProfile.getDefaultDevice());  
		GLCapabilities caps = new GLCapabilities(glp);
		//caps.setDoubleBuffered(false);  
		if (GLDrawableFactory.getFactory(glp).canCreateGLPbuffer(GLProfile.getDefaultDevice())) {
		    try {  
		        buffer = GLDrawableFactory.getFactory(glp).createGLPbuffer(GLProfile.getDefaultDevice(), caps, null, _width, _height, null);
		        buffer.addGLEventListener(SceneRenderFactory.create(this, type));  
		        buffer.getContext().makeCurrent();  
		        buffer.display();
		        
		        screenshot = Screenshot.readToBufferedImage(_width, _height);
		        
		        buffer.getContext().release();  
		        buffer.destroy(); 
		    } catch (Exception e) {  
		        buffer = null;  
		    }
		} else {  
		    System.out.println("Graphics processor does not have PBuffer capability!");  
		} 
		return screenshot;
	}
	private void writeToFile(BufferedImage image, String formatName, File output) {
		try {
			ImageIO.write(image, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDimensions(int width, int height) {
		this._width = width;
		this._height = height;
	}
	
	/**
	 * Adds a model to the list of drawables that will be rendered in the Scene
	 * @param model - the model to be added
	 */
	public void addDrawable(Model model) {
		_drawables.add(model);
	}
	/**
	 * Returns the list of elements that are to be rendered in the Scene
	 * @return
	 * the list of drawables
	 */
	public List<Model> getDrawables() {
		return _drawables;
	}

	/**
	 * Moves the Scene camera to a new position.
	 * @param x - the X coordinate to where the Camera will be moved to.
	 * @param y
	 * @param z
	 */
	public void setCameraPosition(double x, double y, double z) {
		setCameraPosition(new Point3D.Double(x, y, z));
	}
	public void setCameraPosition(Point3D position) {
		_cameraPosition = position;
	}
	
	/**
	 * 
	 * @param x - the X coordinate of the newly constructed Vector3D.
	 * @param y - the Y coordinate of the newly constructed Vector3D.
	 * @param z - the Z coordinate of the newly constructed Vector3D.
	 */
	public void setCameraUp(double x, double y, double z) {
		setCameraUp(new Point3D.Double(x, y, z));
	}
	public void setCameraUp(Point3D position) {
		_cameraUp = position;
	}
	
	public Point3D getCameraPosition() {
		return _cameraPosition;
	}
    public Point3D getCameraLookAt() {
    	return _cameraLookAt;
    }
    public Point3D getCameraUp() {
    	return _cameraUp;
    }
}















