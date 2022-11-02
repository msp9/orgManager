package modules.orgManager.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.Staff.Status;
import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.domain.ChildBean;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Timestamp;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.TimestampMapper;

/**
 * StaffStatusHistory
 * 
 * @depend - - - Status
 * @stereotype "persistent child"
 */
@XmlType
@XmlRootElement
public class StaffStatusHistory extends AbstractPersistentBean implements ChildBean<StaffExtension> {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	public static final String DOCUMENT_NAME = "StaffStatusHistory";

	/** @hidden */
	public static final String statusHistoryPropertyName = "statusHistory";

	/** @hidden */
	public static final String statusTimestampPropertyName = "statusTimestamp";

	/**
	 * Staff Status
	 **/
	private Status statusHistory;

	/**
	 * Status Timestamp
	 **/
	private Timestamp statusTimestamp;

	private StaffExtension parent;

	private Integer bizOrdinal;

	@Override
	@XmlTransient
	public String getBizModule() {
		return StaffStatusHistory.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return StaffStatusHistory.DOCUMENT_NAME;
	}

	public static StaffStatusHistory newInstance() {
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
			return org.skyve.util.Binder.formatMessage("StaffStatusHistory", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof StaffStatusHistory) && 
					this.getBizId().equals(((StaffStatusHistory) o).getBizId()));
	}

	/**
	 * {@link #statusHistory} accessor.
	 * @return	The value.
	 **/
	public Status getStatusHistory() {
		return statusHistory;
	}

	/**
	 * {@link #statusHistory} mutator.
	 * @param statusHistory	The new value.
	 **/
	@XmlElement
	public void setStatusHistory(Status statusHistory) {
		preset(statusHistoryPropertyName, statusHistory);
		this.statusHistory = statusHistory;
	}

	/**
	 * {@link #statusTimestamp} accessor.
	 * @return	The value.
	 **/
	public Timestamp getStatusTimestamp() {
		return statusTimestamp;
	}

	/**
	 * {@link #statusTimestamp} mutator.
	 * @param statusTimestamp	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimestampMapper.class)
	public void setStatusTimestamp(Timestamp statusTimestamp) {
		preset(statusTimestampPropertyName, statusTimestamp);
		this.statusTimestamp = statusTimestamp;
	}

	@Override
	public StaffExtension getParent() {
		return parent;
	}

	@Override
	@XmlElement
	public void setParent(StaffExtension parent) {
		if (this.parent != parent) {
			preset(ChildBean.PARENT_NAME, parent);
			this.parent = parent;
		}
	}

	@Override
	public Integer getBizOrdinal() {
		return bizOrdinal;
	}

	@Override
	@XmlElement
	public void setBizOrdinal(Integer bizOrdinal) {
		preset(Bean.ORDINAL_NAME, bizOrdinal);
		this.bizOrdinal =  bizOrdinal;
	}
}
