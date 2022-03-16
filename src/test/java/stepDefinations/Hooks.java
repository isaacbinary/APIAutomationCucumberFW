package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write a code that will give you place id
		//execute this code when place id is null
		
		StepDefination m = new StepDefination();
		
		if(StepDefination.place_id == null) { //we can use m.place_id as well but since it's static, we are calling by class name instead
		m.add_place_payload_with("Shetty", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		
		//above method is used when tags are used in test runner file 
		}
	}

}
