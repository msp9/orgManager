package au.sa.gov;

import java.util.List;

import modules.orgManager.domain.SuburbResult;

public interface SuburbService {
	
	public List<SuburbResult> getSuburbsByPostCode(String postcode);

}
