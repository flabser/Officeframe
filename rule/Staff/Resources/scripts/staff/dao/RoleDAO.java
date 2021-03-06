package staff.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import staff.model.Role;

public class RoleDAO extends DAO<Role, UUID> {

	public RoleDAO(_Session session) {
		super(Role.class, session);
	}

}
