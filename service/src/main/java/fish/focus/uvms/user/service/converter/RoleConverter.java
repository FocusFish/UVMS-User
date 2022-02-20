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

import fish.focus.uvms.usm.information.domain.Feature;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RoleConverter {


    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String roleName;
     * private Set<Feature> features;
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String roleName;
     * protected List<Feature> feature;
     */
    public static fish.focus.wsdl.user.types.Role
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.Role domainRole) {
        fish.focus.wsdl.user.types.Role typesRole = new fish.focus.wsdl.user.types.Role();
        typesRole.setRoleName(domainRole.getRoleName());
		/*List<eu.europa.ec.fisheries.wsdl.user.types.Feature> typesFeatures =
				new ArrayList<eu.europa.ec.fisheries.wsdl.user.types.Feature>();*/
        Set<fish.focus.uvms.usm.information.domain.Feature> domainFeatures = domainRole.getFeatures();
        for (Feature domainFeatureElement : domainFeatures) {
            fish.focus.wsdl.user.types.Feature typesFeatureElement = FeatureConverter.convertDomainInformationModelToUserModel(domainFeatureElement);
            /*typesFeatures.add(typesFeatureElement);*/
            typesRole.getFeature().add(typesFeatureElement);
        }
        return typesRole;
    }

    public static fish.focus.uvms.usm.information.domain.Role
    convertUserModelToInformationService(fish.focus.wsdl.user.types.Role typesRole) {
        fish.focus.uvms.usm.information.domain.Role domainRole = new fish.focus.uvms.usm.information.domain.Role();
        domainRole.setRoleName(typesRole.getRoleName());
        Set<fish.focus.uvms.usm.information.domain.Feature> domainFeatures = new HashSet<fish.focus.uvms.usm.information.domain.Feature>();
        domainRole.setFeatures(domainFeatures);
        List<fish.focus.wsdl.user.types.Feature> typesFeatures = typesRole.getFeature();
        for (fish.focus.wsdl.user.types.Feature typesFeatureElement : typesFeatures) {
            Feature domainFeatureElement = FeatureConverter.convertUserModelToDomainInformationModel(typesFeatureElement);
            domainFeatures.add(domainFeatureElement);
        }
        return domainRole;
    }
}