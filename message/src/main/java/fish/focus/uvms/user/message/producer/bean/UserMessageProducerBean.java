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
package fish.focus.uvms.user.message.producer.bean;

import fish.focus.uvms.commons.message.impl.AbstractProducer;
import fish.focus.uvms.user.message.event.ErrorEvent;
import fish.focus.uvms.user.message.event.carrier.EventMessage;
import fish.focus.uvms.user.model.exception.ModelMarshallException;
import fish.focus.uvms.user.model.mapper.JAXBMarshaller;
import fish.focus.wsdl.user.types.UserFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.math.BigInteger;

@Stateless
@LocalBean
public class UserMessageProducerBean extends AbstractProducer {

    private static final Logger LOG = LoggerFactory.getLogger(UserMessageProducerBean.class);

    public void sendMessageBackToRecipient(TextMessage requestMessage, String returnMessage) throws JMSException {
        sendResponseMessageToSender(requestMessage, returnMessage);
    }

    public void sendErrorMessageBackToRecipient(@Observes @ErrorEvent EventMessage message) {
        try {
            TextMessage requestMessage = message.getJmsMessage();
            UserFault userFault = new UserFault();
            userFault.setCode(BigInteger.ZERO);
            userFault.setFault(message.getErrorMessage());
            String text = JAXBMarshaller.marshallJaxBObjectToString(userFault);
            sendResponseMessageToSender(requestMessage, text);

        } catch (ModelMarshallException | JMSException e) {
            LOG.error("Error when sending error message.", e);
        }
    }

    @Override
    public Destination getDestination() {
        return null;
    }

}