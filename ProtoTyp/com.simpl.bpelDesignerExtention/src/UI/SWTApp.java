package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.custom.CBanner;

import org.eclipse.swt.widgets.*;

public class SWTApp {    
        Display display;
        Shell shell;
        Tree tree;
        Composite comb,comb2,ButtonComp;
        Composite AuthDataCombosite,GeneralCombosite;
        Group childGroupAuthData;
        
        //**********
        Label userLabel;
        Text userTf;
        Label passwordLabel;
        Text passwordTf;
        
        //*********
        
        //******
        Label auditingLabel;
        private  Button on;
        //********
        
        public SWTApp(Display display){
        	//Container container= new Container();
	        //display= new Display();
	        shell = new Shell(display);
	        shell.setSize(730,530);
	        shell.setText("SIMPL Admistration Console");
	        
	        GridLayout gridLayout = new GridLayout();
	        gridLayout.numColumns = 2;
	        
	        shell.setLayout(gridLayout);
	        
	        creatTreeMenu();
	        
	        comb=new Composite(shell, SWT.BORDER);
	        comb.setLayout(new FillLayout());
	        //comb.setBounds(1, 0, 100, 400);
	        GridData gridData4 = new GridData();
	        gridData4.widthHint = 530;
	        gridData4.heightHint = 415;
	        comb.setLayoutData(gridData4);
	        comb.setVisible(false);
	        
//	        comb2=new Composite(shell, SWT.BORDER);
//	        comb2.setLayout(new FillLayout());
//	        //comb.setBounds(1, 0, 100, 400);
//	        GridData gridData5 = new GridData();
//	        gridData5.widthHint = 530;
//	        gridData5.heightHint = 415;
//	        comb2.setLayoutData(gridData4);
//	        comb2.setVisible(false);
        	
	        Creat_AuthentificationData_Elements();
		    Creat_General_Elements();
	        CreatButtons();
	        
	        
//	        Button button=new Button(comb2, SWT.BORDER);
//	        button.setText("sdfsdfsdf");
	        
	        shell.open();
	        while(!shell.isDisposed()){
	        if(!display.readAndDispatch())
	        display.sleep();
	            }
	            display.dispose();
        }
        
        private void creatTreeMenu(){
        	tree = new Tree(shell, SWT.BORDER);
	        
        	
        	
	        GridData gridData3 = new GridData();
	        gridData3.widthHint = 150;
	        gridData3.heightHint = 400;
	        
	        tree.setLayoutData(gridData3);

	        
	        
	        
	        TreeItem item1 = new TreeItem(tree, SWT.NONE, 0);
	        item1.setText("Globale Settings");
	        final TreeItem item1a = new TreeItem(item1, SWT.NONE, 0);
	        item1a.setText("Authentification data");
	        
	        TreeItem item2 = new TreeItem(tree, SWT.NONE, 0);
	        item2.setText("Auditing");
	        final TreeItem item2a = new TreeItem(item2, SWT.NONE, 0);
	        item2a.setText("General");
	
	        tree.addListener(SWT.Selection, new Listener() {
      	      public void handleEvent(Event event) {
      	        if(event.item==item1a){
      	        	System.out.println("Authentification data");
      	        	
      	        	//Creat_AuthentificationData_Elements();
      	        	//AuthDataCombosite.dispose();
      	        	//comb2.setVisible(false);
      	        	on.setVisible(false);
      	        	auditingLabel.setVisible(false);
      	        	
      	        	userLabel.setVisible(true);
      	        	userTf.setVisible(true);
      	        	passwordLabel.setVisible(true);
      	         	passwordTf.setVisible(true);
      	         	childGroupAuthData.setText("Authentification Data");
      	         	
      	        	comb.setVisible(true);
      	        }
      	        if(event.item==item2a){
      	        	
      	        	on.setVisible(true);
      	        	auditingLabel.setVisible(true);
      	        	
      	        	userLabel.setVisible(false);
      	        	userTf.setVisible(false);
      	        	passwordLabel.setVisible(false);
      	         	passwordTf.setVisible(false);
      	         	childGroupAuthData.setText("General");
      	        	comb.setVisible(true);
    	        }
      	      
      	      }
      	});
	        
        }
        
        /**
         * to creat the UI elements and fields of the "Authentification Data"
         */
        private void Creat_AuthentificationData_Elements() {
        	System.out.println("in Creat_AuthentificationData_Elements() \n");
        	
        	AuthDataCombosite = new Composite(comb, SWT.NONE);
	        AuthDataCombosite.setLayout(new FillLayout());
	        //comb.setBounds(1, 0, 100, 400);
//	        GridData gridDataAuthDataCombosite = new GridData();
//	        gridDataAuthDataCombosite.widthHint = 530;
//	        gridDataAuthDataCombosite.heightHint = 215;
//	        AuthDataCombosite.setLayoutData(gridDataAuthDataCombosite);
	        
	        childGroupAuthData = new Group(AuthDataCombosite, SWT.NONE);
	        //childGroupAuthData.setText("Authentification Data");
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 2;
	        childGroupAuthData.setLayout(layout);
	        GridData data = new GridData();
	        data.widthHint = 250;
	        data.heightHint = 60;
	        //data.horizontalSpan = 2;
	        childGroupAuthData.setLayoutData(data);
	        
	        GridData gridData = new GridData();
			gridData.widthHint = 100;
			gridData.heightHint = 20;
	        
        	userLabel=new Label(childGroupAuthData, SWT.LEFT);
			userLabel.setLayoutData(gridData);
			userLabel.setText("User: ");
			
			userTf=new Text(childGroupAuthData, SWT.BORDER);
			GridData gridDataUserTf = new GridData();
			gridDataUserTf.widthHint = 100;
			gridDataUserTf.heightHint = 20;
			userTf.setLayoutData(gridDataUserTf);
			
			passwordLabel=new Label(childGroupAuthData, SWT.LEFT);
			passwordLabel.setLayoutData(gridData);
			passwordLabel.setText("Password: ");
			
			
			passwordTf=new Text(childGroupAuthData, SWT.BORDER);
			GridData gridDataPasswordTf = new GridData();
			gridDataPasswordTf.widthHint = 100;
			gridDataPasswordTf.heightHint = 20;
			passwordTf.setLayoutData(gridDataPasswordTf);
			
			//AuthDataCombosite.setVisible(false);
		}
        /**
         * to creat the UI elements and fields of the "General"
         */
        private void Creat_General_Elements() {
        	//GeneralCombosite=new Composite(comb, SWT.NONE);
        	//GeneralCombosite.setLayout(new FillLayout());
	        //comb.setBounds(1, 0, 100, 400);
	        //GridData gridDataAuthDataCombosite = new GridData();
	        //gridDataAuthDataCombosite.widthHint = 530;
	        //gridDataAuthDataCombosite.heightHint = 215;
	        //GeneralCombosite.setLayoutData(gridDataAuthDataCombosite);
	        
	        //Group childGroup = new Group(AuthDataCombosite, SWT.NONE);
        	//childGroupAuthData.setText("General");
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 2;
	        childGroupAuthData.setLayout(layout);
	        GridData data = new GridData();
	        data.widthHint = 250;
	        data.heightHint = 60;
	        //data.horizontalSpan = 2;
	        childGroupAuthData.setLayoutData(data);
        	
	        GridData gridData = new GridData();
			gridData.widthHint = 100;
			gridData.heightHint = 20;
			
			auditingLabel=new Label(childGroupAuthData, SWT.LEFT);
			auditingLabel.setLayoutData(gridData);
			auditingLabel.setText("Activate or deactivate the Auditing: ");
			
			//Composite buttons=new Composite(childGroup, SWT.BORDER);
			//buttons.setLayoutData(gridData);
			on=new Button(childGroupAuthData, SWT.CENTER);
			on.setText("Activate");
			on.setLayoutData(gridData);
			//on.set(Color.gray);
			//Button off=new Button(buttons, SWT.CENTER);
			//off.setText("Deactivate");
			
			on.addListener(SWT.Selection, new Listener() {
	      	      public void handleEvent(Event event) {
	      	    	  if(on.getText()=="Activate"){
	      	    		on.setText("Deactivate");
	      	    	  }
	      	    	  else on.setText("Activate");
	      	        }
	      	});
			
	      
			
			
	        
	        //GeneralCombosite.setVisible(false);
		}
        
        private void CreatButtons(){
        	ButtonComp =new Composite(shell, SWT.RIGHT_TO_LEFT);
        	ButtonComp.setLayout(new GridLayout());
        	ButtonComp.setSize(160, 70);
        	
//	        GridData gridDataButtons = new GridData();
//	        gridDataButtons.widthHint = 100;
//	        gridDataButtons.heightHint = 50;
//	        comb.setLayoutData(gridDataButtons);
	        
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 2;
	        ButtonComp.setLayout(layout);
	        GridData data = new GridData(GridData.FILL_BOTH);
	        data.horizontalSpan = 2;
	        ButtonComp.setLayoutData(data);
	        
	        
	        Button resetButton = new Button(ButtonComp, SWT.NONE);
	        GridData gridDataResetButton = new GridData();
	        gridDataResetButton.widthHint = 75;
	        gridDataResetButton.heightHint = 25;
	        resetButton.setLayoutData(gridDataResetButton);
	        //resetButton.setBounds(1, 1, 50, 25);
	        resetButton.setText("Reset");

	        Button defaultButton = new Button(ButtonComp, SWT.NONE);
	        GridData gridDataDefaultButton = new GridData();
	        gridDataDefaultButton.widthHint = 75;
	        gridDataDefaultButton.heightHint = 25;
	        defaultButton.setLayoutData(gridDataDefaultButton);
	        //defaultButton.setBounds(1, 0, 50, 25);
	        defaultButton.setText("Default");
	        
        	Button saveButton = new Button(ButtonComp, SWT.NONE);
//        	saveButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        	GridData gridDatasaveButton = new GridData();
	        gridDatasaveButton.widthHint = 75;
	        gridDatasaveButton.heightHint = 25;
	        saveButton.setLayoutData(gridDatasaveButton);
        	//saveButton.setSize(70, 20);
	        saveButton.setText("Save");

	        Button closeButton = new Button(ButtonComp, SWT.NONE);
	        GridData gridDataCloseButton = new GridData();
	        gridDataCloseButton.widthHint = 75;
	        gridDataCloseButton.heightHint = 25;
	        closeButton.setLayoutData(gridDataCloseButton);
	        //closeButton.setBounds(0, 1, 50, 25);
	        closeButton.setText("Close");
	        
	       

//	        FormData closeData = new FormData(80, 30);
//	        closeData.right = new FormAttachment(98);
//	        closeData.bottom = new FormAttachment(resetButton, -5, SWT.TOP);
//	        //closeData.bottom = new FormAttachment(95);
//	        closeButton.setLayoutData(closeData);
//
//	        FormData saveData = new FormData(80, 30);
//	        saveData.right = new FormAttachment(closeButton, -5, SWT.LEFT);
//	        saveData.bottom = new FormAttachment(defaultButton, -5, SWT.TOP);
//	        //saveData.bottom = new FormAttachment(closeButton, 0, SWT.BOTTOM);
//	        saveButton.setLayoutData(saveData);
//	        
//	        
//	        FormData resetData = new FormData(80, 30);
//	        resetData.right = new FormAttachment(98);
//	        resetData.top = new FormAttachment(95);
//	        resetButton.setLayoutData(resetData);
//	        
//	        FormData defaultData = new FormData(80, 30);
//	        defaultData.right = new FormAttachment(resetButton, -5, SWT.LEFT);
//	        defaultData.top = new FormAttachment(resetButton, 0, SWT.TOP);
//	        defaultData.bottom = new FormAttachment(resetButton, 0, SWT.BOTTOM);
//	        defaultButton.setLayoutData(defaultData);
	    }
        
        
        protected CBanner createCBanner(Composite parent) {
            return new CBanner(parent, SWT.NONE);
        }

        
       
}