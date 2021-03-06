package reference.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.exponentus.common.model.SimpleReferenceEntity;
import com.exponentus.scripting._Session;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "city_districts", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "locality_id" }) )
@NamedQuery(name = "CityDistrict.findAll", query = "SELECT m FROM CityDistrict AS m ORDER BY m.regDate")
public class CityDistrict extends SimpleReferenceEntity {

	@JsonIgnore
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Locality locality;

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append(super.getFullXMLChunk(ses));
		chunk.append("<locality id=\"" + locality.getId() + "\">" + locality.getLocalizedName(ses.getLang()) + "</locality>");
		return chunk.toString();
	}
}
