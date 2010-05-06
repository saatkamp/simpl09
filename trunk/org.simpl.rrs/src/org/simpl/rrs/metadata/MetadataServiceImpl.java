package org.simpl.rrs.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.databinding.types.NCName;
import org.apache.axis2.databinding.types.URI;
import org.apache.axis2.databinding.types.URI.MalformedURIException;
import org.simpl.rrs.RRS;
import org.simpl.rrs.webservices.EPR;
import org.simpl.rrs.webservices.EPRArray;
import org.simpl.rrs.webservices.ReferenceParameters;
import org.simpl.rrs.webservices.ReferenceProperties;
import org.simpl.rrs.webservices.ServiceName;

public class MetadataServiceImpl implements MetadataService {

	private static final String DATABASE_NAME = "rrsDB";
	private static final String Reference_TABLE_NAME = "ReferenceTable";

	private Connection getConnection() {
		Connection connect = null;
		StringBuilder uri = new StringBuilder();
		uri.append("jdbc:derby:");
		uri.append(DATABASE_NAME);
		uri.append(";create=true");
		try {
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connect = DriverManager.getConnection(uri.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	@Override
	public EPRArray getAllEPR() {

		List<EPR> allEPRs = new ArrayList<EPR>();
		ResultSet rs = null;

		Connection conn = getConnection();

		try {
			Statement statm = conn.createStatement();
			rs = statm.executeQuery("SELECT * FROM " + Reference_TABLE_NAME);
			
			while (rs.next()) {
				/*
				 * "referenceName", epr.getParameters().getReferenceName()
				 * "rrsAddress", epr.getRrsAddress(); "adapterType",
				 * epr.getProperties().getRrsAdapter()); "statement",
				 * epr.getParameters().getStatement() "dsAddress",
				 * epr.getParameters().getDsAddress()) "portType",
				 * epr.getPortType()) "serviceName",
				 * epr.getService().getServiceName() "portName",
				 * epr.getService().getPortName() "rrsPolicy",
				 * epr.getRrsPolicy()
				 */
				EPR rsEPR = new EPR();
				ReferenceProperties properties = new ReferenceProperties();
				ReferenceParameters parameters = new ReferenceParameters();
				ServiceName serviceName = new ServiceName();

				parameters.setReferenceName(rs.getString(1));
				try {
					if (!rs.getString(2).isEmpty()){
						rsEPR.setAddress(new URI(rs.getString(2)));
					}
				} catch (MalformedURIException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				properties.setResolutionSystem(rs.getString(3));
				parameters.setStatement(rs.getString(4));
				parameters.setDsAddress(rs.getString(5));
				String portType = rs.getString(6);
				if (portType != null){
					rsEPR.setPortType(QName.valueOf(portType));
				}
				String service = rs.getString(7);
				if (service != null){
					serviceName.setQName(QName.valueOf(service));
				}
				String portName = rs.getString(8);
				if (portName != null){
					serviceName.setPortName(new NCName(service));
				}
				
				rsEPR.setPolicy(rs.getString(9));

				rsEPR.setReferenceParameters(parameters);
				rsEPR.setReferenceProperties(properties);
				rsEPR.setServiceName(serviceName);

				allEPRs.add(rsEPR);
			}
			
			rs.close();
			statm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EPRArray eprArray = new EPRArray();
		eprArray.setItem(allEPRs.toArray(new EPR[allEPRs.size()]));
		return eprArray;
	}

	@Override
	public EPR getEPR(String name) {

		Connection conn = getConnection();
		ResultSet rs = null;

		EPR rsEPR = null;

		try {
			Statement statm = conn.createStatement();
			rs = statm.executeQuery("SELECT * FROM " + Reference_TABLE_NAME
					+ " WHERE REFERENCENAME = '" + name + "'");

			while (rs.next()) {
				/*
				 * "referenceName", epr.getParameters().getReferenceName()
				 * "rrsAddress", epr.getRrsAddress(); "adapterType",
				 * epr.getProperties().getRrsAdapter()); "statement",
				 * epr.getParameters().getStatement() "dsAddress",
				 * epr.getParameters().getDsAddress()) "portType",
				 * epr.getPortType()) "serviceName",
				 * epr.getService().getServiceName() "portName",
				 * epr.getService().getPortName() "rrsPolicy",
				 * epr.getRrsPolicy()
				 */
				rsEPR = new EPR();
				ReferenceProperties properties = new ReferenceProperties();
				ReferenceParameters parameters = new ReferenceParameters();
				ServiceName serviceName = new ServiceName();

				parameters.setReferenceName(rs.getString(1));
				try {
					if (!rs.getString(2).isEmpty()){
						rsEPR.setAddress(new URI(rs.getString(2)));
					}
				} catch (MalformedURIException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				properties.setResolutionSystem(rs.getString(3));
				parameters.setStatement(rs.getString(4));
				parameters.setDsAddress(rs.getString(5));
				String portType = rs.getString(6);
				if (portType != null){
					rsEPR.setPortType(QName.valueOf(portType));
				}
				String service = rs.getString(7);
				if (service != null){
					serviceName.setQName(QName.valueOf(service));
				}
				String portName = rs.getString(8);
				if (portName != null){
					serviceName.setPortName(new NCName(service));
				}
				
				rsEPR.setPolicy(rs.getString(9));

				rsEPR.setReferenceParameters(parameters);
				rsEPR.setReferenceProperties(properties);
				rsEPR.setServiceName(serviceName);
			}
			
			rs.close();
			statm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		TODO: IN ODE DURCHFÜHREN, DA MAN KEINE NODES VERSCHICKEN KANN...
//		if (rsEPR != null) {
//
//			try {
//				DocumentBuilderFactory factory =
//				      DocumentBuilderFactory.newInstance();
//				factory.setNamespaceAware(true);
//				DocumentBuilder builder = factory.newDocumentBuilder();
//				
//				Document doc = builder.newDocument();
//				
//				Node eprNode = doc; 
//				 
//				// create JAXB context and instantiate marshaller
//				JAXBContext context = JAXBContext.newInstance(EPR.class);
//				Marshaller m = context.createMarshaller();
//				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//				m.marshal(rsEPR, eprNode);
//				
//				//Testausgabe
//				m.marshal(rsEPR, System.out);
//				
//				return eprNode;
//			} catch (JAXBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ParserConfigurationException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}

		return rsEPR;
	}

	/* (non-Javadoc)
	 * @see org.simpl.rrs.metadata.MetadataService#getAvailableAdapters()
	 */
	@Override
	public String[] getAvailableAdapters() {
		List<String> adapters = RRS.getInstance().getAvailableAdapters();
		
		return adapters.toArray(new String[0]);
	}

}
