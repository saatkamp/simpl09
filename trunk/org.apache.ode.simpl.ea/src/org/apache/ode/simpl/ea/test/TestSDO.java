package org.apache.ode.simpl.ea.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ode.simpl.ea.sdo.SDOSender;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

public class TestSDO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream file;
		try {
			file = new FileInputStream(new File("simpl-activity-sdo.xsd"));
			InputStream is = file;

			if (is != null){
				XSDHelper.INSTANCE.define(is, null);

				is.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Hier werden alle Daten für das Auditing in ein SDO gepackt und abgeschickt.
		 * 
		 * Mit diesem Vorgehen können wir ohne Probleme alle für SIMPL relevanten Daten
		 * auslesen und so ein SIMPL Auditing erstellen. Die restlichen Daten, die bei
		 * der Ausführung von nicht DM-Aktivitäten anfallen, werden dadurch allerdings
		 * vorerst nicht berücksichtigt. 
		 */
		
		//queryObject erzeugen und mit Werten füllen
		DataObject queryObject = DataFactory.INSTANCE.create("http://www.example.org/simpl-activity-sdo", "tQueryActivitySDO");
		//Standardattribute die alle DM-SDOs haben
		queryObject.setString("activityName", "QueryActivity");
		queryObject.setString("dsType", "database");
		queryObject.setString("dsKind", "rdb");
		queryObject.setString("dsStatement", "SELECT * FROM table WHERE column='a'");
		queryObject.setString("dsLanguage", "SQL");
		queryObject.setString("dsAddress", "http://localhost:8080");
		//Spezialattribut das nur das QuerySDO hat
		queryObject.setString("queryTarget", "COPY");
		
		//Hier hinterlegen wir noch, ob die Ausführung der Aktivität erfolgreich war
		queryObject.setBoolean("successfullExecution", true);
		
		//Hier wird ein Containment-SDO erzeugt, dass heisst ein SDO das in das QueryActivitySDO eingebunden ist
		/* TODO:
		 * Dies muss noch sowohl im XSD als auch hier in der Implementierung verfeinert und
		 * um die fehlenden Teile (Scope, Events) ergänzt werden.
		 * Auch die Plausibilität und Verwendbarkeit der bereits vorhandenen Elemente sollte nochmal überprüft werden
		 */
		DataObject processIObject = queryObject.createDataObject("ProcessInstance");
		processIObject.setLong("processID", 12L);
		
		
		//SDO "abschicken", d.h. in/auf einer Datenquelle speichern
		/* TODO:
		 * Vorerst wird es nur in eine Datei geschrieben. Die komplette Funktionalität
		 * sollte dafür aber in der SDOSender-Klasse liegen
		 */
		SDOSender.getInstance().sendSDO(queryObject, "QueryActivity");
	}

}
