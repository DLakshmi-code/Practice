package resources;

import pojo.body;
import pojo.location;

public class TestDataBuild {
	
	public body addPlacePayload(String name,String language,String address)
	{
		body bd = new body();
		bd.setAccuracy(50);
		bd.setAddress(address);
		bd.setPhone_number("(+91) 983 893 3937");
		bd.setName(name);
		bd.setLanguage(language);
		
		String[] typ=new String[2];
		typ[0]="shop";
		typ[1]="shop";
		bd.setTypes(typ);
		
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		bd.setLocation(loc);	
		bd.setWebsite("www.google.com");
		
		return bd;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\"place_id\":\""+placeId+"\"}";
	}

}
