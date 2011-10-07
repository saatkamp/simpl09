package org.eclipse.bpel.apache.ode.deploy.ui.pages.dialogs;

import java.util.List;

import org.eclipse.bpel.apache.ode.deploy.model.dd.ProcessType;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddFactory;
import org.eclipse.bpel.apache.ode.deploy.ui.util.DeployUtils;
import org.eclipse.bpel.apache.ode.deploy.ui.util.ResourceManagementMetaData;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class EditDataSourceDialog extends TitleAreaDialog {

  private Text name;
  private Text address;
  private CCombo type;
  private CCombo subtype;
  private CCombo language;
  private Text user;
  private Text password;
  private CCombo format;
  private TDatasource datasource;

  private ProcessType processType;

  public EditDataSourceDialog(Shell parentShell, TDatasource datasource,
      ProcessType processType) {
    super(parentShell);
    this.datasource = datasource;
    this.processType = processType;
  }

  public TDatasource getDatasource() {
    return datasource;
  }

  @Override
  protected Control createContents(Composite parent) {
    Control contents = super.createContents(parent);
    setTitle("Edit an existing data source");
    setMessage("Please edit the data of the selected data source",
        IMessageProvider.INFORMATION);
    return contents;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    parent.getShell()
        .setImage(
            new Image(parent.getDisplay(), getClass().getResourceAsStream(
                "/icons/edit.png")));

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    parent.setLayout(layout);

    GridData gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;

    Label label1 = new Label(parent, SWT.NONE);
    label1.setText("Name *");
    name = new Text(parent, SWT.BORDER);
    if (this.datasource != null && this.datasource.getDataSourceName() != null) {
      name.setText(this.datasource.getDataSourceName());
    }

    Label label2 = new Label(parent, SWT.NONE);
    label2.setText("Address *");
    address = new Text(parent, SWT.BORDER);
    if (this.datasource != null && this.datasource.getAddress() != null) {
      address.setText(this.datasource.getAddress());
    }

    Label label3 = new Label(parent, SWT.NONE);
    label3.setText("Type *");
    type = new CCombo(parent, SWT.BORDER);
    
    // load types
    updateTypes();
    
    type.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        datasource.setType(type.getText());
        subtype.setText("");
        language.removeAll();
        format.removeAll();
        updateSubTypes();
      }
    });
    if (this.datasource != null && this.datasource.getType() != null) {
      type.setText(this.datasource.getType());
    }

    Label label4 = new Label(parent, SWT.NONE);
    label4.setText("Subtype *");
    subtype = new CCombo(parent, SWT.BORDER);
    subtype.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        datasource.setSubtype(subtype.getText());
        language.setText("");
        format.removeAll();
        updateLanguages();
      }
    });
    if (this.datasource != null && this.datasource.getSubtype() != null) {
      subtype.setText(this.datasource.getSubtype());
    }

    Label label5 = new Label(parent, SWT.NONE);
    label5.setText("Language *");
    language = new CCombo(parent, SWT.BORDER);
    language.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        datasource.setLanguage(language.getText());
        format.removeAll();
        updateFormats();
      }
    });
    if (this.datasource != null && this.datasource.getLanguage() != null) {
      language.setText(this.datasource.getLanguage());
    }

    Label label8 = new Label(parent, SWT.NONE);
    label8.setText("Format *");
    format = new CCombo(parent, SWT.BORDER);
    if (this.datasource != null && this.datasource.getFormat() != null) {
      format.setText(this.datasource.getFormat());
    }

    Label label6 = new Label(parent, SWT.NONE);
    label6.setText("User name");
    user = new Text(parent, SWT.BORDER);
    if (this.datasource != null && this.datasource.getUserName() != null) {
      user.setText(this.datasource.getUserName());
    }

    Label label7 = new Label(parent, SWT.NONE);
    label7.setText("Password");
    password = new Text(parent, SWT.BORDER | SWT.PASSWORD);
    if (this.datasource != null && this.datasource.getPassword() != null) {
      password.setText(this.datasource.getPassword());
    }

    name.setLayoutData(gridData);
    address.setLayoutData(gridData);
    type.setLayoutData(gridData);
    subtype.setLayoutData(gridData);
    language.setLayoutData(gridData);
    format.setLayoutData(gridData);
    user.setLayoutData(gridData);
    password.setLayoutData(gridData);

    return parent;
  }

  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    ((GridLayout) parent.getLayout()).numColumns++;

    Button button = new Button(parent, SWT.PUSH);
    button.setText("Save");
    button.setFont(JFaceResources.getDialogFont());
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        if (!name.getText().isEmpty() && !address.getText().isEmpty()
            && !type.getText().isEmpty() && !subtype.getText().isEmpty()
            && !language.getText().isEmpty() && !format.getText().isEmpty()) {

          if (DeployUtils.getProcessDataSourceNames(processType) != null
              && (!DeployUtils.getProcessDataSourceNames(processType).contains(
                  name.getText()) || datasource.getDataSourceName()
                  .equals(name.getText()))) {
            /*
             * Saving the values in the TDatasource
             */
            // Create a new TDatasource
            ddFactory factory = ddFactory.eINSTANCE;
            datasource = factory.createTDatasource();
            datasource.setDataSourceName(name.getText());
            datasource.setAddress(address.getText());
            datasource.setType(type.getText());
            datasource.setSubtype(subtype.getText());
            datasource.setLanguage(language.getText());
            datasource.setUserName(user.getText());
            datasource.setPassword(password.getText());
            datasource.setFormat(format.getText());
            close();
          } else {
            setErrorMessage("The specified name is allready in use, please choose another one.");
          }
        } else {
          setErrorMessage("Please enter a value for name, address, type, subtype and language.");
        }
      }
    });
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  /**
   * Updates the types combo box.
   */
  private void updateTypes() {
    if (ResourceManagementMetaData.isSIMPLCoreAvailable()) {
      List<String> types = ResourceManagementMetaData.getDataSourceTypes();

      if (types != null) {
        type.setItems(types.toArray(new String[0]));
      }
    }
  }

  /**
   * Updates the sub types combo box.
   */
  private void updateSubTypes() {
    if (ResourceManagementMetaData.isSIMPLCoreAvailable()) {
      if (type.getText() != null) {
        List<String> subTypes = ResourceManagementMetaData.getDataSourceSubTypes(type
            .getText());

        if (subTypes != null) {
          subtype.setItems(subTypes.toArray(new String[0]));
        }
      }
    }
  }

  /**
   * Updates the format combo box.
   */
  private void updateFormats() {
    if (ResourceManagementMetaData.isSIMPLCoreAvailable()) {
      if (!type.getText().equals("") && !subtype.getText().equals("")
          && !language.getText().equals("")) {
        List<String> formats = ResourceManagementMetaData
            .getDataSourceFormats(datasource);

        if (formats != null) {
          format.setItems(formats.toArray(new String[0]));
        }
      }
    }
  }

  /**
   * Updates the language combo box
   */
  private void updateLanguages() {
    if (ResourceManagementMetaData.isSIMPLCoreAvailable()) {
      if (!subtype.getText().equals("")) {
        List<String> languages = ResourceManagementMetaData
            .getDatasourceLanguages(subtype.getText());

        if (languages != null) {
          language.setItems(languages.toArray(new String[0]));
        }
      }
    }
  }
}