package view;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import Model.file;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;
 
public final class OpenFile {
	

	JFileChooser fileChooser = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	private file loadF;
	
	public void PickMe(createNewFileGUI currGUI) throws Exception {
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			java.io.File file = fileChooser.getSelectedFile();
			file f = new file(null,file.getPath(),null,null); //filename, dir, filetype, fileOuner
			setF(f);
			Scanner input = new Scanner(file);
			
			while(input.hasNext()) {
				sb.append(input.hasNext());
				sb.append("\n");

			}
			
			
			currGUI.setTextField2(f.getDirection());
			input.close();
		}
		else
			sb.append("No files was selected");
	}

	public file getF() {
		return loadF;
	}

	public void setF(file loadF) {
		this.loadF = loadF;
	}
	
	
}