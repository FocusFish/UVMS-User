
package fish.focus.wsdl.user.module;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fish.focus.wsdl.user.types.DatasetList;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datasetList" type="{types.user.wsdl.fisheries.ec.europa.eu}DatasetList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datasetList"
})
@XmlRootElement(name = "filterDatasetResponse")
public class FilterDatasetResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected DatasetList datasetList;

    /**
     * Gets the value of the datasetList property.
     * 
     * @return
     *     possible object is
     *     {@link DatasetList }
     *     
     */
    public DatasetList getDatasetList() {
        return datasetList;
    }

    /**
     * Sets the value of the datasetList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatasetList }
     *     
     */
    public void setDatasetList(DatasetList value) {
        this.datasetList = value;
    }

}
