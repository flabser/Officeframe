package reference.dao;

import java.util.UUID;

import kz.lof.scripting._Session;
import reference.model.StructureType;

public class StructureTypeDAO extends ReferenceDAO<StructureType, UUID> {

	public StructureTypeDAO(_Session session) {
		super(StructureType.class, session);
	}

}