package tech.madest.eparser.entity.shopizer.user;

import tech.madest.eparser.entity.shopizer.common.Criteria;

public class UserCriteria extends Criteria {
	
	private String adminEmail;
	private String adminName;
	private boolean active = true;
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

}
