package com.charles.lesamisdelescalade.webapp.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charles.lesamisdelescalade.business.utils.bean.DepartementManager;
import com.charles.lesamisdelescalade.business.utils.bean.SiteManager;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.Topo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
 
/**
 * Controller class in relation with addTopo jsp
 * 
 * @author Charles
 *
 */
@Controller
public class AddTopoController {

	@Autowired
	private WebContentManager webContentManager;
	@Autowired
	private SiteManager siteManager;
	@Autowired
	private DepartementManager departementManager;
	
	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(AddTopoController.class);

	/**
	 * Display add topo page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/addTopo", method = RequestMethod.GET)
	public String displayAddTopoPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		logger.info("Requête d'accès à l'url /addTopo");
		if (sessionUtilisateur ==null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			logger.warn("Utilisateur non connecté - Accès refusé");
			return "redirect:/";
		} else {
			model.addAttribute("departements", departementManager.findAllDepartement());
			model.addAttribute("sessionUtilisateur", sessionUtilisateur);
			return "addTopo";
		}
	}

	/**
	 * record chosen departement and return addTopo page
	 * 
	 * @param departementId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "processSelectDepartementAtAddTopoPage", method = RequestMethod.GET)
	public String processSelectDepartementAtAddTopoPage(
			@RequestParam(value = "departementId") int departementId,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /processSelectDepartementAtAddTopoPage");
		if(departementId == 0) {
			return "redirect:/addTopo";
		}else {
			redirectAttributes.addFlashAttribute("newTopo", new Topo());
			redirectAttributes.addFlashAttribute("departementId", departementId);
			redirectAttributes.addFlashAttribute("sites", siteManager.findAllSiteByDepartement(departementId));
			return "redirect:/addTopo";
		}

	}

	/**
	 * Handle add topo request
	 * 
	 * @param model
	 * @param newTopo
	 * @param result
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "processAddTopo", method = RequestMethod.POST)
	public String processAddTopo(Model model, @Valid @ModelAttribute(value="newTopo") Topo newTopo, BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		
		logger.info("Requête d'accès à l'url /processAddTopo");
		if (result.hasErrors()) {
			
			if(result.getFieldErrorCount("site_id")>0) {
				model.addAttribute("messageErrorTopo", "Erreur - Aucun site sélectionné.");
			}else {
				int departementId = departementManager.getDepartementIdBySiteId(newTopo.getSite_id());
				model.addAttribute("departementId", departementId);
				model.addAttribute("sites", siteManager.findAllSiteByDepartement(departementId));
				model.addAttribute("siteId", newTopo.getSite_id());
			}
			model.addAttribute("departements", departementManager.findAllDepartement());
			return "addTopo";
		} else {
			if( webContentManager.addTopo(newTopo)) {
				redirectAttributes.addFlashAttribute("messageErrorTopo", "Erreur - Ce topo est déjà enregistré.");
			}else {
				redirectAttributes.addFlashAttribute("messageSuccessTopo", "Topo ajouté avec succès.");
			}
			
			return "redirect:/addTopo";
		}

	}
}
