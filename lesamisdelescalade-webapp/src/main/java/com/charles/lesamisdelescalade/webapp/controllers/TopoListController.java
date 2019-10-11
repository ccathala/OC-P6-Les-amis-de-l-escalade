package com.charles.lesamisdelescalade.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

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

@Controller
public class TopoListController {

	@Autowired
	private WebContentManager webContentManager;

	@RequestMapping(value = "/topoList", method = RequestMethod.GET)
	public String displayTopoListPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@RequestParam(value = "messageReservationSuccess", required = false) String messageReservationSuccess,
			@RequestParam(value = "messageReservationError", required = false) String messageReservationError,
			RedirectAttributes redirectAttributes) {

		if (sessionUtilisateur == null) {
			redirectAttributes.addFlashAttribute("messageError",
					"Vous devez être connecté pour acceder à la page demandée.");
			return "redirect:/";
		} else {

			List<ListTopoPageData> avalaibleTopoAndExtendedDataList = webContentManager
					.findAllAvailableTopoAndExtendedData();
			model.addAttribute("liste_topos", webContentManager.findAllTopoAndExtendedData());
			model.addAttribute("liste_topos_disponibles", avalaibleTopoAndExtendedDataList);
			model.addAttribute("sessionUtilisateur", sessionUtilisateur);
			model.addAttribute("avalaibleTopoIdList",
					webContentManager.extractAvalaibleTopoIdList(avalaibleTopoAndExtendedDataList));
			return "topoList";
		}

	}

	@RequestMapping(value = "/processTopoReservationRequest/{topoId}/{possesseurId}/{demandeurId}", method = RequestMethod.GET)
	public String processprocessTopoReservationRequest(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "topoId") int topoId, @PathVariable(value = "possesseurId") int possesseurId,
			@PathVariable(value = "demandeurId") int demandeurId, RedirectAttributes redirectAttributes) {

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
