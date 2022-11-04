package modules.orgManager.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class SearchTest extends AbstractDomainTest<Search> {

	@Override
	protected Search getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Search.MODULE_NAME, Search.DOCUMENT_NAME);
	}
}