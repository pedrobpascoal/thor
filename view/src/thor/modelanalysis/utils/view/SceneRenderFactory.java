package thor.modelanalysis.utils.view;

import thor.modelanalysis.utils.GLScene;

public class SceneRenderFactory {
	public static SceneRender create(GLScene scene, GLScene.Type type) {
		if(type == GLScene.Type.Silhouette) {
			return new SceneRenderSilhouette(scene);
		} else if(type == GLScene.Type.Contourn) {
			return new SceneRenderContourn(scene);
		} else if(type == GLScene.Type.Sketch) {
			return new SceneRenderSketch(scene);
		} else {
			return new SceneRender(scene);
		}
	}
	
}