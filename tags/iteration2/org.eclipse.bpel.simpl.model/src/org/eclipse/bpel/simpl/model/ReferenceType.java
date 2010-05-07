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
package org.eclipse.bpel.simpl.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

// TODO: Auto-generated Javadoc
/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Reference Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpel.simpl.model.ModelPackage#getReferenceType()
 * @model
 * @generated
 */
public enum ReferenceType implements Enumerator {
	/**
	 * The '<em><b>On Instantiation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_INSTANTIATION_VALUE
	 * @generated
	 * @ordered
	 */
	ON_INSTANTIATION(0, "onInstantiation", "onInstantiation"),

	/**
	 * The '<em><b>Fresh</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FRESH_VALUE
	 * @generated
	 * @ordered
	 */
	FRESH(1, "fresh", "fresh");

	/**
	 * The '<em><b>On Instantiation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>On Instantiation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ON_INSTANTIATION
	 * @model name="onInstantiation"
	 * @generated
	 * @ordered
	 */
	public static final int ON_INSTANTIATION_VALUE = 0;

	/**
	 * The '<em><b>Fresh</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fresh</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FRESH
	 * @model name="fresh"
	 * @generated
	 * @ordered
	 */
	public static final int FRESH_VALUE = 1;

	/**
	 * An array of all the '<em><b>Reference Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReferenceType[] VALUES_ARRAY =
		new ReferenceType[] {
			ON_INSTANTIATION,
			FRESH,
		};

	/**
	 * A public read-only list of all the '<em><b>Reference Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReferenceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified
	 * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param literal
	 *            the literal
	 * @return the reference type
	 * @generated
	 */
	public static ReferenceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified
	 * name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param name
	 *            the name
	 * @return the by name
	 * @generated
	 */
	public static ReferenceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferenceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reference Type</b></em>' literal with the specified
	 * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the value
	 * @return the reference type
	 * @generated
	 */
	public static ReferenceType get(int value) {
		switch (value) {
			case ON_INSTANTIATION_VALUE: return ON_INSTANTIATION;
			case FRESH_VALUE: return FRESH;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReferenceType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the value
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the name
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->.
	 * 
	 * @return the literal
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string
	 * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the string
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ReferenceType
