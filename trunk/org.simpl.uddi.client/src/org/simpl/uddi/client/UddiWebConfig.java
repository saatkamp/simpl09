package org.simpl.uddi.client;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UddiWebConfig {

	private static UddiWebConfig config = null;

	private String address = "";

	private String password = "";

	private String username = "";

	private String prefix = "";

	private UddiWebConfig() {

		try {
			File file = new File("WEB-INF/config.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList addressNode = doc.getElementsByTagName("address");
			this.address = ((Node) addressNode).getNodeValue();

			NodeList passwordNode = doc.getElementsByTagName("password");
			this.password = ((Node) passwordNode).getNodeValue();

			NodeList usernameNode = doc.getElementsByTagName("username");
			this.username = ((Node) usernameNode).getNodeValue();

			NodeList prefixNode = doc.getElementsByTagName("key-prefix");
			this.prefix = ((Node) prefixNode).getNodeValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public static UddiWebConfig getInstance() {
		if (config == null) {
			config = new UddiWebConfig();
		}

		return config;
	}
}