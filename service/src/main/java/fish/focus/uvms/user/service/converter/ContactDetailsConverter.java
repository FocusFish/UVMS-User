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

public class ContactDetailsConverter {
    /*
	Information-Model
	package eu.europa.ec.mare.usm.information.domain;
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private String mobileNumber;
		private String faxNumber;
		private String eMail;
	    private String organisationName;
	
	 user-model
	 package eu.europa.ec.fisheries.wsdl.user.types;
	    protected String firstName;
	    protected String lastName;
	    protected String phoneNumber;
	    protected String mobileNumber;
	    protected String faxNumber;
	    protected String eMail;
	    protected String organisationName;
	
	
	
	*/

    public static fish.focus.wsdl.user.types.ContactDetails
    convertInformationModelToUserModel(fish.focus.uvms.usm.information.domain.ContactDetails domainContactDetails) {
        if (domainContactDetails == null) {
            return null;
        }
        fish.focus.wsdl.user.types.ContactDetails typesContactDetails = new fish.focus.wsdl.user.types.ContactDetails();
        typesContactDetails.setFirstName(domainContactDetails.getFirstName());
        typesContactDetails.setLastName(domainContactDetails.getLastName());
        typesContactDetails.setPhoneNumber(domainContactDetails.getPhoneNumber());
        typesContactDetails.setMobileNumber(domainContactDetails.getMobileNumber());
        typesContactDetails.setFaxNumber(domainContactDetails.getFaxNumber());
        typesContactDetails.setEMail(domainContactDetails.geteMail());
        typesContactDetails.setOrganisationName(domainContactDetails.getOrganisationName());
        return typesContactDetails;
    }

	       /*
	Administration-Model
	package eu.europa.ec.mare.usm.administration.domain;
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private String mobileNumber;
		private String faxNumber;
		private String email;

	 user-model
	 package eu.europa.ec.fisheries.wsdl.user.types;
	    protected String firstName;
	    protected String lastName;
	    protected String phoneNumber;
	    protected String mobileNumber;
	    protected String faxNumber;
	    protected String eMail;
	    protected String organisationName;



	*/

    public static fish.focus.wsdl.user.types.ContactDetails
    convertAdministrationModelToUserModel(fish.focus.uvms.usm.administration.domain.EndPointContact domainContactDetails) {
        if (domainContactDetails == null) {
            return null;
        }
        fish.focus.wsdl.user.types.ContactDetails typesContactDetails = new fish.focus.wsdl.user.types.ContactDetails();
        typesContactDetails.setFirstName(domainContactDetails.getFirstName());
        typesContactDetails.setLastName(domainContactDetails.getLastName());
        typesContactDetails.setPhoneNumber(domainContactDetails.getPhoneNumber());
        typesContactDetails.setMobileNumber(domainContactDetails.getMobileNumber());
        typesContactDetails.setFaxNumber(domainContactDetails.getFaxNumber());
        typesContactDetails.setEMail(domainContactDetails.getEmail());
        typesContactDetails.setOrganisationName("LM: NOT Available");
        return typesContactDetails;
    }

}