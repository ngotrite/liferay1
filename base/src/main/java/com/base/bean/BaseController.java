package com.base.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import com.base.util.LocaleUtils;

//@Service
//@ManagedBean(name="baseController")
//@SessionScoped
public class BaseController {

	// @Autowired
	// protected ApplicationContext appContext;//Todo: null now

	// protected int currentDomainId;

	public BaseController() {

	}

	/**
	 * @author TENT
	 */
	public void showNotification(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, this.readProperties(summary), this.readProperties(detail));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void showNotificationSuccsess() {
		this.showNotification(FacesMessage.SEVERITY_INFO, "common.success", "");
	}

	public void showNotificationFail() {
		this.showNotification(FacesMessage.SEVERITY_WARN, "common.fail", "");
	}

	/**
	 * Tent 19112016 get Request Bean
	 */
	public Object getBean(String nameBean) {
		Map<String, Object> map = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		Object obj = null;
		try {
			obj = map.get(nameBean);
		} catch (Exception e) {
			e.printStackTrace();
//			throw e;
		}

		return obj;
	}

	public void showMessageDlgINFO(String value1, String value2) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, value1, value2);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	/***
	 * 
	 * @param actionKey
	 *            Key trong lang.properties, Ex: common.save
	 * @param objName
	 *            Ex: Offer
	 */
	public void showMessageINFO(String actionKey, String objName, String... additionalMessages) {

		String details = String.format("%s %s %s", this.readProperties(actionKey), objName,
				this.readProperties("common.success"));
		for (String additionalMsg : additionalMessages) {
			details += this.readProperties(additionalMsg);
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.readProperties("common.summary.info"),
				details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/***
	 * 
	 * @param actionKey
	 *            Key trong lang.properties, Ex: common.save
	 * @param objName
	 *            Ex: Offer
	 */
	public void showMessageINFO(String actionKey, String objName) {

		String details = String.format("%s %s", this.readProperties(actionKey), objName);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.readProperties("common.summary.info"),
				details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/***
	 * 
	 * @param actionKey
	 *            Key trong lang.properties, Ex: common.save
	 * @param objName
	 *            Ex: Offer
	 */
	// public void showMessageWARN(String actionKey, String objName, String...
	// additionalMessages) {
	//
	// String details = String.format("%s %s %s",
	// this.readProperties(actionKey), objName,
	// this.readProperties("common.notSafe"));
	// for (String additionalMsg : additionalMessages) {
	// details += this.readProperties(additionalMsg);
	// }
	// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
	// this.readProperties("common.summary.warning"), details);
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// }
	public void showMessageWARN(String actionKey, String objName, String... additionalMessages) {

		// String details = String.format("%s %s %s",
		// this.readProperties(actionKey), objName,
		// this.readProperties("common.notSafe"));
		String details = String.format("%s %s", this.readProperties(actionKey), objName);
		for (String additionalMsg : additionalMessages) {
			details += this.readProperties(additionalMsg);
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
				this.readProperties("common.summary.warning"), details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/***
	 * 
	 * @param actionKey
	 *            Key trong lang.properties, Ex: common.save
	 * @param objName
	 *            Ex: Offer
	 */
	public void showMessageERROR(String actionKey, String objName, String... additionalMessages) {

		String details = String.format("%s %s %s", this.readProperties(actionKey), objName,
				this.readProperties("common.unsuccessful"));
		for (String additionalMsg : additionalMessages) {
			details += this.readProperties(additionalMsg);
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				this.readProperties("common.summary.error"), details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void showMessage(Severity severity, String summary, String detail) {
		FacesMessage facesmessage = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesmessage);
	}

	public String readProperties(String key) {

//		UserSession userSession = (UserSession) getBean("userSession");
//		if (userSession != null)
//			return LocaleUtils.getString(userSession.getLocale(), key);
//		else
			return LocaleUtils.getString(Locale.US, key);
	}

	/***
	 * @param treeType
	 * @param objName
	 */
	public void openTreeOfferDialog(String treeType, String objectName) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("widgetVar", "dlgTree");
		options.put("width", 500);
		options.put("height", 450);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		List<String> lstPara = new ArrayList<String>();
		lstPara.add(treeType + ";" + objectName);
		mapPara.put("treeType", lstPara);
		RequestContext.getCurrentInstance().openDialog("/pages/tree_offer_dialog", options, mapPara);
	}

	/***
	 * @param treeType
	 * @param objName
	 */
	public void openTreeCommonDialog(String treeType, String objectName) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("widgetVar", "dlgTree");
		options.put("width", 500);
		options.put("height", 450);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		List<String> lstPara = new ArrayList<String>();
		lstPara.add(treeType + ";" + objectName);
		mapPara.put("treeType", lstPara);
		RequestContext.getCurrentInstance().openDialog("/pages/tree_common_dialog", options, mapPara);
	}

	/***
	 * @param treeType
	 * @param objName
	 */
	public void openTreeCategoryDialog(String treeType, String objectName) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("widgetVar", "dlgTree");
		options.put("width", 500);
		options.put("height", 450);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		List<String> lstPara = new ArrayList<String>();
		lstPara.add(treeType + ";" + objectName);
		mapPara.put("treeType", lstPara);
		RequestContext.getCurrentInstance().openDialog("/pages/tree_category_dialog", options, mapPara);
	}

	public void showDependencies(Long objID, Class t) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("widgetVar", "dlgTree");
		options.put("width", 500);
		options.put("height", 550);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		List<String> lstPara = new ArrayList<String>();
		lstPara.add(objID + ";" + t.getName());
		mapPara.put("treeType", lstPara);
		RequestContext.getCurrentInstance().openDialog("/pages/show_dependencies", options, mapPara);
	}
	
	public void resetDataTable(String dataTableId) {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(dataTableId);
		if (!dataTable.getFilters().isEmpty()) {
			dataTable.reset();// working
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update(dataTableId);
		}
	}

}
