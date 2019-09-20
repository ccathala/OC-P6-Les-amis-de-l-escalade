package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

@Controller
public class AddSiteController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	@RequestMapping(value = "/site/processAddSite", method = RequestMethod.POST)
	public String addSite(Model model, @Valid @ModelAttribute(value = "site") Site site, BindingResult result) {

		if (result.hasErrors()) {

			model.addAllAttributes(addWebContentFormUtil.getAddSiteAttributesWhenValidationErrors(site, result));
			return "addWebContent";

		} else {
			Boolean siteAddedWithSuccess = webContentManager.addSite(site);
			model.addAllAttributes(addWebContentFormUtil.getAddSiteAttributes(site, result, siteAddedWithSuccess));

			return "addWebContent";
		}
	}
}
