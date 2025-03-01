package ejercicio_unidad1;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		// TODO Auto-generated method stub
		
		if (file.isDirectory()) {
			return true;
		}
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name);
		
		if(extension== null) {
			return false;
		}
		
		if (extension.equals("per")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "person database files (*.per)";
	}

}
