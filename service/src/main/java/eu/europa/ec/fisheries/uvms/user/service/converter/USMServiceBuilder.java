package eu.europa.ec.fisheries.uvms.user.service.converter;

import eu.europa.ec.fisheries.wsdl.user.module.GetAllOrganisationRequest;
import eu.europa.ec.mare.usm.administration.domain.FindOrganisationsQuery;
import eu.europa.ec.mare.usm.administration.domain.Paginator;
import eu.europa.ec.mare.usm.administration.domain.ServiceRequest;

public final class USMServiceBuilder {

    private static final int OFFSET = 0;
    private static final int LIMIT = 100;
    private static final String SORT_BY_COLUMN = "name";
    private static final String ORDER_BY_DIRECTION = "DESC";

    public static ServiceRequest<FindOrganisationsQuery> buildAdministrationServiceRequestForAll(GetAllOrganisationRequest getAllOrganisationRequest) {
        Paginator paginator = new Paginator();
        paginator.setOffset(OFFSET);
        paginator.setLimit(LIMIT);
        paginator.setSortColumn(SORT_BY_COLUMN);
        paginator.setSortDirection(ORDER_BY_DIRECTION);
        ServiceRequest<FindOrganisationsQuery> request = new ServiceRequest<>();
        request.setRequester(getAllOrganisationRequest.getRequestorName());
        request.setRoleName(getAllOrganisationRequest.getRoleName());
        request.setScopeName(getAllOrganisationRequest.getScopeName());
        FindOrganisationsQuery findOrganisationsQuery = new FindOrganisationsQuery();
        findOrganisationsQuery.setName(null);
        findOrganisationsQuery.setNation(null);
        findOrganisationsQuery.setStatus(null);
        findOrganisationsQuery.setPaginator(paginator);
        request.setBody(findOrganisationsQuery);
        return request;
    }

    public static ServiceRequest<Long> buildAdministrationServiceRequestForId(Long organisationId, GetAllOrganisationRequest getAllOrganisationRequest) {
        ServiceRequest<Long> request = new ServiceRequest<>();
        request.setRequester(getAllOrganisationRequest.getRequestorName());
        request.setRoleName(getAllOrganisationRequest.getRoleName());
        request.setScopeName(getAllOrganisationRequest.getScopeName());
        request.setBody(organisationId);
        return request;
    }
}
