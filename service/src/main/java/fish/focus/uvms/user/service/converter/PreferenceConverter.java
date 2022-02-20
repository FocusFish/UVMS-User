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


public class PreferenceConverter {

    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String applicationName;
     * private String optionName;
     * private String optionValue;
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String applicationName;
     * protected String optionName;
     * protected String optionValue;
     */
    public static fish.focus.wsdl.user.types.Preference
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.Preference domainPreference) {
        fish.focus.wsdl.user.types.Preference typesPreference = new fish.focus.wsdl.user.types.Preference();
        typesPreference.setApplicationName(domainPreference.getApplicationName());
        typesPreference.setOptionName(domainPreference.getOptionName());
        typesPreference.setOptionValue(domainPreference.getOptionValue());
        return typesPreference;
    }

    public static fish.focus.uvms.usm.information.domain.Preference convertUserModelToInformationModel(fish.focus.wsdl.user.types.Preference typesPreference) {
        fish.focus.uvms.usm.information.domain.Preference domainPreference = new fish.focus.uvms.usm.information.domain.Preference();
        domainPreference.setApplicationName(typesPreference.getApplicationName());
        domainPreference.setOptionName(typesPreference.getOptionName());
        domainPreference.setOptionValue(typesPreference.getOptionValue());
        return domainPreference;
    }

}