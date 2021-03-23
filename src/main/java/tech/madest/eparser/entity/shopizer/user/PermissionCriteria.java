package tech.madest.eparser.entity.shopizer.user;

import java.util.List;
import java.util.Set;

import tech.madest.eparser.entity.shopizer.common.Criteria;

public class PermissionCriteria extends Criteria {
	
	
	private String permissionName;

	
	private Boolean available = null;
	
	private Set<Integer> groupIds;
	
	private List<String> availabilities;


	public List<String> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<String> availabilities) {
		this.availabilities = availabilities;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Set<Integer> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Integer> groupIds) {
		this.groupIds = groupIds;
	}


}
