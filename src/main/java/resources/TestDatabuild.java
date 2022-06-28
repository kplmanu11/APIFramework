package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Direction;
import pojo.PojoClass;

public class TestDatabuild {
	public PojoClass addPlacePayload() {
		PojoClass pc = new PojoClass();
		pc.setAccuracy(50);
		pc.setName("ram");
		pc.setPhone_number("111-222-333");
		pc.setAddress("USA");
		pc.setWebsite("https://www.google.com");
		pc.setLanguage("French_IN");

		List<String> myTypes = new ArrayList<String>();
		myTypes.add("shoe-park");
		myTypes.add("shop");
		pc.setTypes(myTypes);

		Direction d = new Direction();
		d.setLat(-31.3213);
		d.setLng(324.323);
		pc.setLocation(d);
		
		return pc;

	}

}
