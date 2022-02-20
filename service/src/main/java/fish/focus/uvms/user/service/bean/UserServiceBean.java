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
package fish.focus.uvms.user.service.bean;

import fish.focus.uvms.usm.administration.service.organisation.OrganisationService;
import fish.focus.uvms.usm.information.domain.UserContextQuery;
import fish.focus.uvms.usm.information.service.DeploymentService;
import fish.focus.uvms.usm.information.service.InformationService;
import fish.focus.uvms.user.service.UserService;
import fish.focus.uvms.user.service.converter.*;
import fish.focus.wsdl.user.module.GetAllOrganisationRequest;
import fish.focus.wsdl.user.types.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserServiceBean implements UserService {

    @EJB
    private InformationService informationService;

    @EJB
    private OrganisationService organisationService;

    @EJB
    private DeploymentService deploymentService;

    public UserContext getUserContext(UserContextId userContextId) {
        UserContextQuery userContextQuery = UserContextConverter.convertToContextQuery(userContextId);
        return UserContextConverter.convertInformationModelToUserModel(informationService.getUserContext(userContextQuery));
    }

    @Override
    public void updateUserPreferences(UserContext request) {
        informationService.updateUserPreferences(UserContextConverter.convertUserModelToInformationModel(request));
    }

    @Override
    public void deployApplication(Application application) {
        deploymentService.deployApplication(ApplicationConverter.convertUserModelToInformationModel(application));
    }

    @Override
    public void redeployApplication(Application application) {
        deploymentService.redeployApplication(ApplicationConverter.convertUserModelToInformationModel(application));
    }

    @Override
    public void undeployApplication(String request) {
        deploymentService.undeployApplication(request);
    }

    @Override
    public Application getDeploymentDescriptor(String applicationName) {
        return ApplicationConverter.convertInformationModelToUserModel(deploymentService.getDeploymentDescriptor(applicationName));
    }

    @Override
    public Organisation getOrganisation(String organisationName) {
        return OrganisationConverter.convertInformationModelToUserModel(informationService.getOrganisation(organisationName));
    }

    @Override
    public List<Organisation> getAllOrganisations(GetAllOrganisationRequest request) {
        List<fish.focus.uvms.usm.administration.domain.Organisation> organisationList = new ArrayList<>();
        List<fish.focus.uvms.usm.administration.domain.Organisation> allOrganisationList =
                organisationService.findOrganisations(USMServiceBuilder.buildAdministrationServiceRequestForAll(request)).getResults();
        for (fish.focus.uvms.usm.administration.domain.Organisation organisation : allOrganisationList) {
            organisationList.add(organisationService.getOrganisation(
                    USMServiceBuilder.buildAdministrationServiceRequestForId(organisation.getOrganisationId(), request)));
        }
        return OrganisationConverter.convertAdministrationModelToUserModel(organisationList);
    }

    @Override
    public List<Organisation> findOrganisations(String nationIsoName) {
        return OrganisationConverter.convertInformationModelToUserModel(informationService.findOrganisations(nationIsoName));
    }

    @Override
    public ContactDetails getContactDetails(String userName) {
        return ContactDetailsConverter.convertInformationModelToUserModel(informationService.getContactDetails(userName));
    }

    @Override
    public void putPreference(UserPreference userPreference) {
        fish.focus.uvms.usm.information.domain.UserPreference pref = UserPreferenceConverter.convertUserModelToInformationModel(userPreference);
        fish.focus.uvms.usm.information.domain.UserPreference prefRetrieved = informationService.getUserPreference(pref);
        if (prefRetrieved.getOptionName() == null) {
            informationService.createUserPreference(pref);
        } else {
            informationService.updateUserPreference(pref);
        }
    }

    @Override
    public void createPreference(UserPreference userPreference) {
        informationService.createUserPreference(UserPreferenceConverter.convertUserModelToInformationModel(userPreference));
    }

    @Override
    public void deletePreference(UserPreference userPreference) {
        informationService.deleteUserPreference(UserPreferenceConverter.convertUserModelToInformationModel(userPreference));
    }

    @Override
    public void updatePreference(UserPreference userPreference) {
        informationService.updateUserPreference(UserPreferenceConverter.convertUserModelToInformationModel(userPreference));
    }

    @Override
    public void createDataset(DatasetExtension dataset) {
        informationService.createDataSet(DatasetConverter.convertUserModelToDomainInformationModel(dataset));
    }

    @Override
    public void deleteDataset(DatasetExtension dataset) {
        informationService.deleteDataSet(DatasetConverter.convertUserModelToDomainInformationModel(dataset));
    }

    @Override
    public void updateDataset(DatasetExtension dataset) {
        informationService.updateDataSet(DatasetConverter.convertUserModelToDomainInformationModel(dataset));
    }

    @Override
    public DatasetList findDataset(DatasetFilter datasetFilter) {
        return DatasetConverter.convertInformationModelToUserModel(
                informationService.getDataSets(DatasetConverter.convertUserModelToDomainInformationModel(datasetFilter)));
    }
}
