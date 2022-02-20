
package fish.focus.wsdl.user.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Scope complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Scope">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scopeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activeFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="activeTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataFrom" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataset" type="{types.user.wsdl.fisheries.ec.europa.eu}Dataset" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Scope", propOrder = {
    "scopeName",
    "activeFrom",
    "activeTo",
    "dataFrom",
    "dataTo",
    "dataset"
})
public class Scope
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String scopeName;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar activeFrom;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar activeTo;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFrom;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataTo;
    protected List<Dataset> dataset;

    /**
     * Gets the value of the scopeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScopeName() {
        return scopeName;
    }

    /**
     * Sets the value of the scopeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScopeName(String value) {
        this.scopeName = value;
    }

    /**
     * Gets the value of the activeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveFrom() {
        return activeFrom;
    }

    /**
     * Sets the value of the activeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveFrom(XMLGregorianCalendar value) {
        this.activeFrom = value;
    }

    /**
     * Gets the value of the activeTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveTo() {
        return activeTo;
    }

    /**
     * Sets the value of the activeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveTo(XMLGregorianCalendar value) {
        this.activeTo = value;
    }

    /**
     * Gets the value of the dataFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFrom() {
        return dataFrom;
    }

    /**
     * Sets the value of the dataFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFrom(XMLGregorianCalendar value) {
        this.dataFrom = value;
    }

    /**
     * Gets the value of the dataTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTo() {
        return dataTo;
    }

    /**
     * Sets the value of the dataTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTo(XMLGregorianCalendar value) {
        this.dataTo = value;
    }

    /**
     * Gets the value of the dataset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dataset }
     * 
     * 
     */
    public List<Dataset> getDataset() {
        if (dataset == null) {
            dataset = new ArrayList<Dataset>();
        }
        return this.dataset;
    }

}
