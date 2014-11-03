// Copyright 2012 Pedro B. Pascoal
package thor.model.io; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import thor.model.BufferedModel;
import thor.model.geoset.BufferedMesh;

// Polygon File Format
class ModelReaderPly extends ModelReader {
	
	public ModelReaderPly(String name, String extension) {
		super(name, extension);
	}
	public BufferedModel read(String filename) throws IOException {
		BufferedReader hdrReader = new BufferedReader(new FileReader(filename));
        String plyHeader = hdrReader.readLine();
        if (!"ply".equals(plyHeader)) {
    		hdrReader.close();
            throw new IOException("ModelReader: File header must start with \"ply\"!");
        }
        String format = "";
        int nVertex = 0, nFaces = 0;//, nProperties = 0;

        for (String line = hdrReader.readLine(); true; line = hdrReader.readLine()) {
            if (line == null) {
        		hdrReader.close();
                throw new IOException("ModelReader: File ended unexpectatly!");
            }
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("format ")) {
            	format = line;
            } else if (line.startsWith("element ")){
                if (line.contains("vertex"))
                	nVertex = Integer.valueOf(line.split("\\s+")[2]);
                else if (line.contains("face"))
                	nFaces = Integer.valueOf(line.split("\\s+")[2]);
        	} else if (line.startsWith("property ")) {
                //nProperties++;
            } else if (line.startsWith("end_header")) {
                break;
            } else if (!line.startsWith("comment ")) {
        		hdrReader.close();
                throw new IOException("ModelReader: File header contains unsupported entry.");
            }
        }
        if (format.isEmpty()) {
    		hdrReader.close();
            throw new IOException("ModelReader: Missing format entry.");
        }
        
		BufferedModel model = new BufferedModel(filename, "ply");
		BufferedMesh mesh = new BufferedMesh();
		String line = null;
		Scanner lScanner = null;
		for (int i = 0; i<nVertex; i++){
			line = hdrReader.readLine();
			line = line.replace('.',',');
			lScanner = new Scanner(line);
			mesh.addVertex(lScanner.nextDouble(), lScanner.nextDouble(), lScanner.nextDouble());
		}
		for (int i = 0; i<nFaces; i++){
			line = hdrReader.readLine();
			lScanner = new Scanner(line);
			ArrayList<Integer> intList = new ArrayList<Integer>();
			int k = lScanner.nextInt();
			for(int j = 0; j<k; j++){
				int x = lScanner.nextInt();
				intList.add(x);
			}
			mesh.addFace(intList);
		}
		hdrReader.close();
		lScanner.close();
		model.addMesh(mesh);
		return model;
	}
}