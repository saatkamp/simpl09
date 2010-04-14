package org.eclipse.simpl.rrs.transformation.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.rrs.transformation.TransformerPlugIn;
import org.eclipse.simpl.rrs.transformation.client.TransformationClient;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class TransformationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO: Hier wird die eigentlich Transformation angestoßen, d.h. es
		// werden alle
		// selektierten (mehrfachauswahl möglich) bzw. im Editor geöffnetete
		// (einfachauswahl) BPEL-Dateien
		// an den Transformator geschickt und die transformierten Dateien müssen
		// auch von diesem Plug-In lokal im Workspace in einem Unterordner
		// "transformed" gespeichert werden

		String projectPath = "";
		String bpelFileName = "";

		// Check if the TransformationService is available or not
		if (TransformationClient.getClient().isTransformerAvailable()) {

			StringBuilder string = new StringBuilder();

			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(
						new FileInputStream(projectPath + bpelFileName+TransformerPlugIn.getDefault().BPEL_EXTENSION)));

				try {
					String line;
					while ((line = in.readLine()) != null) {
						string.append(line);
						string.append("\n");
					}
				} finally {
					in.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String response = TransformationClient.getClient().transform(
					string.toString());

			try {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(projectPath
								+ System.getProperty("file.separator")
								+ "transformed"
								+ System.getProperty("file.separator")
								+ bpelFileName + "_TF" + TransformerPlugIn.getDefault().BPEL_EXTENSION)));
				out.write(response);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MessageDialog
					.openError(
							Display.getCurrent().getActiveShell(),
							"SIMPL TransformationService Connection Exception",
							"The TransformationService isn't available. Please check if your Apache Tomcat Server is running and the TransformationService is installed successfully.");
		}
		return null;
	}

}
