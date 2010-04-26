package org.simpl.rrs.retrieval;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.simpl.rrs.EPR;
import org.simpl.rrs.RRS;
import org.simpl.rrs.dsadapter.DSAdapter;
import org.simpl.rrs.dsadapter.DSAdapterProvider;
import org.simpl.rrs.dsadapter.exceptions.ConnectionException;

public class RetrievalServiceImpl implements RetrievalService {
	
	public Object get(String uri, String adapterType, String referenceName, String statement) {

		Connection connection = null;

		String dsType = null;
		String dsSubtype = null;
		String dsLanguage = null;
		Object data = null;
		
		StringTokenizer st = new StringTokenizer(adapterType, ":");
		dsType = st.nextToken();
		dsSubtype = st.nextToken();
		dsLanguage = st.nextToken();

		System.out.println(uri + " " + statement + " "
				+ dsType + " " + dsSubtype);

		// Hier der ganze selbe Mist wie bei DatasourceService, Adapter
		// auswählen
		// anhand von Type und Subtype, und Daten holen...
		DSAdapter dsAdapter = RRS.getInstance().dsAdapter(dsType, dsSubtype);
		try {
			connection = dsAdapter.openConnection(uri);
			data = dsAdapter.retrieveData(uri, statement);
			dsAdapter.closeConnection(connection);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}

//	public static EPR getDSInfos(InputStream in) {
//
//		XMLInputFactory factory = XMLInputFactory.newInstance();
//		XMLStreamReader parser = null;
//		EPR epr1 = new EPR();
//
//		try {
//			parser = factory.createXMLStreamReader(in);
//
//			while (parser.hasNext()) {
//				int event = parser.next();
//
//				switch (event) {
//				case XMLStreamConstants.END_DOCUMENT:
//					parser.close();
//
//					break;
//				case XMLStreamConstants.START_ELEMENT:
//					if (parser.getLocalName().equals("resolutionSystem")) {
//						for (int i = 0; i < parser.getAttributeCount(); i++) {
//							if (parser.getAttributeLocalName(i).equals(
//									"adapterURI")) {
//								epr1.setadapterURI(parser.getAttributeValue(i));
//							}
//						}
//					}
//
//					if (parser.getLocalName().equals("referenceParameters")) {
//						for (int i = 0; i < parser.getAttributeCount(); i++) {
//							if (parser.getAttributeLocalName(i).equals(
//									"statement")) {
//								epr1.setStatement(parser.getAttributeValue(i));
//							}
//						}
//					}
//
//					if (parser.getLocalName().equals("address")) {
//						for (int i = 0; i < parser.getAttributeCount(); i++) {
//							if (parser.getAttributeLocalName(i).equals("uri")) {
//								epr1.setAdress(parser.getAttributeValue(i));
//							}
//						}
//					}
//
//					break;
//				case XMLStreamConstants.CHARACTERS:
//					break;
//				case XMLStreamConstants.END_ELEMENT:
//					break;
//				default:
//					break;
//				}
//			}
//		} catch (XMLStreamException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return epr1;
//	}

}
