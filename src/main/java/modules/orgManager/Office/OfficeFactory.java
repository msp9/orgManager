package modules.orgManager.Office;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.DataMap;
import org.skyve.util.test.SkyveFactory;
import org.skyve.util.test.SkyveFixture;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.orgManager.domain.Office;

@SkyveFactory(value = { @DataMap(attributeName = Office.buildingNamePropertyName, fileName = "office.txt"),
		@DataMap(attributeName = Office.phonePropertyName, fileName = "phoneNum.txt")

})
public class OfficeFactory {
	@SkyveFixture(types = FixtureType.crud)
	public static Office crudInstance() throws Exception {
		Office office = new DataBuilder().build(Office.MODULE_NAME, Office.DOCUMENT_NAME);
		return office;
	}
}
