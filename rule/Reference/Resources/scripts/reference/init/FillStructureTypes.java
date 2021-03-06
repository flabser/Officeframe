package reference.init;

import java.util.ArrayList;
import java.util.List;

import com.exponentus.dataengine.jpa.deploying.InitialDataAdapter;
import com.exponentus.localization.LanguageCode;
import com.exponentus.localization.Vocabulary;
import com.exponentus.scripting._Session;

import reference.dao.StructureTypeDAO;
import reference.model.StructureType;

/**
 * Created by Kayra on 30/12/15.
 */

public class FillStructureTypes extends InitialDataAdapter<StructureType, StructureTypeDAO> {

	@Override
	public List<StructureType> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
		List<StructureType> entities = new ArrayList<StructureType>();
		String[] data = { "Бассейн", "Буровая скважина", "Водокачка", "Встроенно-пристроенная часть", "Дорога", "Другое", "Мост",
		        "Ограждение парков, скверов и общественных садов", "Отдельно стоящее строение", "Памятник", "Подвальная часть",
		        "Помещения внутри здания", "Складское, гаражное, котельная", "Стадион", "Штольня" };

		for (int i = 0; i < data.length; i++) {
			StructureType entity = new StructureType();
			entity.setName(data[i]);
			entities.add(entity);
		}

		return entities;
	}

}
