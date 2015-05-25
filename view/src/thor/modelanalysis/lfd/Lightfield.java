// Copyright 2013 Pedro B. Pascoal
package thor.modelanalysis.lfd;

import java.awt.image.BufferedImage;

import thor.modelanalysis.common.Feature;

/**
 * 
 * @author Pedro B. Pascoal
 */
class Lightfield implements Feature {
	BufferedImage[] _silhouettes = null;
	int _silhouettesCount = 0;
	
	public Lightfield(int silhouettesCount) {
		_silhouettes = new BufferedImage[silhouettesCount];
		_silhouettesCount = silhouettesCount;
	}
	
	public void addImage(int index, BufferedImage silhouette) {
		_silhouettes[index] = silhouette;
	}
	public int size() {
		return _silhouettesCount;
	}
	public BufferedImage[] getImages() {
		return _silhouettes;
	}
}
