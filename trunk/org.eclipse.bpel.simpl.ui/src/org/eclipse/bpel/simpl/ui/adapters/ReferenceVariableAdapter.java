/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.adapters;

import java.util.ArrayList;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.adapters.AbstractStatefulAdapter;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.ReferenceVariable;
import org.eclipse.bpel.simpl.ui.BPELDMUIPlugIn;
import org.eclipse.bpel.simpl.ui.DataManagementUIConstants;
import org.eclipse.bpel.simpl.ui.editparts.ReferenceVariableEditPart;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.VariableAdapter;
import org.eclipse.bpel.ui.editparts.OutlineTreeEditPart;
import org.eclipse.bpel.ui.editparts.VariableEditPart;
import org.eclipse.bpel.ui.properties.PropertiesLabelProvider;
import org.eclipse.bpel.ui.uiextensionmodel.UiextensionmodelFactory;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

// TODO: Auto-generated Javadoc
/**
 * ReferenceVariable adapter.
 * 
 */
public class ReferenceVariableAdapter extends VariableAdapter {

	/**
	 * Notify changed.
	 * 
	 * @param notification
	 *            the notification
	 * @see org.eclipse.bpel.model.adapters.AbstractAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		switch (notification.getEventType()) {
		case NOTIFICATION_MARKERS_STALE:
			fMarkers.clear();
			break;
		case NOTIFICATION_MARKER_ADDED:
			fMarkers.add((IMarker) notification.getNewValue());
			break;
		case NOTIFICATION_MARKER_DELETED:
			fMarkers.remove(notification.getOldValue());
			break;
		}
	}

	/** The markers. */
	ArrayList<IMarker> fMarkers = new ArrayList<IMarker>();

	/** The EMPT y_ markers. */
	static IMarker[] EMPTY_MARKERS = {};

	/**
	 * (non-Javadoc).
	 * 
	 * @param object
	 *            the object
	 * @return the markers
	 * @see org.eclipse.bpel.ui.adapters.IMarkerHolder#getMarkers(java.lang.Object)
	 */

	public IMarker[] getMarkers(Object object) {

		if (fMarkers.size() == 0) {
			return EMPTY_MARKERS;
		}
		return fMarkers.toArray(EMPTY_MARKERS);
	}

	/**
	 * Gets the name.
	 * 
	 * @param modelObject
	 *            the model object
	 * @return the name
	 * @see org.eclipse.bpel.ui.adapters.INamedElement#getName(java.lang.Object)
	 */
	public String getName(Object modelObject) {
		return getTarget(modelObject, ReferenceVariable.class).getName();
	}

	/**
	 * Sets the name.
	 * 
	 * @param modelObject
	 *            the model object
	 * @param name
	 *            the name
	 * @see org.eclipse.bpel.ui.adapters.INamedElement#setName(java.lang.Object,
	 *      java.lang.String)
	 */

	public void setName(Object modelObject, String name) {
		getTarget(modelObject, ReferenceVariable.class).setName(name);
	}

	/**
	 * Checks if is name affected.
	 * 
	 * @param modelObject
	 *            the model object
	 * @param n
	 *            the n
	 * @return true, if is name affected
	 * @see org.eclipse.bpel.ui.adapters.INamedElement#isNameAffected(java.lang.Object,
	 *      org.eclipse.emf.common.notify.Notification)
	 */
	public boolean isNameAffected(Object modelObject, Notification n) {
		return (n.getFeatureID(ReferenceVariable.class) == ModelPackage.REFERENCE_VARIABLE__NAME);
	}

	/**
	 * Gets the small image.
	 * 
	 * @param object
	 *            the object
	 * @return the small image
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getSmallImage(java.lang.Object)
	 */

	public Image getSmallImage(Object object) {
		return BPELDMUIPlugIn.INSTANCE
				.getImage(DataManagementUIConstants.ICON_REFERENCE_VARIABLE_16);
	}

	/**
	 * Gets the large image.
	 * 
	 * @param object
	 *            the object
	 * @return the large image
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLargeImage(java.lang.Object)
	 */
	public Image getLargeImage(Object object) {
		return BPELDMUIPlugIn.INSTANCE
				.getImage(DataManagementUIConstants.ICON_REFERENCE_VARIABLE_16);
	}

	/**
	 * Gets the type label.
	 * 
	 * @param object
	 *            the object
	 * @return the type label
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getTypeLabel(java.lang.Object)
	 */
	public String getTypeLabel(Object object) {
		return "ReferenceVariable";
	}

	/**
	 * Gets the label.
	 * 
	 * @param object
	 *            the object
	 * @return the label
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLabel(java.lang.Object)
	 */
	public String getLabel(Object object) {
		String name = getName(object);
		if (name != null) {
			return name;
		}
		return getTypeLabel(object);
	}

	/**
	 * Creates the edit part.
	 * 
	 * @param context
	 *            the context
	 * @param model
	 *            the model
	 * @return the edits the part
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */

	public EditPart createEditPart(EditPart context, Object model) {
		ReferenceVariableEditPart result = new ReferenceVariableEditPart();
		result.setLabelProvider(PropertiesLabelProvider.getInstance());
		result.setModel(model);
		return result;
	}

	/**
	 * Creates the outline edit part.
	 * 
	 * @param context
	 *            the context
	 * @param model
	 *            the model
	 * @return the edits the part
	 * @see org.eclipse.bpel.ui.adapters.IOutlineEditPartFactory#createOutlineEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */

	public EditPart createOutlineEditPart(EditPart context, Object model) {
		EditPart result = new OutlineTreeEditPart();
		result.setModel(model);
		return result;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param context
	 *            the context
	 * @param model
	 *            the model
	 * @return the edits the part
	 * @see org.eclipse.bpel.ui.adapters.ITrayEditPartFactory#createTrayEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */

	public EditPart createTrayEditPart(EditPart context, Object model) {
		return createEditPart(context, model);
	}

	/**
	 * Creates the extension.
	 * 
	 * @param object
	 *            the object
	 * @return the e object
	 * @see org.eclipse.bpel.ui.adapters.IExtensionFactory#createExtension(org.eclipse.emf.ecore.EObject)
	 */
	public EObject createExtension(EObject object) {
		return UiextensionmodelFactory.eINSTANCE.createVariableExtension();
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @return the content
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
	 */
	public String getContent() {
		return getLabel(getTarget());
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @return the cursor position
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
	 */
	public int getCursorPosition() {
		return -1;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
	 */
	public String getDescription() {
		// TODO: When we have a DOM representation, then description could be
		// returned
		// Variable v = (Variable) getTarget ( null, Variable.class );
		// 
		// Element elm = v.getDocumentationElement();
		// return (elm != null ? elm.getNodeValue() : null);
		return null;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
	 */

	public String getLabel() {
		return NLS.bind(Messages.VariableAdapter_0, getLabel(getTarget()),
				getLabel(getTarget()));
	}

}
