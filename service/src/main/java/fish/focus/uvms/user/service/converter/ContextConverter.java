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


public class ContextConverter {
	/*
    Information-Model
    package eu.europa.ec.mare.usm.information.domain;
	  private Role role;
	  private Scope scope;
 
 
    user-model   
	package eu.europa.ec.fisheries.wsdl.user.types;
      protected Role role;
      protected Scope scope;
      protected Preferences preferences;
 
 
	  
	 */

    public static fish.focus.wsdl.user.types.Context
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.Context domainContext) {
        fish.focus.wsdl.user.types.Context typesContext = new fish.focus.wsdl.user.types.Context();
        if (domainContext.getRole() != null) {
            typesContext.setRole(RoleConverter.convertInformationModelToUserModel(domainContext.getRole()));
        }
        if (domainContext.getScope() != null) {
            typesContext.setScope(ScopeConverter.convertInformationModelToUserModel(domainContext.getScope()));
        }
        if (domainContext.getPreferences() != null) {
            typesContext.setPreferences(PreferencesConverter.convertInformationModelToUserModel(domainContext.getPreferences()));
        }
        return typesContext;
    }

    public static fish.focus.uvms.usm.information.domain.Context
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.Context typesContext) {
        fish.focus.uvms.usm.information.domain.Context domainContext = new fish.focus.uvms.usm.information.domain.Context();
        if (typesContext.getRole() != null) {
            domainContext.setRole(RoleConverter.convertUserModelToInformationService(typesContext.getRole()));
        }
        if (typesContext.getScope() != null) {
            domainContext.setScope(ScopeConverter.convertUserModelToInformationModel(typesContext.getScope()));
        }
        if (typesContext.getPreferences() != null) {
            domainContext.setPreferences(PreferencesConverter.convertUserModelToInformationModel(typesContext.getPreferences()));
        }
        return domainContext;
    }
}