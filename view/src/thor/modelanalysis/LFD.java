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
 * 
 * Updated: 2015.05.20
 */
package thor.modelanalysis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import thor.Model;
import thor.modelanalysis.lfd.LightfieldDescriptor;

/**
 * The LFD (Light Field Descriptor) is a view-based geometry feature descriptor.
 * This class provides the <i>default</i> settings for LFD usage, as described by the authors.<br />
 * <br />
 * More information can be found in: 
 * Ding-Yun Chen, Xiao-Pei Tian, Yu-Te Shen and Ming Ouhyoung, 
 * <i>On Visual Similarity Based 3D Model Retrieval</i>, 
 * Computer Graphics Forum (EUROGRAPHICS'03), Vol. 22, No. 3, pp. 223-232, Sept. 2003.
 * 
 * @author Pedro B. Pascoal, pmbp@tecnico.ulisboa.pt
 */
public class LFD {
	public static void run(Model model) {
		//Model model = ModelIO.read(new File(""));
				
		Map<LightfieldDescriptor.Settings, String> settings = new HashMap<LightfieldDescriptor.Settings, String>();
		settings.put(LightfieldDescriptor.Settings.NumberOfLightfields, "10");
		settings.put(LightfieldDescriptor.Settings.NumberOfCameras, "10");
		/*
		settings.put(LightfieldDescriptor.Settings.ImageOutputEnabled, "true");
		settings.put(LightfieldDescriptor.Settings.ImageOutputSize, "512");
		settings.put(LightfieldDescriptor.Settings.ImageOutputFormat, "png");
		settings.put(LightfieldDescriptor.Settings.ImageOutputDirectory, ".\\png\\");
		*/
		
		LightfieldDescriptor lfd = new LightfieldDescriptor();
		lfd.setup(settings);
		
		lfd.extract(model);
		try {
			lfd.writeToFolder(".\\", model.getModelName(), ".sig");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}


