package modules.orgManager.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class NewDocumentTest extends AbstractDomainTest<NewDocument> {

	@Override
	protected NewDocument getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(NewDocument.MODULE_NAME, NewDocument.DOCUMENT_NAME);
	}
}