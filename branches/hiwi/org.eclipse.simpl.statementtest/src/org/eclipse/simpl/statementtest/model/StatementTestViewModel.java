package org.eclipse.simpl.statementtest.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.simpl.statementtest.StatementTestPlugin;
import org.eclipse.simpl.statementtest.ui.views.StatementTestView;
import org.eclipse.ui.IViewPart;

/**
 * <b>Purpose: Holds all data about tests and results that are shown in the view.</b> <br>
 * <b>Description:</b>Automatically updates the view on data changes.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTestViewModel.java 72 2010-07-23 20:02:14Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTestViewModel {
  static final String STATEMENT_TESTS_PREFERENCE = "statementTests";

  static StatementTestViewModel statementTestModel;
  static StatementTestView statementTestView = null;
  static List<StatementTest> statementTests = new ArrayList<StatementTest>();

  /**
   * Initializes the model.
   * 
   * @return The model instance.
   */
  public static StatementTestViewModel getInstance() {
    if (statementTestModel != null) {
      return statementTestModel;
    }

    statementTestModel = new StatementTestViewModel();

    // loadModel();

    return statementTestModel;
  }

  /**
   * Sets the view that is used to update by this model.
   * 
   * @param view
   */
  public void setView(IViewPart view) {
    statementTestView = (StatementTestView) view.getViewSite().getPart();
  }

  /**
   * Adds a statement test.
   * 
   * @param statementTest
   */
  public void addStatementTest(StatementTest statementTest) {
    statementTests.add(statementTest);
    statementTestView.addStatementTest(statementTest);
    statementTestView.showLog();

    // save
    // saveModel();
  }

  public String[] getStatementTests() {
    List<String> tests = new ArrayList<String>();

    for (StatementTest statementTest : statementTests) {
      tests.add(statementTest.getActivity().getName());
    }

    return tests.toArray(new String[tests.size()]);
  }

  /**
   * Loads the statement tests from the plugin preference store.
   */
  @SuppressWarnings( { "unchecked", "unused" })
  private static void loadModel() {
    String serializedStatementTests = null;
    ObjectInputStream in = null;
    List<StatementTest> deserializedStatementTests = null;

    serializedStatementTests = StatementTestPlugin.INSTANCE.getPreferenceStore()
        .getString(STATEMENT_TESTS_PREFERENCE);

    if (serializedStatementTests != null && !serializedStatementTests.equals("")) {
      try {
        in = new ObjectInputStream(new ByteArrayInputStream(serializedStatementTests
            .getBytes()));

        deserializedStatementTests = (List<StatementTest>) in.readObject();
        in.close();

        if (deserializedStatementTests != null) {
          statementTests = deserializedStatementTests;
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * Saves the statement tests to the plugin preference store.
   */
  @SuppressWarnings("unused")
  private static void saveModel() {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;
    String serializedStatementTests = null;

    try {
      out = new ObjectOutputStream(bos);
      out.writeObject(statementTests);
      out.close();

      serializedStatementTests = new String(bos.toByteArray());
      StatementTestPlugin.INSTANCE.getPreferenceStore().setValue(
          STATEMENT_TESTS_PREFERENCE, serializedStatementTests);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}