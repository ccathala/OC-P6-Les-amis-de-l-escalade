package com.charles.lesamisdelescalade.webapp.controllers;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.webapp.utils.AddWebContentFormUtil;

/**
 * Controller class in relation with addWebContent jsp
 * 
 * @author Charles
 *
 */
@Controller
public class AddWebContentController {

	@Autowired
	private AddWebContentFormUtil addWebContentFormUtil;

	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(AddWebContentController.class);

	/**
	 * Display addWebContent page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/site/ajouter", method = RequestMethod.GET)
	public String displayAddWebContentPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /site/ajouter");
		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			logger.warn("Utilisateur non connecté - Accès refusé");
			return "redirect:/";
		} else {
			model.addAllAttributes(addWebContentFormUtil.getAddWebContentAttributes(sessionUtilisateur));
			return "addWebContent";
		}

	}

}
