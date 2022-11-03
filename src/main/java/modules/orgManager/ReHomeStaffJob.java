package modules.orgManager;

import java.util.Date;
import java.util.List;

import org.skyve.CORE;
import org.skyve.EXT;
import org.skyve.domain.messages.MessageSeverity;
import org.skyve.domain.types.converters.datetime.DD_MMM_YYYY_HH24_MI;
import org.skyve.impl.metadata.model.document.field.DateTime;
import org.skyve.job.Job;
import org.skyve.persistence.DocumentQuery;
import org.skyve.util.Binder;
import org.skyve.util.PushMessage;

import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.Staff.Status;

public class ReHomeStaffJob extends Job {

	@Override
	public void execute() throws Exception {
		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
		q.getFilter().addEquals(Staff.statusPropertyName, Status.in);
		List<StaffExtension> staffs = q.beanResults();
		DD_MMM_YYYY_HH24_MI converter = new DD_MMM_YYYY_HH24_MI();
		int total = staffs.size();
		int counter=0;
		List<String> log = getLog();
		for(StaffExtension s: staffs) {
			s.home();
			counter++;
			setPercentComplete(counter, total);
			log.add("Rehomed staff"+s.getName()+" at "+new DateTime());
			//log.add(Binder.formatMessage("Cleaning up {code} {name} at ", s))+ converter.toDisplayValue(new org.skyve.domain.types.DateTime());
		}
		
		log.add("Finished ReHoming Staffs at " + new Date() + ". " + counter);
		EXT.push(new PushMessage().user(CORE.getUser()).growl(MessageSeverity.info, "Job completed successfully"));

	}

}
