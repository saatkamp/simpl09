/**
 * <b>Purpose:</b> Implements the property section for the container to container pattern. <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.model.ContainerToContainerPattern;
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

public class ContainerToContainerPatternPropertySection extends
    BPELPropertySection {

  private ContainerToContainerPattern pattern = null;

  private Composite parentComposite = null;
  private CCombo sourceContainerCombo = null;
  private CCombo targetContainerCombo = null;

  private Color white = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

  @Override
  protected void createClient(Composite parent) {
    setInput(getPart(), getBPELEditor().getSelection());
    pattern = getModel();

    createWidget(parent);

    sourceContainerCombo
        .setText(!pattern.getSourceContainer().equals("") ? pattern
            .getSourceContainer() : "");
    targetContainerCombo
        .setText(!pattern.getTargetContainer().equals("") ? pattern
            .getTargetContainer() : "");

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

    Label sourceContainerLabel = new Label(parentComposite, SWT.NONE);
    sourceContainerLabel.setText("Source Container to extract the data:");
    sourceContainerLabel.setBackground(white);

    sourceContainerCombo = new CCombo(parentComposite, SWT.BORDER);
    sourceContainerCombo.setLayoutData(gridDataCombo);
    sourceContainerCombo.setBackground(white);
    sourceContainerCombo.setItems(VariableUtils.getUseableVariables(
        getProcess()).toArray(new String[0]));
    sourceContainerCombo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent arg0) {
        getCommandFramework().execute(
            new SetCommand(pattern, sourceContainerCombo.getText(),
                ModelPackage.eINSTANCE
                    .getContainerToContainerPattern_SourceContainer()));
      }
    });

    Label targetContainerLabel = new Label(parentComposite, SWT.NONE);
    targetContainerLabel.setText("Target container to insert the data:");
    targetContainerLabel.setBackground(white);

    targetContainerCombo = new CCombo(parentComposite, SWT.BORDER);
    targetContainerCombo.setLayoutData(gridDataCombo);
    targetContainerCombo.setBackground(white);
    targetContainerCombo.setItems(VariableUtils.getUseableVariables(
        getProcess()).toArray(new String[0]));
    targetContainerCombo.addModifyListener(new ModifyListener() {

      @Override
      public void modifyText(ModifyEvent arg0) {
        getCommandFramework().execute(
            new SetCommand(pattern, targetContainerCombo.getText(),
                ModelPackage.eINSTANCE
                    .getContainerToContainerPattern_TargetContainer()));
      }
    });
  }
}
