package org.eclipse.simpl.core.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.core.Activator;
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

public class SIMPLCorePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private static final String SIMPL_CORE_ADDRESS_PREF = "SIMPL_CORE_ADDRESS";
	
	Text simplCoreAddress;
	
	public SIMPLCorePreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public SIMPLCorePreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public SIMPLCorePreferencePage(String title, ImageDescriptor image) {
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
		Label uddiLabel = new Label(result, SWT.NONE);
		uddiLabel.setText("SIMPL Core Address:");
		uddiLabel.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING));

		simplCoreAddress = new Text(result, SWT.BORDER);
		simplCoreAddress.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		simplCoreAddress.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				simplCoreAddress.setData("lastValue", simplCoreAddress.getText());
			}

			public void focusLost(FocusEvent e) {
				String url = simplCoreAddress.getText();

				// no change.
				if (url.equals(simplCoreAddress.getData("lastValue"))) {
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
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		simplCoreAddress.setText(store.getString(SIMPL_CORE_ADDRESS_PREF));
	}

	
	
	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setValue(SIMPL_CORE_ADDRESS_PREF , simplCoreAddress.getText());	
	}
}
