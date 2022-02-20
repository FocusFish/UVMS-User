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
package fish.focus.uvms.user.model.mapper;

import fish.focus.uvms.user.model.exception.ModelMarshallException;
import fish.focus.wsdl.user.module.CreateDatasetRequest;
import fish.focus.wsdl.user.module.CreatePreferenceRequest;
import fish.focus.wsdl.user.module.DeleteDatasetRequest;
import fish.focus.wsdl.user.module.DeletePreferenceRequest;
import fish.focus.wsdl.user.module.DeployApplicationRequest;
import fish.focus.wsdl.user.module.FilterDatasetRequest;
import fish.focus.wsdl.user.module.FindOrganisationsRequest;
import fish.focus.wsdl.user.module.GetAllOrganisationRequest;
import fish.focus.wsdl.user.module.GetContactDetailsRequest;
import fish.focus.wsdl.user.module.GetDeploymentDescriptorRequest;
import fish.focus.wsdl.user.module.GetOrganisationRequest;
import fish.focus.wsdl.user.module.GetUserContextRequest;
import fish.focus.wsdl.user.module.PutPreferenceRequest;
import fish.focus.wsdl.user.module.PutUserPreferencesRequest;
import fish.focus.wsdl.user.module.RedeployApplicationRequest;
import fish.focus.wsdl.user.module.UndeployApplicationRequest;
import fish.focus.wsdl.user.module.UpdateDatasetRequest;
import fish.focus.wsdl.user.module.UpdatePreferenceRequest;
import fish.focus.wsdl.user.module.UserModuleMethod;
import fish.focus.wsdl.user.types.Application;
import fish.focus.wsdl.user.types.DatasetExtension;
import fish.focus.wsdl.user.types.DatasetFilter;
import fish.focus.wsdl.user.types.UserContext;
import fish.focus.wsdl.user.types.UserContextId;
import fish.focus.wsdl.user.types.UserPreference;
import javax.servlet.http.HttpServletRequest;

public class UserModuleRequestMapper {

    public static String mapToGetUserContextRequest(UserContextId userContextId) throws ModelMarshallException {
        GetUserContextRequest getUserContextRequest = new GetUserContextRequest();
        getUserContextRequest.setMethod(UserModuleMethod.GET_USER_CONTEXT);
        getUserContextRequest.setContextId(userContextId);       
        return JAXBMarshaller.marshallJaxBObjectToString(getUserContextRequest);
    }
    
    public static String mapToPutUserPreferencesRequest(UserContext userContext) throws ModelMarshallException {
        PutUserPreferencesRequest putUserPreferencesRequest = new PutUserPreferencesRequest();
        putUserPreferencesRequest.setMethod(UserModuleMethod.PUT_USER_PREFERENCES);
        putUserPreferencesRequest.setContext(userContext);
        return JAXBMarshaller.marshallJaxBObjectToString(putUserPreferencesRequest);
    }    
    
    public static String mapToDeployApplicationRequest(Application application) throws ModelMarshallException {
        DeployApplicationRequest deployApplicationRequest = new DeployApplicationRequest();
        deployApplicationRequest.setMethod(UserModuleMethod.DEPLOY_APPLICATION);
        deployApplicationRequest.setApplication(application);
        return JAXBMarshaller.marshallJaxBObjectToString(deployApplicationRequest);
    }

    public static String mapToRedeployApplicationRequest(Application application) throws ModelMarshallException {
        RedeployApplicationRequest redeployApplicationRequest = new RedeployApplicationRequest();
        redeployApplicationRequest.setMethod(UserModuleMethod.REDEPLOY_APPLICATION);
        redeployApplicationRequest.setApplication(application);
        return JAXBMarshaller.marshallJaxBObjectToString(redeployApplicationRequest);
    }        
    
    public static String mapToUndeployApplicationRequest(String applicationName) throws ModelMarshallException {
        UndeployApplicationRequest undeployApplicationRequest = new UndeployApplicationRequest();
        undeployApplicationRequest.setMethod(UserModuleMethod.UNDEPLOY_APPLICATION);
        undeployApplicationRequest.setApplicationName(applicationName);
        return JAXBMarshaller.marshallJaxBObjectToString(undeployApplicationRequest);
    }

    public static String mapToGetApplicationRequest(String applicationName) throws ModelMarshallException {
        GetDeploymentDescriptorRequest getDeploymentDescriptorRequest = new GetDeploymentDescriptorRequest();
        getDeploymentDescriptorRequest.setMethod(UserModuleMethod.GET_DEPLOYMENT_DESCRIPTOR);
        getDeploymentDescriptorRequest.setApplicationName(applicationName);
        return JAXBMarshaller.marshallJaxBObjectToString(getDeploymentDescriptorRequest);
    }

    public static String mapToGetOrganisationRequest(String organizationName) throws ModelMarshallException {
        GetOrganisationRequest getOrganisationRequest = new GetOrganisationRequest();
        getOrganisationRequest.setMethod(UserModuleMethod.GET_ORGANISATIONS);        
        getOrganisationRequest.setOrganizationName(organizationName);        
        return JAXBMarshaller.marshallJaxBObjectToString(getOrganisationRequest);
    }

    public static String mapToGetAllOrganisationRequest(String scopeName, String roleName, String requester) throws ModelMarshallException {
        GetAllOrganisationRequest getAllOrganisationRequest = new GetAllOrganisationRequest();
        getAllOrganisationRequest.setMethod(UserModuleMethod.GET_ALLORGANISATIONS);
        getAllOrganisationRequest.setRequestorName( requester );
        getAllOrganisationRequest.setRoleName( roleName );
        getAllOrganisationRequest.setScopeName( scopeName );
        return JAXBMarshaller.marshallJaxBObjectToString(getAllOrganisationRequest);
    }

    public static String mapToFindOrganisationsRequest(String nationIsoName) throws ModelMarshallException {
        FindOrganisationsRequest findOrganisationsRequest = new FindOrganisationsRequest();
        findOrganisationsRequest.setMethod(UserModuleMethod.FIND_ORGANISATIONS);        
        findOrganisationsRequest.setNationIsoName(nationIsoName);        
        return JAXBMarshaller.marshallJaxBObjectToString(findOrganisationsRequest);
    }

    public static String mapToGetContactDetailsRequest(String userName) throws ModelMarshallException {
        GetContactDetailsRequest getContactDetailsRequest = new GetContactDetailsRequest();
        getContactDetailsRequest.setMethod(UserModuleMethod.GET_CONTACT_DETAILS);
        getContactDetailsRequest.setUserName(userName);        
        return JAXBMarshaller.marshallJaxBObjectToString(getContactDetailsRequest);
    }    

    public static String mapToPutUserPreferenceRequest(UserPreference userPreference) throws ModelMarshallException {
        PutPreferenceRequest putPreferenceRequest = new PutPreferenceRequest();
        putPreferenceRequest.setMethod(UserModuleMethod.PUT_PREFERENCE);
        putPreferenceRequest.setUserPreference(userPreference);
        return JAXBMarshaller.marshallJaxBObjectToString(putPreferenceRequest);
    }    
    
    public static String mapToCreateUserPreferenceRequest(UserPreference userPreference) throws ModelMarshallException {
        CreatePreferenceRequest createPreferenceRequest = new CreatePreferenceRequest();
        createPreferenceRequest.setMethod(UserModuleMethod.CREATE_PREFERENCE);
        createPreferenceRequest.setUserPreference(userPreference);
        return JAXBMarshaller.marshallJaxBObjectToString(createPreferenceRequest);
    }
    
    public static String mapToUpdateUserPreferenceRequest(UserPreference userPreference) throws ModelMarshallException {
        UpdatePreferenceRequest updatePreferenceRequest = new UpdatePreferenceRequest();
        updatePreferenceRequest.setMethod(UserModuleMethod.UPDATE_PREFERENCE);
        updatePreferenceRequest.setUserPreference(userPreference);
        return JAXBMarshaller.marshallJaxBObjectToString(updatePreferenceRequest);
    }
    
    public static String mapToDeleteUserPreferenceRequest(UserPreference userPreference) throws ModelMarshallException {
        DeletePreferenceRequest deletePreferenceRequest = new DeletePreferenceRequest();
        deletePreferenceRequest.setMethod(UserModuleMethod.DELETE_PREFERENCE);
        deletePreferenceRequest.setUserPreference(userPreference);
        return JAXBMarshaller.marshallJaxBObjectToString(deletePreferenceRequest);
    }
    
    public static String mapToCreateDatasetRequest(DatasetExtension dataset) throws ModelMarshallException {
        CreateDatasetRequest createDatasetRequest = new CreateDatasetRequest();
        createDatasetRequest.setMethod(UserModuleMethod.CREATE_DATASET);
        createDatasetRequest.setDataset(dataset);
        return JAXBMarshaller.marshallJaxBObjectToString(createDatasetRequest);
    }
    
    public static String mapToUpdateDatasetRequest(DatasetExtension dataset) throws ModelMarshallException {
        UpdateDatasetRequest updateDatasetRequest = new UpdateDatasetRequest();
        updateDatasetRequest.setMethod(UserModuleMethod.UPDATE_DATASET);
        updateDatasetRequest.setDataset(dataset);
        return JAXBMarshaller.marshallJaxBObjectToString(updateDatasetRequest);
    }
    
    public static String mapToDeleteDatasetRequest(DatasetExtension dataset) throws ModelMarshallException {
        DeleteDatasetRequest deleteDatasetRequest = new DeleteDatasetRequest();
        deleteDatasetRequest.setMethod(UserModuleMethod.DELETE_DATASET);
        deleteDatasetRequest.setDataset(dataset);
        return JAXBMarshaller.marshallJaxBObjectToString(deleteDatasetRequest);
    }
    
    public static String mapToFindDatasetRequest(DatasetFilter datasetFilter) throws ModelMarshallException {
        FilterDatasetRequest filterDatasetRequest = new FilterDatasetRequest();
        filterDatasetRequest.setMethod(UserModuleMethod.FIND_DATASETS);
        filterDatasetRequest.setDatasetFilter(datasetFilter);
        return JAXBMarshaller.marshallJaxBObjectToString(filterDatasetRequest);
    }
    
}