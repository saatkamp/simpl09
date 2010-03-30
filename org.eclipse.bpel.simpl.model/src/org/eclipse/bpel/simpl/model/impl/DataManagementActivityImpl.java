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
package org.eclipse.bpel.simpl.model.impl;

import org.eclipse.bpel.model.impl.ExtensionActivityImpl;
import org.eclipse.bpel.model.util.ReconciliationHelper;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Management Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDsAddress <em>Ds Address</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDsType <em>Ds Type</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDsKind <em>Ds Kind</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDsStatement <em>Ds Statement</em>}</li>
 *   <li>{@link org.eclipse.bpel.simpl.model.impl.DataManagementActivityImpl#getDsLanguage <em>Ds Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class DataManagementActivityImpl extends ExtensionActivityImpl implements DataManagementActivity {
	/**
	 * The default value of the '{@link #getDsAddress() <em>Ds Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String DS_ADDRESS_EDEFAULT = "address";

	/**
	 * The cached value of the '{@link #getDsAddress() <em>Ds Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsAddress()
	 * @generated
	 * @ordered
	 */
	protected String dsAddress = DS_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDsType() <em>Ds Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsType()
	 * @generated
	 * @ordered
	 */
	protected static final String DS_TYPE_EDEFAULT = "type";

	/**
	 * The cached value of the '{@link #getDsType() <em>Ds Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsType()
	 * @generated
	 * @ordered
	 */
	protected String dsType = DS_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDsKind() <em>Ds Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsKind()
	 * @generated
	 * @ordered
	 */
	protected static final String DS_KIND_EDEFAULT = "subtype";

	/**
	 * The cached value of the '{@link #getDsKind() <em>Ds Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsKind()
	 * @generated
	 * @ordered
	 */
	protected String dsKind = DS_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getDsStatement() <em>Ds Statement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsStatement()
	 * @generated
	 * @ordered
	 */
	protected static final String DS_STATEMENT_EDEFAULT = "statement";

	/**
	 * The cached value of the '{@link #getDsStatement() <em>Ds Statement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsStatement()
	 * @generated
	 * @ordered
	 */
	protected String dsStatement = DS_STATEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDsLanguage() <em>Ds Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String DS_LANGUAGE_EDEFAULT = "language";

	/**
	 * The cached value of the '{@link #getDsLanguage() <em>Ds Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsLanguage()
	 * @generated
	 * @ordered
	 */
	protected String dsLanguage = DS_LANGUAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataManagementActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.DATA_MANAGEMENT_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the ds statement
	 * @generated
	 */
	public String getDsStatement() {
		return dsStatement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newDsStatement
	 *            the new ds statement
	 * @customized
	 */
	public void setDsStatement(String newDsStatement) {
		String oldDsStatement = dsStatement;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getDataManagementActivity_DsStatement().getName(),
					newDsStatement);
		}
		dsStatement = newDsStatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT, oldDsStatement, dsStatement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the ds language
	 * @generated
	 */
	public String getDsLanguage() {
		return dsLanguage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newDsLanguage
	 *            the new ds language
	 * @customized
	 */
	public void setDsLanguage(String newDsLanguage) {
		String oldDsLanguage = dsLanguage;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getDataManagementActivity_DsLanguage().getName(),
					newDsLanguage);
		}
		dsLanguage = newDsLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE, oldDsLanguage, dsLanguage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the ds kind
	 * @generated
	 */
	public String getDsKind() {
		return dsKind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newDsKind
	 *            the new ds kind
	 * @customized
	 */
	public void setDsKind(String newDsKind) {
		String oldDsKind = dsKind;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getDataManagementActivity_DsKind().getName(),
					newDsKind);
		}
		dsKind = newDsKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_KIND, oldDsKind, dsKind));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the ds type
	 * @generated
	 */
	public String getDsType() {
		return dsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newDsType
	 *            the new ds type
	 * @customized
	 */
	public void setDsType(String newDsType) {
		String oldDsType = dsType;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getDataManagementActivity_DsType().getName(),
					newDsType);
		}
		dsType = newDsType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_TYPE, oldDsType, dsType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the ds address
	 * @generated
	 */
	public String getDsAddress() {
		return dsAddress;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param newDsAddress
	 *            the new ds address
	 * @customized
	 */
	public void setDsAddress(String newDsAddress) {
		String oldDsAddress = dsAddress;
		if (!isReconciling) {
			ReconciliationHelper.replaceAttribute(this, ModelPackage.eINSTANCE
					.getDataManagementActivity_DsAddress().getName(),
					newDsAddress);
		}
		dsAddress = newDsAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS, oldDsAddress, dsAddress));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @param resolve
	 *            the resolve
	 * @param coreType
	 *            the core type
	 * @return the object
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS:
				return getDsAddress();
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_TYPE:
				return getDsType();
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_KIND:
				return getDsKind();
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT:
				return getDsStatement();
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE:
				return getDsLanguage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @param newValue
	 *            the new value
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS:
				setDsAddress((String)newValue);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_TYPE:
				setDsType((String)newValue);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_KIND:
				setDsKind((String)newValue);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT:
				setDsStatement((String)newValue);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE:
				setDsLanguage((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS:
				setDsAddress(DS_ADDRESS_EDEFAULT);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_TYPE:
				setDsType(DS_TYPE_EDEFAULT);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_KIND:
				setDsKind(DS_KIND_EDEFAULT);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT:
				setDsStatement(DS_STATEMENT_EDEFAULT);
				return;
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE:
				setDsLanguage(DS_LANGUAGE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @param featureID
	 *            the feature id
	 * @return true, if successful
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_ADDRESS:
				return DS_ADDRESS_EDEFAULT == null ? dsAddress != null : !DS_ADDRESS_EDEFAULT.equals(dsAddress);
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_TYPE:
				return DS_TYPE_EDEFAULT == null ? dsType != null : !DS_TYPE_EDEFAULT.equals(dsType);
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_KIND:
				return DS_KIND_EDEFAULT == null ? dsKind != null : !DS_KIND_EDEFAULT.equals(dsKind);
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_STATEMENT:
				return DS_STATEMENT_EDEFAULT == null ? dsStatement != null : !DS_STATEMENT_EDEFAULT.equals(dsStatement);
			case ModelPackage.DATA_MANAGEMENT_ACTIVITY__DS_LANGUAGE:
				return DS_LANGUAGE_EDEFAULT == null ? dsLanguage != null : !DS_LANGUAGE_EDEFAULT.equals(dsLanguage);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the string
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dsAddress: ");
		result.append(dsAddress);
		result.append(", dsType: ");
		result.append(dsType);
		result.append(", dsKind: ");
		result.append(dsKind);
		result.append(", dsStatement: ");
		result.append(dsStatement);
		result.append(", dsLanguage: ");
		result.append(dsLanguage);
		result.append(')');
		return result.toString();
	}

} //DataManagementActivityImpl
