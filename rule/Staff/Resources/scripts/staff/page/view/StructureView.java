package staff.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._POJOObjectWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.scriptprocessor.page.IOutcomeObject;
import com.exponentus.user.IUser;
import com.exponentus.user.SuperUser;

import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class StructureView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		List<IOutcomeObject> content = new ArrayList<IOutcomeObject>();
		OrganizationDAO dao = new OrganizationDAO(session);
		Organization org = dao.findPrimaryOrg();
		if (org != null) {
			content.add(new _POJOObjectWrapper(org, session));
		} else {
			content.add(new _POJOListWrapper(getLocalizedWord("no_primary_org", lang), ""));
		}

		IUser<Long> user = session.getUser();
		if (user.getId() == SuperUser.ID || user.getRoles().contains("staff_admin")) {
			_ActionBar actionBar = new _ActionBar(session);
			_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization");
			newDocAction.setURL("Provider?id=organization-form");
			actionBar.addAction(newDocAction);
			actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));
			addContent(actionBar);
		}
		addContent(content);
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrganizationDAO dao = new OrganizationDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Organization m = dao.findById(UUID.fromString(id));
			try {
				dao.delete(m);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
