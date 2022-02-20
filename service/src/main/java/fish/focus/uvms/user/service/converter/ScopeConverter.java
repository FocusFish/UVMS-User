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
package fish.focus.uvms.user.service.converter;

import fish.focus.uvms.usm.information.domain.DataSet;
import fish.focus.wsdl.user.types.Dataset;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ScopeConverter {

    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String scopeName;
     * private Date activeFrom;
     * private Date activeTo;
     * private Date dataFrom;
     * private Date dataTo;
     * private Set<DataSet> datasets;
     * <p>
     * <p>
     * user-model
     * eu.europa.ec.fisheries.wsdl.user.types;
     * protected String scopeName;
     * protected XMLGregorianCalendar activeFrom;
     * protected XMLGregorianCalendar activeTo;
     * protected XMLGregorianCalendar dataFrom;
     * protected XMLGregorianCalendar dataTo;
     * protected List<Dataset> dataset;
     */
    public static fish.focus.wsdl.user.types.Scope convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.Scope domainScope) {
        fish.focus.wsdl.user.types.Scope typesScope = new fish.focus.wsdl.user.types.Scope();
        typesScope.setScopeName(domainScope.getScopeName());
        typesScope.setActiveFrom(convertDateToXMLGregorianCalendar(domainScope.getActiveFrom()));
        typesScope.setActiveTo(convertDateToXMLGregorianCalendar(domainScope.getActiveTo()));
        typesScope.setDataFrom(convertDateToXMLGregorianCalendar(domainScope.getDataFrom()));
        typesScope.setDataTo(convertDateToXMLGregorianCalendar(domainScope.getDataTo()));
		/*Set<eu.europa.ec.fisheries.wsdl.user.types.Dataset> typesDatasets =
				new HashSet<eu.europa.ec.fisheries.wsdl.user.types.Dataset>();*/
        Set<fish.focus.uvms.usm.information.domain.DataSet> domainDatasets = domainScope.getDatasets();
        if (domainDatasets != null) {
            for (DataSet domainDatasetElement : domainDatasets) {
                Dataset typesDatasetElement = DatasetConverter.convertDomainInformationModelToUserModel(domainDatasetElement);
                /*typesDatasets.add(typesDatasetElement);*/
                typesScope.getDataset().add(typesDatasetElement);
            }
        }
        return typesScope;
    }

    public static fish.focus.uvms.usm.information.domain.Scope
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.Scope typesScope) {
        fish.focus.uvms.usm.information.domain.Scope domainScope = new fish.focus.uvms.usm.information.domain.Scope();
        domainScope.setScopeName(typesScope.getScopeName());
        domainScope.setActiveFrom(convertXMLGregorianCalendarToDate(typesScope.getActiveFrom()));
        domainScope.setActiveTo(convertXMLGregorianCalendarToDate(typesScope.getActiveTo()));
        domainScope.setDataFrom(convertXMLGregorianCalendarToDate(typesScope.getDataFrom()));
        domainScope.setDataTo(convertXMLGregorianCalendarToDate(typesScope.getDataTo()));
        Set<fish.focus.uvms.usm.information.domain.DataSet> domainDatasets = new HashSet<fish.focus.uvms.usm.information.domain.DataSet>();
        domainScope.setDatasets(domainDatasets);
        List<fish.focus.wsdl.user.types.Dataset> typesDatasets = typesScope.getDataset();
        for (Dataset typesDatasetElement : typesDatasets) {
            DataSet domainDatasetElement = DatasetConverter.convertUserModelToDomainInformationModel(typesDatasetElement);
            domainDatasets.add(domainDatasetElement);
        }
        return domainScope;
    }

    private static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date d) {
        if (d == null) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(d);
        XMLGregorianCalendar xmlDate;
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            xmlDate = null;
        }
        return xmlDate;
    }

    private static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar x) {
        if (x == null)
            return null;
        return x.toGregorianCalendar().getTime();
    }


}