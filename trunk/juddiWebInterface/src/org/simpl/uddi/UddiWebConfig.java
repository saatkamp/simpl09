package org.simpl.uddi;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class UddiWebConfig {

	private static UddiWebConfig config = null;

	private String address = "";

	private String password = "";

	private String username = "";

	private UddiWebConfig() {

		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		
		File file = new File(System.getProperty("user.dir") + "\\webapps\\juddiweb\\WEB-INF\\config.xml");
		
//		File file = new File("config.xml");
			
			try {
				doc = builder.build(new FileInputStream(file));
				Element root = doc.getRootElement();
				
				Element address = root.getChild("address");
				
				Element username = root.getChild("username");
				
				Element password = root.getChild("password");
				
				this.address = address.getText();
				
				this.username = username.getText();
				
				this.password = password.getText();
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	public static UddiWebConfig getInstance() {
		if (config == null) {
			config = new UddiWebConfig();
		}

		return config;
	}
}