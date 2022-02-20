
package fish.focus.wsdl.user.module;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fish.focus.wsdl.user.types.DatasetExtension;


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
 *         &lt;element name="dataset" type="{types.user.wsdl.fisheries.ec.europa.eu}DatasetExtension"/>
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
    "dataset"
})
@XmlRootElement(name = "createDatasetRequest")
public class CreateDatasetRequest
    extends UserBaseRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected DatasetExtension dataset;

    /**
     * Gets the value of the dataset property.
     * 
     * @return
     *     possible object is
     *     {@link DatasetExtension }
     *     
     */
    public DatasetExtension getDataset() {
        return dataset;
    }

    /**
     * Sets the value of the dataset property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatasetExtension }
     *     
     */
    public void setDataset(DatasetExtension value) {
        this.dataset = value;
    }

}
