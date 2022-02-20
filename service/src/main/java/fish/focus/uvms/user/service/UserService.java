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
package fish.focus.uvms.user.service;

import fish.focus.uvms.user.service.exception.UserServiceException;
import fish.focus.wsdl.user.module.GetAllOrganisationRequest;
import fish.focus.wsdl.user.types.*;
import javax.ejb.Local;
import java.util.List;

@Local
public interface UserService {

    UserContext getUserContext(UserContextId request) throws UserServiceException;

    void updateUserPreferences(UserContext request) throws UserServiceException;

    void deployApplication(Application application) throws UserServiceException;

    void redeployApplication(Application application) throws UserServiceException;

    void undeployApplication(String request) throws UserServiceException;

    Application getDeploymentDescriptor(String applicationName) throws UserServiceException;

    Organisation getOrganisation(String organisationName) throws UserServiceException;

    List<Organisation> getAllOrganisations(GetAllOrganisationRequest request) throws UserServiceException;

    List<Organisation> findOrganisations(String nationIsoName) throws UserServiceException;

    ContactDetails getContactDetails(String userName) throws UserServiceException;

    void putPreference(UserPreference userPreference) throws UserServiceException;

    void createPreference(UserPreference userPreference) throws UserServiceException;

    void deletePreference(UserPreference userPreference) throws UserServiceException;

    void updatePreference(UserPreference userPreference) throws UserServiceException;

    void createDataset(DatasetExtension dataset) throws UserServiceException;

    void deleteDataset(DatasetExtension dataset) throws UserServiceException;

    void updateDataset(DatasetExtension dataset) throws UserServiceException;

    DatasetList findDataset(DatasetFilter datasetFilter) throws UserServiceException;

}
