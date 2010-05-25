package org.simpl.uddi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class UddiWebConfig {

	private static UddiWebConfig config = null;

	private String address = "";

	private String password = "";

	private String username = "";

	private UddiWebConfig() throws FileNotFoundException {

		SAXBuilder builder = new SAXBuilder();
		Document doc = null;

		File file = new File(System.getProperty("user.dir")
				+ "\\webapps\\juddiweb\\WEB-INF\\config.xml");

		// File file = new File("config.xml");

		try {
			doc = builder.build(new FileInputStream(file));
			Element root = doc.getRootElement();

			Element address = root.getChild("address");

			Element username = root.getChild("username");

			Element password = root.getChild("password");

			this.address = address.getText();

			this.username = username.getText();

			this.password = password.getText();

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserdir() {
		return System.getProperty("user.dir");
	}

	public static UddiWebConfig getInstance() throws FileNotFoundException {
		if (config == null) {
			config = new UddiWebConfig();
		}

		return config;
	}

	public void writeConfig() {
		File file = new File(System.getProperty("user.dir")
				+ "\\webapps\\juddiweb\\WEB-INF\\config.xml");

		SAXBuilder builder = new SAXBuilder();

		try {
			Document doc = builder.build(file);
			
			XMLOutputter outputter = new XMLOutputter();

			outputter.setFormat(Format.getPrettyFormat());

			if (file == null || !file.exists()) {
				Element rootElement = new Element("juddi-properties");

				Element address = new Element("address");

				address.setText(this.address);

				Element username = new Element("username");

				username.setText(this.username);

				Element password = new Element("password");

				password.setText(this.password);

				rootElement.addContent(address);

				rootElement.addContent(username);

				rootElement.addContent(password);

				doc.addContent(rootElement);

				

			} else {
				
				Element root = doc.getRootElement();
				
				root.getChild("address").setText(getAddress());

				root.getChild("username").setText(getUsername());

				root.getChild("password").setText(getPassword());
				
			}

			FileOutputStream out = new FileOutputStream(file);

			outputter.output(doc, out);
			
			out.close();

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}