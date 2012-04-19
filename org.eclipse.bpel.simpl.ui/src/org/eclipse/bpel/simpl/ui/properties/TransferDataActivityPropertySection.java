package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.ui.properties.util.PropertySectionUtils;
import org.eclipse.bpel.simpl.ui.properties.util.VariableUtils;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class TransferDataActivityPropertySection extends
		ADataManagementActivityPropertySection {

	private Label typeLabel = null;
	private Text typeText = null;

	private Label dataSourceIdentifierLabel = null;
	private CCombo dataSourceIdentifierCombo = null;
	private Label kindLabel = null;
	private Text kindText = null;
	private Label languageLabel = null;
	private Text languageText = null;
	private Composite parentComposite = null;
	private Label writeTargetLabel = null;
	private CCombo writeTargetCombo = null;

	private TransferDataActivity transferDataActivity;
	private DataSource dataSink = null;

	/**
	 * Make this section use all the vertical space it can get.
	 * 
	 * @return true, if successful
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	protected void createClient(Composite parent) {
		// Setzen die im Editor ausgewählte Aktivität als Input.
		setInput(getPart(), getBPELEditor().getSelection());
		// Laden der Transfer-Aktivität
		this.transferDataActivity = (TransferDataActivity) getModel();

		createWidgets(parent);
		
		setValues();
	}

	/**
	 * Creates the property section.
	 * 
	 * @param composite
	 *            to put the content in.
	 */
	@SuppressWarnings("unused")
  private void createWidgets(Composite composite) {
		this.parentComposite = composite;
		GridData gridData13 = new GridData();
		gridData13.horizontalAlignment = GridData.FILL;
		gridData13.grabExcessHorizontalSpace = true;
		gridData13.verticalAlignment = GridData.CENTER;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.verticalAlignment = GridData.CENTER;
		GridData gridData21 = new GridData();
		gridData21.horizontalAlignment = GridData.FILL;
		gridData21.verticalAlignment = GridData.CENTER;
		GridData gridData51 = new GridData();
		gridData51.horizontalAlignment = GridData.FILL;
		gridData51.verticalAlignment = GridData.CENTER;
		GridData gridData31 = new GridData();
		gridData31.grabExcessHorizontalSpace = false;
		GridData gridData12 = new GridData();
		gridData12.grabExcessHorizontalSpace = true;
		gridData12.verticalAlignment = GridData.CENTER;
		gridData12.horizontalAlignment = GridData.FILL;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = GridData.FILL;
		gridData11.grabExcessHorizontalSpace = false;
		gridData11.horizontalIndent = 1;
		gridData11.heightHint = -1;
		gridData11.horizontalSpan = 2;
		gridData11.grabExcessVerticalSpace = true;
		gridData11.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = false;
		gridData1.verticalAlignment = GridData.END;
		gridData1.horizontalSpan = 3;
		gridData1.horizontalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.heightHint = -1;
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.END;
		gridData.horizontalIndent = 0;
		gridData.grabExcessVerticalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		parentComposite.setLayout(gridLayout);
		parentComposite.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		parentComposite.setSize(new Point(582, 294));
		typeLabel = new Label(composite, SWT.NONE);
		typeLabel.setText("Type of data sink:");
		typeLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeLabel.setLayoutData(gridData51);
		createTypeCombo();
		Label filler2 = new Label(composite, SWT.NONE);
		Label filler5 = new Label(composite, SWT.NONE);
		kindLabel = new Label(composite, SWT.NONE);
		kindLabel.setText("Subtype of data sink:");
		kindLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		createKindCombo();
		dataSourceIdentifierLabel = new Label(composite, SWT.NONE);
		dataSourceIdentifierCombo = new CCombo(composite, SWT.BORDER);
		dataSourceIdentifierCombo.setLayoutData(gridData12);
		// Änderungen im Modell speichern
		dataSourceIdentifierCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

      @Override
      public void widgetSelected(SelectionEvent e) {
        getCommandFramework().execute(
            new SetCommand(transferDataActivity, dataSourceIdentifierCombo
                .getText(), ModelPackage.eINSTANCE
                .getTransferDataActivity_DataSink()));

        setValues();
      }
    });
		dataSourceIdentifierCombo.setItems(PropertySectionUtils
				.getAllDataSourceIdentifiers(getProcess()));

		dataSourceIdentifierLabel.setText("Data sink:");
		dataSourceIdentifierCombo.setEditable(false);
		dataSourceIdentifierCombo.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		dataSourceIdentifierLabel.setLayoutData(gridData31);
		dataSourceIdentifierLabel.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		languageLabel = new Label(composite, SWT.NONE);
		languageText = new Text(composite, SWT.BORDER);
		languageText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		languageText.setEditable(false);
		languageText.setLayoutData(gridData4);

		writeTargetLabel = new Label(composite, SWT.NONE);
		writeTargetLabel.setText("Target container to insert the data:");
		writeTargetLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		languageLabel.setText("Query language of data sink:");
		languageLabel.setVisible(true);
		languageLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		writeTargetCombo = new CCombo(composite, SWT.BORDER);
		writeTargetCombo.setLayoutData(gridData13);
		writeTargetCombo.setItems(VariableUtils.getUseableVariables(getProcess())
				.toArray(new String[0]));
		writeTargetCombo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				getCommandFramework()
						.execute(
								new SetCommand(
										transferDataActivity,
										writeTargetCombo.getText(),
										ModelPackage.eINSTANCE
												.getTransferDataActivity_DataSinkContainer()));
			}
		});

		typeText.setEnabled(false);
		kindText.setEnabled(false);
		languageText.setEnabled(false);
	}

	/**
	 * This method initializes typeCombo
	 * 
	 */
	private void createTypeCombo() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.CENTER;
		typeText = new Text(parentComposite, SWT.BORDER);
		typeText.setToolTipText("Choose the type of data sink");
		typeText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeText.setLayoutData(gridData2);
		typeText.setEditable(false);
	}

	/**
	 * This method initializes kindCombo
	 * 
	 */
	private void createKindCombo() {
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.FILL;
		gridData6.verticalAlignment = GridData.CENTER;
		kindText = new Text(parentComposite, SWT.BORDER);
		kindText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		kindText.setToolTipText("Choose the subtype of data sink");
		kindText.setEditable(false);
		kindText.setLayoutData(gridData6);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#getStatement
	 * ()
	 */
	@Override
	public String getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#
	 * saveStatementToModel()
	 */
	@Override
	public void saveStatementToModel() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#setStatement
	 * (java.lang.String)
	 */
	@Override
	public void setStatement(String statement) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DataManagementActivityPropertySection#getDataSource
	 * ()
	 */
	@Override
	public DataSource getDataSource() {
		return PropertySectionUtils.findDataSourceByIdentifier(getProcess(),
				dataSourceIdentifierCombo.getText());
	}
	
  private void setValues() {
    dataSourceIdentifierCombo.setText(transferDataActivity.getDataSink());
    writeTargetCombo.setText(transferDataActivity.getDataSinkContainer());
    
    dataSink = getDataSource();
    
    if (dataSink != null) {
      typeText.setText(dataSink.getType());
      kindText.setText(dataSink.getSubType());
      languageText.setText(dataSink.getLanguage());
    } else {
      typeText.setText("");
      kindText.setText("");
      languageText.setText("");
    }
  }
}
