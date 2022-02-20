
package fish.focus.wsdl.user.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Organisation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organisation">
 *   &lt;complexContent>
 *     &lt;extension base="{types.user.wsdl.fisheries.ec.europa.eu}NameAndDescription">
 *       &lt;sequence>
 *         &lt;element name="parentOrganisation" type="{types.user.wsdl.fisheries.ec.europa.eu}Name" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childOrganisation" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="endPoints" type="{types.user.wsdl.fisheries.ec.europa.eu}EndPoint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organisation", propOrder = {
    "parentOrganisation",
    "email",
    "status",
    "nation",
    "childOrganisation",
    "endPoints"
})
public class Organisation
    extends NameAndDescription
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String parentOrganisation;
    protected String email;
    protected boolean status;
    @XmlElement(required = true)
    protected String nation;
    protected List<String> childOrganisation;
    protected List<EndPoint> endPoints;

    /**
     * Gets the value of the parentOrganisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrganisation() {
        return parentOrganisation;
    }

    /**
     * Sets the value of the parentOrganisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrganisation(String value) {
        this.parentOrganisation = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the nation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNation() {
        return nation;
    }

    /**
     * Sets the value of the nation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNation(String value) {
        this.nation = value;
    }

    /**
     * Gets the value of the childOrganisation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childOrganisation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildOrganisation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getChildOrganisation() {
        if (childOrganisation == null) {
            childOrganisation = new ArrayList<String>();
        }
        return this.childOrganisation;
    }

    /**
     * Gets the value of the endPoints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endPoints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndPoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EndPoint }
     * 
     * 
     */
    public List<EndPoint> getEndPoints() {
        if (endPoints == null) {
            endPoints = new ArrayList<EndPoint>();
        }
        return this.endPoints;
    }

}
