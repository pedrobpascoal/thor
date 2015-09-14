package thor.demo.modelviewer.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FolderFilter extends FileFilter {
	@Override
	public boolean accept(File file) {
		return file.isDirectory();
	}

	@Override
	public String getDescription() {
		return "We only take directories";
	}
}