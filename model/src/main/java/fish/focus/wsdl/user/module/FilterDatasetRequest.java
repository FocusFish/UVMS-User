
package fish.focus.wsdl.user.module;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import fish.focus.wsdl.user.types.DatasetFilter;


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
 *         &lt;element name="datasetFilter" type="{types.user.wsdl.fisheries.ec.europa.eu}DatasetFilter"/>
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
    "datasetFilter"
})
@XmlRootElement(name = "filterDatasetRequest")
public class FilterDatasetRequest
    extends UserBaseRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected DatasetFilter datasetFilter;

    /**
     * Gets the value of the datasetFilter property.
     * 
     * @return
     *     possible object is
     *     {@link DatasetFilter }
     *     
     */
    public DatasetFilter getDatasetFilter() {
        return datasetFilter;
    }

    /**
     * Sets the value of the datasetFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatasetFilter }
     *     
     */
    public void setDatasetFilter(DatasetFilter value) {
        this.datasetFilter = value;
    }

}
