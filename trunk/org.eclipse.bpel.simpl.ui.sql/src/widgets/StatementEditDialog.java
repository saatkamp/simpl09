package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;



/**
 * this widget should be a dialog which enable the editing of
 * statement bevor saving and closing the statement-Editor.
 * @author F.Zoabi
 *
 */
public class StatementEditDialog {
	
	private Shell theShell = null;  //  @jve:decl-index=0:visual-constraint="140,-28"
	private Display display = null;
	
	private Label labelDialogMassege = null;
	private Button buttonSave = null;
	private Button buttonAbort;
	
	LiveEditStyleText styleTextEditor;	
	
	public StatementEditDialog(final LiveEditStyleText statementFeld){
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = false;
		
		theShell = new Shell(Display.getDefault());
		GridData gridData23 = new GridData();
		gridData23.horizontalAlignment = GridData.FILL;
		gridData23.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		theShell.setText("Data-Management-Activity properties");
		theShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		theShell.setLayout(gridLayout);
		theShell.setSize(new Point(582, 164));
		theShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));

		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		labelDialogMassege = new Label(theShell, SWT.NONE);
		labelDialogMassege.setText("Do you like to edit the statement manualy before saving it ...");
		labelDialogMassege.setFont(new Font(Display.getDefault(), "Tahoma", 14, SWT.NORMAL));
		labelDialogMassege.setLayoutData(gridData1);
		Label filler1 = new Label(theShell, SWT.NONE);
		styleTextEditor = new LiveEditStyleText(theShell);
		styleTextEditor.setLayoutData(gridData);
		styleTextEditor.setText(statementFeld.getText());
		
		Label filler = new Label(theShell, SWT.NONE);
		buttonAbort = new Button(theShell, SWT.CENTER);
		buttonAbort.setText("Abort");
		buttonAbort.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				closeWindow();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				closeWindow();
				
			}
		});
		
		buttonSave = new Button(theShell, SWT.CENTER);
		buttonSave.setText("Save Statement");
		buttonSave.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				statementFeld.setText(styleTextEditor.getText());
				closeWindow();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statementFeld.setText(styleTextEditor.getText());
				closeWindow();
				
			}
		});
	}
	
	/**
	 * Open window.
	 */
	public void openWindow(){
		theShell.open();
	}
	
	/**
	 * Close window.
	 */
	public void closeWindow(){
		theShell.close();
	}
}
