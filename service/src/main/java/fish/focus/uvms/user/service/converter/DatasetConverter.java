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

import java.util.List;

public class DatasetConverter {

    /**
     * Information-Model
     * package eu.europa.ec.mare.usm.information.domain;
     * private String applicationName;
     * private String name;
     * private String category;
     * private String discriminator;
     * <p>
     * package eu.europa.ec.mare.usm.information.domain.deployment;
     * protected String category;
     * protected String discriminator;
     * <p>
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * <p>
     * protected String category;
     * protected String discriminator;
     */

    public static fish.focus.wsdl.user.types.Dataset
    convertDomainInformationModelToUserModel(fish.focus.uvms.usm.information.domain.DataSet domainDataset) {
        fish.focus.wsdl.user.types.Dataset typesDataset = new fish.focus.wsdl.user.types.Dataset();
        typesDataset.setCategory(domainDataset.getCategory());
        typesDataset.setDiscriminator(domainDataset.getDiscriminator());
        typesDataset.setName(domainDataset.getName());
        return typesDataset;
    }

    public static fish.focus.wsdl.user.types.Dataset
    convertDeploymentInformationModelToUserModel(fish.focus.uvms.usm.information.domain.deployment.Dataset deploymentDataset) {
        fish.focus.wsdl.user.types.Dataset typesDataset = new fish.focus.wsdl.user.types.Dataset();
        typesDataset.setCategory(deploymentDataset.getCategory());
        typesDataset.setDiscriminator(deploymentDataset.getDiscriminator());
        typesDataset.setName(deploymentDataset.getName());
        typesDataset.setDescription(deploymentDataset.getDescription());
        return typesDataset;
    }

    public static fish.focus.uvms.usm.information.domain.DataSet
    convertUserModelToDomainInformationModel(fish.focus.wsdl.user.types.Dataset typesDataset) {
        fish.focus.uvms.usm.information.domain.DataSet domainDataset = new fish.focus.uvms.usm.information.domain.DataSet();
        domainDataset.setCategory(typesDataset.getCategory());
        domainDataset.setDiscriminator(typesDataset.getDiscriminator());
        domainDataset.setName(typesDataset.getName());
        return domainDataset;
    }

    public static fish.focus.uvms.usm.information.domain.deployment.Dataset
    convertUserModelToDeploymentInformationModel(fish.focus.wsdl.user.types.Dataset typesDataset) {
        fish.focus.uvms.usm.information.domain.deployment.Dataset deploymentDataset = new fish.focus.uvms.usm.information.domain.deployment.Dataset();
        deploymentDataset.setCategory(typesDataset.getCategory());
        deploymentDataset.setDiscriminator(typesDataset.getDiscriminator());
        deploymentDataset.setName(typesDataset.getName());
        deploymentDataset.setDescription(typesDataset.getDescription());
        return deploymentDataset;
    }

    public static fish.focus.uvms.usm.information.domain.DataSet
    convertUserModelToDomainInformationModel(fish.focus.wsdl.user.types.DatasetExtension typesDatasetExtension) {
        fish.focus.uvms.usm.information.domain.DataSet domainDataset = new fish.focus.uvms.usm.information.domain.DataSet();
        domainDataset.setCategory(typesDatasetExtension.getCategory());
        domainDataset.setDiscriminator(typesDatasetExtension.getDiscriminator());
        domainDataset.setName(typesDatasetExtension.getName());
        domainDataset.setApplicationName(typesDatasetExtension.getApplicationName());
        domainDataset.setDescription(typesDatasetExtension.getDescription());
        return domainDataset;
    }

    public static fish.focus.uvms.usm.information.domain.DataSetFilter
    convertUserModelToDomainInformationModel(fish.focus.wsdl.user.types.DatasetFilter typesDatasetFilter) {
        fish.focus.uvms.usm.information.domain.DataSetFilter domainDatasetFilter = new fish.focus.uvms.usm.information.domain.DataSetFilter();
        domainDatasetFilter.setApplicationName(typesDatasetFilter.getApplicationName());
        domainDatasetFilter.setName(typesDatasetFilter.getName());
        domainDatasetFilter.setCategory(typesDatasetFilter.getCategory());
        domainDatasetFilter.setDiscriminator(typesDatasetFilter.getDiscriminator());
        domainDatasetFilter.setName(typesDatasetFilter.getName());
        return domainDatasetFilter;
    }

    public static fish.focus.wsdl.user.types.DatasetList
    convertInformationModelToUserModel(List<fish.focus.uvms.usm.information.domain.DataSet> domainDatasetList) {
        fish.focus.wsdl.user.types.DatasetList userDatasetList = new fish.focus.wsdl.user.types.DatasetList();
        if (!domainDatasetList.isEmpty()) {
            for (fish.focus.uvms.usm.information.domain.DataSet element : domainDatasetList) {
                userDatasetList.getList().add(convertInformationModelToUserModel(element));
            }
        }
        return userDatasetList;
    }

    public static fish.focus.wsdl.user.types.DatasetExtension
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.DataSet datasetModel) {
        fish.focus.wsdl.user.types.DatasetExtension datasetUserType = new fish.focus.wsdl.user.types.DatasetExtension();
        datasetUserType.setApplicationName(datasetModel.getApplicationName());
        datasetUserType.setName(datasetModel.getName());
        datasetUserType.setDiscriminator(datasetModel.getDiscriminator());
        datasetUserType.setCategory(datasetModel.getDiscriminator());
        datasetUserType.setDescription(datasetModel.getDescription());
        return datasetUserType;
    }
}