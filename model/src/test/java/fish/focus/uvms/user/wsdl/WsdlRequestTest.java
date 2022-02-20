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
package fish.focus.uvms.user.wsdl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import fish.focus.uvms.user.model.exception.ModelMarshallException;
import fish.focus.uvms.user.model.mapper.UserModuleRequestMapper;
import fish.focus.wsdl.user.types.Application;
import fish.focus.wsdl.user.types.Context;
import fish.focus.wsdl.user.types.ContextSet;
import fish.focus.wsdl.user.types.Dataset;
import fish.focus.wsdl.user.types.DatasetExtension;
import fish.focus.wsdl.user.types.DatasetFilter;
import fish.focus.wsdl.user.types.Feature;
import fish.focus.wsdl.user.types.Option;
import fish.focus.wsdl.user.types.Preference;
import fish.focus.wsdl.user.types.Preferences;
import fish.focus.wsdl.user.types.Role;
import fish.focus.wsdl.user.types.Scope;
import fish.focus.wsdl.user.types.UserContext;
import fish.focus.wsdl.user.types.UserContextId;
import fish.focus.wsdl.user.types.UserPreference;

public class WsdlRequestTest {

    public WsdlRequestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void generateGetUserContextRequest() throws ModelMarshallException {
        UserContextId userContextId = new UserContextId();
        userContextId.setApplicationName("Quota");
        userContextId.setUserName("user_module_test");
        String getContactDetailsRequest = UserModuleRequestMapper.mapToGetUserContextRequest(userContextId);
        
        System.out.println("======");
        System.out.println(getContactDetailsRequest);
        System.out.println("======");
    }

    @Test
    public void generatePutUserPreferencesRequest() throws ModelMarshallException {
        UserContext userContext = new UserContext();
        userContext.setApplicationName("Quota");
        userContext.setUserName("user_module_test");
        
        ContextSet contextSet = new ContextSet();
        Context context = new Context();
        
        Role role = new Role();
        role.setRoleName("MARE Quota Manager");
        Feature feature = new Feature();
        feature.setName("test feature name");
        feature.setDescription("test feature description");
        feature.setGroup("test feature group");
        role.getFeature().add(feature);
        context.setRole(role);
        
        Scope scope = new Scope();
        scope.setScopeName("DG_MARE");
        Dataset dataset = new Dataset();
        dataset.setName("test dataset");
        dataset.setDescription("test dataset  description");
        dataset.setCategory("test dataset category");
        dataset.setDiscriminator("test dataset discriminator");        
        scope.getDataset().add(dataset);
        context.setScope(scope);
        
        Preferences preferences = new Preferences();
        Preference preference = new Preference();
        preference.setApplicationName("Quota");
        preference.setOptionName("userLocale");
        preference.setOptionValue("test option value");
        preferences.getPreference().add(preference);
        
        context.setPreferences(preferences);
        
        contextSet.getContexts().add(context);
        
        userContext.setContextSet(contextSet);
        
        String putUserPreferencesRequest = UserModuleRequestMapper.mapToPutUserPreferencesRequest(userContext);
        
        System.out.println("======");
        System.out.println(putUserPreferencesRequest);
        System.out.println("======");
    }    
    
    
    
    @Test
    public void generateGetApplicationRequest() throws ModelMarshallException {
        String applicationName = "APPLICATION_NAME";
        String getApplicationRequest = UserModuleRequestMapper.mapToGetApplicationRequest(applicationName);
        
        System.out.println("======");
        System.out.println(getApplicationRequest);
        System.out.println("======");
    }    
    
    
    @Test
    public void generateDeployApplicationRequest() throws ModelMarshallException {
        
        Application application = new Application();
        application.setName("APPLICATION_NAME");
        application.setDescription("APPLICATION_DESCRIPTION");
        application.setParent("USM");
        
        Dataset dataset = new Dataset();
        dataset.setName("DATASET NAME");
        dataset.setDescription("DATASET DESCRIPTION");
        dataset.setCategory("DATASET CATEGORY");
        dataset.setDiscriminator("DATASSET DISCRIMINATOR");
        application.getDataset().add(dataset);
        
        Feature feature = new Feature();
        feature.setName("FEATURE NAME");
        feature.setDescription("FEATURE DESCRIPTION");
        feature.setGroup("FEATURE GROUP");
        application.getFeature().add(feature);

        Option option = new Option();
        option.setName("OPTION NAME");
        option.setDescription("OPTION DESCRIPTION");
        option.setDataType("String");
        option.setDefaultValue("OPTION VALUE");
        option.setGroup("OPTION GROUP");        
        application.getOption().add(option);
        
        
        String deployApplicationRequest = UserModuleRequestMapper.mapToDeployApplicationRequest(application);
        
        System.out.println("======");
        System.out.println(deployApplicationRequest);
        System.out.println("======");
    }
    
    @Test
    public void generateRedeployApplicationRequest() throws ModelMarshallException {
        
        Application application = new Application();
        application.setName("APPLICATION_NAME");
        application.setDescription("APPLICATION_DESCRIPTION UPDATED");
        application.setParent("USM");
        
        Dataset dataset = new Dataset();
        dataset.setName("DATASET NAME UPDATED");
        dataset.setDescription("DATASET DESCRIPTION");
        dataset.setCategory("DATASET CATEGORY");
        dataset.setDiscriminator("DATASSET DISCRIMINATOR");
        application.getDataset().add(dataset);
        
        Feature feature = new Feature();
        feature.setName("FEATURE NAME UPDATED");
        feature.setDescription("FEATURE DESCRIPTION");
        feature.setGroup("FEATURE GROUP");
        application.getFeature().add(feature);

        Option option = new Option();
        option.setName("OPTION NAME UPDATED");
        option.setDescription("OPTION DESCRIPTION");
        option.setDataType("String");
        option.setDefaultValue("OPTION VALUE");
        option.setGroup("OPTION GROUP");        
        application.getOption().add(option);
        
        
        String deployApplicationRequest = UserModuleRequestMapper.mapToRedeployApplicationRequest(application);
        
        System.out.println("======");
        System.out.println(deployApplicationRequest);
        System.out.println("======");
    }    
    
    @Test
    public void generateUndeployApplicationRequest() throws ModelMarshallException {
        String applicationName = "APPLICATION_NAME";
        String undeployApplicationRequest = UserModuleRequestMapper.mapToUndeployApplicationRequest(applicationName);
        
        System.out.println("======");
        System.out.println(undeployApplicationRequest);
        System.out.println("======");
    }     
    
    
     @Test
     public void generateGetContactDetailsRequest() throws ModelMarshallException {
         String userName = "quota_usr_fra";
         String getContactDetailsRequest = UserModuleRequestMapper.mapToGetContactDetailsRequest(userName);
         
         System.out.println("======");
         System.out.println(getContactDetailsRequest);
         System.out.println("======");
     }
     
     @Test
    public void generateGetOrganisationRequest() throws ModelMarshallException {
        String organizationName = "FRA";
        String getOrganisationRequest = UserModuleRequestMapper.mapToGetOrganisationRequest(organizationName);

        System.out.println("======");
        System.out.println(getOrganisationRequest);
        System.out.println("======");
    }

    @Test
    public void generateGetAllOrganisationRequest() throws ModelMarshallException {
        String REQUESTOR = "rep_power";

        String ROLE_NAME = "rep_power_role";

        String SCOPE_NAME = "DG_MARE";

        String getAllOrganisationRequest = UserModuleRequestMapper.mapToGetAllOrganisationRequest(SCOPE_NAME,ROLE_NAME,REQUESTOR);

        System.out.println("======");
        System.out.println(getAllOrganisationRequest);
        System.out.println("======");
    }
     
     @Test
     public void generateCreateUserPreference() throws ModelMarshallException {
    	 UserPreference userPreference = new UserPreference();
    		userPreference.setApplicationName("Quota");
    		userPreference.setRoleName("Super User");
    		userPreference.setScopeName(null);
    		userPreference.setUserName("vms_super_com");
    		userPreference.setOptionName("userLocale");
    		userPreference.setOptionValue("optionValue".getBytes());
    		
    		String createUserPreferenceRequest = UserModuleRequestMapper.mapToCreateUserPreferenceRequest(userPreference);
    		
    		System.out.println("======");
            System.out.println(createUserPreferenceRequest);
            System.out.println("======");
     }
     
     
     @Test
     public void generateUpdateUserPreference() throws ModelMarshallException {
    	 UserPreference userPreference = new UserPreference();
    	 userPreference.setApplicationName("Quota");
    		userPreference.setRoleName("Super User");
    		userPreference.setScopeName("");
    		userPreference.setUserName("vms_super_com");
    		userPreference.setOptionName("userLocale");
    		userPreference.setOptionValue("optionValue2".getBytes());
    		
    		String updateUserPreferenceRequest = UserModuleRequestMapper.mapToUpdateUserPreferenceRequest(userPreference);
    		
    		System.out.println("======");
            System.out.println(updateUserPreferenceRequest);
            System.out.println("======");
     }
     
     @Test
     public void generateDeleteUserPreference() throws ModelMarshallException {
    	 UserPreference userPreference = new UserPreference();
    	 userPreference.setApplicationName("Quota");
    		userPreference.setRoleName("Super User");
    		userPreference.setScopeName("");
    		userPreference.setUserName("vms_super_com");
    		userPreference.setOptionName("userLocale");
    		userPreference.setOptionValue("optionValue2".getBytes());
    		
    		String deleteUserPreferenceRequest = UserModuleRequestMapper.mapToDeleteUserPreferenceRequest(userPreference);
    		
    		System.out.println("======");
            System.out.println(deleteUserPreferenceRequest);
            System.out.println("======");
     }
     
   @Test  
     public void generateCreateDataset() throws ModelMarshallException{
	   DatasetExtension dataset=new DatasetExtension();
	   dataset.setApplicationName("Quota");
	   dataset.setName("test_dataset");
	   dataset.setCategory("category");
	   dataset.setDescription("description");
	   dataset.setDiscriminator("discriminator");
	   
	   String createDatasetRequest = UserModuleRequestMapper.mapToCreateDatasetRequest(dataset);
		
		System.out.println("======");
       System.out.println(createDatasetRequest);
       System.out.println("======");
   }
     
   @Test  
   public void generateUpdateDataset() throws ModelMarshallException{
	   DatasetExtension dataset=new DatasetExtension();
	   dataset.setApplicationName("Quota");
	   dataset.setName("test_dataset");
	   dataset.setCategory("category");
	   dataset.setDescription("descriptionUpdated");
	   dataset.setDiscriminator("discriminatorUpdated");
	   
	   String updateDatasetRequest = UserModuleRequestMapper.mapToUpdateDatasetRequest(dataset);
		
		System.out.println("======");
		System.out.println(updateDatasetRequest);
		System.out.println("======");
 }
   
   
   @Test  
   public void generateDeleteDataset() throws ModelMarshallException{
	   DatasetExtension dataset=new DatasetExtension();
	   dataset.setApplicationName("Quota");
	   dataset.setName("test_dataset");
	   dataset.setCategory("category");
	   dataset.setDescription("description");
	   dataset.setDiscriminator("discriminator");
	   
	   String deleteDatasetRequest = UserModuleRequestMapper.mapToDeleteDatasetRequest(dataset);
		
		System.out.println("======");
		System.out.println(deleteDatasetRequest);
		System.out.println("======");
 }
   
   @Test  
   public void generateFindDataset() throws ModelMarshallException{
	   DatasetFilter datasetFilter=new DatasetFilter();
	   datasetFilter.setApplicationName("Quota");
		
	   String findDatasetRequest = UserModuleRequestMapper.mapToFindDatasetRequest(datasetFilter);
		
		System.out.println("======");
		System.out.println(findDatasetRequest);
		System.out.println("======");
   }
   
}