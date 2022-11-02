package modules.orgManager.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.orgManager.Staff.StaffExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Office
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Office extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Office";

	/** @hidden */
	public static final String streetAddress1PropertyName = "streetAddress1";

	/** @hidden */
	public static final String streetAddress2PropertyName = "streetAddress2";

	/** @hidden */
	public static final String levelUnitPropertyName = "levelUnit";

	/** @hidden */
	public static final String buildingNamePropertyName = "buildingName";

	/** @hidden */
	public static final String postcodePropertyName = "postcode";

	/** @hidden */
	public static final String phonePropertyName = "phone";

	/** @hidden */
	public static final String fileInstructionsPropertyName = "fileInstructions";

	/** @hidden */
	public static final String allStaffPropertyName = "allStaff";

	/**
	 * Street Address
	 **/
	private String streetAddress1;

	/**
	 * Street Address 2
	 **/
	private String streetAddress2;

	/**
	 * Level Unit
	 **/
	private String levelUnit;

	/**
	 * Building Name
	 **/
	private String buildingName;

	/**
	 * Postcode
	 **/
	private String postcode;

	/**
	 * Phone
	 **/
	private String phone;

	/**
	 * Evacuation Instructions
	 **/
	private String fileInstructions;

	/**
	 * All Staff
	 **/
	private List<StaffExtension> allStaff = new ArrayList<>();

	@Override
	@XmlTransient
	public String getBizModule() {
		return Office.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Office.DOCUMENT_NAME;
	}

	public static Office newInstance() {
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
			return org.skyve.util.Binder.formatMessage("Office - {buildingName}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Office) && 
					this.getBizId().equals(((Office) o).getBizId()));
	}

	/**
	 * {@link #streetAddress1} accessor.
	 * @return	The value.
	 **/
	public String getStreetAddress1() {
		return streetAddress1;
	}

	/**
	 * {@link #streetAddress1} mutator.
	 * @param streetAddress1	The new value.
	 **/
	@XmlElement
	public void setStreetAddress1(String streetAddress1) {
		preset(streetAddress1PropertyName, streetAddress1);
		this.streetAddress1 = streetAddress1;
	}

	/**
	 * {@link #streetAddress2} accessor.
	 * @return	The value.
	 **/
	public String getStreetAddress2() {
		return streetAddress2;
	}

	/**
	 * {@link #streetAddress2} mutator.
	 * @param streetAddress2	The new value.
	 **/
	@XmlElement
	public void setStreetAddress2(String streetAddress2) {
		preset(streetAddress2PropertyName, streetAddress2);
		this.streetAddress2 = streetAddress2;
	}

	/**
	 * {@link #levelUnit} accessor.
	 * @return	The value.
	 **/
	public String getLevelUnit() {
		return levelUnit;
	}

	/**
	 * {@link #levelUnit} mutator.
	 * @param levelUnit	The new value.
	 **/
	@XmlElement
	public void setLevelUnit(String levelUnit) {
		preset(levelUnitPropertyName, levelUnit);
		this.levelUnit = levelUnit;
	}

	/**
	 * {@link #buildingName} accessor.
	 * @return	The value.
	 **/
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * {@link #buildingName} mutator.
	 * @param buildingName	The new value.
	 **/
	@XmlElement
	public void setBuildingName(String buildingName) {
		preset(buildingNamePropertyName, buildingName);
		this.buildingName = buildingName;
	}

	/**
	 * {@link #postcode} accessor.
	 * @return	The value.
	 **/
	public String getPostcode() {
		return postcode;
	}

	/**
	 * {@link #postcode} mutator.
	 * @param postcode	The new value.
	 **/
	@XmlElement
	public void setPostcode(String postcode) {
		preset(postcodePropertyName, postcode);
		this.postcode = postcode;
	}

	/**
	 * {@link #phone} accessor.
	 * @return	The value.
	 **/
	public String getPhone() {
		return phone;
	}

	/**
	 * {@link #phone} mutator.
	 * @param phone	The new value.
	 **/
	@XmlElement
	public void setPhone(String phone) {
		preset(phonePropertyName, phone);
		this.phone = phone;
	}

	/**
	 * {@link #fileInstructions} accessor.
	 * @return	The value.
	 **/
	public String getFileInstructions() {
		return fileInstructions;
	}

	/**
	 * {@link #fileInstructions} mutator.
	 * @param fileInstructions	The new value.
	 **/
	@XmlElement
	public void setFileInstructions(String fileInstructions) {
		preset(fileInstructionsPropertyName, fileInstructions);
		this.fileInstructions = fileInstructions;
	}

	/**
	 * {@link #allStaff} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<StaffExtension> getAllStaff() {
		return allStaff;
	}

	/**
	 * {@link #allStaff} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public StaffExtension getAllStaffElementById(String bizId) {
		return getElementById(allStaff, bizId);
	}

	/**
	 * {@link #allStaff} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setAllStaffElementById(String bizId, StaffExtension element) {
		setElementById(allStaff, element);
	}

	/**
	 * {@link #allStaff} add.
	 * @param element	The element to add.
	 **/
	public boolean addAllStaffElement(StaffExtension element) {
		boolean result = false;
		if (getElementById(allStaff, element.getBizId()) == null) {
			result = allStaff.add(element);
		}
		element.setBaseOffice(this);
		return result;
	}

	/**
	 * {@link #allStaff} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addAllStaffElement(int index, StaffExtension element) {
		allStaff.add(index, element);
		element.setBaseOffice(this);
	}

	/**
	 * {@link #allStaff} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeAllStaffElement(StaffExtension element) {
		boolean result = allStaff.remove(element);
		if (result) {
			element.nullBaseOffice();
		}
		return result;
	}

	/**
	 * {@link #allStaff} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public StaffExtension removeAllStaffElement(int index) {
		StaffExtension result = allStaff.remove(index);
		result.nullBaseOffice();
		return result;
	}
}
