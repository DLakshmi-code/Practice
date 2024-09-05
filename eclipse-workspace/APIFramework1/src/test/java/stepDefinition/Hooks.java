package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinition sd = new StepDefinition();
		if(StepDefinition.placeId ==null)
		{
			sd.add_place_payload("beforeName","beforeLan","beforeAdd");
			sd.user_calls_addplace_api_with_post_https_request("AddPlaceApi","POST");
			sd.verify_place_id_created_maps_to_using("beforeName","getPlaceApi");
		}
	}
}
