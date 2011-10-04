package org.eclipse.simpl.core.adminconsole;

import java.util.LinkedHashMap;

import org.eclipse.simpl.communication.SIMPLCoreCommunication;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DataTransformationServiceSettingsComposite extends AAdminConsoleComposite {
  // Global hinterlegte Keys der Einstellungen
  private final String ADDRESS = "ADDRESS";
  private final String MODE = "MODE";

  private Label addressLabel = null;
  private Text addressText = null;
  private Label modeLabel = null;
  private Button modeCheckBox = null;

  // Default-Einstellungen
  private String dAddress = "http://localhost:8080/axis2/services/DataTransformationService.DataTransformationPort?wsdl";
  private String dMode = "inactive";

  // Buffer-Einstellungen
  private String bAddress = "";
  private String bMode = "inactive";

  // LastSaved-Einstellungen
  private String lAddress = "";
  private String lMode = "";

  @Override
  public void createComposite(Composite composite) {
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 2;
    Composite comp = new Composite(composite, SWT.NONE);
    GridData gridDataComp = new GridData();
    gridDataComp.horizontalAlignment = GridData.FILL;
    gridDataComp.grabExcessHorizontalSpace = true;
    gridDataComp.verticalAlignment = GridData.CENTER;

    comp.setLayout(gridLayout);
    comp.setLayoutData(gridDataComp);

    setComposite(comp);

    GridData gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.CENTER;

    addressLabel = new Label(comp, SWT.NONE);
    addressLabel.setText("Address of the Data Transformation Service web service:");
    addressText = new Text(comp, SWT.BORDER);
    addressText.setLayoutData(gridData);
    addressText.setText(this.bAddress);
    addressText.addModifyListener(new ModifyListener() {
      @Override
      public void modifyText(ModifyEvent e) {
        bAddress = addressText.getText();
      }
    });

    modeLabel = new Label(comp, SWT.NONE);
    modeLabel.setText("Activate or deactivate using the web service:");
    modeCheckBox = new Button(comp, SWT.CHECK);
    modeCheckBox.setText(this.bMode);

    if (this.bMode.equals("active")) {
      modeCheckBox.setSelection(true);
    } else {
      modeCheckBox.setSelection(false);
    }

    modeCheckBox.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent event) {
        if (modeCheckBox.getSelection()) {
          modeCheckBox.setText("active");
        } else {
          modeCheckBox.setText("inactive");
        }
        bMode = modeCheckBox.getText();
      }

      public void widgetDefaultSelected(SelectionEvent event) {
        widgetSelected(event);
      }
    });
  }

  /**
   * @param dataSource
   */
  protected void setInfoTextFields(String address) {
    addressText.setText(address);
  }

  @Override
  public void saveSettings(String parentItem, String item, String settingName) {
    // Überprüfen, ob mindestens ein Wert geändert wurde
    if (haveSettingsChanged()) {
      // Settings-Liste erstellen und mit Werte füllen zum Speichern
      LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

      // Werte aus den Buffervariablen einfügen
      settings.put(this.ADDRESS, this.bAddress);
      settings.put(this.MODE, this.bMode);

      // Über den SIMPL Core in einer embedded DerbyDB speichern
      SIMPLCoreCommunication.getInstance().save(parentItem, item, settingName, settings);

      // Last-Saved Werte aktualisieren
      this.lAddress = this.bAddress;
      this.lMode = this.bMode;
    }
  }

  @Override
  public void loadSettings(String parentItem, String item) {
    // Settings-Liste erstellen
    LinkedHashMap<String, String> settings = new LinkedHashMap<String, String>();

    // LastSaved-Einstellungen aus SIMPL Core DB laden
    settings = SIMPLCoreCommunication.getInstance().load(parentItem, item, "lastSaved");

    if (!settings.isEmpty()) {
      this.lAddress = settings.get(this.ADDRESS);
      this.bAddress = this.lAddress;

      this.lMode = settings.get(this.MODE).isEmpty() ? "inactive"
          : settings.get(this.MODE);

      this.bMode = this.lMode;
    } else {
      this.bAddress = "http://localhost:8080/axis2/services/DataTransformationService.DataTransformationPort?wsdl";
      this.bMode = "inactive";
    }

    // Default-Einstellungen aus SIMPL Core DB laden
    settings = SIMPLCoreCommunication.getInstance().load(parentItem, item, "default");

    if (!settings.isEmpty()) {
      this.dAddress = settings.get(this.ADDRESS);
      this.dMode = settings.get(this.MODE);
    }
  }

  @Override
  public boolean haveSettingsChanged() {
    boolean changed = false;
    // Überprüfen, ob Werte geändert wurden
    if (!this.bMode.equals(this.lMode) || !this.bAddress.equals(this.lAddress)) {
      changed = true;
    }
    return changed;
  }

  @Override
  public void loadSettingsFromBuffer(String settingName) {
    if (settingName.equals("default")) {
      if (getComposite() != null && !getComposite().isDisposed()) {
        // Default-Werte in GUI-Elementen setzen
        addressText.setText(this.dAddress);

        if (this.dMode.equals("active")) {
          modeCheckBox.setSelection(true);
          modeCheckBox.setText("active");
        } else {
          modeCheckBox.setSelection(false);
          modeCheckBox.setText("inactive");
        }
      }

      // Buffer-Werte aktualisieren
      this.bAddress = this.dAddress;
      this.bMode = this.dMode;
    } else {
      if (settingName.equals("lastSaved")) {
        if (getComposite() != null && !getComposite().isDisposed()) {
          // Last-Saved Werte in GUI-Elementen setzen
          addressText.setText(this.lAddress);

          if (this.lMode.equals("active")) {
            modeCheckBox.setSelection(true);
            modeCheckBox.setText("active");
          } else {
            modeCheckBox.setSelection(false);
            modeCheckBox.setText("inactive");
          }
        }
        // Buffer-Werte aktualisieren
        this.bAddress = this.lAddress;
      } else {
        if (getComposite() != null && !getComposite().isDisposed()) {
          // Buffer-Werte in GUI-Elementen setzen
          addressText.setText(this.bAddress);

          if (this.bMode.equals("active")) {
            modeCheckBox.setSelection(true);
            modeCheckBox.setText("active");
          } else {
            modeCheckBox.setSelection(false);
            modeCheckBox.setText("inactive");
          }
        }
      }
    }
  }

  @Override
  public String getConsoleItem() {
    // TODO Auto-generated method stub
    return "Settings";
  }

  @Override
  public String getParentConsoleItem() {
    // TODO Auto-generated method stub
    return "DataTransformationService";
  }
}