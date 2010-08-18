package org.eclipse.simpl.statementtest.ui.wizards.pages;

import org.eclipse.bpel.model.Variable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariable;
import org.eclipse.simpl.statementtest.model.variables.ParameterVariable;
import org.eclipse.simpl.statementtest.ui.wizards.StatementTestWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * <b>Purpose:</b>Wizard page for the statement screening.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * TODO: Textfeld für Limit
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementScreeningPage.java 88 2010-08-18 14:55:33Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementScreeningPage extends StatementTestWizardPage {
  static final String PAGE_TITLE = "Check the settings";
  static final String PAGE_MESSAGE = "The statement is executed on the data source and may change or delete data.";
  static final String STATEMENT_GROUP_TEXT = "Statement";
  static final String DATA_SOURCE_GROUP_TEXT = "Data Source";
  static final String OPTIONS_GROUP_TEXT = "Result Options";
  static final String LIMITED_RESULT_LABEL = "Limited: limits the result to 10 tuple (uses SQL LIMIT) ";
  static final String EXCLUSIVE_RESULT_LABEL = "Exclusive: only shows affected tuple and values ";
  static final String ENHANCED_RESULT_LABEL = "Enhanced: also shows original tuple and values ";

  StatementTest statementTest = null;
  GridData gridData = null;
  Text text = null;
  Group statementGroup = null;
  Group dataSourceGroup = null;
  Group optionsGroup = null;

  Button limitedResultCheckbox = null;
  Button exclusiveResultCheckbox = null;
  Button enhancedResultCheckbox = null;
  Label limitedResultLabel = null;
  Label exclusiveResultLabel = null;
  Label enhancedResultLabel = null;
  Label optionsLabel = null;

  Label dataSourceNameLabel = null;
  Label dataSourceTypeLabel = null;
  Label dataSourceSubTypeLabel = null;
  Label dataSourceLanguageLabel = null;
  Label dataSourceAddressLabel = null;
  Label dataSourceDataFormatLabel = null;

  /**
   * @param pageName
   * @param title
   * @param titleImage
   */
  public StatementScreeningPage(String pageName, ImageDescriptor titleImage) {
    super(pageName, PAGE_TITLE, titleImage);

    titleImage = ImageDescriptor.createFromImageData(titleImage.getImageData().scaledTo(
        56, 56));

    this.setImageDescriptor(titleImage);
    this.setMessage(StatementScreeningPage.PAGE_MESSAGE);
  }

  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);
    statementTest = ((StatementTestWizard) this.getWizard()).getStatementTest();

    dataSourceGroup = new Group(this.pageComposite, SWT.SHADOW_NONE);
    dataSourceGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    dataSourceGroup.setLayout(new GridLayout(2, true));
    dataSourceGroup.setText(DATA_SOURCE_GROUP_TEXT);

    statementGroup = new Group(this.pageComposite, SWT.SHADOW_NONE);
    statementGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    statementGroup.setLayout(new GridLayout());
    statementGroup.setText(STATEMENT_GROUP_TEXT);

    gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    gridData.heightHint = 50;
    text = new Text(statementGroup, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
    text.setLayoutData(gridData);

    dataSourceNameLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    dataSourceTypeLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceTypeLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    dataSourceSubTypeLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceSubTypeLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    dataSourceLanguageLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceLanguageLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    dataSourceAddressLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceAddressLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    dataSourceDataFormatLabel = new Label(dataSourceGroup, SWT.NONE);
    dataSourceDataFormatLabel
        .setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

    this.updateDataSource();

    if (!this.statementTest.getActivity().eClass().getName().equals("CreateActivity")) {
      optionsGroup = new Group(this.pageComposite, SWT.SHADOW_NONE);
      optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
      optionsGroup.setLayout(new GridLayout(2, false));
      optionsGroup.setText(OPTIONS_GROUP_TEXT);

      limitedResultLabel = new Label(optionsGroup, SWT.NONE);
      limitedResultLabel.setText(LIMITED_RESULT_LABEL);
      limitedResultLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseDown(MouseEvent e) {
          limitedResultCheckbox.setSelection(!limitedResultCheckbox.getSelection());
          statementTest.setLimitedResult(limitedResultCheckbox.getSelection());
        }
      });

      limitedResultCheckbox = new Button(optionsGroup, SWT.CHECK);
      limitedResultCheckbox.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          statementTest.setLimitedResult(((Button) e.widget).getSelection());
        }
      });
    }

    if (!this.statementTest.getActivity().eClass().getName().equals("QueryActivity")
        && !this.statementTest.getActivity().eClass().getName().equals("CreateActivity")
        && !this.statementTest.getActivity().eClass().getName().equals("DropActivity")
        && !this.statementTest.getActivity().eClass().getName().equals("TransferActivity")
        && !this.statementTest.getActivity().eClass().getName().equals("RetrieveDataActivity")) {
      exclusiveResultLabel = new Label(optionsGroup, SWT.NONE);
      exclusiveResultLabel.setText(EXCLUSIVE_RESULT_LABEL);
      exclusiveResultLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseDown(MouseEvent e) {
          exclusiveResultCheckbox.setSelection(!exclusiveResultCheckbox.getSelection());
          statementTest.setExclusiveResult(exclusiveResultCheckbox.getSelection());
        }
      });

      exclusiveResultCheckbox = new Button(optionsGroup, SWT.CHECK);
      exclusiveResultCheckbox.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          statementTest.setExclusiveResult(((Button) e.widget).getSelection());
        }
      });

      enhancedResultLabel = new Label(optionsGroup, SWT.NONE);
      enhancedResultLabel.setText(ENHANCED_RESULT_LABEL);
      enhancedResultLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseDown(MouseEvent e) {
          enhancedResultCheckbox.setSelection(!enhancedResultCheckbox.getSelection());
          statementTest.setEnhancedResult(enhancedResultCheckbox.getSelection());
        }
      });

      enhancedResultCheckbox = new Button(optionsGroup, SWT.CHECK);
      enhancedResultCheckbox.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          statementTest.setEnhancedResult(((Button) e.widget).getSelection());
        }
      });
    }

    this.setControl(this.parentComposite);
  }

  /**
   * Generates the statement from the parameterized variables.
   */
  public void updateStatement() {
    String generatedStatement = statementTest.getStatement();

    // replace parameter variables
    for (Variable bpelParameterVariable : statementTest.getParameterVariables().keySet()) {
      ParameterVariable parameterVariable = statementTest.getParameterVariables().get(
          bpelParameterVariable);

      generatedStatement = generatedStatement.replace("#" + parameterVariable.getName()
          + "#", parameterVariable.toString());
    }

    // replace container variables
    for (Variable bpelContainerVariable : statementTest.getContainerVariables().keySet()) {
      ContainerVariable containerVariable = statementTest.getContainerVariables().get(
          bpelContainerVariable);

      generatedStatement = generatedStatement.replaceAll("\\["
          + containerVariable.getName() + "\\]", containerVariable.toString());
    }

    text.setText(generatedStatement);

    // save generated statement
    statementTest.setGeneratedStatement(generatedStatement);
  }

  /**
   * Updates the data source information.
   * 
   * @param dataSource
   */
  public void updateDataSource() {
    if (this.statementTest.getDataSource() != null) {
      dataSourceNameLabel
          .setText("Name: " + this.statementTest.getDataSource().getName());
      dataSourceTypeLabel
          .setText("Type: " + this.statementTest.getDataSource().getType());
      dataSourceSubTypeLabel.setText("Subtype: "
          + this.statementTest.getDataSource().getSubType());
      dataSourceLanguageLabel.setText("Language: "
          + this.statementTest.getDataSource().getLanguage());
      dataSourceAddressLabel.setText("Address: "
          + this.statementTest.getDataSource().getAddress());
      dataSourceDataFormatLabel.setText("Data Format: "
          + this.statementTest.getDataSource().getDataFormat());
    }
  }
}