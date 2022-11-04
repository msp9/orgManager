package modules.orgManager.Search;

import java.util.ArrayList;
import java.util.List;

import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.metadata.SortDirection;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.metadata.model.document.SingletonCachedBizlet;
import org.skyve.persistence.DocumentQuery;
import org.skyve.util.Binder;
import org.skyve.web.WebContext;

import modules.orgManager.domain.Office;
import modules.orgManager.domain.Search;

public class SearchBizlet extends SingletonCachedBizlet<Search> {

	@Override
	public List<DomainValue> getVariantDomainValues(String attributeName) throws Exception {
		List<DomainValue> suburbList = new ArrayList<DomainValue>();
		// TODO Auto-generated method stub
		if (attributeName.equals(Search.suburbPropertyName)) {
			DocumentQuery query = CORE.getPersistence().newDocumentQuery(Office.MODULE_NAME, Office.DOCUMENT_NAME);
			query.addBoundProjection(Office.suburbPropertyName);
			query.setDistinct(true);
			query.addBoundOrdering(Office.suburbPropertyName, SortDirection.ascending);
			query.getFilter().addNotNull(Office.suburbPropertyName);
			List<Bean> suburbs = query.projectedResults();
			for (Bean suburb : suburbs) {
				suburbList.add(new DomainValue((String) Binder.get(suburb, Office.suburbPropertyName)));
			}

		}
		return suburbList;
	}

	@Override
	public void preRerender(String source, Search bean, WebContext webContext) throws Exception {
		CORE.getPersistence().upsertBeanTuple(bean);
		super.preRerender(source, bean, webContext);
	}
	
	

}
