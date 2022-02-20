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
package fish.focus.uvms.user.message.consumer.bean;

import fish.focus.uvms.commons.message.api.MessageConstants;
import fish.focus.uvms.user.message.event.*;
import fish.focus.uvms.user.message.event.carrier.EventMessage;
import fish.focus.uvms.user.model.exception.ModelMapperException;
import fish.focus.uvms.user.model.mapper.JAXBMarshaller;
import fish.focus.wsdl.user.module.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = MessageConstants.DESTINATION_TYPE_STR, propertyValue = MessageConstants.DESTINATION_TYPE_QUEUE),
    @ActivationConfigProperty(propertyName = MessageConstants.DESTINATION_LOOKUP_STR, propertyValue = MessageConstants.QUEUE_USER_IN)
})
public class UserMessageConsumerBean implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(UserMessageConsumerBean.class);


    @Inject
    @GetUserContexEvent
    private Event<EventMessage> getUserContextEvent;

    @Inject
    @UpdateUserContexEvent
    private Event<EventMessage> updateUserContextEvent;
    

    @Inject
    @DeployApplicationEvent
    private Event<EventMessage> deployApplicationEvent;

    @Inject
    @RedeployApplicationEvent
    private Event<EventMessage> redeployApplicationEvent;

    @Inject
    @UndeployApplicationEvent
    private Event<EventMessage> undeployApplicationEvent;

    @Inject
    @GetApplicationEvent
    private Event<EventMessage> getApplicationEvent;

    @Inject
    @GetOrganizationEvent
    private Event<EventMessage> getOrganizationEvent;

    @Inject
    @GetAllOrganizationEvent
    private Event<EventMessage> getAllOrganizationEvent;

    @Inject
    @FindOrganizationsEvent
    private Event<EventMessage> findOrganizationsEvent;
    
    @Inject
    @GetContactDetailsEvent
    private Event<EventMessage> getContactDetailsEvent;
    
    @Inject
    @PutPreferenceEvent
    private Event<EventMessage> putPreferenceEvent;
    
    @Inject
    @CreatePreferenceEvent
    private Event<EventMessage> createPreferenceEvent;
    
    @Inject
    @UpdatePreferenceEvent
    private Event<EventMessage> updatePreferenceEvent;
    
    @Inject
    @DeletePreferenceEvent
    private Event<EventMessage> deletePreferenceEvent;
    
    @Inject
    @CreateDatasetEvent
    private Event<EventMessage> createDatasetEvent;
    
    @Inject
    @UpdateDatasetEvent
    private Event<EventMessage> updateDatasetEvent;
    
    @Inject
    @DeleteDatasetEvent
    private Event<EventMessage> deleteDatasetEvent;
    
    @Inject
    @FindDatasetsEvent
    private Event<EventMessage> findDatasetEvent;
    
    
    @Inject
    @PingEvent
    private Event<EventMessage> pingEvent;
    
    @Inject
    @ErrorEvent
    private Event<EventMessage> errorEvent;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            LOG.info("Message received in user");
            UserBaseRequest request = JAXBMarshaller.unmarshallTextMessage(textMessage, UserBaseRequest.class);
            switch (request.getMethod()) {
                case GET_USER_CONTEXT:
                    GetUserContextRequest getUserContextRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, GetUserContextRequest.class);
                    getUserContextEvent.fire(new EventMessage(textMessage, getUserContextRequest.getContextId().toString()));
                    break;
                case PUT_USER_PREFERENCES:
                    PutUserPreferencesRequest updateUserContextRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, PutUserPreferencesRequest.class);
                    updateUserContextEvent.fire(new EventMessage(textMessage, updateUserContextRequest.getContext().getApplicationName()));
                    break;
                case GET_DEPLOYMENT_DESCRIPTOR:
                    GetDeploymentDescriptorRequest deploymentDescriptorRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, GetDeploymentDescriptorRequest.class);
                    getApplicationEvent.fire(new EventMessage(textMessage, deploymentDescriptorRequest.getApplicationName()));
                    break;
                case DEPLOY_APPLICATION:
                    DeployApplicationRequest deployApplicationRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, DeployApplicationRequest.class);
                    deployApplicationEvent.fire(new EventMessage(textMessage, deployApplicationRequest.getApplication().toString()));
                    break;
                case REDEPLOY_APPLICATION:
                    RedeployApplicationRequest redeployApplicationRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, RedeployApplicationRequest.class);
                    redeployApplicationEvent.fire(new EventMessage(textMessage, redeployApplicationRequest.getApplication().toString()));
                    break;
                case UNDEPLOY_APPLICATION:
                    UndeployApplicationRequest undeployApplicationRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, UndeployApplicationRequest.class);
                    undeployApplicationEvent.fire(new EventMessage(textMessage, undeployApplicationRequest.getApplicationName()));
                    break;
                case GET_ORGANISATIONS:
                    GetOrganisationRequest getOrganisationRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, GetOrganisationRequest.class);
                    getOrganizationEvent.fire(new EventMessage(textMessage, getOrganisationRequest.getOrganizationName()));
                    break;
                case GET_ALLORGANISATIONS:
                    getAllOrganizationEvent.fire(new EventMessage(textMessage));
                    break;
                case GET_CONTACT_DETAILS:
                    GetContactDetailsRequest getContactDetailsRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, GetContactDetailsRequest.class);
                    getContactDetailsEvent.fire(new EventMessage(textMessage, getContactDetailsRequest.getUserName()));
                    break;
                case PUT_PREFERENCE:
                    PutPreferenceRequest putPreferenceRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, PutPreferenceRequest.class);
                    putPreferenceEvent.fire(new EventMessage(textMessage, putPreferenceRequest.getUserPreference().toString()));
                    break;
                case CREATE_PREFERENCE:
                    CreatePreferenceRequest createPreferenceRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, CreatePreferenceRequest.class);
                    createPreferenceEvent.fire(new EventMessage(textMessage, createPreferenceRequest.getUserPreference().toString()));
                    break;
                case UPDATE_PREFERENCE:
                    UpdatePreferenceRequest updatePreferenceRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, UpdatePreferenceRequest.class);
                    updatePreferenceEvent.fire(new EventMessage(textMessage, updatePreferenceRequest.getUserPreference().toString()));
                    break;
                case DELETE_PREFERENCE:
                    DeletePreferenceRequest deletePreferenceRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, DeletePreferenceRequest.class);
                    deletePreferenceEvent.fire(new EventMessage(textMessage, deletePreferenceRequest.getUserPreference().toString()));
                    break;
                case CREATE_DATASET:
                    CreateDatasetRequest createDatasetRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, CreateDatasetRequest.class);
                    createDatasetEvent.fire(new EventMessage(textMessage, createDatasetRequest.getDataset().toString()));
                    break;
                case UPDATE_DATASET:
                    UpdateDatasetRequest updateDatasetRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, UpdateDatasetRequest.class);
                    updateDatasetEvent.fire(new EventMessage(textMessage, updateDatasetRequest.getDataset().toString()));
                    break;
                case DELETE_DATASET:
                    DeleteDatasetRequest deleteDatasetRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, DeleteDatasetRequest.class);
                    deleteDatasetEvent.fire(new EventMessage(textMessage, deleteDatasetRequest.getDataset().toString()));
                    break;
                case FIND_DATASETS:
                    FilterDatasetRequest filterDatasetRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, FilterDatasetRequest.class);
                    findDatasetEvent.fire(new EventMessage(textMessage, filterDatasetRequest.getDatasetFilter().toString()));
                    break;
                case PING:
                    pingEvent.fire(new EventMessage(textMessage));
                    break;
                case FIND_ORGANISATIONS:
                    FindOrganisationsRequest findOrganisationsRequest = JAXBMarshaller.unmarshallTextMessage(textMessage, FindOrganisationsRequest.class);
                    findOrganizationsEvent.fire(new EventMessage(textMessage, findOrganisationsRequest.getNationIsoName()));
                    break;
                default:
                    break;
            }
        } catch (ModelMapperException | NullPointerException e) {
            LOG.error("[ Error when receiving message in user: ]", e);
            errorEvent.fire(new EventMessage(textMessage, "Error when receiving message in user: " + e.getMessage()));
        }
    }

}