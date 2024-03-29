/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.details.providers;

import java.util.List;

import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 25, 2006
 *
 */
public class SeparatorContentProvider extends AbstractContentProvider {

	
	
	@Override
	public void collectElements(Object input, List list) {
		list.add( new Separator () );
	}
	
	
	public class Separator implements IContentProposal , ILabelProvider, ILabeledElement {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
		 */
		public String getContent() {
			return ""; //$NON-NLS-1$
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
		 */
		public int getCursorPosition() {
			return (-1);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
		 */
		public String getDescription() {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
		 */
		public String getLabel() {
			return "----------------------------------------"; //$NON-NLS-1$
		}

		
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {			
			return BPELUIPlugin.INSTANCE.getImage(IBPELUIConstants.ICON_SEPARATOR_16);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
			return getLabel();
		}

		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void addListener (ILabelProviderListener listener) {
			
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
		 */
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
		 */
		public boolean isLabelProperty(Object element, String property) {
			return true;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void removeListener(ILabelProviderListener listener) {
			
		}
		
		
		

		/* (non-Javadoc)
		 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLabel(java.lang.Object)
		 */
		public String getLabel(Object object) {
			return getLabel();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLargeImage(java.lang.Object)
		 */
		public Image getLargeImage(Object object) {		
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getSmallImage(java.lang.Object)
		 */
		public Image getSmallImage(Object object) {
			return getImage( object );
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getTypeLabel(java.lang.Object)
		 */
		public String getTypeLabel(Object object) {

			return null;
		}
		
	}

	

}
