package com.guestmanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.guestmanagement.model.Property;

public class PropertyService {
	private List<Property> propertyList;
	
	public PropertyService(){
	    propertyList = new ArrayList<>();
	}
	
	public void addProperty(Property property){
	    propertyList.add(property);
	    System.out.println("Property Added Successfully");
	}
	
	public void updateProperty(int propertyId, String name, String location){

	    for(Property p : propertyList){

	        if(p.getPropertyId() == propertyId){

	            p.setPropertyName(name);
	            p.setLocation(location);

	            System.out.println("Property Updated Successfully");
	        }
	    }
	}
}
