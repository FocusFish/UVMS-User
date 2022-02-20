package fish.focus.uvms.user.rest.service;

import fish.focus.uvms.commons.date.JsonBConfigurator;
import fish.focus.uvms.user.service.UserService;
import fish.focus.uvms.user.service.exception.UserServiceException;
import fish.focus.wsdl.user.module.GetAllOrganisationRequest;
import fish.focus.wsdl.user.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Stateless
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
public class UserResource {

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @EJB
    private UserService userService;

    @Context
    private HttpServletRequest httpServletRequest;

    private Jsonb jsonb;

    @PostConstruct
    public void init() {
        jsonb = new JsonBConfigurator().getContext(null);
    }

    @PUT
    @Path("/updateUserPreferences")
    public Response updateUserPreferences(UserContext userContext) throws UserServiceException {
        LOG.info("getUserContext invoked in rest layer");
        try {
            userService.updateUserPreferences(userContext);
            return Response.ok().build();
        } catch (UserServiceException | NullPointerException ex) {
            LOG.error("[ Error when updating User Preferences. ]", ex);
            throw ex;
        }
    }

    @GET
    @Path("/getOrganisation")
    public Response getOrganisation(@QueryParam("organisationName") String organisationName) throws UserServiceException {
        try {
            Organisation organisation = userService.getOrganisation(organisationName);
            String returnString = jsonb.toJson(organisation);
            return Response.ok(returnString).build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when fetching Organisation. ]", ex);
            throw ex;
        }
    }

    @POST
    @Path("/getAllOrganisations")
    public Response getAllOrganisations(GetAllOrganisationRequest request) throws UserServiceException {
        try {
            List<Organisation> allOrganisations = userService.getAllOrganisations(request);
            String returnString = jsonb.toJson(allOrganisations);
            return Response.ok(returnString).build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when fetching all Organisations. ]", ex);
            throw ex;
        }
    }

    @GET
    @Path("/findOrganisations")
    public Response findOrganisations(@QueryParam("nationIsoName") String nationIsoName) throws UserServiceException {
        try {
            List<Organisation> organisations = userService.findOrganisations(nationIsoName);
            String returnString = jsonb.toJson(organisations);
            return Response.ok(returnString).build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when finding Organisations. ]", ex);
            throw ex;
        }
    }

    @GET
    @Path("/getContactDetails")
    public Response getContactDetails() throws UserServiceException {
        try {
            ContactDetails contactDetails = userService.getContactDetails(httpServletRequest.getRemoteUser());
            String returnString = jsonb.toJson(contactDetails);
            return Response.ok(returnString).build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when fetching ContactDetails. ]", ex);
            throw ex;
        }
    }

    @PUT
    @Path("/putPreference")
    public Response putPreference(UserPreference userPreference) throws UserServiceException {
        try {
            userService.putPreference(userPreference);
            return Response.ok().build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when updating Preferences. ]", ex);
            throw ex;
        }
    }

    @POST
    @Path("/createDataset")
    public Response createDataset(DatasetExtension dataset) throws UserServiceException {
        try {
            userService.createDataset(dataset);
            return Response.ok().build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when creating DataSet. ]", ex);
            throw ex;
        }
    }

    @DELETE
    @Path("/deleteDataset")
    public Response deleteDataset(@QueryParam("name") String name,
                                  @QueryParam("applicationName") String applicationName) throws UserServiceException {

        DatasetExtension datasetExtension = createDatasetExtension(name, applicationName);

        try {
            userService.deleteDataset(datasetExtension);
            return Response.ok().build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when deleting DataSet. ]", ex);
            throw ex;
        }
    }

    @PUT
    @Path("/updateDataset")
    public Response updateDataset(DatasetExtension dataset) throws UserServiceException {
        try {
            userService.updateDataset(dataset);
            return Response.ok().build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when updating DataSet. ]", ex);
            throw ex;
        }
    }

    @POST
    @Path("/findDataset")
    public Response findDataset(DatasetFilter datasetFilter) throws UserServiceException {
        try {
            DatasetList datasetList = userService.findDataset(datasetFilter);
            String returnString = jsonb.toJson(datasetList);
            return Response.ok(returnString).build();
        } catch (UserServiceException ex) {
            LOG.error("[ Error when finding DataSet. ]", ex);
            throw ex;
        }
    }

    private DatasetExtension createDatasetExtension(String name, String applicationName) {
        DatasetExtension datasetExtension = new DatasetExtension();
        datasetExtension.setName(name);
        datasetExtension.setApplicationName(applicationName);
        datasetExtension.setCategory("");
        datasetExtension.setDiscriminator("");
        datasetExtension.setDescription("");
        return datasetExtension;
    }

}
