/**
 * <b>Purpose:</b> Implements the property section for the data iteration pattern. <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.model.DataIterationPattern;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.bpel.ui.properties.BPELPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class DataIterationPatternPropertySection extends BPELPropertySection {

  private DataIterationPattern pattern = null;

  private Composite parentComposite = null;
  private CCombo referenceListCombo = null;
  private CCombo currentContainerCombo = null;
  
  private Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

  @Override
  protected void createClient(Composite parent) {
    setInput(getPart(), getBPELEditor().getSelection());
    pattern = getModel();

    createWidget(parent);

    referenceListCombo
        .setText(!pattern.getContainerReferenceList().equals("") ? pattern
            .getContainerReferenceList() : "");
    currentContainerCombo
        .setText(!pattern.getCurrentContainer().equals("") ? pattern
            .getCurrentContainer() : "");

  }

  private void createWidget(Composite parent) {
    parentComposite = parent;

    GridLayout gridLayout = new GridLayout(4, false);
    parent.setLayout(gridLayout);

    GridData gridDataCombo = new GridData();
    gridDataCombo.horizontalAlignment = GridData.FILL;
    gridDataCombo.verticalAlignment = GridData.FILL;
    gridDataCombo.grabExcessHorizontalSpace = true;
    gridDataCombo.grabExcessVerticalSpace = true;

    Label referenceListLabel = new Label(parentComposite, SWT.NONE);
    referenceListLabel.setText("Container reference list:");
    referenceListLabel.setBackground(white);

    referenceListCombo = new CCombo(parentComposite, SWT.BORDER);
    referenceListCombo.setLayoutData(gridDataCombo);
    referenceListCombo.setBackground(white);
    referenceListCombo.setItems(VariableUtils.getUseableVariables(getProcess())
        .toArray(new String[0]));
    referenceListCombo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent arg0) {
        getCommandFramework().execute(
            new SetCommand(pattern, referenceListCombo.getText(),
                ModelPackage.eINSTANCE
                    .getDataIterationPattern_ContainerReferenceList()));
      }
    });

    Label currentContainerLabel = new Label(parentComposite, SWT.NONE);
    currentContainerLabel.setText("Container reference for indivudual loop cycle:");
    currentContainerLabel.setBackground(white);
    
    currentContainerCombo = new CCombo(parentComposite, SWT.BORDER);
    currentContainerCombo.setLayoutData(gridDataCombo);
    currentContainerLabel.setBackground(white);
    currentContainerCombo.setItems(VariableUtils.getUseableVariables(
        getProcess()).toArray(new String[0]));
    currentContainerCombo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent arg0) {
        getCommandFramework().execute(
            new SetCommand(pattern, currentContainerCombo.getText(),
                ModelPackage.eINSTANCE
                    .getDataIterationPattern_CurrentContainer()));
      }
    });
  }
}
