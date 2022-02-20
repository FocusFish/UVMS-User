
package fish.focus.wsdl.user.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contextSet" type="{types.user.wsdl.fisheries.ec.europa.eu}ContextSet"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserContext", propOrder = {
    "userName",
    "applicationName",
    "contextSet"
})
public class UserContext
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String userName;
    @XmlElement(required = true)
    protected String applicationName;
    @XmlElement(required = true)
    protected ContextSet contextSet;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the contextSet property.
     * 
     * @return
     *     possible object is
     *     {@link ContextSet }
     *     
     */
    public ContextSet getContextSet() {
        return contextSet;
    }

    /**
     * Sets the value of the contextSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContextSet }
     *     
     */
    public void setContextSet(ContextSet value) {
        this.contextSet = value;
    }

}
