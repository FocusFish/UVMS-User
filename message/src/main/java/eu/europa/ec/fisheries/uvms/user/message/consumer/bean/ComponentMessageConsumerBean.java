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
package eu.europa.ec.fisheries.uvms.user.message.consumer.bean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.europa.ec.fisheries.uvms.commons.message.impl.JMSUtils;
import eu.europa.ec.fisheries.uvms.user.message.constants.MessageConstants;
import eu.europa.ec.fisheries.uvms.user.message.consumer.UserMessageConsumer;
import eu.europa.ec.fisheries.uvms.user.message.exception.UserMessageException;

@Stateless
public class ComponentMessageConsumerBean implements UserMessageConsumer {

    final static Logger LOG = LoggerFactory.getLogger(ComponentMessageConsumerBean.class);
    final static int ONE_MINUTE = 60000;

    @Resource(mappedName = MessageConstants.USER_RESPONSE_QUEUE)
    private Queue responseQueue;

    @Resource(lookup = MessageConstants.CONNECTION_FACTORY)
    private ConnectionFactory connectionFactory;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public <T> T getMessage(String correlationId, Class type) throws UserMessageException {
    	if (correlationId == null || correlationId.isEmpty()) {
    		LOG.error("[ No CorrelationID provided when listening to JMS message, aborting ]");
    		throw new UserMessageException("No CorrelationID provided!");
    	}
    	
        Connection connection=null;
        try {
            connection = connectionFactory.createConnection();
            final Session session = JMSUtils.connectToQueue(connection);


            return (T) session.createConsumer(responseQueue, "JMSCorrelationID='" + correlationId + "'").receive(ONE_MINUTE);

        } catch (Exception e) {
            LOG.error("[ Error when getting medssage ] {}", e.getMessage());
            throw new UserMessageException("Error when retrieving message: ", e);
        } finally {
        	JMSUtils.disconnectQueue(connection);
        }
    }

}