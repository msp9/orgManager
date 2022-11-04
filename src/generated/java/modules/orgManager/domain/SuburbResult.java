package modules.orgManager.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;

/**
 * SuburbResult
 * 
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class SuburbResult extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	public static final String DOCUMENT_NAME = "SuburbResult";

	/** @hidden */
	public static final String namePropertyName = "name";

	/** @hidden */
	public static final String statePropertyName = "state";

	/** @hidden */
	public static final String stateAbbreviationPropertyName = "stateAbbreviation";

	/**
	 * Name
	 **/
	private String name;

	/**
	 * State
	 **/
	private String state;

	/**
	 * State Abbreviation
	 **/
	private String stateAbbreviation;

	@Override
	@XmlTransient
	public String getBizModule() {
		return SuburbResult.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return SuburbResult.DOCUMENT_NAME;
	}

	public static SuburbResult newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage("SuburbResult", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof SuburbResult) && 
					this.getBizId().equals(((SuburbResult) o).getBizId()));
	}

	/**
	 * {@link #name} accessor.
	 * @return	The value.
	 **/
	public String getName() {
		return name;
	}

	/**
	 * {@link #name} mutator.
	 * @param name	The new value.
	 **/
	@XmlElement
	public void setName(String name) {
		preset(namePropertyName, name);
		this.name = name;
	}

	/**
	 * {@link #state} accessor.
	 * @return	The value.
	 **/
	public String getState() {
		return state;
	}

	/**
	 * {@link #state} mutator.
	 * @param state	The new value.
	 **/
	@XmlElement
	public void setState(String state) {
		preset(statePropertyName, state);
		this.state = state;
	}

	/**
	 * {@link #stateAbbreviation} accessor.
	 * @return	The value.
	 **/
	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	/**
	 * {@link #stateAbbreviation} mutator.
	 * @param stateAbbreviation	The new value.
	 **/
	@XmlElement
	public void setStateAbbreviation(String stateAbbreviation) {
		preset(stateAbbreviationPropertyName, stateAbbreviation);
		this.stateAbbreviation = stateAbbreviation;
	}
}
