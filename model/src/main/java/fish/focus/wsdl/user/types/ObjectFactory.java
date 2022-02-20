
package fish.focus.wsdl.user.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.europa.ec.fisheries.wsdl.user.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UserContext_QNAME = new QName("types.user.wsdl.fisheries.ec.europa.eu", "userContext");
    private final static QName _UserContextId_QNAME = new QName("types.user.wsdl.fisheries.ec.europa.eu", "userContextId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.europa.ec.fisheries.wsdl.user.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserFault }
     * 
     */
    public UserFault createUserFault() {
        return new UserFault();
    }

    /**
     * Create an instance of {@link UserContextId }
     * 
     */
    public UserContextId createUserContextId() {
        return new UserContextId();
    }

    /**
     * Create an instance of {@link UserContext }
     * 
     */
    public UserContext createUserContext() {
        return new UserContext();
    }

    /**
     * Create an instance of {@link Channel }
     * 
     */
    public Channel createChannel() {
        return new Channel();
    }

    /**
     * Create an instance of {@link Dataset }
     * 
     */
    public Dataset createDataset() {
        return new Dataset();
    }

    /**
     * Create an instance of {@link Option }
     * 
     */
    public Option createOption() {
        return new Option();
    }

    /**
     * Create an instance of {@link DatasetExtension }
     * 
     */
    public DatasetExtension createDatasetExtension() {
        return new DatasetExtension();
    }

    /**
     * Create an instance of {@link DatasetList }
     * 
     */
    public DatasetList createDatasetList() {
        return new DatasetList();
    }

    /**
     * Create an instance of {@link Feature }
     * 
     */
    public Feature createFeature() {
        return new Feature();
    }

    /**
     * Create an instance of {@link Context }
     * 
     */
    public Context createContext() {
        return new Context();
    }

    /**
     * Create an instance of {@link Preferences }
     * 
     */
    public Preferences createPreferences() {
        return new Preferences();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link Scope }
     * 
     */
    public Scope createScope() {
        return new Scope();
    }

    /**
     * Create an instance of {@link UserPreference }
     * 
     */
    public UserPreference createUserPreference() {
        return new UserPreference();
    }

    /**
     * Create an instance of {@link Organisation }
     * 
     */
    public Organisation createOrganisation() {
        return new Organisation();
    }

    /**
     * Create an instance of {@link ContactDetails }
     * 
     */
    public ContactDetails createContactDetails() {
        return new ContactDetails();
    }

    /**
     * Create an instance of {@link ContextSet }
     * 
     */
    public ContextSet createContextSet() {
        return new ContextSet();
    }

    /**
     * Create an instance of {@link NameAndDescription }
     * 
     */
    public NameAndDescription createNameAndDescription() {
        return new NameAndDescription();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link Preference }
     * 
     */
    public Preference createPreference() {
        return new Preference();
    }

    /**
     * Create an instance of {@link DatasetFilter }
     * 
     */
    public DatasetFilter createDatasetFilter() {
        return new DatasetFilter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "types.user.wsdl.fisheries.ec.europa.eu", name = "userContext")
    public JAXBElement<UserContext> createUserContext(UserContext value) {
        return new JAXBElement<UserContext>(_UserContext_QNAME, UserContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserContextId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "types.user.wsdl.fisheries.ec.europa.eu", name = "userContextId")
    public JAXBElement<UserContextId> createUserContextId(UserContextId value) {
        return new JAXBElement<UserContextId>(_UserContextId_QNAME, UserContextId.class, null, value);
    }

}
