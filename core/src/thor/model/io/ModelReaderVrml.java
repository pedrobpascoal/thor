package thor.model.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import thor.model.BufferedModel;
import thor.model.geoset.BufferedMesh;

//Virtual Reality Modeling Language
class ModelReaderVrml extends ModelReader {
	private static final String COMMENT_SYMBOL = "#";

	public ModelReaderVrml(String name, String extension) {
		super(name, extension);
	}

	@Override
	public BufferedModel read(String filename) throws IOException {
		BufferedModel model = new BufferedModel(_name, _extension);
		BufferedMesh mesh = new BufferedMesh();

		Scanner root = new Scanner(new FileReader(filename));
		while(root.hasNext()) {
			String nextLine = root.nextLine();
			if(nextLine.startsWith(COMMENT_SYMBOL))
				continue;
			
			Scanner line = new Scanner(nextLine);
			
			line.close();
			
		}
		model.addMesh(mesh);
		root.close();
		
		return model;
	}
}