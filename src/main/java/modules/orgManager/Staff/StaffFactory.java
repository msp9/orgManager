package modules.orgManager.Staff;

import java.util.concurrent.ThreadLocalRandom;

import org.skyve.domain.types.DateOnly;
import org.skyve.impl.util.TimeUtil;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.DataMap;
import org.skyve.util.test.SkyveFactory;
import org.skyve.util.test.SkyveFixture;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.admin.User.UserExtension;
import modules.admin.domain.Contact;
import modules.admin.domain.Group;
import modules.admin.domain.User;
import modules.orgManager.domain.Staff;

@SkyveFactory(value = { @DataMap(attributeName = Staff.namePropertyName, fileName = "personName.txt")

})
public class StaffFactory {
	@SkyveFixture(types = FixtureType.crud)
	public static Staff crudInstance() throws Exception {
		Staff staff = new DataBuilder().build(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);

		
		int min = 18;  
		int max = 50;  
		//Generate random double value from 18 to 50   
		System.out.println("Random value of type int between "+min+" to "+max+ ":");  
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		DateOnly date = new DateOnly();
		DateOnly date1 = TimeUtil.addYearsToNew(date, -randomNum);
		
		staff.setDateOfBirth(date1);
		

		return staff;
	}
}
