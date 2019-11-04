package com.charles.lesamisdelescalade.webapp.controllers;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.ReservationTopo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.ListTopoPageData;
/**
 * Controller class in relation with topoList.jsp
 * 
 * @author Charles
 *
 */
@Controller
public class TopoListController {

	@Autowired
	private WebContentManager webContentManager;
	
	// Set logger
	private static final Logger logger = LoggerFactory.getLogger(TopoListController.class);
	
	
	/**
	 * Display topoList page
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param messageReservationSuccess
	 * @param messageReservationError
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/topoList", method = RequestMethod.GET)
	public String displayTopoListPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@RequestParam(value = "messageReservationSuccess", required = false) String messageReservationSuccess,
			@RequestParam(value = "messageReservationError", required = false) String messageReservationError,
			RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /topoList");

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			logger.warn("Utilisateur non connecté - Accès refusé");
			return "redirect:/";
		} else {

			List<ListTopoPageData> avalaibleTopoAndExtendedDataList = webContentManager
					.findAllAvailableTopoAndExtendedData(sessionUtilisateur.getId());
			model.addAttribute("liste_topos", webContentManager.findAllTopoAndExtendedData());
			model.addAttribute("liste_topos_disponibles", avalaibleTopoAndExtendedDataList);
			model.addAttribute("sessionUtilisateur", sessionUtilisateur);
			model.addAttribute("avalaibleTopoIdList",
					webContentManager.extractAvalaibleTopoIdList(avalaibleTopoAndExtendedDataList));
			return "topoList";
		}

	}

	/**
	 * Process chosen topo reservation
	 * 
	 * @param model
	 * @param sessionUtilisateur
	 * @param topoId
	 * @param possesseurId
	 * @param demandeurId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/processTopoReservationRequest/{topoId}/{possesseurId}/{demandeurId}", method = RequestMethod.GET)
	public String processprocessTopoReservationRequest(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "topoId") int topoId, @PathVariable(value = "possesseurId") int possesseurId,
			@PathVariable(value = "demandeurId") int demandeurId, RedirectAttributes redirectAttributes) {
		
		logger.info("Requête d'accès à l'url /processTopoReservationRequest/" + topoId + "/" + possesseurId + "/" + demandeurId);

		ReservationTopo reservationTopo = new ReservationTopo(topoId, possesseurId, demandeurId, 0);
		if (webContentManager.addReservation(reservationTopo)) {
			redirectAttributes.addFlashAttribute("messageReservationError",
					"Erreur - Vous avez déjà fais cette demande de réservation");
		} else {
			redirectAttributes.addFlashAttribute("messageReservationSuccess", "Réservation faite avec succès");
		}

		return "redirect:/topoList";

	}

}
