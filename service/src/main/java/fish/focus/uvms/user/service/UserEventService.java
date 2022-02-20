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
package fish.focus.uvms.user.service;

import fish.focus.uvms.user.message.event.*;
import fish.focus.uvms.user.message.event.carrier.EventMessage;
import javax.ejb.Local;
import javax.enterprise.event.Observes;

@Local
public interface UserEventService {

    public void getUserContext(@Observes @GetUserContexEvent EventMessage message);
    
    public void updateUserPreferences(@Observes @UpdateUserContexEvent EventMessage message);
    
    
    public void deployApplication(@Observes @DeployApplicationEvent EventMessage message);
    
    public void redeployApplication(@Observes @RedeployApplicationEvent EventMessage message);
    
    public void undeployApplication(@Observes @UndeployApplicationEvent EventMessage message);
    
    public void getApplication(@Observes @GetApplicationEvent EventMessage message);
    
    public void getOrganisation(@Observes @GetOrganizationEvent EventMessage message);

    public void getAllOrganisation(@Observes @GetAllOrganizationEvent EventMessage message);

    public void findOrganisations(@Observes @FindOrganizationsEvent EventMessage message);
    
    public void getContactDetails(@Observes @GetContactDetailsEvent EventMessage message);

	public void ping(@Observes @PingEvent EventMessage message);
	
	public void putPreference(@Observes @PutPreferenceEvent EventMessage message);
	
	public void createPreference(@Observes @CreatePreferenceEvent EventMessage message);
	
	public void deletePreference(@Observes @DeletePreferenceEvent EventMessage message);
	
	public void updatePreference(@Observes @UpdatePreferenceEvent EventMessage message);

	public void createDataset(@Observes @CreateDatasetEvent EventMessage message);
	
	public void deleteDataset(@Observes @DeleteDatasetEvent EventMessage message);
	
	public void updateDataset(@Observes @UpdateDatasetEvent EventMessage message);

	public void findDatasets(@Observes @FindDatasetsEvent EventMessage message);
}