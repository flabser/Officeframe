package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.exponentus.common.model.SimpleReferenceEntity;

@Entity
@Table(name = "responsible_types", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }) )
@NamedQuery(name = "ResponsibleType.findAll", query = "SELECT m FROM ResponsibleType AS m ORDER BY m.regDate")
public class ResponsibleType extends SimpleReferenceEntity {

}
