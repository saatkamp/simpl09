package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferActivity;
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
import org.simpl.core.webservices.client.DataSource;

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
public class TransferActivityToPropertySection extends
		DMActivityPropertySection {

	private Label typeLabel = null;
	private Text typeText = null;

	private Label dataSourceAddressLabel = null;
	private CCombo dataSourceAddressCombo = null;
	private Label kindLabel = null;
	private Text kindText = null;
	private Label languageLabel = null;
	private Text languageText = null;
	private Composite parentComposite = null;
	private Label targetLabel = null;
	private CCombo targetCombo = null;

	private TransferActivity transferActivity;

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
		this.transferActivity = (TransferActivity) getModel();

		createWidgets(parent);

		// Setzen die Datenquellenadresse
		dataSourceAddressCombo.setText(transferActivity.getTargetDsAddress());
		targetCombo.setText(transferActivity.getTargetDsContainer());
		// Setzen die Sprache
		languageText.setText(transferActivity.getTargetDsLanguage());
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
		typeLabel.setText("Type of data source:");
		typeLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeLabel.setLayoutData(gridData51);
		createTypeCombo();
		Label filler2 = new Label(composite, SWT.NONE);
		Label filler5 = new Label(composite, SWT.NONE);
		kindLabel = new Label(composite, SWT.NONE);
		kindLabel.setText("Subtype of data source:");
		kindLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		createKindCombo();
		dataSourceAddressLabel = new Label(composite, SWT.NONE);
		dataSourceAddressCombo = new CCombo(composite, SWT.BORDER);
		dataSourceAddressCombo.setLayoutData(gridData12);
		// Änderungen im Modell speichern
		dataSourceAddressCombo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandFramework().execute(
						new SetCommand(transferActivity, dataSourceAddressCombo
								.getText(), ModelPackage.eINSTANCE
								.getTransferActivity_TargetDsAddress()));

				DataSource dataSource = PropertySectionUtils
						.findDataSourceByName(getProcess(),
								dataSourceAddressCombo.getText());
				if (dataSource != null) {
					typeText.setText(dataSource.getType());
					kindText.setText(dataSource.getSubType());
					languageText.setText(dataSource.getLanguage());
				}
			}
		});
		dataSourceAddressCombo.setItems(PropertySectionUtils
				.getAllDataSourceNames(getProcess()));

		dataSourceAddressLabel.setText("Data source name:");
		dataSourceAddressCombo.setEditable(false);
		dataSourceAddressCombo.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		dataSourceAddressLabel.setLayoutData(gridData31);
		dataSourceAddressLabel.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));
		languageLabel = new Label(composite, SWT.NONE);
		languageText = new Text(composite, SWT.BORDER);
		languageText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		languageText.setEditable(false);
		languageText.setLayoutData(gridData4);
		languageText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// Auswahl im Modell speichern
				getCommandFramework()
						.execute(
								new SetCommand(transferActivity, languageText
										.getText(), ModelPackage.eINSTANCE
										.getTransferActivity_TargetDsLanguage()));
				
			}
		});

		targetLabel = new Label(composite, SWT.NONE);
		targetLabel.setText("Target to insert the data:");
		targetLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));

		languageLabel.setText("Query language:");
		languageLabel.setVisible(true);
		languageLabel.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		targetCombo = new CCombo(composite, SWT.BORDER);
		targetCombo.setLayoutData(gridData13);
		targetCombo.setItems(VariableUtils.getUseableVariables(getProcess())
				.toArray(new String[0]));
		targetCombo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				getCommandFramework()
						.execute(
								new SetCommand(
										transferActivity,
										targetCombo.getText(),
										ModelPackage.eINSTANCE
												.getTransferActivity_TargetDsContainer()));
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
		typeText.setToolTipText("Choose the type of data source");
		typeText.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		typeText.setLayoutData(gridData2);

		// Aktualisieren der KindCombo-Daten
		typeText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetCommand(transferActivity, typeText.getText(),
								ModelPackage.eINSTANCE
										.getTransferActivity_TargetDsType()));
			}
		});
		typeText.setEditable(false);

		// Wert aus Modell setzen
		typeText.setText(this.transferActivity.getTargetDsType());
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
		kindText.setToolTipText("Choose the subtype of data source");
		kindText.setEditable(false);
		kindText.setLayoutData(gridData6);

		kindText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// Speichern Auswahl in Modell
				getCommandFramework().execute(
						new SetCommand(transferActivity, kindText.getText(),
								ModelPackage.eINSTANCE
										.getTransferActivity_TargetDsKind()));
			}
		});

		// Wert aus Modell setzen
		kindText.setText(this.transferActivity.getTargetDsKind());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getStatement
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
	 * @seeorg.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#
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
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#setStatement
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
	 * org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection#getDataSource
	 * ()
	 */
	@Override
	public DataSource getDataSource() {
		return PropertySectionUtils.findDataSourceByName(getProcess(),
				dataSourceAddressCombo.getText());
	}
}
