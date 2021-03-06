package reference.dao;

import java.util.UUID;

import com.exponentus.scripting._Session;
import reference.model.CityDistrict;

public class CityDistrictDAO extends ReferenceDAO<CityDistrict, UUID> {

	public CityDistrictDAO(_Session session) {
		super(CityDistrict.class, session);
	}

}
