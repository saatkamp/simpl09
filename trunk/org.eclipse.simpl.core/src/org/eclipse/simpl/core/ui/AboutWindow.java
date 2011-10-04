package org.eclipse.simpl.core.ui;

import java.net.URLDecoder;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.simpl.core.SIMPLCorePlugIn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;


/**
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * This class is based on the eclipse internal AboutDialog class.
 */
public class AboutWindow extends TrayDialog {

	final String aboutText = "SIMPL Framework for Eclipse\n" +
			"\n" +
			"Version: " + SIMPLCorePlugIn.getDefault().getBundle().getVersion() + " \n" +
			"\n" +
			"(c) Licensed under the Apache License, Version 2.0.\n" +
			"http://www.apache.org/licenses/LICENSE-2.0.\n" +
			"\n" +
			"Visit http://code.google.com/p/simpl09/\n" +
			"\n" +
			"Members of the SIMPL project team:\n" +
			"Wolfgang Hüttig, Daniel Brüderle, Michael Schneidt,\n" +
			"René Rehn, Firas Zoabi, Tu Xi, Michael Hahn.\n"+
			"\n"+
			"This product includes software developed by the\n" +
			"Apache Software Foundation http://www.apache.org/\n";

	private StyledText text;

	/**
	 * Create an instance of the AboutDialog for the given window.
	 * 
	 * @param parentShell
	 *            The parent of the dialog.
	 */
	public AboutWindow(Shell parentShell) {
		super(parentShell);
	}

	/*
	 * (non-Javadoc) Method declared on Window.
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("About SIMPL");
	}

	/**
	 * Add buttons to the dialog's button bar.
	 * 
	 * Subclasses should override.
	 * 
	 * @param parent
	 *            the button bar composite
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label l = new Label(parent, SWT.NONE);
		l.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = (GridLayout) parent.getLayout();
		layout.numColumns++;
		layout.makeColumnsEqualWidth = false;

		Button b = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		b.setFocus();
	}

	/**
	 * Creates and returns the contents of the upper part of the dialog (above
	 * the button bar).
	 * 
	 * Subclasses should overide.
	 * 
	 * @param parent
	 *            the parent composite to contain the dialog area
	 * @return the dialog area control
	 */
	protected Control createDialogArea(Composite parent) {
		// brand the about box if there is product info
		Image aboutImage = null;
		ImageDescriptor imageDescriptor = SIMPLCorePlugIn
				.getImageDescriptor("icons/SIMPL-logo-big.png");

		if (imageDescriptor != null) {
			aboutImage = imageDescriptor.createImage();
		}

		// create a composite which is the parent of the top area and the bottom
		// button bar, this allows there to be a second child of this composite
		// with
		// a banner background on top but not have on the bottom
		Composite workArea = new Composite(parent, SWT.NONE);
		GridLayout workLayout = new GridLayout();
		workLayout.marginHeight = 0;
		workLayout.marginWidth = 0;
		workLayout.verticalSpacing = 0;
		workLayout.horizontalSpacing = 0;
		workArea.setLayout(workLayout);
		workArea.setLayoutData(new GridData(GridData.FILL_BOTH));

		// page group
		Color background = JFaceColors.getBannerBackground(parent.getDisplay());
		Color foreground = JFaceColors.getBannerForeground(parent.getDisplay());
		Composite top = (Composite) super.createDialogArea(workArea);

		// override any layout inherited from createDialogArea
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		top.setLayout(layout);
		top.setLayoutData(new GridData(GridData.FILL_BOTH));
		top.setBackground(background);
		top.setForeground(foreground);

		// the image & text
		final Composite topContainer = new Composite(top, SWT.NONE);
		topContainer.setBackground(background);
		topContainer.setForeground(foreground);

		layout = new GridLayout();
		layout.numColumns = (aboutImage == null ? 1 : 2);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		topContainer.setLayout(layout);

		GC gc = new GC(parent);
		// arbitrary default
		int topContainerHeightHint = 100;
		try {
			// default height enough for 6 lines of text
			topContainerHeightHint = Math.max(topContainerHeightHint, gc
					.getFontMetrics().getHeight() * 6);
		} finally {
			gc.dispose();
		}

		// image on left side of dialog
		if (aboutImage != null) {
			Label imageLabel = new Label(topContainer, SWT.NONE);
			imageLabel.setBackground(background);
			imageLabel.setForeground(foreground);

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.verticalAlignment = GridData.BEGINNING;
			data.grabExcessHorizontalSpace = false;
			imageLabel.setLayoutData(data);
			imageLabel.setImage(aboutImage);
			topContainerHeightHint = Math.max(topContainerHeightHint,
					aboutImage.getBounds().height);
		}

		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.heightHint = topContainerHeightHint;
		topContainer.setLayoutData(data);

		final int minWidth = 400; // This value should really be calculated
		// from the computeSize(SWT.DEFAULT,
		// SWT.DEFAULT) of all the
		// children in infoArea excluding the
		// wrapped styled text
		// There is no easy way to do this.
		final ScrolledComposite scroller = new ScrolledComposite(topContainer,
				SWT.V_SCROLL | SWT.H_SCROLL);
		data = new GridData(GridData.FILL_BOTH);
		data.widthHint = minWidth;
		scroller.setLayoutData(data);

		final Composite textComposite = new Composite(scroller, SWT.NONE);
		textComposite.setBackground(background);

		layout = new GridLayout();
		textComposite.setLayout(layout);

		text = new StyledText(textComposite, SWT.MULTI | SWT.WRAP
				| SWT.READ_ONLY);
		text.setCaret(null);
		text.setFont(parent.getFont());
		text.setText(aboutText);
		text.setCursor(null);
		text.setBackground(background);
		text.setForeground(foreground);

		StyleRange style1 = new StyleRange();
	    style1.start = 0;
	    style1.length = 27;
	    style1.fontStyle = SWT.BOLD;
	    
	    text.setStyleRange(style1);

		createTextMenu();

		GridData gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		text.setLayoutData(gd);

		// Adjust the scrollbar increments
		scroller.getHorizontalBar().setIncrement(20);
		scroller.getVerticalBar().setIncrement(20);

		final boolean[] inresize = new boolean[1]; // flag to stop unneccesary
		// recursion
		textComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				if (inresize[0])
					return;
				inresize[0] = true;
				// required because of bugzilla report 4579
				textComposite.layout(true);
				// required because you want to change the height that the
				// scrollbar will scroll over when the width changes.
				int width = textComposite.getClientArea().width;
				Point p = textComposite.computeSize(width, SWT.DEFAULT);
				scroller.setMinSize(minWidth, p.y);
				inresize[0] = false;
			}
		});

		scroller.setExpandHorizontal(true);
		scroller.setExpandVertical(true);
		Point p = textComposite.computeSize(minWidth, SWT.DEFAULT);
		textComposite.setSize(p.x, p.y);
		scroller.setMinWidth(minWidth);
		scroller.setMinHeight(p.y);

		scroller.setContent(textComposite);

		// horizontal bar
		Label bar = new Label(workArea, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		bar.setLayoutData(data);

		// add image buttons for bundle groups that have them
		Composite bottom = (Composite) super.createDialogArea(workArea);
		// override any layout inherited from createDialogArea
		layout = new GridLayout();
		bottom.setLayout(layout);
		data = new GridData();
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;

		bottom.setLayoutData(data);

		// spacer
		bar = new Label(bottom, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		bar.setLayoutData(data);

		return workArea;
	}

	/**
	 * Create the context menu for the text widget.
	 * 
	 * @since 3.4
	 */
	private void createTextMenu() {
		final MenuManager textManager = new MenuManager();
		textManager.add(new CommandContributionItem(
				new CommandContributionItemParameter(PlatformUI.getWorkbench(),
						null, IWorkbenchCommandConstants.EDIT_COPY,
						CommandContributionItem.STYLE_PUSH)));
		textManager.add(new CommandContributionItem(
				new CommandContributionItemParameter(PlatformUI.getWorkbench(),
						null, IWorkbenchCommandConstants.EDIT_SELECT_ALL,
						CommandContributionItem.STYLE_PUSH)));
		text.setMenu(textManager.createContextMenu(text));
		text.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				textManager.dispose();
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
	 */
	protected boolean isResizable() {
		return true;
	}
}
