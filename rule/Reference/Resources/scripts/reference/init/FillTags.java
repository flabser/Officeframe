package reference.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exponentus.dataengine.jpa.deploying.InitialDataAdapter;
import com.exponentus.localization.LanguageCode;
import com.exponentus.localization.Vocabulary;
import com.exponentus.scripting._Session;

import reference.dao.TagDAO;
import reference.model.Tag;

/**
 * Created by Kayra on 28/01/16.
 */

public class FillTags extends InitialDataAdapter<Tag, TagDAO> {

	@Override
	public List<Tag> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
		List<Tag> entities = new ArrayList<Tag>();

		Tag entity = new Tag();
		entity.setName("starred");
		Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
		name.put(LanguageCode.ENG, "Starred");
		name.put(LanguageCode.RUS, "Избранный");
		name.put(LanguageCode.KAZ, "Сүйікті");
		entity.setLocalizedName(name);
		entity.setColor("#d94600");
		entities.add(entity);

		return entities;
	}

}
