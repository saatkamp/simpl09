package org.simpl.rrs.transformation.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.simpl.rrs.transformation.Transformer;

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
public class TransformerTest {

	private static final String PATH = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		StringBuilder string = new StringBuilder();
		
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + "PickProcess.bpel")));
			
			try {
				String line;
				while ((line = in.readLine()) != null) {
					string.append(line);
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
		
		String response = Transformer.getTransformer().transform(string.toString());
		
		try {
			OutputStream out = new FileOutputStream(new File(PATH + "PickProcess_transformed.bpel"));
			out.write(response.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
