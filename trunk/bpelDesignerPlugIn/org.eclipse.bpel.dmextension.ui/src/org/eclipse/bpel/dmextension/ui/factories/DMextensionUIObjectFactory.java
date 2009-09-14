package org.eclipse.bpel.dmextension.ui.factories;


import org.eclipse.bpel.dmextension.model.ModelPackage;
import org.eclipse.bpel.dmextension.ui.Activator;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.ui.factories.AbstractUIObjectFactory;
import org.eclipse.bpel.ui.factories.IExtensionUIObjectFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class DMextensionUIObjectFactory extends AbstractUIObjectFactory
		implements IExtensionUIObjectFactory {
	
	protected static final String GIF = ".gif";

	protected static final String PNG = ".png";
	
	protected static final String OBJ16 = "obj16/";

	protected static final String OBJ20 = "obj20/";
	
	private EClass modelType;
	
	private EClass[] classArray = { 
			ModelPackage.eINSTANCE.getDataManagementActivity(), 
			ModelPackage.eINSTANCE.getCallProcedureActivity(),
			ModelPackage.eINSTANCE.getCreateActivity(), 
			ModelPackage.eINSTANCE.getDeleteActivity(),
			ModelPackage.eINSTANCE.getInsertActivity(), 
			ModelPackage.eINSTANCE.getRetrieveSetActivity(),
			ModelPackage.eINSTANCE.getWriteBackActivity(), 
			ModelPackage.eINSTANCE.getUpdateActivity(),
			ModelPackage.eINSTANCE.getSelectActivity()};

	public DMextensionUIObjectFactory(EClass modelType) {
		super();
		this.modelType = modelType;
	}

	protected static String baseImageName(EClass modelObject) {
		// TODO: this is a hack and shouldn't be necessary
		if (modelObject == BPELPackage.eINSTANCE.getVariable()) {
			return "variable"; //$NON-NLS-1$
		}
		if (modelObject == BPELPackage.eINSTANCE.getPartnerLink()) {
			return "partner"; //$NON-NLS-1$
		}
		return modelObject.getName().toLowerCase();
	}
	
	public DMextensionUIObjectFactory() {
		super();
	}

	/**
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getSmallImageDescriptor()
	 */
	@Override
	public ImageDescriptor getSmallImageDescriptor() {
		return getSmallImageDescriptor(getModelType());
	}
	
	/**
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getLargeImageDescriptor()
	 */
	@Override
	public ImageDescriptor getLargeImageDescriptor() {
		return getLargeImageDescriptor(getModelType());
	}
	
	/**
	 * Return the small image descriptor for the modelObject class passed.
	 * @param modelObject
	 * @return the small image descriptor 
	 */
	
	public static ImageDescriptor getSmallImageDescriptor (EClass modelObject) {
		return Activator.getDefault().getImageDescriptor(
				OBJ16 + baseImageName(modelObject) + GIF);
	}
	
	/**
	 * Return the large image descriptor for the modelObject class passed.
	 * @param modelObject
	 * @return the large image descriptor 
	 */

	public static ImageDescriptor getLargeImageDescriptor(EClass modelObject) {
		return Activator.getDefault().getImageDescriptor(
				OBJ20 + baseImageName(modelObject) + PNG);
	}
	
	/**
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getSmallImage()
	 */
	@Override
	public Image getSmallImage() {
		return Activator.getDefault().getImage(
				OBJ16 + baseImageName(getModelType()) + GIF);
	}

	/**
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getLargeImage()
	 */
	@Override
	public Image getLargeImage() {
		return Activator.getDefault().getImage(
				OBJ20 + baseImageName(getModelType()) + PNG);
	}
	
	@Override
	public EClass getModelType() {
		return this.modelType;
	}

	@Override
	public String getTypeLabel() {
		return getModelType().getName();
	}

	@Override
	public EClass[] getClassArray() {
		return this.classArray;
	}

	@Override
	public void setModelType(EClass modelType) {
		this.modelType = modelType;
	}

}
