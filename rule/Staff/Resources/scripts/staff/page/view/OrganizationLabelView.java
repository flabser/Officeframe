package staff.page.view;

import java.util.List;
import java.util.UUID;

import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.user.SuperUser;

import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;
import staff.model.OrganizationLabel;

/**
 * @author Kayra created 08-01-2016
 */

public class OrganizationLabelView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
		String id = formData.getValueSilently("categoryid");
		if (!id.isEmpty()) {
			OrganizationLabel role = dao.findById(UUID.fromString(id));
			List<Organization> emps = role.getLabels();
			addContent(new _POJOListWrapper(emps, session));
		} else {
			IUser<Long> user = session.getUser();
			if (user.getId() == SuperUser.ID || user.getRoles().contains("staff_admin")) {
				_ActionBar actionBar = new _ActionBar(session);
				_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_organization_label");
				newDocAction.setURL("Provider?id=organization-label-form");
				actionBar.addAction(newDocAction);
				actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));
				addContent(actionBar);
			}
			addContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			OrganizationLabel m = dao.findById(UUID.fromString(id));
			try {
				dao.delete(m);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
