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

import fish.focus.uvms.usm.information.domain.Context;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContextSetConverter {
	/*
	Information-Model
	package eu.europa.ec.mare.usm.information.domain;
	private Set<Context> contexts;
	
	user-model
	package eu.europa.ec.fisheries.wsdl.user.types;
	protected List<Context> context;
	
	
	*/


    public static fish.focus.wsdl.user.types.ContextSet
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.ContextSet domainContextSet) {
        fish.focus.wsdl.user.types.ContextSet typesContextSet = new fish.focus.wsdl.user.types.ContextSet();
        for (Context domainContextElement : domainContextSet.getContexts()) {
            fish.focus.wsdl.user.types.Context typesContextElement = ContextConverter.convertInformationModelToUserModel(domainContextElement);
            typesContextSet.getContexts().add(typesContextElement);
        }
        return typesContextSet;
    }

    public static fish.focus.uvms.usm.information.domain.ContextSet
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.ContextSet typesContextSet) {
        fish.focus.uvms.usm.information.domain.ContextSet domainContextSet = new fish.focus.uvms.usm.information.domain.ContextSet();
        Set<fish.focus.uvms.usm.information.domain.Context> domainContexts = new HashSet<fish.focus.uvms.usm.information.domain.Context>();
        domainContextSet.setContexts(domainContexts);
        List<fish.focus.wsdl.user.types.Context> typesContexts = typesContextSet.getContexts();
        for (fish.focus.wsdl.user.types.Context typesContextElement : typesContexts) {
            Context domainContextElement = ContextConverter.convertUserModelToInformationModel(typesContextElement);
            domainContexts.add(domainContextElement);
        }
        return domainContextSet;
    }

}