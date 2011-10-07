package org.eclipse.simpl.statementtest.ui.wizards.pages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * <b>Purpose: Base wizard page that prescribes the layout and common elements of all
 * statement test wizard pages.</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTestWizardPage.java 63 2010-07-10 22:25:38Z graflow $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTestWizardPage extends WizardPage {
  /**
   * The parent composite.
   */
  protected Composite parentComposite = null;

  /**
   * The common composite.
   */
  protected Composite commonComposite = null;

  /**
   * The individual page composite.
   */
  protected Composite pageComposite = null;

  /**
   * @param pageName
   * @param title
   * @param titleImage
   */
  protected StatementTestWizardPage(String pageName, String title,
      ImageDescriptor titleImage) {
    super(pageName, title, titleImage);
  }

  /*
   * (non-Javadoc)
   * @see
   * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite
   * )
   */
  @Override
  public void createControl(Composite parent) {
    // initialize the composites
    this.parentComposite = new Composite(parent, SWT.NONE);
    this.commonComposite = new Composite(this.parentComposite, SWT.NONE);
    this.pageComposite = new Composite(this.parentComposite, SWT.NONE);

    GridData commonCompositeGridData = new GridData(SWT.TOP, SWT.LEFT, false, true);
    commonCompositeGridData.widthHint = 0; // common composite is not shown yet

    // define the composite layout
    this.parentComposite.setLayout(new GridLayout(2, false));
    this.pageComposite.setLayout(new GridLayout());
    this.commonComposite.setLayout(new GridLayout());
    this.commonComposite.setLayoutData(commonCompositeGridData);
    this.pageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    // set background for transparent labels
    this.commonComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
    this.pageComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);

    // debug composite colors
    // this.parentComposite.setBackground(new Color(parent.getDisplay(), 0, 250, 0));
    // this.commonComposite.setBackground(new Color(parent.getDisplay(), 250, 250, 0));
    // this.pageComposite.setBackground(new Color(parent.getDisplay(), 250, 0, 250));

    // common composite test label
    // Label label = new Label(this.commonComposite, SWT.TOP);
    // label.setText("I am visible on every page");
    // label.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, true));

    // Liste mit DMAktivitäten abrufen
    // IViewPart outline =
    // PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.ui.views.ContentOutline");
    // Object outlinePage = outline.getAdapter(ContentOutlinePage.class);
    // System.out.println((ContentOutlinePage)outlinePage);
    // System.out.println("outline: " + outline.getAdapter(ContentOutlinePage.class));

    // Object a = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
    // .getActiveEditor().getAdapter(IOutlineEditPartFactory.class);
    //
    // ProcessOutlineEditPart p = (ProcessOutlineEditPart)a;
    // System.out.println(a);

    // System.out.println(a);
    // ProcessOutlineEditPart p = new ProcessOutlineEditPart();

    // GUI List erstellen und mit DMAktivitäten füllen
    // List activityList = new List(this.pageComposite, SWT.BORDER);
    //
    // activityList.setLayoutData(activityListGridData);
    //
    // activityList.add("Aktivität1");
    // activityList.add("Aktivität2");
  }
}