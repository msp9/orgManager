package modules.orgManager.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.orgManager.Office.OfficeExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Search
 * 
 * @navhas n listOffice 0..1 Office
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Search extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Search";

	/** @hidden */
	public static final String suburbPropertyName = "suburb";

	/** @hidden */
	public static final String listOfficePropertyName = "listOffice";

	/**
	 * Suburb
	 **/
	private String suburb;

	/**
	 * Offices
	 **/
	private OfficeExtension listOffice = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Search.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Search.DOCUMENT_NAME;
	}

	public static Search newInstance() {
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
			return org.skyve.util.Binder.formatMessage("Search", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Search) && 
					this.getBizId().equals(((Search) o).getBizId()));
	}

	/**
	 * {@link #suburb} accessor.
	 * @return	The value.
	 **/
	public String getSuburb() {
		return suburb;
	}

	/**
	 * {@link #suburb} mutator.
	 * @param suburb	The new value.
	 **/
	@XmlElement
	public void setSuburb(String suburb) {
		preset(suburbPropertyName, suburb);
		this.suburb = suburb;
	}

	/**
	 * {@link #listOffice} accessor.
	 * @return	The value.
	 **/
	public OfficeExtension getListOffice() {
		return listOffice;
	}

	/**
	 * {@link #listOffice} mutator.
	 * @param listOffice	The new value.
	 **/
	@XmlElement
	public void setListOffice(OfficeExtension listOffice) {
		if (this.listOffice != listOffice) {
			preset(listOfficePropertyName, listOffice);
			this.listOffice = listOffice;
		}
	}
}
