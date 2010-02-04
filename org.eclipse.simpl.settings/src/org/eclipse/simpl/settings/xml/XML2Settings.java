package org.eclipse.simpl.settings.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XML2Settings {

	public static void main(String[] args) {
		if (args.length != 6) {
			System.err.println("Attention:");
			System.err.println("jdom.jar must be added to classpath.");
			System.err.println("Usage:");
			System.err.println("java ExampleJdomAddWrite <XmlFile> <NewFile>"
					+ " <ParentElem> <Child> <FindText> <New>");
			System.err.println("Example:");
			System.err.println("java -classpath .;jdom.jar ExampleJdomAddWrite"
					+ " MyXmlFile.xml NewXmlFile.xml Button Title"
					+ " \"Mein dritter Button\" \"Mein neuer Button\"");
			System.exit(1);
		}
		try {
			// ---- Read XML file ----
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(args[0]));
			// ---- Modify XML data ----
			Element root = doc.getRootElement();
			List listParentElements = root.getChildren(args[2]); // <ParentElem>
			for (int i = 0; i < listParentElements.size(); i++) {
				// Find searched element with given text:
				Element elMain = (Element) (listParentElements.get(i));
				if (null == elMain)
					continue;
				Element elChild = elMain.getChild(args[3]); // <Child>
				if (null == elChild)
					continue;
				String s = elChild.getTextTrim();
				if (null == s || !s.equals(args[4]))
					continue; // <FindText>
				// Add new element at correct position:
				Element elNew = new Element(args[2]); // <ParentElem>
				elNew.addContent((new Element(args[3])).addContent(args[5]));
				listParentElements.add(i, elNew); // <New>
				// ---- Write XML file ----
				XMLOutputter outp = new XMLOutputter();
				outp.setFormat(Format.getPrettyFormat());
				outp.output(doc, new FileOutputStream(new File(args[1])));
				break; // <NewFile>
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
