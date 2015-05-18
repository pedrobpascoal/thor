// Copyright 2013 Pedro B. Pascoal
package thor.model.io; 

import java.io.File;
import java.io.IOException;

import thor.Model;
import thor.model.BufferedModel;

/**
 * A class containing static convenience methods for locating ModelReaders and ModelWriters, and performing simple encoding and decoding.
 * 
 * @author Pedro B. Pascoal
 */
public final class ModelIO extends Object {
	public enum OutputFormat {
		ThOR_JAVA,
		OBJ,
		OFF
	}
	/**
	 * Returns a BufferedModel as the result of decoding a supplied File with an ModelReader chosen automatically from among those currently registered.
	 * 
	 * @param file - an InputStream to read from.
	 * @return
	 * a BufferedModel containing the decoded contents of the input, or null.
	 * @throws IllegalArgumentException
	 * if input is null.
	 * @throws IOException
	 * if an error occurs during reading.
	 */
	public static BufferedModel read(File file) throws IOException, IllegalArgumentException {
		if(file == null)
			throw new IllegalArgumentException("ModelIO: Argument cannot be null");
		
		System.out.println("ModelIO read file: " + file.getName());
		
		if(!file.exists() || !file.isFile())
			throw new IOException("ModelIO: file not found!");
		
		String name = file.getName();
		String extension = null;
        int i = name.lastIndexOf('.');
        if (i > 0 &&  i < name.length() - 1) {
        	extension = name.substring(i+1).toLowerCase();
        }
		String absolutePath = file.getAbsolutePath();
		
		if(extension.toString().compareToIgnoreCase("obj") == 0) { // Object File Format (OFF)
			return (new ModelReaderObj(name, extension)).read(absolutePath);
		} else if(extension.toString().compareToIgnoreCase("off") == 0) { // Wavefront
			return (new ModelReaderOff(name, extension)).read(absolutePath);
		} else if(extension.toString().compareToIgnoreCase("ply") == 0) { // Polygon File Format
			return (new ModelReaderPly(name, extension)).read(absolutePath);
		} else if(extension.toString().compareToIgnoreCase("stl") == 0) { // STereoLithography
			return (new ModelReaderStl(name, extension)).read(absolutePath);
		} else if(extension.toString().compareToIgnoreCase("mdl") == 0) { // MDL Blizzard Entertainment 3D model file
			return (new ModelReaderMdl(name, extension)).read(absolutePath);
		} else {
			throw new IOException("ModelIO: file format not recognized");
		}
	}
	
	/**
	 * Writes an image using an arbitrary ModelWriter that supports the given format to a File.
	 * If there is already a File present, its contents are discarded.
	 * 
	 * @param model - a Model to be written.
	 * @param format - a OutputFormat containing the format.
	 * @param output - a File to be written to.
	 * @return
	 * false if no appropriate writer is found.
	 * @throws IllegalArgumentException
	 * if any parameter is null.
	 * @throws IOException
	 * if an error occurs during writing.
	 */
	public static boolean write(Model model, OutputFormat format, File output) throws IOException, IllegalArgumentException {
		if(output == null)
			throw new IllegalArgumentException("ModelIO: Argument cannot be null");
		
		if(format == OutputFormat.ThOR_JAVA) {
			(new ModelWriterThorCode()).write(model, output);
			return true;
		} else if(format == OutputFormat.OBJ) {
			(new ModelWriterObj()).write(model, output);
			return true;
		} else if(format == OutputFormat.OFF) {
			(new ModelWriterOff()).write(model, output);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Writes an image using an arbitrary ImageWriter that supports the given format to a File.
	 * If there is already a File present, its contents are discarded.
	 * 
	 * @param model - a Model to be written.
	 * @param formatName - a String containing the informal name of the format.
	 * @param output - a File to be written to.
	 * @return
	 * false if no appropriate writer is found.
	 * @throws IllegalArgumentException
	 * if any parameter is null.
	 * @throws IOException
	 * if an error occurs during writing.
	 */
	public static boolean write(Model model, String formatName, File output) throws IOException, IllegalArgumentException {
		if(output == null)
			throw new IllegalArgumentException("ModelIO: Argument cannot be null");
		
		if(formatName.equals("java")) {
			(new ModelWriterThorCode()).write(model, output);
			return true;
		} else if(formatName.equals("obj")) {
			(new ModelWriterObj()).write(model, output);
			return true;
		} else if(formatName.equals("off")) {
			(new ModelWriterOff()).write(model, output);
			return true;
		} else {
			return false;
		}
	}
}
