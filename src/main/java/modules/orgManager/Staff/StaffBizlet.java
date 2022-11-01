package modules.orgManager.Staff;

import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.domain.types.DateOnly;
import org.skyve.metadata.model.document.Bizlet;

import modules.orgManager.domain.Staff;

public class StaffBizlet extends Bizlet<Staff> {

	@Override
	public void validate(Staff bean, ValidationException e) throws Exception {
		if(bean.getDateOfBirth().after(new DateOnly())) {
			e.getMessages().add(new Message(Staff.dateOfBirthPropertyName, "You must enter a valid date of Birth"));
		}
		super.validate(bean, e);
	}
	

}
