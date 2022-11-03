package modules.orgManager.AbstractLastChanged;

import org.skyve.domain.types.DateTime;
import org.skyve.metadata.model.document.Bizlet;

import modules.orgManager.domain.AbstractLastChanged;



public abstract class AbstractLastChangedBizlet<T extends AbstractLastChanged> extends Bizlet<T>{

	@Override
	public void preSave(T bean) throws Exception {
		bean.setLastChanged(new DateTime());
		super.preSave(bean);
	}
	
	

}
