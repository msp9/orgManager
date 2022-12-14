package modules.orgManager.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.orgManager.Office.OfficeExtension;
import modules.orgManager.Staff.StaffExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.util.Util;

/**
 * Staff
 * 
 * @depend - - - Status
 * @navhas n baseOffice 0..1 Office
 * @navcomposed 1 histories 0..n StaffStatusHistory
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public abstract class Staff extends AbstractLastChanged {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	@SuppressWarnings("hiding")
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	@SuppressWarnings("hiding")
	public static final String DOCUMENT_NAME = "Staff";

	/** @hidden */
	public static final String codePropertyName = "code";

	/** @hidden */
	public static final String namePropertyName = "name";

	/** @hidden */
	public static final String dateOfBirthPropertyName = "dateOfBirth";

	/** @hidden */
	public static final String locationPropertyName = "location";

	/** @hidden */
	public static final String imagePropertyName = "image";

	/** @hidden */
	public static final String resumeUploadPropertyName = "resumeUpload";

	/** @hidden */
	public static final String baseOfficePropertyName = "baseOffice";

	/** @hidden */
	public static final String statusPropertyName = "status";

	/** @hidden */
	public static final String ageInYearsPropertyName = "ageInYears";

	/** @hidden */
	public static final String historiesPropertyName = "histories";

	/**
	 * Status
	 **/
	@XmlEnum
	public static enum Status implements Enumeration {
		in("In", "In Office"),
		outToLunch("Out To Lunch", "Out To Lunch"),
		out("Out", "Out");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Status(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Status fromCode(String code) {
			Status result = null;

			for (Status value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Status fromLocalisedDescription(String description) {
			Status result = null;

			for (Status value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Status[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Status value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Code
	 **/
	private String code;

	/**
	 * Name
	 **/
	private String name;

	/**
	 * Date Of Birth
	 **/
	private DateOnly dateOfBirth;

	/**
	 * Location
	 **/
	private Geometry location;

	/**
	 * Image
	 **/
	private String image;

	/**
	 * Resume
	 **/
	private String resumeUpload;

	/**
	 * Office
	 **/
	private OfficeExtension baseOffice = null;

	/**
	 * Status
	 **/
	private Status status = Status.in;

	/**
	 * Age In Years
	 * <br/>
	 * How old is the person
	 **/
	private Integer ageInYears;

	/**
	 * Status History
	 **/
	private List<StaffStatusHistory> histories = new ChangeTrackingArrayList<>("histories", this);

	@Override
	@XmlTransient
	public String getBizModule() {
		return Staff.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Staff.DOCUMENT_NAME;
	}

	public static StaffExtension newInstance() {
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
			return org.skyve.util.Binder.formatMessage("Staff - code", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Staff) && 
					this.getBizId().equals(((Staff) o).getBizId()));
	}

	/**
	 * {@link #code} accessor.
	 * @return	The value.
	 **/
	public String getCode() {
		return code;
	}

	/**
	 * {@link #code} mutator.
	 * @param code	The new value.
	 **/
	@XmlElement
	public void setCode(String code) {
		preset(codePropertyName, code);
		this.code = code;
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
	 * {@link #dateOfBirth} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * {@link #dateOfBirth} mutator.
	 * @param dateOfBirth	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	public void setDateOfBirth(DateOnly dateOfBirth) {
		preset(dateOfBirthPropertyName, dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * {@link #location} accessor.
	 * @return	The value.
	 **/
	public Geometry getLocation() {
		return location;
	}

	/**
	 * {@link #location} mutator.
	 * @param location	The new value.
	 **/
	@XmlElement
	@XmlJavaTypeAdapter(GeometryMapper.class)
	public void setLocation(Geometry location) {
		preset(locationPropertyName, location);
		this.location = location;
	}

	/**
	 * {@link #image} accessor.
	 * @return	The value.
	 **/
	public String getImage() {
		return image;
	}

	/**
	 * {@link #image} mutator.
	 * @param image	The new value.
	 **/
	@XmlElement
	public void setImage(String image) {
		preset(imagePropertyName, image);
		this.image = image;
	}

	/**
	 * {@link #resumeUpload} accessor.
	 * @return	The value.
	 **/
	public String getResumeUpload() {
		return resumeUpload;
	}

	/**
	 * {@link #resumeUpload} mutator.
	 * @param resumeUpload	The new value.
	 **/
	@XmlElement
	public void setResumeUpload(String resumeUpload) {
		preset(resumeUploadPropertyName, resumeUpload);
		this.resumeUpload = resumeUpload;
	}

	/**
	 * {@link #baseOffice} accessor.
	 * @return	The value.
	 **/
	public OfficeExtension getBaseOffice() {
		return baseOffice;
	}

	/**
	 * {@link #baseOffice} mutator.
	 * @param baseOffice	The new value.
	 **/
	@XmlElement
	public void setBaseOffice(OfficeExtension baseOffice) {
		if (this.baseOffice != baseOffice) {
			preset(baseOfficePropertyName, baseOffice);
			OfficeExtension oldBaseOffice = this.baseOffice;
			this.baseOffice = baseOffice;
			if ((baseOffice != null) && (baseOffice.getAllStaffElementById(getBizId()) == null)) {
				baseOffice.getAllStaff().add((StaffExtension) this);
			}
			if (oldBaseOffice != null) {
				oldBaseOffice.getAllStaff().remove(this);
			}
		}
	}

	public void nullBaseOffice() {
		this.baseOffice = null;
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	public Status getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(Status status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #ageInYears} accessor.
	 * @return	The value.
	 **/
	public Integer getAgeInYears() {
		return ageInYears;
	}

	/**
	 * {@link #ageInYears} mutator.
	 * @param ageInYears	The new value.
	 **/
	@XmlElement
	public void setAgeInYears(Integer ageInYears) {
		this.ageInYears = ageInYears;
	}

	/**
	 * {@link #histories} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<StaffStatusHistory> getHistories() {
		return histories;
	}

	/**
	 * {@link #histories} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public StaffStatusHistory getHistoriesElementById(String bizId) {
		return getElementById(histories, bizId);
	}

	/**
	 * {@link #histories} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setHistoriesElementById(String bizId, StaffStatusHistory element) {
		setElementById(histories, element);
	}

	/**
	 * {@link #histories} add.
	 * @param element	The element to add.
	 **/
	public boolean addHistoriesElement(StaffStatusHistory element) {
		boolean result = histories.add(element);
		if (result) {
			element.setParent((StaffExtension) this);
		}
		return result;
	}

	/**
	 * {@link #histories} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addHistoriesElement(int index, StaffStatusHistory element) {
		histories.add(index, element);
		element.setParent((StaffExtension) this);
	}

	/**
	 * {@link #histories} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeHistoriesElement(StaffStatusHistory element) {
		boolean result = histories.remove(element);
		if (result) {
			element.setParent(null);
		}
		return result;
	}

	/**
	 * {@link #histories} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public StaffStatusHistory removeHistoriesElement(int index) {
		StaffStatusHistory result = histories.remove(index);
		result.setParent(null);
		return result;
	}
}
