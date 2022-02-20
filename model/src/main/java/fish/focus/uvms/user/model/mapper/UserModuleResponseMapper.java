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

import java.math.BigInteger;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import fish.focus.uvms.user.model.exception.ModelMapperException;
import fish.focus.uvms.user.model.exception.ModelMarshallException;
import fish.focus.wsdl.user.module.*;
import fish.focus.wsdl.user.types.Application;
import fish.focus.wsdl.user.types.ContactDetails;
import fish.focus.wsdl.user.types.DatasetList;
import fish.focus.wsdl.user.types.Organisation;
import fish.focus.wsdl.user.types.UserContext;
import fish.focus.wsdl.user.types.UserFault;

public class UserModuleResponseMapper {

    private static void validateResponse(TextMessage response, String correlationId) throws ModelMapperException, JMSException {

        if (response == null) {
            throw new ModelMapperException("Error when validating response in ResponseMapper: Response is Null");
        }

        if (response.getJMSCorrelationID() == null) {
            throw new ModelMapperException("No corelationId in response (Null) . Expected was: " + correlationId);
        }

        if (!correlationId.equalsIgnoreCase(response.getJMSCorrelationID())) {
            throw new ModelMapperException("Wrong corelationId in response. Expected was: " + correlationId + "But actual was: " + response.getJMSCorrelationID());
        }

    }

    public static String mapToGetUserContextResponse(UserContext userContext) throws ModelMarshallException {
        GetUserContextResponse getUserContextResponse = new GetUserContextResponse();
        getUserContextResponse.setContext(userContext);        
        return JAXBMarshaller.marshallJaxBObjectToString(getUserContextResponse);
    }
    
    public static String mapToPutUserPreferencesResponse() throws ModelMarshallException {
        PutUserPreferencesResponse putUserPreferencesResponse = new PutUserPreferencesResponse();
        putUserPreferencesResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(putUserPreferencesResponse);
    }
    
    public static String mapToDeployApplicationResponse() throws ModelMarshallException {
        DeployApplicationResponse deployApplicationResponse = new DeployApplicationResponse();
        deployApplicationResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(deployApplicationResponse);
    }
    
    public static String mapToUserFault(IllegalArgumentException e, UserModuleMethod userModuleMethod) throws ModelMarshallException {
    	UserFault fault = new UserFault();
    	fault.setCode(BigInteger.valueOf(-1));
    	fault.setFault("Operation " + userModuleMethod.name() + " failed due to : " + e.getMessage());
    	return JAXBMarshaller.marshallJaxBObjectToString(fault);
    }

    public static String mapToRedeployApplicationResponse() throws ModelMarshallException {
        RedeployApplicationResponse redeployApplicationResponse = new RedeployApplicationResponse();
        redeployApplicationResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(redeployApplicationResponse);
    }        
    
    public static String mapToUndeployApplicationResponse() throws ModelMarshallException {
        UndeployApplicationResponse undeployApplicationResponse = new UndeployApplicationResponse();
        undeployApplicationResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(undeployApplicationResponse);
    }

    public static String mapToGetApplicationResponse(Application application) throws ModelMarshallException {
        GetDeploymentDescriptorResponse getDeploymentDescriptorResponse = new GetDeploymentDescriptorResponse();
        getDeploymentDescriptorResponse.setApplication(application);
        return JAXBMarshaller.marshallJaxBObjectToString(getDeploymentDescriptorResponse);
    }

    public static String mapToGetOrganisationResponse(Organisation organization) throws ModelMarshallException {
        GetOrganisationResponse getOrganisationResponse = new GetOrganisationResponse();
        getOrganisationResponse.setOrganisation(organization);
        
        return JAXBMarshaller.marshallJaxBObjectToString(getOrganisationResponse);
    }

    public static String mapToFindOrganisationsResponse(List<Organisation> organizationList) throws ModelMarshallException {
        FindOrganisationsResponse findOrganisationsResponse = new FindOrganisationsResponse();
        if (organizationList != null && !organizationList.isEmpty()) {
        	List<Organisation> orgs = findOrganisationsResponse.getOrganisation();
        	for (Organisation org: organizationList) {
        		orgs.add(org);        		
        	}
        }
        return JAXBMarshaller.marshallJaxBObjectToString(findOrganisationsResponse);
    }

    public static String mapToGetContactDetailsResponse(ContactDetails contactDetails) throws ModelMarshallException {
        GetContactDetailResponse getContactDetailResponse = new GetContactDetailResponse();
        getContactDetailResponse.setContactDetails(contactDetails);
        
        return JAXBMarshaller.marshallJaxBObjectToString(getContactDetailResponse);
    }

    public static String mapToPutPreferenceResponse() throws ModelMarshallException {
        PutPreferenceResponse putPreferenceResponse = new PutPreferenceResponse();
        putPreferenceResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(putPreferenceResponse);
    }
    
    public static String mapToCreatePreferenceResponse() throws ModelMarshallException {
        CreatePreferenceResponse createPreferenceResponse = new CreatePreferenceResponse();
        createPreferenceResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(createPreferenceResponse);
    }
    
    public static String mapToUpdatePreferenceResponse() throws ModelMarshallException {
        UpdatePreferenceResponse updatePreferenceResponse = new UpdatePreferenceResponse();
        updatePreferenceResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(updatePreferenceResponse);
    }
    
    public static String mapToDeletePreferenceResponse() throws ModelMarshallException {
        DeletePreferenceResponse deletePreferenceResponse = new DeletePreferenceResponse();
        deletePreferenceResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(deletePreferenceResponse);
    }
 
    public static String mapToCreateDatasetResponse() throws ModelMarshallException {
        CreateDatasetResponse createDatasetResponse = new CreateDatasetResponse();
        createDatasetResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(createDatasetResponse);
    }
    
    public static String mapToUpdateDatasetResponse() throws ModelMarshallException {
        UpdateDatasetResponse updateDatasetResponse = new UpdateDatasetResponse();
        updateDatasetResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(updateDatasetResponse);
    }
    
    public static String mapToDeleteDatasetResponse() throws ModelMarshallException {
        DeleteDatasetResponse deleteDatasetResponse = new DeleteDatasetResponse();
        deleteDatasetResponse.setResponse("OK");
        return JAXBMarshaller.marshallJaxBObjectToString(deleteDatasetResponse);
    }
    
    public static String mapToFindDatasetResponse(DatasetList datasetList ) throws ModelMarshallException {
       FilterDatasetResponse filterDatasetResponse = new FilterDatasetResponse();
       filterDatasetResponse.setDatasetList(datasetList);        
       return JAXBMarshaller.marshallJaxBObjectToString(filterDatasetResponse);
    }
}