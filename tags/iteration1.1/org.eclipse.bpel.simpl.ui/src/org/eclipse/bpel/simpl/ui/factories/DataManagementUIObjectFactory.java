package org.eclipse.bpel.simpl.ui.factories;


import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.ui.Activator;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.ui.factories.AbstractUIObjectFactory;
import org.eclipse.bpel.ui.factories.IExtensionUIObjectFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class DataManagementUIObjectFactory extends AbstractUIObjectFactory
		implements IExtensionUIObjectFactory {

	protected static final String PNG = ".png";
	
	protected static final String OBJ16 = "obj16/";

	protected static final String OBJ20 = "obj20/";
	
	private EClass modelType;
	
	private EClass[] classArray = {  
			ModelPackage.eINSTANCE.getQueryActivity(),
			ModelPackage.eINSTANCE.getInsertActivity(),
			ModelPackage.eINSTANCE.getUpdateActivity(),
			ModelPackage.eINSTANCE.getDeleteActivity(),
			ModelPackage.eINSTANCE.getCreateActivity(),
			ModelPackage.eINSTANCE.getDropActivity(),
			ModelPackage.eINSTANCE.getCallActivity(),
			ModelPackage.eINSTANCE.getRetrieveDataActivity()};

	public DataManagementUIObjectFactory(EClass modelType) {
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
	
	public DataManagementUIObjectFactory() {
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
				OBJ16 + baseImageName(modelObject) + PNG);
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
				OBJ16 + baseImageName(getModelType()) + PNG);
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
