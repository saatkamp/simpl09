package org.eclipse.simpl.statementtest.ui.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.model.Variable;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariable;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariableElement;
import org.eclipse.simpl.statementtest.model.variables.ParameterVariable;
import org.eclipse.simpl.statementtest.ui.wizards.StatementTestWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * <b>Purpose:</b>Wizard page for the parameter adjustment.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * TODO: Scrolling bei groﬂer Anzahl an Parametern?
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: ParameterAdjustmentPage.java 72 2010-07-23 20:02:14Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ParameterAdjustmentPage extends StatementTestWizardPage {
  static final String PAGE_TITLE = "Adjust the parameters";
  static final String PAGE_MESSAGE = "Specify empty parameters and change existing parameters.";
  static final String PARAMETER_VARIABLES_GROUP = "Parameter Variables";
  static final String CONTAINER_VARIABLES_GROUP = "Container Variables";
  static final String ERROR_ALL_PARAMETERS_MUST_BE_SET = "All required parameters must be set.";
  static final String QUOTE_VALUE = "Quote Value";

  StatementTest statementTest = null;
  Group parameterGroup = null;
  Group containerGroup = null;

  List<Text> optionalTextFields = new ArrayList<Text>();

  /**
   * @param pageName
   * @param title
   * @param titleImage
   */
  public ParameterAdjustmentPage(String pageName, ImageDescriptor titleImage) {
    super(pageName, PAGE_TITLE, titleImage);

    titleImage = ImageDescriptor.createFromImageData(titleImage.getImageData().scaledTo(
        56, 56));

    this.setImageDescriptor(titleImage);
    this.setMessage(ParameterAdjustmentPage.PAGE_MESSAGE);
  }

  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);
    statementTest = ((StatementTestWizard) this.getWizard()).getStatementTest();

    // next page is not available until all parameters have a value
    this.setPageComplete(false);

    // layout
    this.pageComposite.setLayout(new GridLayout(1, false));

    if (!statementTest.getParameterVariables().isEmpty()) {
      // parameter variables group
      parameterGroup = new Group(this.pageComposite, SWT.SHADOW_NONE);
      parameterGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
      parameterGroup.setLayout(new GridLayout(4, false));
      parameterGroup.setText(ParameterAdjustmentPage.PARAMETER_VARIABLES_GROUP);

      // add parameter variables
      for (Variable parameterVariable : statementTest.getParameterVariables().keySet()) {
        this.addParameterVariable(parameterGroup, statementTest.getParameterVariables()
            .get(parameterVariable));
      }
    }

    if (!statementTest.getContainerVariables().isEmpty()) {
      // container variables group
      containerGroup = new Group(this.pageComposite, SWT.SHADOW_NONE);
      containerGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
      containerGroup.setLayout(new GridLayout(2, false));
      containerGroup.setText(ParameterAdjustmentPage.CONTAINER_VARIABLES_GROUP);

      // add container variables
      for (Variable containerVariable : statementTest.getContainerVariables().keySet()) {
        this.addContainerVariable(containerGroup, statementTest.getContainerVariables()
            .get(containerVariable));
      }
    }

    if ((parameterGroup != null && containerGroup != null
        && allParametersAreSet(parameterGroup) && allParametersAreSet(containerGroup))
        || (parameterGroup != null && allParametersAreSet(parameterGroup))
        || (containerGroup != null && allParametersAreSet(containerGroup))) {
      setPageComplete(true);
      setMessage(ParameterAdjustmentPage.PAGE_MESSAGE, IMessageProvider.NONE);
    } else {
      setPageComplete(false);
      setMessage(ERROR_ALL_PARAMETERS_MUST_BE_SET, IMessageProvider.ERROR);
    }

    this.setControl(this.parentComposite);
  }

  /**
   * Checks if all text parameters in a group are set with a value.
   * 
   * @param group
   * @return <i>true</i> if all parameters are set, <i>false</i> otherwise.
   */
  private boolean allParametersAreSet(Group group) {
    boolean allParametersAreSet = true;

    for (Control control : group.getChildren()) {
      if (control instanceof Text) {
        if (((Text) control).getText().equals("")
            && !optionalTextFields.contains(control)) {
          allParametersAreSet = false;
        }
      }
    }

    return allParametersAreSet;
  }

  /**
   * Adds a parameter variable to a group.
   * 
   * @param group
   * @param variable
   */
  private void addParameterVariable(final Group group, final ParameterVariable variable) {
    Label label = null;
    Text text = null;
    Button quoteCheckbox = null;
    Label quoteLabel = null;

    label = new Label(group, SWT.NULL);
    label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    label.setText("    " + variable.getName() + " (" + variable.getType() + ")" + " : ");

    text = new Text(group, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    if (variable.getValue() != null) {
      text.setText(variable.getValue());
    }

    text.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        // save variable value
        variable.setValue(((Text) e.widget).getText());

        if (allParametersAreSet(group)) {
          setPageComplete(true);
          setMessage(ParameterAdjustmentPage.PAGE_MESSAGE, IMessageProvider.NONE);
        } else {
          setPageComplete(false);
          setMessage(ERROR_ALL_PARAMETERS_MUST_BE_SET, IMessageProvider.ERROR);
        }
      }
    });

    quoteCheckbox = new Button(group, SWT.CHECK);
    quoteCheckbox.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    quoteCheckbox.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        variable.setQuote(((Button) e.widget).getSelection());
      }
    });

    quoteLabel = new Label(group, SWT.NULL);
    quoteLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    quoteLabel.setText(QUOTE_VALUE);
  }

  /**
   * Adds a container variable to a group.
   * 
   * @param group
   * @param variable
   */
  private void addContainerVariable(final Group group, final ContainerVariable variable) {
    Label variableLabel = null;
    Label variableElementLabel = null;
    Text variableElementText = null;

    variableLabel = new Label(group, SWT.NULL);
    variableLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 0));
    variableLabel.setText(variable.getName() + " (" + variable.getType() + ") ");

    for (final ContainerVariableElement variableElement : variable.getElements()) {
      variableElementLabel = new Label(group, SWT.NULL);
      variableElementLabel
          .setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

      if (variableElement.isRequired()) {
        variableElementLabel.setText(" " + variableElement.getName() + " ("
            + variableElement.getType() + ") *");
      } else {
        variableElementLabel.setText(" " + variableElement.getName() + " ("
            + variableElement.getType() + ")");
      }

      variableElementText = new Text(group, SWT.BORDER);

      if (!variableElement.isRequired()) {
        optionalTextFields.add(variableElementText);
      }

      // extract and set the value
      int valueStart = variable.getValue().indexOf("<" + variableElement.getName() + ">") + variableElement.getName().length() + 2;
      int valueStop = variable.getValue().indexOf("</" + variableElement.getName() + ">", valueStart);
      String value = variable.getValue().substring(valueStart, valueStop);
      variableElementText.setText(value);
      variableElement.setValue(value);
      
      variableElementText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
      variableElementText.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          // save variable value
          variableElement.setValue(((Text) e.widget).getText());

          if (allParametersAreSet(group)) {
            setPageComplete(true);
            setMessage(ParameterAdjustmentPage.PAGE_MESSAGE, IMessageProvider.NONE);
          } else {
            setPageComplete(false);
            setMessage(ParameterAdjustmentPage.ERROR_ALL_PARAMETERS_MUST_BE_SET,
                IMessageProvider.ERROR);
          }
        }
      });
    }
  }
}