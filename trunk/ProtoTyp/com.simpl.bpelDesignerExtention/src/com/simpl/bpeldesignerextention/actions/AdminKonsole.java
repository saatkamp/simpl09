package com.simpl.bpeldesignerextention.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import UI.SWTApp;



public class AdminKonsole implements IWorkbenchWindowActionDelegate{
	
	private IWorkbenchWindow window;
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IAction arg0) {
		// TODO Auto-generated method stub
		SWTApp swtUI=new SWTApp(Display.getDefault());
		//window.getShell().open();
	//System.out.print(testApp.test);
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

}
