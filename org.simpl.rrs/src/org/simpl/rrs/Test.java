package org.simpl.rrs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Test {

	public static void main(String argv[]) {

		System.out.println("blub");
		
		StringBuilder createTableStatement = new StringBuilder();

		InputStream in = null;

		try {
			in = new FileInputStream("C:\\ref1.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		LinkedHashMap<String, String> EPRData = new LinkedHashMap<String, String>();

		EPR epr1 = getDQInfos(in);

		EPRData.put("adapterURI", epr1.getadapterURI());
		EPRData.put("referenceName", epr1.getReferenceName());
		EPRData.put("statement", epr1.getStatement());
		EPRData.put("uri", epr1.getAdress());

		// Erstellen den standard Teil des Befehls
		createTableStatement.append("CREATE TABLE");
		createTableStatement.append(" ");
		// Name der Tabelle
		createTableStatement.append("blub");
		createTableStatement.append(" ( ");

		// Spalten festlegen
		// id einfügen
		createTableStatement.append("ID VARCHAR (20) NOT NULL, ");

		// Einfügen der Einstellungen als Spalten
		for (String name : EPRData.keySet()) {
			createTableStatement.append(name);
			createTableStatement.append(" VARCHAR(255), ");
		}

		// ID als primaryKey setzen
		createTableStatement.append("PRIMARY KEY ( ID)");
		createTableStatement.append(" )");

		// Beispiel Endergebnis:
		// CREATE TABLE GlobalSettings.AuthentificationData (
		// ID VARCHAR (10) NOT NULL,
		// USER VARCHAR (20),
		// PASSWORD VARCHAR (20),
		// PRIMARY KEY ( ID) ) ;

		System.out.println(createTableStatement.toString());
	}

	public static EPR getDQInfos(InputStream in) {

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = null;
		EPR epr1 = new EPR();

		try {
			parser = factory.createXMLStreamReader(in);

			while (parser.hasNext()) {
				int event = parser.next();

				switch (event) {
				case XMLStreamConstants.END_DOCUMENT:
					parser.close();

					break;
				case XMLStreamConstants.START_ELEMENT:
					if (parser.getLocalName().equals("resolutionSystem")) {
						for (int i = 0; i < parser.getAttributeCount(); i++) {
							if (parser.getAttributeLocalName(i).equals(
									"adapterURI")) {
								epr1.setadapterURI(parser.getAttributeValue(i));
							}
						}
					}

					if (parser.getLocalName().equals("referenceParameters")) {
						for (int i = 0; i < parser.getAttributeCount(); i++) {
							if (parser.getAttributeLocalName(i).equals(
									"referenceName")) {
								epr1.setReferenceName(parser
										.getAttributeValue(i));
							}
						}
					}

					if (parser.getLocalName().equals("referenceParameters")) {
						for (int i = 0; i < parser.getAttributeCount(); i++) {
							if (parser.getAttributeLocalName(i).equals(
									"statement")) {
								epr1.setStatement(parser.getAttributeValue(i));
							}
						}
					}

					if (parser.getLocalName().equals("address")) {
						for (int i = 0; i < parser.getAttributeCount(); i++) {
							if (parser.getAttributeLocalName(i).equals("uri")) {
								epr1.setAdress(parser.getAttributeValue(i));
							}
						}
					}

					break;
				case XMLStreamConstants.CHARACTERS:
					break;
				case XMLStreamConstants.END_ELEMENT:
					break;
				default:
					break;
				}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return epr1;
	}

}
