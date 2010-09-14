package org.simpl.core.plugins.dataformat.fs;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from every file (also unstructured once)
 * and vice versa.<br>
 * <b>Description:</b>This is just a very simple implementation which handles
 * the data of a file as a string.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id:$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RandomFileDataFormat extends DataFormatPlugin<File, File> {
	static Logger logger = Logger.getLogger(RandomFileDataFormat.class);

	/**
	 * Initialize the plug-in.
	 */
	public RandomFileDataFormat() {
		this.setType("RandomFile");
		this.setSchemaFile("RandomFileDataFormat.xsd");
		this.setSchemaType("tRandomFileDataFormat");

		// Set up a simple configuration that logs on the console.
		PropertyConfigurator.configure("log4j.properties");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang
	 * .Object )
	 */
	public DataObject toSDO(File file) {

		DataObject sdo = this.getSDO();

		if (RandomFileDataFormat.logger.isDebugEnabled()) {
			RandomFileDataFormat.logger
					.debug("Convert data from 'File' to 'DataObject'.");
		}

		if (file.isDirectory()) {
			try {
				sdo.setString("folder", file.getName());

				// Returns only files, sub directories will be ignored
				File[] directoryFiles = file.listFiles(new FileFilter() {

					@Override
					public boolean accept(File pathname) {
						return pathname.isFile();
					}
				});

				for (File current : directoryFiles) {
					DataObject fileSDO = sdo.createDataObject("file");

					fileSDO.setString("name", current.getName());

					// Read the entire contents of the file and save it in a
					// string
					String content = FileUtils.readFileToString(current);

					fileSDO.setString("content", content);

					if (logger.isDebugEnabled()) {
						logger.debug("Filename: " + fileSDO.getString("name"));
						logger.debug("File Content: "
								+ fileSDO.getString("content"));
					}
				}

			} catch (IOException e) {
				logger
						.error(
								"Exception during transformation from file to data object",
								e);
			}
		} else {
			logger
					.error("The specified file is not a directory. An empty SDO will be returned.");
		}

		return sdo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
	 * .DataObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public File fromSDO(DataObject data) {
		// TODO: Check if the temporary files are deleted, else there
		// could be a huge amount of temporary data.
		File folder = createTempDir(data.getString("folder"));
		folder.deleteOnExit();

		if (RandomFileDataFormat.logger.isDebugEnabled()) {
			RandomFileDataFormat.logger
					.debug("Convert data from 'DataObject' to 'File'.");
		}

		try {
			List<DataObject> files = data.getList("file");

			for (DataObject fileSDO : files) {

				File file = new File(folder, fileSDO.getString("name"));

				String content = fileSDO.getString("content");

				FileUtils.writeStringToFile(file, content);
			}

		} catch (IOException e) {
			logger.error(
					"Exception during transformation from data object to file",
					e);
		}

		return folder;
	}

	/**
	 * Create a new temporary directory. Use something like
	 * {@link #recursiveDelete(File)} to clean this directory up since it isn't
	 * deleted automatically
	 * 
	 * @return the new directory
	 * @throws IOException
	 *             if there is an error creating the temporary directory
	 */
	public File createTempDir(String folder) {
		final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
		File newTempDir;
		
		String dirName = folder;
		newTempDir = new File(sysTempDir, dirName);
		
		if (newTempDir.exists() && newTempDir.isDirectory()) {
			for (File content : newTempDir.listFiles()) {
				content.delete();
			}
			newTempDir.delete();
			
			logger.info("Temporary directory " + newTempDir + " already exists and was deleted to use it again.");
		}

		if (newTempDir.mkdirs()) {
			return newTempDir;
		} else {
			logger.error("Failed to create temp dir named "
					+ newTempDir.getAbsolutePath());
		}
		return null;
	}

	// /**
	// * Recursively delete file or directory
	// *
	// * @param fileOrDir
	// * the file or dir to delete
	// * @return true if all files are successfully deleted
	// */
	// public boolean recursiveDelete(File fileOrDir) {
	// if (fileOrDir.isDirectory()) {
	// // recursively delete contents
	// for (File innerFile : fileOrDir.listFiles()) {
	// if (!recursiveDelete(innerFile)) {
	// return false;
	// }
	// }
	// }
	//
	// return fileOrDir.delete();
	// }

}