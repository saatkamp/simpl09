package org.eclipse.simpl.rrs.transformation.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.rrs.transformation.TransformerPlugIn;
import org.eclipse.swt.widgets.Display;

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
public class TransformationClient {

	private static TransformationClient client = null;

	private final String TRANSFORMER_WSDL = TransformerPlugIn.getDefault()
			.getPreferenceStore().getString("TRANSFORMER_ADDRESS");

	public static TransformationClient getClient() {
		if (client == null) {
			client = new TransformationClient();
		}
		return client;
	}

	public void transform(String sourcePath, String bpelFileName,
			String targetPath, String rrsNamespaceURI) {

		System.out.println("FILENAME: " + bpelFileName);

		// Check if the TransformationService is available or not
		if (TransformationClient.getClient().isTransformerAvailable()) {

			StringBuilder string = new StringBuilder();

			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(
						new FileInputStream(sourcePath)));

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

			String response = executeTransformation(string.toString(),
					rrsNamespaceURI);

			try {
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(
										targetPath
												+ System
														.getProperty("file.separator")
												+ bpelFileName
												+ TransformerPlugIn
														.getDefault().BPEL_EXTENSION)));
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
							"SIMPL TransformationService: Connection Exception",
							"The TransformationService isn't available. Please check if your Apache Tomcat Server is running and the TransformationService is installed successfully.");
		}
	}

	public boolean isTransformerAvailable() {
		boolean isAvailable = false;
		URL url;
		try {
			url = new URL(TRANSFORMER_WSDL);
			URLConnection connection = url.openConnection();
			connection.connect();
			isAvailable = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAvailable;
	}

	private String executeTransformation(String bpelFileContent,
			String rrsNamespaceURI) {
		RRSTransformationService transformationService = new RRSTransformationService_Service()
				.getRRSTransformationServicePort();

		String response = "";

		response = transformationService.transform(bpelFileContent,
				rrsNamespaceURI);

		return response;
	}
}
