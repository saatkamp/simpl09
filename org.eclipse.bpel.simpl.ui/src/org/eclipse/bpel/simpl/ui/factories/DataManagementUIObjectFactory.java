/**
 * <b>Purpose:</b> This factory is used to create a graphical representation for every {@link DataManagementActivity}.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementUIObjectFactory.java 1755 2011-01-17 16:16:42Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.factories;


import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.ui.BPELDMUIPlugIn;
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
			ModelPackage.eINSTANCE.getQueryDataActivity(),
			ModelPackage.eINSTANCE.getIssueCommandActivity(),
			ModelPackage.eINSTANCE.getRetrieveDataActivity(),
			ModelPackage.eINSTANCE.getWriteDataBackActivity(),
			ModelPackage.eINSTANCE.getTransferDataActivity()};

	/**
	 * Instantiates a new data management UI object factory.
	 * 
	 * @param modelType
	 *            the model type
	 */
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
	
	/**
	 * Instantiates a new DataManagementUIObjectFactory.
	 */
	public DataManagementUIObjectFactory() {
		super();
	}

	/**
	 * Gets the small image descriptor.
	 * 
	 * @return the small image descriptor
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getSmallImageDescriptor()
	 */
	@Override
	public ImageDescriptor getSmallImageDescriptor() {
		return getSmallImageDescriptor(getModelType());
	}
	
	/**
	 * Gets the large image descriptor.
	 * 
	 * @return the large image descriptor
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getLargeImageDescriptor()
	 */
	@Override
	public ImageDescriptor getLargeImageDescriptor() {
		return getLargeImageDescriptor(getModelType());
	}
	
	/**
	 * Return the small image descriptor for the modelObject class passed.
	 * 
	 * @param modelObject
	 *            the model object
	 * @return the small image descriptor
	 */
	
	public static ImageDescriptor getSmallImageDescriptor (EClass modelObject) {
		return BPELDMUIPlugIn.getDefault().getImageDescriptor(
				OBJ16 + baseImageName(modelObject) + PNG);
	}
	
	/**
	 * Return the large image descriptor for the modelObject class passed.
	 * 
	 * @param modelObject
	 *            the model object
	 * @return the large image descriptor
	 */

	public static ImageDescriptor getLargeImageDescriptor(EClass modelObject) {
		return BPELDMUIPlugIn.getDefault().getImageDescriptor(
				OBJ20 + baseImageName(modelObject) + PNG);
	}
	
	/**
	 * Gets the small image.
	 * 
	 * @return the small image
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getSmallImage()
	 */
	@Override
	public Image getSmallImage() {
		return BPELDMUIPlugIn.getDefault().getImage(
				OBJ16 + baseImageName(getModelType()) + PNG);
	}

	/**
	 * Gets the large image.
	 * 
	 * @return the large image
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getLargeImage()
	 */
	@Override
	public Image getLargeImage() {
		return BPELDMUIPlugIn.getDefault().getImage(
				OBJ20 + baseImageName(getModelType()) + PNG);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getModelType()
	 */
	@Override
	public EClass getModelType() {
		return this.modelType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.factories.AbstractUIObjectFactory#getTypeLabel()
	 */
	@Override
	public String getTypeLabel() {
		return getModelType().getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.factories.IExtensionUIObjectFactory#getClassArray()
	 */
	@Override
	public EClass[] getClassArray() {
		return this.classArray;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.factories.IExtensionUIObjectFactory#setModelType(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public void setModelType(EClass modelType) {
		this.modelType = modelType;
	}

}
