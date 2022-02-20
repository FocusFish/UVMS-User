
package fish.focus.wsdl.user.module;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserBaseRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserBaseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="method" type="{module.user.wsdl.fisheries.ec.europa.eu}UserModuleMethod"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserBaseRequest", propOrder = {
    "method"
})
@XmlSeeAlso({
    FilterDatasetRequest.class,
    GetDeploymentDescriptorResponse.class,
    GetOrganisationRequest.class,
    CreatePreferenceRequest.class,
    UndeployApplicationRequest.class,
    PutUserPreferencesRequest.class,
    UpdatePreferenceRequest.class,
    RedeployApplicationRequest.class,
    DeployApplicationRequest.class,
    CreateDatasetRequest.class,
    PingRequest.class,
    DeletePreferenceRequest.class,
    FindOrganisationsRequest.class,
    GetAllOrganisationResponse.class,
    GetContactDetailResponse.class,
    FindOrganisationsResponse.class,
    GetAllOrganisationRequest.class,
    GetDeploymentDescriptorRequest.class,
    UpdateDatasetRequest.class,
    GetContactDetailsRequest.class,
    GetUserContextRequest.class,
    GetOrganisationResponse.class,
    PutPreferenceRequest.class,
    DeleteDatasetRequest.class
})
public abstract class UserBaseRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected UserModuleMethod method;

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link UserModuleMethod }
     *     
     */
    public UserModuleMethod getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserModuleMethod }
     *     
     */
    public void setMethod(UserModuleMethod value) {
        this.method = value;
    }

}
