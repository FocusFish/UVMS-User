package eu.europa.ec.fisheries.wsdl.user.module;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2018-02-05T11:38:49.628+01:00
 * Generated source version: 2.7.6
 */

@WebFault(name = "UserFault", targetNamespace = "types.user.wsdl.fisheries.ec.europa.eu")
public class FindOrganisationsFault extends Exception {
    
    private eu.europa.ec.fisheries.wsdl.user.types.UserFault userFault;

    public FindOrganisationsFault() {
        super();
    }
    
    public FindOrganisationsFault(String message) {
        super(message);
    }
    
    public FindOrganisationsFault(String message, Throwable cause) {
        super(message, cause);
    }

    public FindOrganisationsFault(String message, eu.europa.ec.fisheries.wsdl.user.types.UserFault userFault) {
        super(message);
        this.userFault = userFault;
    }

    public FindOrganisationsFault(String message, eu.europa.ec.fisheries.wsdl.user.types.UserFault userFault, Throwable cause) {
        super(message, cause);
        this.userFault = userFault;
    }

    public eu.europa.ec.fisheries.wsdl.user.types.UserFault getFaultInfo() {
        return this.userFault;
    }
}
