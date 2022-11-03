package modules.orgManager.Office;

import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.model.document.Bizlet;

import modules.orgManager.domain.Office;

public class OfficeBizlet extends Bizlet<OfficeExtension> {

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
	
	

}
