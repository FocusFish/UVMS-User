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

public class OptionConverter {

    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain.deployment;
     * protected String dataType;
     * protected String defaultValue;
     * protected String group;
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String dataType;
     * protected String defaultValue;
     * protected String group;
     */

    public static fish.focus.wsdl.user.types.Option
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.deployment.Option deploymentOption) {
        fish.focus.wsdl.user.types.Option typesOption = new fish.focus.wsdl.user.types.Option();
        typesOption.setDataType(deploymentOption.getDataType());
        typesOption.setDefaultValue(deploymentOption.getDefaultValue());
        typesOption.setGroup(deploymentOption.getGroup());
        typesOption.setDescription(deploymentOption.getDescription());
        typesOption.setName(deploymentOption.getName());
        return typesOption;
    }

    public static fish.focus.uvms.usm.information.domain.deployment.Option
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.Option typesOption) {
        fish.focus.uvms.usm.information.domain.deployment.Option deploymentOption = new fish.focus.uvms.usm.information.domain.deployment.Option();
        deploymentOption.setDataType(typesOption.getDataType());
        deploymentOption.setDefaultValue(typesOption.getDefaultValue());
        deploymentOption.setGroup(typesOption.getGroup());
        deploymentOption.setDescription(typesOption.getDescription());
        deploymentOption.setName(typesOption.getName());
        return deploymentOption;
    }
}