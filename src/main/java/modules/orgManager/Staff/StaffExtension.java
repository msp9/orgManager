package modules.orgManager.Staff;

import java.time.LocalDate;
import java.time.Period;

import org.skyve.impl.util.TimeUtil;

import modules.orgManager.domain.Staff;

public class StaffExtension extends Staff {

	@Override
	public Integer getAgeInYears() {

		LocalDate curDate = LocalDate.now();
		if (this.getDateOfBirth() != null) {
			Period period = Period.between(TimeUtil.asLocalDate(this.getDateOfBirth()), curDate);
			this.setAgeInYears(period.getYears());
		}

		return super.getAgeInYears();
	}

	public void home() {
		if (this.getBaseOffice() != null) {
			this.setLocation(this.getBaseOffice().getBoundary().getCentroid());
		}

	}

}
