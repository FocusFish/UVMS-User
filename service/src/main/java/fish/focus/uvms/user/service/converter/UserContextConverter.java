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

import fish.focus.uvms.usm.information.domain.UserContextQuery;
import fish.focus.wsdl.user.types.UserContextId;

public class UserContextConverter {


    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String userName;
     * private String applicationName;
     * private ContextSet contextSet;
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String userName;
     * protected String applicationName;
     * protected ContextSet contextSet;
     */

    public static UserContextQuery convertToContextQuery(UserContextId src) {
        UserContextQuery ret = new UserContextQuery();
        ret.setApplicationName(src.getApplicationName());
        ret.setUserName(src.getUserName());
        return ret;
    }

    public static fish.focus.wsdl.user.types.UserContext convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.UserContext domainUserContext) {
        if (domainUserContext == null) {
            return null;
        }
        fish.focus.wsdl.user.types.UserContext typesUserContext = new fish.focus.wsdl.user.types.UserContext();
        typesUserContext.setApplicationName(domainUserContext.getApplicationName());
        typesUserContext.setUserName(domainUserContext.getUserName());
        if (domainUserContext.getContextSet() != null) {
            typesUserContext.setContextSet(
                    ContextSetConverter.convertInformationModelToUserModel(domainUserContext.getContextSet()));
        }
        return typesUserContext;
    }

    public static fish.focus.uvms.usm.information.domain.UserContext
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.UserContext typesUserContext) {
        fish.focus.uvms.usm.information.domain.UserContext domainUserContext = new fish.focus.uvms.usm.information.domain.UserContext();
        domainUserContext.setApplicationName(typesUserContext.getApplicationName());
        domainUserContext.setUserName(typesUserContext.getUserName());
        if (typesUserContext.getContextSet() != null) {
            domainUserContext.setContextSet(ContextSetConverter.convertUserModelToInformationModel(typesUserContext.getContextSet()));
        }
        return domainUserContext;
    }
}