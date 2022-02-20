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

import fish.focus.uvms.usm.information.domain.deployment.Dataset;
import fish.focus.uvms.usm.information.domain.deployment.Feature;
import fish.focus.uvms.usm.information.domain.deployment.Option;
import java.util.List;


public class ApplicationConverter {

	/*
	Information-Model
	package eu.europa.ec.mare.usm.information.domain.deployment;
	protected String parent;
    protected List<Dataset> dataset;
    protected List<Option> option;
    protected List<Feature> feature;
    
    user-model
    package eu.europa.ec.fisheries.wsdl.user.types;
    protected String parent;
    protected List<Dataset> dataset;
    protected List<Option> option;
    protected List<Feature> feature;
	
	
	*/

    public static fish.focus.wsdl.user.types.Application
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.deployment.Application deploymentApplication) {
        if (deploymentApplication == null) {
            return null;
        }
        fish.focus.wsdl.user.types.Application typesApplication =
                new fish.focus.wsdl.user.types.Application();
        typesApplication.setParent(deploymentApplication.getParent());
        typesApplication.setDatasetRetain(deploymentApplication.isRetainDatasets());
        typesApplication.setDescription(deploymentApplication.getDescription());
        typesApplication.setName(deploymentApplication.getName());
        List<fish.focus.wsdl.user.types.Dataset> typesDataset = typesApplication.getDataset();
        List<fish.focus.uvms.usm.information.domain.deployment.Dataset> deploymentDataset = deploymentApplication.getDataset();
        for (Dataset deploymentDatasetElement : deploymentDataset) {
            fish.focus.wsdl.user.types.Dataset typesDatasetElement = DatasetConverter.convertDeploymentInformationModelToUserModel(deploymentDatasetElement);
            typesDataset.add(typesDatasetElement);
        }
        List<fish.focus.wsdl.user.types.Option> typesOptions = typesApplication.getOption();
        List<fish.focus.uvms.usm.information.domain.deployment.Option> deploymentOptions = deploymentApplication.getOption();
        for (Option deploymentOptionElement : deploymentOptions) {
            fish.focus.wsdl.user.types.Option typesOptionElement = OptionConverter.convertInformationModelToUserModel(deploymentOptionElement);
            typesOptions.add(typesOptionElement);
        }
        List<fish.focus.wsdl.user.types.Feature> typesFeatures = typesApplication.getFeature();
        List<fish.focus.uvms.usm.information.domain.deployment.Feature> deploymentFeatures = deploymentApplication.getFeature();
        for (Feature deploymentFeatureElement : deploymentFeatures) {
            fish.focus.wsdl.user.types.Feature typesFeatureElement = FeatureConverter.convertDeploymentInformationModelToUserModel(deploymentFeatureElement);
            typesFeatures.add(typesFeatureElement);
        }
        return typesApplication;
    }

    public static fish.focus.uvms.usm.information.domain.deployment.Application
    convertUserModelToInformationModel(fish.focus.wsdl.user.types.Application typesApplication) {
        fish.focus.uvms.usm.information.domain.deployment.Application deploymentApplication =
                new fish.focus.uvms.usm.information.domain.deployment.Application();
        deploymentApplication.setParent(typesApplication.getParent());
        deploymentApplication.setRetainDatasets(typesApplication.isDatasetRetain());
        deploymentApplication.setDescription(typesApplication.getDescription());
        deploymentApplication.setName(typesApplication.getName());
        List<fish.focus.uvms.usm.information.domain.deployment.Dataset> deploymentDataset = deploymentApplication.getDataset();
        List<fish.focus.wsdl.user.types.Dataset> typesDataset = typesApplication.getDataset();
        for (fish.focus.wsdl.user.types.Dataset typesDatasetElement : typesDataset) {
            Dataset deploymentDatasetElement = DatasetConverter.convertUserModelToDeploymentInformationModel(typesDatasetElement);
            deploymentDataset.add(deploymentDatasetElement);
        }
        List<fish.focus.uvms.usm.information.domain.deployment.Option> deploymentOptions = deploymentApplication.getOption();
        List<fish.focus.wsdl.user.types.Option> typesOptions = typesApplication.getOption();
        for (fish.focus.wsdl.user.types.Option typesOptionElement : typesOptions) {
            Option deploymentOptionElement = OptionConverter.convertUserModelToInformationModel(typesOptionElement);
            deploymentOptions.add(deploymentOptionElement);
        }
        List<fish.focus.uvms.usm.information.domain.deployment.Feature> deploymentFeatures = deploymentApplication.getFeature();
        List<fish.focus.wsdl.user.types.Feature> typesFeatures = typesApplication.getFeature();
        for (fish.focus.wsdl.user.types.Feature typesFeatureElement : typesFeatures) {
            Feature deploymentFeatureElement = FeatureConverter.convertUserModelToDeploymentInformationModel(typesFeatureElement);
            deploymentFeatures.add(deploymentFeatureElement);
        }
        return deploymentApplication;
    }
}