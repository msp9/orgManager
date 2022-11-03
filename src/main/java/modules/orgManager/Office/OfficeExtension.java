package modules.orgManager.Office;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.skyve.CORE;
import org.skyve.persistence.DocumentQuery;

import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.Office;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.Staff.Status;
import modules.orgManager.domain.StaffStatusHistory;

public class OfficeExtension extends Office {

	public void calculateTotalStaffs() {

		Integer total = this.getAllStaff().size();
		this.setTotalNoOfStaffInOffice(total);

	}

	/*
	 * public Integer getTotalNumberOfStaffsInOffice() {
	 * 
	 * }
	 */

	public void calculateTotalStaffsInOffice() {
		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
		q.getFilter().addEquals(Staff.baseOfficePropertyName, this);
		q.getFilter().addEquals(Staff.statusPropertyName, Status.in);
		List<StaffExtension> staffs = q.beanResults();
		this.setNoOfStaffInOffice(staffs.size());
	}

	public void calculateStaffsNeverBeenToOffice() {
		// Retrieves all the staff at the base office
		/*
		 * DocumentQuery q = CORE.getPersistence().newDocumentQuery(Staff.MODULE_NAME,
		 * Staff.DOCUMENT_NAME); q.getFilter().addEquals(Staff.baseOfficePropertyName,
		 * this); List<StaffExtension> staffs = q.beanResults(); List<StaffExtension>
		 * staffsNeverInOffice = new ArrayList<StaffExtension>(); List<StaffExtension>
		 * staffsInOffice = new ArrayList<StaffExtension>(); boolean b = false;
		 * 
		 * for (StaffExtension s : staffs) { List<StaffStatusHistory> history =
		 * s.getHistories(); for (StaffStatusHistory h : history) { if
		 * (h.equals(Status.in) || h.equals(Status.outToLunch)) { b = true; break; } }
		 * if(b==false) { this.addListStaffNeverInOfficeElement(s);
		 * staffsNeverInOffice.add(s); } b=false;
		 * 
		 * }
		 */
		// Retrieves all the staff at the base office
		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
		q.getFilter().addEquals(Staff.baseOfficePropertyName, this);
		List<StaffExtension> staffs = q.beanResults();
		List<StaffExtension> staffsNeverInOffice = new ArrayList<StaffExtension>();

		staffsNeverInOffice.addAll(staffs.stream().filter(
				staff -> staff.getHistories().stream().allMatch(hist -> hist != null && hist.equals(Status.out)))
				.collect(Collectors.toList()));

	}

}
