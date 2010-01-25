/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.dao.simpl;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import commonj.sdo.DataObject;

/**
 * @author Matthieu Riou <mriou at apache dot org>
 */
@Entity
@Table(name="ODE_CORSET_PROP")
public class CorrSetProperty {

    @Id @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long _id;
    @Basic @Column(name="PROP_KEY")
    private String propertyKey;
    @Basic @Column(name="PROP_VALUE")
    private String propertyValue;

    @ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST}) @Column(name="CORRSET_ID")
    private CorrelationSetDAOImpl _corrSet;
    
    DataObject dataObject;
    
    CorrSetPropertySDO corrSetProperty = new CorrSetPropertySDO();

    public CorrSetProperty() {
    	dataObject = corrSetProperty.getSDO(_id);
    }
    public CorrSetProperty(String propertyKey, String propertyValue) {
    	this();
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
        dataObject.setString("propertyKey", propertyKey);
        dataObject.setString("propertyValue", propertyValue);
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
        dataObject.setString("propertyKey", propertyKey);

    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
        dataObject.setString("propertyValue", propertyValue);

    }

    public CorrelationSetDAOImpl getCorrSet() {
        return _corrSet;
    }

    public void setCorrSet(CorrelationSetDAOImpl corrSet) {
        _corrSet = corrSet;
    }
}
