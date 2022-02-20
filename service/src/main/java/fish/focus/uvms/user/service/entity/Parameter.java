/*
 * Developed by the European Commission - Directorate General for Maritime 
 * Affairs and Fisheries © European Union, 2015-2016.
 * 
 * This file is part of the Integrated Fisheries Data Management (IFDM) Suite.
 * The IFDM Suite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * The IFDM Suite is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public 
 * License along with the IFDM Suite. If not, see http://www.gnu.org/licenses/.
 */
package fish.focus.uvms.user.service.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the parameter database table.
 *
 */
@Entity
@NamedQuery(name = Parameter.FIND_BY_NAME, query = "SELECT p FROM Parameter p WHERE p.paramName = :key")
public class Parameter implements Serializable {

    public static final String FIND_BY_NAME = "Parameter.findByName";

    private static final long serialVersionUID = 1L;

    public Parameter() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private Integer paramId;

    @Column(name = "param_description")
    private String paramDescription;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_value")
    private String paramValue;

    public Integer getParamId() {
        return this.paramId;
    }
    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }
    public String getParamDescription() {
        return this.paramDescription;
    }
    public void setParamDescription(String paramDescription) {
        this.paramDescription = paramDescription;
    }
    public String getParamName() {
        return this.paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamValue() {
        return this.paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

}