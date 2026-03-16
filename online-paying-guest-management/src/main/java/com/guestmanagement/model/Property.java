package com.guestmanagement.model;

public class Property {
	
		private int propertyId;
		private String propertyName;
		private String location;
		public Property(int propertyId, String propertyName, String location) {
			this.propertyId = propertyId;
			this.propertyName = propertyName;
			this.location = location;
		}
		public int getPropertyId() {
			return propertyId;
		}
		public void setPropertyId(int propertyId) {
			this.propertyId = propertyId;
		}
		public String getPropertyName() {
			return propertyName;
		}
		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
		
		
		
		
	
	
}
