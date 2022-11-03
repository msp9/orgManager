package orgManager.Office;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.skyve.CORE;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.orgManager.Office.OfficeExtension;
import modules.orgManager.domain.Office;
import util.AbstractH2TestForJUnit5;

public class OfficeTest extends AbstractH2TestForJUnit5 {

	private DataBuilder db;
	private OfficeExtension officeBean;

	@BeforeEach
	public void setup() throws Exception {
		this.db = new DataBuilder().fixture(FixtureType.crud);
		this.officeBean = this.db.build(Office.MODULE_NAME, Office.DOCUMENT_NAME);
	}

	@Test
	public void testOfficeWithNoStaff() {
		this.officeBean = CORE.getPersistence().save(officeBean);
		

		assertThat(officeBean.isPersisted(), is(true));
		this.officeBean.calculateTotalStaffs();
		assertThat(this.officeBean.getTotalNoOfStaffInOffice(), is(Integer.valueOf(0)));

	}

}
