package org.eclipse.bpel.ui.editparts;

import java.util.List;

import org.eclipse.bpel.common.ui.decorator.EditPartMarkerDecorator;
import org.eclipse.bpel.common.ui.tray.TrayCategoryEntryEditPart;
import org.eclipse.bpel.common.ui.tray.TrayMarkerDecorator;
import org.eclipse.bpel.model.Scope;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IHoverHelper;
import org.eclipse.bpel.ui.IHoverHelperSupport;
import org.eclipse.bpel.ui.adapters.IMarkerHolder;
import org.eclipse.bpel.ui.editparts.policies.BPELComponentEditPolicy;
import org.eclipse.bpel.ui.editparts.policies.BPELDirectEditPolicy;
import org.eclipse.bpel.ui.extensions.BPELUIRegistry;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;

/**
 * Tray category edit part.
 * 
 * @author IBM
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 23, 2007
 *
 */

public abstract class BPELTrayCategoryEntryEditPart extends TrayCategoryEntryEditPart implements IHoverHelperSupport{
	
	protected MouseMotionListener mouseMotionListener;
	
	@Override
	protected AccessibleEditPart createAccessible() {
		return new BPELTrayAccessibleEditPart(this);
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		// handles deletions
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BPELComponentEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new BPELDirectEditPolicy());
	}

	/** (non-Javadoc)
	 * @see org.eclipse.bpel.common.ui.tray.TrayCategoryEntryEditPart#createEditPartMarkerDecorator()
	 */
	
	@Override
	protected EditPartMarkerDecorator createEditPartMarkerDecorator() {

		return new TrayMarkerDecorator((EObject)getModel(), new ToolbarLayout()) {
			@Override
			protected IMarker[] getMarkers () {
				
				IMarkerHolder holder = BPELUtil.adapt(modelObject, IMarkerHolder.class);
				
				if (holder != null) {
					return holder.getMarkers(modelObject);
				}
				
				return super.getMarkers();
			}
		};
		
	}

	
	
	/**
	 * @see org.eclipse.bpel.ui.IHoverHelperSupport#refreshHoverHelp()
	 */
	public void refreshHoverHelp() {
		
		// Refresh the tool-tip if we can find a helper.
		IHoverHelper helper = null; 
		try {
			helper = BPELUIRegistry.getInstance().getHoverHelper();
			if (helper == null) {
				return;				
			}
		} catch (CoreException e) {
			getFigure().setToolTip(null);
			BPELUIPlugin.log(e);
			return ;
		}		
		
		
		IFigure tooltip = helper.getHoverFigure((EObject)getModel());
		getFigure().setToolTip(tooltip);

	}

	protected MouseMotionListener getMouseMotionListener() {
		if (mouseMotionListener == null) {
			this.mouseMotionListener = new MouseMotionListener() {
				public void mouseDragged(MouseEvent me) {
				}
				public void mouseEntered(MouseEvent me) {
				}
				public void mouseExited(MouseEvent me) {
				}
				public void mouseHover(MouseEvent me) {
				}
				public void mouseMoved(MouseEvent me) {
					refreshHoverHelp();
				}
			};
		}
		return mouseMotionListener;
	}
	
	@Override
	protected IFigure createFigure() {
		IFigure fig =  super.createFigure();
		fig.addMouseMotionListener(getMouseMotionListener());
		return fig;
	}
	
	/**
	 * HACK
	 * See comments in org.eclipse.bpel.ui.editparts.BPELTrayCategoryEditPart.selectAnotherEntry()
	 */
	
	@Override
	public void removeNotify() {
		// we only do the following hack if we are dealing with scoped variables
		if (!(((EObject)getParent().getModel()).eContainer() instanceof Scope)) {
			super.removeNotify();
			return;
		}
		if (getSelected() != SELECTED_NONE) {
//			getViewer().deselect(this); 
			// instead of deselecting this entry (which would cause the process to be selected)
			// we should ask the parent edit part to select some other child
			((BPELTrayCategoryEditPart)getParent()).selectAnotherEntry();
		}
		if (hasFocus())
			getViewer().setFocus(null);

		List<EditPart> children = getChildren();
		for (int i = 0; i < children.size(); i++)
			children.get(i).removeNotify();
		unregister();
	}
}
