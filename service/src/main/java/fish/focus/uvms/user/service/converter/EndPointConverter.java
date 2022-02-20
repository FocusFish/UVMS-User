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

import fish.focus.uvms.usm.administration.domain.EndPointContact;
import fish.focus.uvms.usm.information.domain.Channel;
import fish.focus.uvms.usm.information.domain.ContactDetails;
import java.util.List;

public class EndPointConverter {

    /**
     * Information-Model
     * eu.europa.ec.mare.usm.information.domain;
     * private String name;
     * private String description;
     * private String uri;
     * private String email;
     * private boolean enabled;
     * private List<Channel> channels;
     * private List<ContactDetails> contactDetails;
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String uri;
     * protected String email;
     * protected boolean enabled;
     * protected List<Channel> channels;
     * protected List<ContactDetails> contactDetails;
     */

    public static fish.focus.wsdl.user.types.EndPoint
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.EndPoint domainEndPoint) {
        fish.focus.wsdl.user.types.EndPoint typesEndPoint = new fish.focus.wsdl.user.types.EndPoint();
        typesEndPoint.setName(domainEndPoint.getName());
        typesEndPoint.setDescription(domainEndPoint.getDescription());
        typesEndPoint.setUri(domainEndPoint.getUri());
        typesEndPoint.setEmail(domainEndPoint.getEmail());
        typesEndPoint.setEnabled(domainEndPoint.isEnabled());
        List<fish.focus.wsdl.user.types.Channel> typesChannels = typesEndPoint.getChannels();
        List<fish.focus.uvms.usm.information.domain.Channel> domainChannels = domainEndPoint.getChannels();
        for (Channel domainChannelElement : domainChannels) {
            fish.focus.wsdl.user.types.Channel typesChannelElement = ChannelConverter.convertInformationModelToUserModel(domainChannelElement);
            typesChannels.add(typesChannelElement);
        }
        List<fish.focus.wsdl.user.types.ContactDetails> typesContactDetails = typesEndPoint.getContactDetails();
        List<fish.focus.uvms.usm.information.domain.ContactDetails> domainContactDetails = domainEndPoint.getContactDetails();
        for (ContactDetails domainContactDetailsElement : domainContactDetails) {
            fish.focus.wsdl.user.types.ContactDetails typesContactDetailsElement = ContactDetailsConverter.convertInformationModelToUserModel(domainContactDetailsElement);
            typesContactDetails.add(typesContactDetailsElement);
        }
        return typesEndPoint;
    }

    /**
     * Information-Model
     * eu.europa.ec.mare.usm.information.domain;
     * private String name;
     * private String description;
     * private String uri;
     * private String email;
     * private boolean enabled;
     * private List<Channel> channels;
     * private List<ContactDetails> contactDetails;
     * <p>
     * user-model
     * package eu.europa.ec.fisheries.wsdl.user.types;
     * protected String uri;
     * protected String email;
     * protected boolean enabled;
     * protected List<Channel> channels;
     * protected List<ContactDetails> contactDetails;
     */
    public static fish.focus.wsdl.user.types.EndPoint
    convertAdministrationModelToUserModel(fish.focus.uvms.usm.administration.domain.EndPoint domainEndPoint) {
        fish.focus.wsdl.user.types.EndPoint typesEndPoint = new fish.focus.wsdl.user.types.EndPoint();
        typesEndPoint.setId(domainEndPoint.getEndpointId());
        typesEndPoint.setName(domainEndPoint.getName());
        typesEndPoint.setDescription(domainEndPoint.getDescription());
        typesEndPoint.setUri(domainEndPoint.getUri());
        typesEndPoint.setEmail(domainEndPoint.getEmail());
        typesEndPoint.setEnabled((domainEndPoint.getStatus() != null ? (domainEndPoint.getStatus().equalsIgnoreCase("E") ? true : false) : false));
        List<fish.focus.wsdl.user.types.Channel> typesChannels = typesEndPoint.getChannels();
        List<fish.focus.uvms.usm.administration.domain.Channel> domainChannels = domainEndPoint.getChannelList();
        for (fish.focus.uvms.usm.administration.domain.Channel domainChannelElement : domainChannels) {
            fish.focus.wsdl.user.types.Channel typesChannelElement = ChannelConverter.convertAdministraionModelToUserModel(domainChannelElement);
            typesChannels.add(typesChannelElement);
        }
        List<fish.focus.wsdl.user.types.ContactDetails> typesContactDetails = typesEndPoint.getContactDetails();
        List<fish.focus.uvms.usm.administration.domain.EndPointContact> domainContactDetails = domainEndPoint.getPersons();
        for (EndPointContact domainContactDetailsElement : domainContactDetails) {
            fish.focus.wsdl.user.types.ContactDetails typesContactDetailsElement = ContactDetailsConverter.convertAdministrationModelToUserModel(domainContactDetailsElement);
            typesContactDetails.add(typesContactDetailsElement);
        }
        return typesEndPoint;
    }
}