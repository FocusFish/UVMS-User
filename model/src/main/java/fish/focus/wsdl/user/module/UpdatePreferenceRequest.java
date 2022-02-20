
package fish.focus.wsdl.user.module;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fish.focus.wsdl.user.types.UserPreference;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{module.user.wsdl.fisheries.ec.europa.eu}UserBaseRequest">
 *       &lt;sequence>
 *         &lt;element name="userPreference" type="{types.user.wsdl.fisheries.ec.europa.eu}UserPreference"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userPreference"
})
@XmlRootElement(name = "updatePreferenceRequest")
public class UpdatePreferenceRequest
    extends UserBaseRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected UserPreference userPreference;

    /**
     * Gets the value of the userPreference property.
     * 
     * @return
     *     possible object is
     *     {@link UserPreference }
     *     
     */
    public UserPreference getUserPreference() {
        return userPreference;
    }

    /**
     * Sets the value of the userPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserPreference }
     *     
     */
    public void setUserPreference(UserPreference value) {
        this.userPreference = value;
    }

}
