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


/**
 * Converts Feature from Information-Model project to Feature from user-model project
 */

public class FeatureConverter {

    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String applicationName;
     * private String name;
     * package eu.europa.ec.mare.usm.information.domain.deployment;
     * protected String group;
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String groupName;
     */
    public static fish.focus.wsdl.user.types.Feature
    convertDeploymentInformationModelToUserModel(fish.focus.uvms.usm.information.domain.deployment.Feature deploymentFeature) {
        fish.focus.wsdl.user.types.Feature typesFeature =
                new fish.focus.wsdl.user.types.Feature();
        typesFeature.setGroup(deploymentFeature.getGroup());
        typesFeature.setName(deploymentFeature.getName());
        typesFeature.setDescription(deploymentFeature.getDescription());

        return typesFeature;

    }

    public static fish.focus.wsdl.user.types.Feature
    convertDomainInformationModelToUserModel(fish.focus.uvms.usm.information.domain.Feature domainFeature) {
        fish.focus.wsdl.user.types.Feature typesFeature =
                new fish.focus.wsdl.user.types.Feature();
        typesFeature.setName(domainFeature.getFeatureName());

        return typesFeature;

    }

    public static fish.focus.uvms.usm.information.domain.deployment.Feature
    convertUserModelToDeploymentInformationModel(fish.focus.wsdl.user.types.Feature typesFeature) {
        fish.focus.uvms.usm.information.domain.deployment.Feature deploymentFeature =
                new fish.focus.uvms.usm.information.domain.deployment.Feature();
        deploymentFeature.setGroup(typesFeature.getGroup());
        deploymentFeature.setName(typesFeature.getName());
        deploymentFeature.setDescription(typesFeature.getDescription());

        return deploymentFeature;
    }

    public static fish.focus.uvms.usm.information.domain.Feature
    convertUserModelToDomainInformationModel(fish.focus.wsdl.user.types.Feature typesFeature) {
        fish.focus.uvms.usm.information.domain.Feature domainFeature =
                new fish.focus.uvms.usm.information.domain.Feature();
        domainFeature.setFeatureName(typesFeature.getName());

        return domainFeature;
    }
}