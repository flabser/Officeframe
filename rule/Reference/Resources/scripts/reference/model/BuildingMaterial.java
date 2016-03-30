package reference.model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import kz.lof.common.model.SimpleEntity;

@Entity
@Table(name = "building_material", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
@NamedQuery(name = "BuildingMaterial.findAll", query = "SELECT m FROM BuildingMaterial AS m ORDER BY m.regDate")
public class BuildingMaterial extends SimpleEntity {

}