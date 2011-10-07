package org.eclipse.simpl.statementtest.model.results;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>Purpose:</b>Common abstract result especially for SDO formats.<br>
 * <b>Description:</b>Provides the result document that is parsed from SDO XML.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: Result.java 81 2010-08-03 16:34:23Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Result {
  protected Document doc = null;
  private DocumentBuilder parser = null;
  private String result = null;

  public Result(String result) {
    if (result != null) {
      this.result = result;

      try {
        parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = parser.parse(new InputSource(new StringReader(result)));
      } catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Creates an empty result.
   */
  public Result() {

  }

  @Override
  public String toString() {
    return this.result;
  }
}