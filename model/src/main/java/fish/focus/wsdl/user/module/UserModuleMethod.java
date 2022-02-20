
package fish.focus.wsdl.user.module;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserModuleMethod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserModuleMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GET_USER_CONTEXT"/>
 *     &lt;enumeration value="PUT_USER_PREFERENCES"/>
 *     &lt;enumeration value="GET_CONTACT_DETAILS"/>
 *     &lt;enumeration value="GET_ORGANISATIONS"/>
 *     &lt;enumeration value="GET_ALLORGANISATIONS"/>
 *     &lt;enumeration value="FIND_ORGANISATIONS"/>
 *     &lt;enumeration value="GET_DEPLOYMENT_DESCRIPTOR"/>
 *     &lt;enumeration value="DEPLOY_APPLICATION"/>
 *     &lt;enumeration value="REDEPLOY_APPLICATION"/>
 *     &lt;enumeration value="UNDEPLOY_APPLICATION"/>
 *     &lt;enumeration value="DEPLOY_DATASETS"/>
 *     &lt;enumeration value="PING"/>
 *     &lt;enumeration value="PUT_PREFERENCE"/>
 *     &lt;enumeration value="CREATE_PREFERENCE"/>
 *     &lt;enumeration value="UPDATE_PREFERENCE"/>
 *     &lt;enumeration value="DELETE_PREFERENCE"/>
 *     &lt;enumeration value="CREATE_DATASET"/>
 *     &lt;enumeration value="DELETE_DATASET"/>
 *     &lt;enumeration value="UPDATE_DATASET"/>
 *     &lt;enumeration value="FIND_DATASETS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserModuleMethod")
@XmlEnum
public enum UserModuleMethod {

    GET_USER_CONTEXT,
    PUT_USER_PREFERENCES,
    GET_CONTACT_DETAILS,
    GET_ORGANISATIONS,
    GET_ALLORGANISATIONS,
    FIND_ORGANISATIONS,
    GET_DEPLOYMENT_DESCRIPTOR,
    DEPLOY_APPLICATION,
    REDEPLOY_APPLICATION,
    UNDEPLOY_APPLICATION,
    DEPLOY_DATASETS,
    PING,
    PUT_PREFERENCE,
    CREATE_PREFERENCE,
    UPDATE_PREFERENCE,
    DELETE_PREFERENCE,
    CREATE_DATASET,
    DELETE_DATASET,
    UPDATE_DATASET,
    FIND_DATASETS;

    public String value() {
        return name();
    }

    public static UserModuleMethod fromValue(String v) {
        return valueOf(v);
    }

}
