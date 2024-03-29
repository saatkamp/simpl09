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
package org.eclipse.bpel.ui.adapters;

import org.eclipse.bpel.model.EndpointReferenceRole;
import org.eclipse.bpel.model.Expression;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Query;
import org.eclipse.bpel.model.To;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.adapters.AbstractStatefulAdapter;
import org.eclipse.bpel.model.messageproperties.Property;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.wsdl.Part;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jun 11, 2007
 *
 */
public class ToAdapter extends AbstractStatefulAdapter implements IVirtualCopyRuleSide {

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getEndpointReference()
	 */
	public EndpointReferenceRole getEndpointReference() {
		return null;				
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getPart()
	 */
	public Part getPart() {
		To _to = getTarget(null, To.class);
		return _to.getPart();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getPartnerLink()
	 */
	public PartnerLink getPartnerLink() {
		To _to = getTarget(null, To.class);
		return _to.getPartnerLink();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getProperty()
	 */
	public Property getProperty() {
		To _to = getTarget(null, To.class);
		return _to.getProperty();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getQuery()
	 */
	public Query getQuery() {
		To _to = getTarget(null, To.class);
		return _to.getQuery();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getVariable()
	 */
	public Variable getVariable() {
		To _to = getTarget(null, To.class);
		return _to.getVariable();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setEndpointReference(org.eclipse.bpel.model.EndpointReferenceRole)
	 */
	public void setEndpointReference(EndpointReferenceRole role) {			
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setPart(org.eclipse.wst.wsdl.Part)
	 */
	public void setPart(Part part) {
		To _to = getTarget(null, To.class);
		_to.setPart(part);				
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setPartnerLink(org.eclipse.bpel.model.PartnerLink)
	 */
	public void setPartnerLink(PartnerLink pl) {
		To _to = getTarget(null, To.class);
		_to.setPartnerLink(pl);		
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setProperty(org.eclipse.bpel.model.messageproperties.Property)
	 */
	public void setProperty(Property property) {
		To _to = getTarget(null, To.class);
		_to.setProperty(property);				
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setQuery(org.eclipse.bpel.model.Query)
	 */
	public void setQuery(Query query) {
		To _to = getTarget(null, To.class);
		_to.setQuery(query);				
		
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setVariable(org.eclipse.bpel.model.Variable)
	 */
	public void setVariable(Variable aModel) {
		To _to = getTarget(null, To.class);
		_to.setVariable(aModel);				
	}
	
	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getExpression()
	 */
	
	public Expression getExpression () {
		To _to = getTarget(null,To.class);
		return _to.getExpression();
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#setExpression(org.eclipse.bpel.model.Expression)
	 */
	public void setExpression (Expression expression) {
		To _to = getTarget(null,To.class);
		_to.setExpression(expression);
	}

	
	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#getCopyRuleSide()
	 */
	public EObject getCopyRuleSide() {
		return getTarget(null,To.class);
	}

	/**
	 * @see org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide#isSource()
	 */
	public boolean isSource() {
		return false;
	}

}
