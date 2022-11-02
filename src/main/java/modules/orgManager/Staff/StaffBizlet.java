package modules.orgManager.Staff;

import org.skyve.CORE;
import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Timestamp;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.orgManager.Staff.actions.SendHome;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.Staff.Status;
import modules.orgManager.domain.StaffStatusHistory;

public class StaffBizlet extends Bizlet<StaffExtension> {

	@Override
	public void validate(StaffExtension bean, ValidationException e) throws Exception {
		if (bean.getDateOfBirth().after(new DateOnly())) {
			e.getMessages().add(new Message(Staff.dateOfBirthPropertyName, "You must enter a valid date of Birth"));
		}
		super.validate(bean, e);
	}

	@Override
	public void preSave(StaffExtension bean) throws Exception {

		/*
		 * if (bean.getStatus() != null &&
		 * bean.originalValues().containsKey(Staff.statusPropertyName)) {
		 * StaffStatusHistory statusHistory = StaffStatusHistory.newInstance();
		 * statusHistory.setStatusHistory(bean.getStatus());
		 * statusHistory.setStatusTimestamp(new Timestamp());
		 * bean.addHistoriesElement(0, statusHistory); }
		 */
		super.preSave(bean);
	}

	@Override
	public StaffExtension newInstance(StaffExtension bean) throws Exception {
		if (bean.getCode() == null) {
			bean.setCode(CORE.getNumberGenerator().next("S", Staff.MODULE_NAME, Staff.DOCUMENT_NAME,
					Staff.codePropertyName, 4));
		}
		return super.newInstance(bean);
	}

	@Override
	public void preRerender(String source, StaffExtension bean, WebContext webContext) throws Exception {
		if (bean.getStatus() != null && bean.originalValues().containsKey(Staff.statusPropertyName)) {
			if(bean.getStatus().equals(Status.in)) {
				bean.home();
			}
			StaffStatusHistory statusHistory = StaffStatusHistory.newInstance();
			statusHistory.setStatusHistory(bean.getStatus());
			statusHistory.setStatusTimestamp(new Timestamp());
			bean.addHistoriesElement(0, statusHistory);
		}

		super.preRerender(source, bean, webContext);
	}
	

}
