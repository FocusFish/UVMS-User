/*
 * Developed by the European Commission - Directorate General for Maritime
 * Affairs and Fisheries © European Union, 2015-2016.
 *
 * This file is part of the Integrated Fisheries Data Management (IFDM) Suite.
 * The IFDM Suite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 * The IFDM Suite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details. You should have received a copy of the GNU General Public
 * License along with the IFDM Suite. If not, see http://www.gnu.org/licenses/.
 */
package fish.focus.uvms.user.rest.service;

import fish.focus.uvms.commons.date.JsonBConfigurator;
import fish.focus.uvms.user.service.UserService;
import fish.focus.uvms.user.service.exception.UserServiceException;
import fish.focus.wsdl.user.types.UserContext;
import fish.focus.wsdl.user.types.UserContextId;
import fish.focus.wsdl.user.types.UserPreference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Stateless
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
public class InformationResource {
    private static final Logger LOG = LoggerFactory.getLogger(InformationResource.class);

    @EJB
    private UserService userService;

    private Jsonb jsonb;

    @PostConstruct
    public void init() {
        jsonb = new JsonBConfigurator().getContext(null);
    }

    @GET
    @Path("/userContext")
    public Response getUserContext(@QueryParam(value = "applicationName") final String applicationName,
                                   @QueryParam(value = "userName") final String userName) throws UserServiceException {

        LOG.info("getUserContext invoked in rest layer");
        try {
            UserContextId userContextId = new UserContextId();
            userContextId.setApplicationName(applicationName);
            userContextId.setUserName(userName);
            UserContext userContext = userService.getUserContext(userContextId);
            String returnString = jsonb.toJson(userContext);
            return Response.ok(returnString).type(MediaType.APPLICATION_JSON).build();
        } catch (UserServiceException | NullPointerException ex) {
            LOG.error("[ Error when getUserContext. ]", ex);
            throw ex;
        }
    }

    @POST
    @Path("/preference")
    public Response createPreference(UserPreference userPreference) throws UserServiceException {
        LOG.info("createPreference invoked in rest layer");
        try {
            userService.createPreference(userPreference);
            return Response.ok().type(MediaType.APPLICATION_JSON).build();
        } catch (UserServiceException | NullPointerException ex) {
            LOG.error("[ Error when createPreference. ]", ex);
            throw ex;
        }
    }

    @PUT
    @Path("/preference")
    public Response updatePreference(UserPreference userPreference) throws UserServiceException {
        LOG.info("updatePreference invoked in rest layer");
        try {
            userService.updatePreference(userPreference);
            return Response.ok().type(MediaType.APPLICATION_JSON).build();
        } catch (UserServiceException | NullPointerException ex) {
            LOG.error("[ Error when updatePreference. ]", ex);
            throw ex;
        }
    }

    @DELETE
    @Path("/preference")
    public Response deletePreference(UserPreference userPreference) throws UserServiceException {
        LOG.info("deployApplication invoked in rest layer");
        try {
            userService.deletePreference(userPreference);
            return Response.ok().type(MediaType.APPLICATION_JSON).build();
        } catch (UserServiceException | NullPointerException ex) {
            LOG.error("[ Error when deletePreference. ]", ex);
            throw ex;
        }
    }
}
