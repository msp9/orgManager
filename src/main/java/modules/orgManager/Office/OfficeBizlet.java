package modules.orgManager.Office;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.model.document.Bizlet;

import au.sa.gov.SuburbService;
import modules.orgManager.AbstractLastChanged.AbstractLastChangedBizlet;
import modules.orgManager.domain.AbstractLastChanged;
import modules.orgManager.domain.Office;
import modules.orgManager.domain.SuburbResult;

public class OfficeBizlet extends AbstractLastChangedBizlet<OfficeExtension> {
	
	@Inject
	private transient SuburbService service;

	@Override
	public void validate(OfficeExtension bean, ValidationException e) throws Exception {
		// TODO Auto-generated method stub
		if (bean.getBuildingName() == null && bean.getStreetAddress1() == null) {
			e.getMessages().add(
					new Message(Office.buildingNamePropertyName, "You must supply atleast building name or ddress"));
		}
		super.validate(bean, e);
	}



	@Override
	public void postLoad(OfficeExtension bean) throws Exception {
		bean.calculateTotalStaffs();
		bean.calculateTotalStaffsInOffice();
		bean.calculateStaffsNeverBeenToOffice();
		super.postLoad(bean);
	}
	
	@Override
	public List<String> complete(String attributeName, String value, OfficeExtension bean) throws Exception {
		// TODO Auto-generated method stub
		
		if(bean.suburbPropertyName.equals(attributeName)) {
			if(bean.getPostcode() != null) {
				List<SuburbResult> results = service.getSuburbsByPostCode(bean.getPostcode().toString());
				return results.stream().map(n->n.getName()).collect(Collectors.toList());
			}
		}
		return super.complete(attributeName, value, bean);
	}

}
