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

import fish.focus.uvms.user.message.event.CreateDatasetEvent;
import fish.focus.uvms.user.message.event.CreatePreferenceEvent;
import fish.focus.uvms.user.message.event.DeleteDatasetEvent;
import fish.focus.uvms.user.message.event.DeletePreferenceEvent;
import fish.focus.uvms.user.message.event.DeployApplicationEvent;
import fish.focus.uvms.user.message.event.ErrorEvent;
import fish.focus.uvms.user.message.event.FindDatasetsEvent;
import fish.focus.uvms.user.message.event.FindOrganizationsEvent;
import fish.focus.uvms.user.message.event.GetAllOrganizationEvent;
import fish.focus.uvms.user.message.event.GetApplicationEvent;
import fish.focus.uvms.user.message.event.GetContactDetailsEvent;
import fish.focus.uvms.user.message.event.GetOrganizationEvent;
import fish.focus.uvms.user.message.event.GetUserContexEvent;
import fish.focus.uvms.user.message.event.PingEvent;
import fish.focus.uvms.user.message.event.PutPreferenceEvent;
import fish.focus.uvms.user.message.event.RedeployApplicationEvent;
import fish.focus.uvms.user.message.event.UndeployApplicationEvent;
import fish.focus.uvms.user.message.event.UpdateDatasetEvent;
import fish.focus.uvms.user.message.event.UpdatePreferenceEvent;
import fish.focus.uvms.user.message.event.UpdateUserContexEvent;
import fish.focus.uvms.user.message.event.carrier.EventMessage;
import fish.focus.uvms.user.message.producer.bean.UserMessageProducerBean;
import fish.focus.uvms.user.model.exception.ModelMarshallException;
import fish.focus.uvms.user.model.mapper.JAXBMarshaller;
import fish.focus.uvms.user.model.mapper.UserModuleResponseMapper;
import fish.focus.uvms.user.service.UserEventService;
import fish.focus.uvms.user.service.UserService;
import fish.focus.uvms.user.service.exception.UserServiceException;
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
import fish.focus.wsdl.user.module.PingResponse;
import fish.focus.wsdl.user.module.PutPreferenceRequest;
import fish.focus.wsdl.user.module.PutUserPreferencesRequest;
import fish.focus.wsdl.user.module.RedeployApplicationRequest;
import fish.focus.wsdl.user.module.UndeployApplicationRequest;
import fish.focus.wsdl.user.module.UpdateDatasetRequest;
import fish.focus.wsdl.user.module.UpdatePreferenceRequest;
import fish.focus.wsdl.user.module.UserBaseRequest;
import fish.focus.wsdl.user.module.UserModuleMethod;
import fish.focus.wsdl.user.types.Application;
import fish.focus.wsdl.user.types.ContactDetails;
import fish.focus.wsdl.user.types.DatasetList;
import fish.focus.wsdl.user.types.Organisation;
import fish.focus.wsdl.user.types.UserContext;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserEventServiceBean implements UserEventService {

    private static final Logger LOG = LoggerFactory.getLogger(UserEventServiceBean.class);

    @Inject
    @ErrorEvent
    private Event<EventMessage> errorEvent;

    @EJB
    private UserService userService;

    @EJB
    private UserMessageProducerBean messageProducer;

    @Override
    public void getUserContext(@Observes @GetUserContexEvent EventMessage message) {
        LOG.info("GetUserContexEvent Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.GET_USER_CONTEXT) {
                message.setErrorMessage(" [ Error, Get user preferences invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            GetUserContextRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), GetUserContextRequest.class);
            String responseString;
            try {
                UserContext userContext = userService.getUserContext(request.getContextId());
                responseString = UserModuleResponseMapper.mapToGetUserContextResponse(userContext);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
                    LOG.debug("Invalid get user context: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.GET_USER_CONTEXT);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when get user context ] ", ex);
            errorEvent.fire(message);
        }

    }

    @Override
    public void updateUserPreferences(@Observes @UpdateUserContexEvent EventMessage message) {
        LOG.info("GetUserContexEvent Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.PUT_USER_PREFERENCES) {
                message.setErrorMessage(" [ Error, Update user preferences invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            PutUserPreferencesRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), PutUserPreferencesRequest.class);
            String responseString;
            try {
                userService.updateUserPreferences(request.getContext());
                responseString = UserModuleResponseMapper.mapToPutUserPreferencesResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid user preferences: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.PUT_USER_PREFERENCES);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when put user preferences ] ", ex);
            errorEvent.fire(message);
        }

    }

    @Override
    public void deployApplication(@Observes @DeployApplicationEvent EventMessage message) {
        LOG.info("deployApplication Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.DEPLOY_APPLICATION) {
                message.setErrorMessage(" [ Error, Deploy application invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            DeployApplicationRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), DeployApplicationRequest.class);
            String responseString;
            try {
                userService.deployApplication(request.getApplication());
                responseString = UserModuleResponseMapper.mapToDeployApplicationResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid deploy application: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.DEPLOY_APPLICATION);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when deploy application ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void redeployApplication(@Observes @RedeployApplicationEvent EventMessage message) {
        LOG.info("redeployApplication Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.REDEPLOY_APPLICATION) {
                message.setErrorMessage(" [ Error, Redeploy application invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            RedeployApplicationRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), RedeployApplicationRequest.class);
            String responseString;
            try {
                userService.redeployApplication(request.getApplication());
                responseString = UserModuleResponseMapper.mapToRedeployApplicationResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid redeploy application: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.REDEPLOY_APPLICATION);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when redeploy application ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void undeployApplication(@Observes @UndeployApplicationEvent EventMessage message) {
        LOG.info("undeployApplication Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.UNDEPLOY_APPLICATION) {
                message.setErrorMessage(" [ Error, Undeploy application invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            UndeployApplicationRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UndeployApplicationRequest.class);
            String responseString;
            try {
                userService.undeployApplication(request.getApplicationName());
                responseString = UserModuleResponseMapper.mapToUndeployApplicationResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid undeploy application: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.UNDEPLOY_APPLICATION);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when undeploy application ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void getApplication(@Observes @GetApplicationEvent EventMessage message) {
        LOG.info("getApplication Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.GET_DEPLOYMENT_DESCRIPTOR) {
                message.setErrorMessage(" [ Error, Get application invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            GetDeploymentDescriptorRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), GetDeploymentDescriptorRequest.class);
            String responseString;
            try {
                Application application = userService.getDeploymentDescriptor(request.getApplicationName());
                responseString = UserModuleResponseMapper.mapToGetApplicationResponse(application);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid get application deployment: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.GET_DEPLOYMENT_DESCRIPTOR);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when get deployment descriptor ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void getOrganisation(@Observes @GetOrganizationEvent EventMessage message) {
        LOG.info("getOrganization Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.GET_ORGANISATIONS) {
                message.setErrorMessage(" [ Error, Get organisation invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            GetOrganisationRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), GetOrganisationRequest.class);
            String responseString;
            try {
                Organisation organization = userService.getOrganisation(request.getOrganizationName());
                responseString = UserModuleResponseMapper.mapToGetOrganisationResponse(organization);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid get organization: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.GET_ORGANISATIONS);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when get organisations ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void findOrganisations(@Observes @FindOrganizationsEvent EventMessage message) {
        LOG.info("finsOrganizations Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.FIND_ORGANISATIONS) {
                message.setErrorMessage(" [ Error, Find organisations invoked but it is not the intended method, "
                        + "caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            FindOrganisationsRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), FindOrganisationsRequest.class);
            String responseString;
            try {
                List<Organisation> organization = userService.findOrganisations(request.getNationIsoName());
                responseString = UserModuleResponseMapper.mapToFindOrganisationsResponse(organization);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid get organization: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause,
                            UserModuleMethod.FIND_ORGANISATIONS);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when get organisations ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void getContactDetails(@Observes @GetContactDetailsEvent EventMessage message) {
        LOG.info("getContactDetails Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.GET_CONTACT_DETAILS) {
                message.setErrorMessage(" [ Error, Get organisation invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            GetContactDetailsRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), GetContactDetailsRequest.class);
            String responseString;
            try {
                ContactDetails contactDetails = userService.getContactDetails(request.getUserName());
                responseString = UserModuleResponseMapper.mapToGetContactDetailsResponse(contactDetails);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid get contact details: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.GET_CONTACT_DETAILS);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when getting contact details ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void ping(@Observes @PingEvent EventMessage message) {
        try {
            PingResponse pingResponse = new PingResponse();
            pingResponse.setResponse("pong");
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), JAXBMarshaller.marshallJaxBObjectToString(pingResponse));
        } catch (JMSException | ModelMarshallException ex) {
            LOG.error("[ Error when marshalling ping response ]", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void putPreference(@Observes @PutPreferenceEvent EventMessage message) {
        LOG.info("putPreference Received.. processing request in UserEventServiceBean");
        try {

            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(),
                    UserBaseRequest.class);

            if (baseRequest.getMethod() != UserModuleMethod.PUT_PREFERENCE) {
                message.setErrorMessage(" [ Error, Put preference invoked but it is not the intended method, "
                        + "caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }

            PutPreferenceRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(),
                    PutPreferenceRequest.class);
            String responseString;
            try {
                userService.putPreference(request.getUserPreference());
                responseString = UserModuleResponseMapper.mapToPutPreferenceResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
                    LOG.debug("Invalid put preference: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.PUT_PREFERENCE);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when put preference ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void createPreference(@Observes @CreatePreferenceEvent EventMessage message) {
        LOG.info("createPreference Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.CREATE_PREFERENCE) {
                message.setErrorMessage(" [ Error, Create preference invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            CreatePreferenceRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), CreatePreferenceRequest.class);
            String responseString;
            try {
                userService.createPreference(request.getUserPreference());
                responseString = UserModuleResponseMapper.mapToCreatePreferenceResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid create preference: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.CREATE_PREFERENCE);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when create preference ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void deletePreference(@Observes @DeletePreferenceEvent EventMessage message) {
        LOG.info("deletePreference Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.DELETE_PREFERENCE) {
                message.setErrorMessage(" [ Error, Delete preference invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            DeletePreferenceRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), DeletePreferenceRequest.class);
            String responseString = null;
            try {
                userService.deletePreference(request.getUserPreference());
                responseString = UserModuleResponseMapper.mapToDeletePreferenceResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid delete preference: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.DELETE_PREFERENCE);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when delete preference ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void updatePreference(@Observes @UpdatePreferenceEvent EventMessage message) {
        LOG.info("updatePreference Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.UPDATE_PREFERENCE) {
                message.setErrorMessage(" [ Error, Update preference invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            UpdatePreferenceRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UpdatePreferenceRequest.class);
            String responseString;
            try {
                userService.updatePreference(request.getUserPreference());
                responseString = UserModuleResponseMapper.mapToUpdatePreferenceResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid update preference: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.UPDATE_PREFERENCE);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when Update preference ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void createDataset(@Observes @CreateDatasetEvent EventMessage message) {
        LOG.info("createDataset Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.CREATE_DATASET) {
                message.setErrorMessage(" [ Error, create dataset invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            CreateDatasetRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), CreateDatasetRequest.class);
            String responseString = null;
            try {
                userService.createDataset(request.getDataset());
                responseString = UserModuleResponseMapper.mapToCreateDatasetResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid create dataset: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.CREATE_DATASET);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when create dataset ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void deleteDataset(@Observes @DeleteDatasetEvent EventMessage message) {
        LOG.info("deleteDataset Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.DELETE_DATASET) {
                message.setErrorMessage(" [ Error, delete dataset invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            DeleteDatasetRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), DeleteDatasetRequest.class);
            String responseString;
            try {
                userService.deleteDataset(request.getDataset());
                responseString = UserModuleResponseMapper.mapToDeleteDatasetResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid delete dataset: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.DELETE_DATASET);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when delete dataset ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void updateDataset(@Observes @UpdateDatasetEvent EventMessage message) {
        LOG.info("updateDataset Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.UPDATE_DATASET) {
                message.setErrorMessage(" [ Error, update dataset invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            UpdateDatasetRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UpdateDatasetRequest.class);
            String responseString;
            try {
                userService.updateDataset(request.getDataset());
                responseString = UserModuleResponseMapper.mapToUpdateDatasetResponse();
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid update dataset: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.UPDATE_DATASET);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when update dataset ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void findDatasets(@Observes @FindDatasetsEvent EventMessage message) {
        LOG.info("findDatasets Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.FIND_DATASETS) {
                message.setErrorMessage(" [ Error, Find datasets invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            FilterDatasetRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), FilterDatasetRequest.class);
            String responseString;
            try {
                DatasetList datasetList = userService.findDataset(request.getDatasetFilter());
                responseString = UserModuleResponseMapper.mapToFindDatasetResponse(datasetList);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid find datasets: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.FIND_DATASETS);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when find datasets ] ", ex);
            errorEvent.fire(message);
        }
    }

    @Override
    public void getAllOrganisation(@Observes @GetAllOrganizationEvent EventMessage message) {
        LOG.info("getAllOrganization Received.. processing request in UserEventServiceBean");
        try {
            UserBaseRequest baseRequest = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), UserBaseRequest.class);
            if (baseRequest.getMethod() != UserModuleMethod.GET_ALLORGANISATIONS) {
                message.setErrorMessage(" [ Error, Get all organisation invoked but it is not the intended method, caller is trying: " + baseRequest.getMethod().name() + " ]");
                errorEvent.fire(message);
            }
            GetAllOrganisationRequest request = JAXBMarshaller.unmarshallTextMessage(message.getJmsMessage(), GetAllOrganisationRequest.class);
            String responseString;
            try {
                List<Organisation> organizationList = userService.getAllOrganisations(request);
                responseString = UserModuleResponseMapper.mapToFindOrganisationsResponse(organizationList);
            } catch (Exception e) {
                if (e.getCause() != null && e.getCause() instanceof IllegalArgumentException) {
                    IllegalArgumentException cause = (IllegalArgumentException) e.getCause();

                    LOG.debug("Invalid get all organisation: ", cause.getMessage());
                    responseString = UserModuleResponseMapper.mapToUserFault(cause, UserModuleMethod.GET_ALLORGANISATIONS);
                } else {
                    throw e;
                }
            }
            messageProducer.sendMessageBackToRecipient(message.getJmsMessage(), responseString);
        } catch (JMSException | ModelMarshallException | UserServiceException ex) {
            LOG.error("[ Error when get all organisations ] ", ex);
            errorEvent.fire(message);
        }
    }
}