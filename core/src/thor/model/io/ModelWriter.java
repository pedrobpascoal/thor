// Copyright 2013 Pedro B. Pascoal
package thor.model.io; 

import java.io.IOException;

import thor.Model;

//MYTODO: javadoc
public abstract class ModelWriter extends Object {

	public abstract void write(Model model) throws IOException;
	
}

