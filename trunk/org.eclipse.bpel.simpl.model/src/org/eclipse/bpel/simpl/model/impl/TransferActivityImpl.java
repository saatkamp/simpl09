/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.TransferActivity;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Transfer Activity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getFromSource
 * <em>From Source</em>}</li>
 * <li>
 * {@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getToSource
 * <em>To Source</em>}</li>
 * <li>{@link org.eclipse.bpel.simpl.model.impl.TransferActivityImpl#getTarget
 * <em>Target</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TransferActivityImpl extends DataManagementActivityImpl implements
		TransferActivity {
	/**
	 * The cached value of the '{@link #getFromSource() <em>From Source</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFromSource()
	 * @generated
	 * @ordered
	 */
	protected DataManagementActivity fromSource;

	/**
	 * The cached value of the '{@link #getToSource() <em>To Source</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getToSource()
	 * @generated
	 * @ordered
	 */
	protected DataManagementActivity toSource;

	/**
	 * The default value of the '{@link #getTarget() <em>Target</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected String target = TARGET_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TransferActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TRANSFER_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DataManagementActivity getFromSource() {
		if (fromSource != null && fromSource.eIsProxy()) {
			InternalEObject oldFromSource = (InternalEObject) fromSource;
			fromSource = (DataManagementActivity) eResolveProxy(oldFromSource);
			if (fromSource != oldFromSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE,
							oldFromSource, fromSource));
			}
		}
		return fromSource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DataManagementActivity basicGetFromSource() {
		return fromSource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @customized
	 */
	public void setFromSource(DataManagementActivity newFromSource) {
		DataManagementActivity oldFromSource = fromSource;
		if (!isReconciling) {
			ReconciliationHelper.replaceChild(this, oldFromSource,
					newFromSource);
		}
		fromSource = newFromSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE, oldFromSource,
					fromSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DataManagementActivity getToSource() {
		if (toSource != null && toSource.eIsProxy()) {
			InternalEObject oldToSource = (InternalEObject) toSource;
			toSource = (DataManagementActivity) eResolveProxy(oldToSource);
			if (toSource != oldToSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE,
							oldToSource, toSource));
			}
		}
		return toSource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DataManagementActivity basicGetToSource() {
		return toSource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @customized
	 */
	public void setToSource(DataManagementActivity newToSource) {
		DataManagementActivity oldToSource = toSource;
		if (!isReconciling) {
			ReconciliationHelper.replaceChild(this, oldToSource, newToSource);
		}
		toSource = newToSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE, oldToSource,
					toSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @customized
	 */
	public void setTarget(String newTarget) {
		String oldTarget = target;
		target = newTarget;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getTransferActivity_Target().getName(), newTarget);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.TRANSFER_ACTIVITY__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE:
			if (resolve)
				return getFromSource();
			return basicGetFromSource();
		case ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE:
			if (resolve)
				return getToSource();
			return basicGetToSource();
		case ModelPackage.TRANSFER_ACTIVITY__TARGET:
			return getTarget();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE:
			setFromSource((DataManagementActivity) newValue);
			return;
		case ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE:
			setToSource((DataManagementActivity) newValue);
			return;
		case ModelPackage.TRANSFER_ACTIVITY__TARGET:
			setTarget((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE:
			setFromSource((DataManagementActivity) null);
			return;
		case ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE:
			setToSource((DataManagementActivity) null);
			return;
		case ModelPackage.TRANSFER_ACTIVITY__TARGET:
			setTarget(TARGET_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ModelPackage.TRANSFER_ACTIVITY__FROM_SOURCE:
			return fromSource != null;
		case ModelPackage.TRANSFER_ACTIVITY__TO_SOURCE:
			return toSource != null;
		case ModelPackage.TRANSFER_ACTIVITY__TARGET:
			return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT
					.equals(target);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (target: ");
		result.append(target);
		result.append(')');
		return result.toString();
	}

} // TransferActivityImpl
