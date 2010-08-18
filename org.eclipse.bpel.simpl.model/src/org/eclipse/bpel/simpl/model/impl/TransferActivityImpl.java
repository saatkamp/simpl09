/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferActivity;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transfer Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTargetDsAddress <em>Target Ds Address</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTargetDsType <em>Target Ds Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTargetDsKind <em>Target Ds Kind</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTargetDsLanguage <em>Target Ds Language</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTargetDsContainer <em>Target Ds Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransferActivityImpl extends DataManagementActivityImpl implements TransferActivity {
	/**
	 * The default value of the '{@link #getTargetDsAddress() <em>Target Ds Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsAddress()
	 * @customized
	 * @ordered
	 */
	protected static final String TARGET_DS_ADDRESS_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTargetDsAddress() <em>Target Ds Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsAddress()
	 * @generated
	 * @ordered
	 */
	protected String targetDsAddress = TARGET_DS_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetDsType() <em>Target Ds Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsType()
	 * @customized
	 * @ordered
	 */
	protected static final String TARGET_DS_TYPE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTargetDsType() <em>Target Ds Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsType()
	 * @generated
	 * @ordered
	 */
	protected String targetDsType = TARGET_DS_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetDsKind() <em>Target Ds Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsKind()
	 * @customized
	 * @ordered
	 */
	protected static final String TARGET_DS_KIND_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTargetDsKind() <em>Target Ds Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsKind()
	 * @generated
	 * @ordered
	 */
	protected String targetDsKind = TARGET_DS_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetDsLanguage() <em>Target Ds Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsLanguage()
	 * @customized
	 * @ordered
	 */
	protected static final String TARGET_DS_LANGUAGE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTargetDsLanguage() <em>Target Ds Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsLanguage()
	 * @generated
	 * @ordered
	 */
	protected String targetDsLanguage = TARGET_DS_LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetDsContainer() <em>Target Ds Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsContainer()
	 * @customized
	 * @ordered
	 */
	protected static final String TARGET_DS_CONTAINER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTargetDsContainer() <em>Target Ds Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetDsContainer()
	 * @generated
	 * @ordered
	 */
	protected String targetDsContainer = TARGET_DS_CONTAINER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransferActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TRANSFER_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetDsAddress() {
		return targetDsAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setTargetDsAddress(String newTargetDsAddress) {
		String oldTargetDsAddress = targetDsAddress;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsAddress().getName(),
					newTargetDsAddress);
		}
		targetDsAddress = newTargetDsAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_ADDRESS, oldTargetDsAddress, targetDsAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetDsType() {
		return targetDsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setTargetDsType(String newTargetDsType) {
		String oldTargetDsType = targetDsType;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsType().getName(),
					newTargetDsType);
		}
		targetDsType = newTargetDsType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_TYPE, oldTargetDsType, targetDsType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetDsKind() {
		return targetDsKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setTargetDsKind(String newTargetDsKind) {
		String oldTargetDsKind = targetDsKind;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsKind().getName(),
					newTargetDsKind);
		}
		targetDsKind = newTargetDsKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_KIND, oldTargetDsKind, targetDsKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetDsLanguage() {
		return targetDsLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setTargetDsLanguage(String newTargetDsLanguage) {
		String oldTargetDsLanguage = targetDsLanguage;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsLanguage().getName(),
					newTargetDsLanguage);
		}
		targetDsLanguage = newTargetDsLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE, oldTargetDsLanguage, targetDsLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetDsContainer() {
		return targetDsContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @customized
	 */
	public void setTargetDsContainer(String newTargetDsContainer) {
		String oldTargetDsContainer = targetDsContainer;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_TargetDsContainer().getName(),
					newTargetDsContainer);
		}
		targetDsContainer = newTargetDsContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_CONTAINER, oldTargetDsContainer, targetDsContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_ADDRESS:
				return getTargetDsAddress();
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_TYPE:
				return getTargetDsType();
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_KIND:
				return getTargetDsKind();
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE:
				return getTargetDsLanguage();
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_CONTAINER:
				return getTargetDsContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_ADDRESS:
				setTargetDsAddress((String)newValue);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_TYPE:
				setTargetDsType((String)newValue);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_KIND:
				setTargetDsKind((String)newValue);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE:
				setTargetDsLanguage((String)newValue);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_CONTAINER:
				setTargetDsContainer((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_ADDRESS:
				setTargetDsAddress(TARGET_DS_ADDRESS_EDEFAULT);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_TYPE:
				setTargetDsType(TARGET_DS_TYPE_EDEFAULT);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_KIND:
				setTargetDsKind(TARGET_DS_KIND_EDEFAULT);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE:
				setTargetDsLanguage(TARGET_DS_LANGUAGE_EDEFAULT);
				return;
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_CONTAINER:
				setTargetDsContainer(TARGET_DS_CONTAINER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_ADDRESS:
				return TARGET_DS_ADDRESS_EDEFAULT == null ? targetDsAddress != null : !TARGET_DS_ADDRESS_EDEFAULT.equals(targetDsAddress);
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_TYPE:
				return TARGET_DS_TYPE_EDEFAULT == null ? targetDsType != null : !TARGET_DS_TYPE_EDEFAULT.equals(targetDsType);
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_KIND:
				return TARGET_DS_KIND_EDEFAULT == null ? targetDsKind != null : !TARGET_DS_KIND_EDEFAULT.equals(targetDsKind);
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_LANGUAGE:
				return TARGET_DS_LANGUAGE_EDEFAULT == null ? targetDsLanguage != null : !TARGET_DS_LANGUAGE_EDEFAULT.equals(targetDsLanguage);
			case ModelPackage.TRANSFER_ACTIVITY__TARGET_DS_CONTAINER:
				return TARGET_DS_CONTAINER_EDEFAULT == null ? targetDsContainer != null : !TARGET_DS_CONTAINER_EDEFAULT.equals(targetDsContainer);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (targetDsAddress: ");
		result.append(targetDsAddress);
		result.append(", targetDsType: ");
		result.append(targetDsType);
		result.append(", targetDsKind: ");
		result.append(targetDsKind);
		result.append(", targetDsLanguage: ");
		result.append(targetDsLanguage);
		result.append(", targetDsContainer: ");
		result.append(targetDsContainer);
		result.append(')');
		return result.toString();
	}

} //TransferActivityImpl
