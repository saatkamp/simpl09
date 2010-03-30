package org.eclipse.simpl.rrs.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RRSPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private static final String RRS_ADDRESS_PREF = "RRS_ADDRESS";
	
	Text rrsAddress;
	
	public RRSPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public RRSPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public RRSPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite result = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.verticalSpacing = 15;
		layout.makeColumnsEqualWidth = false;

		result.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		// result.setLayoutData(data);

		// WSIL directory
		Label rrsLabel = new Label(result, SWT.NONE);
		rrsLabel.setText("RRS Address:");
		rrsLabel.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING));

		rrsAddress = new Text(result, SWT.BORDER);
		rrsAddress.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rrsAddress.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				rrsAddress.setData("lastValue", rrsAddress.getText());
			}

			public void focusLost(FocusEvent e) {
				String url = rrsAddress.getText();

				// no change.
				if (url.equals(rrsAddress.getData("lastValue"))) {
					return;
				}
			}

		});
		
		initializeValues();		
		
		return result;
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		initializeDefaults();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		storeValues();
		return true;
	}

	@Override
	protected void performApply() {
		performOk();
	}
	
	/**
	 * Initializes states of the controls using default values in the preference store.
	 */
	private void initializeDefaults() {
		
	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
		IPreferenceStore store = RRSUIPlugIn.getDefault().getPreferenceStore();
		
		rrsAddress.setText(store.getString(RRS_ADDRESS_PREF));
	}

	
	
	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		IPreferenceStore store = RRSUIPlugIn.getDefault().getPreferenceStore();
		
		store.setValue(RRS_ADDRESS_PREF , rrsAddress.getText());	
	}
}
