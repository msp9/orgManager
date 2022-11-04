package au.sa.gov.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.skyve.domain.messages.DomainException;



import au.sa.gov.SuburbService;
import modules.orgManager.domain.SuburbResult;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class SuburbServiceImpl implements SuburbService {

	@Override
	public List<SuburbResult> getSuburbsByPostCode(String postcode)  {
		List<SuburbResult> suburbResults = new ArrayList<SuburbResult>();
		if (postcode == null) {
			throw new DomainException("Postcode is null");
		}
		
		try {
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI("http://api.zippopotam.us/au/"+postcode))
					  .GET()
					  .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			
			
			if(response != null) {
				String json = response.body();
				
				JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
				JSONObject body = (JSONObject) parser.parse(json);
				JSONArray places =  (JSONArray)body.get("places");
				for (Object i: places) {
					JSONObject place = (JSONObject) i;
					SuburbResult result = new SuburbResult();
					result.setName(place.getAsString("place name"));
					result.setState(place.getAsString("state"));
					result.setStateAbbreviation(place.getAsString("state abbreviation"));
					suburbResults.add(result);
					
				}
			}
		} catch (IOException|InterruptedException |URISyntaxException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suburbResults;

	}

}
