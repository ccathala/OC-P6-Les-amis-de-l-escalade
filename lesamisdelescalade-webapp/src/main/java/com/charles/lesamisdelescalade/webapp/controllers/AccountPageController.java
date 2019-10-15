package com.charles.lesamisdelescalade.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.charles.lesamisdelescalade.business.webcontent.WebContentManager;
import com.charles.lesamisdelescalade.model.beans.PossesseurTopo;
import com.charles.lesamisdelescalade.model.beans.Utilisateur;

@Controller
public class AccountPageController {

	@Autowired
	private WebContentManager webContentManager;

	@RequestMapping(value = "/goToAccountPage", method = RequestMethod.GET)
	public String goToAccountPage(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("sessionUtilisateur", sessionUtilisateur);
		return "redirect:/accountPage/" + sessionUtilisateur.getId();

	}

	@RequestMapping(value = "/accountPage/{sessionUtilisateurId}", method = RequestMethod.GET)
	public String displayAccountPage(Model model,
			@PathVariable(value = "sessionUtilisateurId") int sessionUtilisateurId,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@RequestParam(value = "departementId", required = false) Integer departementId) {

		model.addAttribute("sessionUtilisateur", sessionUtilisateur);
		if (sessionUtilisateurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			model.addAttribute("departements", webContentManager.findAllDepartement());
			model.addAttribute("departementId", departementId);
			model.addAttribute("myTopos", webContentManager.findAllMyTopoByUtilisateurId(sessionUtilisateurId));
			model.addAttribute("receivedReservationRequest",
					webContentManager.findAllReceivedReservationRequestByUtilisateurId(sessionUtilisateur.getId()));
			model.addAttribute("sentReservationRequest",
					webContentManager.findAllSentReservationRequestByUtilisateurId(sessionUtilisateur.getId()));
			if (departementId != null) {
				model.addAttribute("newPossesseurTopo", new PossesseurTopo());
				model.addAttribute("topos", webContentManager.getDataForAccountPageDataBySiteId(departementId));
			}

			return "accountPage";
		}
	}

	@RequestMapping(value = "/processChooseDepartementToAddTopo", method = RequestMethod.GET)
	public String processChooseDepartementToAddTopo(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@RequestParam(value = "departementId") int departementId, RedirectAttributes redirectAttributes) {
		if (departementId == 0) {
			return "redirect:/goToAccountPage";
		} else {
			redirectAttributes.addFlashAttribute("departementId", departementId);
			redirectAttributes.addFlashAttribute("sessionUtilisateur", sessionUtilisateur);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}

	@RequestMapping(value = "/accountPage/processAddOwnedTopo", method = RequestMethod.POST)
	public String addOwnedTopo(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@ModelAttribute(value = "newPossesseurTopo") PossesseurTopo newPossesseurTopo,
			RedirectAttributes redirectAttributes) {
		if (webContentManager.addPossesseurTopo(newPossesseurTopo)) {
			redirectAttributes.addFlashAttribute("messageAddPossesseurTopoSuccessfully",
					"Le topo a été ajouté avec succès dans votre espace personnel.");
		} else {
			redirectAttributes.addFlashAttribute("messageAddPossesseurTopoError",
					"Erreur - Vous possédez déjà ce topo.");
		}
		return "redirect:/accountPage/" + sessionUtilisateur.getId();

	}

	@RequestMapping(value = "/setTopoAvailability/{topoId}/{utilisateurId}/{available}", method = RequestMethod.GET)
	public String setTopoAvailability(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "topoId") int topoId, @PathVariable(value = "utilisateurId") int utilisateurId,
			@PathVariable(value = "available") Boolean available) {

		if (utilisateurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.setTopoAvailability(new PossesseurTopo(topoId, utilisateurId, available, null));
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}

	}

	@RequestMapping(value = "/deleteOwnedTopo/{topoId}/{utilisateurId}", method = RequestMethod.GET)
	public String deleteOwnedTopo(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "topoId") int topoId, @PathVariable(value = "utilisateurId") int utilisateurId) {

		if (utilisateurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.deleteOwnedTopo(topoId, utilisateurId);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}

	}

	// ==================================================================================================================
	// Received Request Section
	// ==================================================================================================================

	@RequestMapping(value = "/processUpdateReservationRequestStatusToAccepted/{reservationRequestId}/{possesseurId}/{topoId}", method = RequestMethod.GET)
	public String processUpdateReservationRequestStatusToAccepted(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId,
			@PathVariable(value="topoId") int topoId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.acceptTopoReservation(reservationRequestId, new PossesseurTopo(topoId, possesseurId, null, null));
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}

	@RequestMapping(value = "/processUpdateReservationRequestStatusToRefused/{reservationRequestId}/{possesseurId}", method = RequestMethod.GET)
	public String processUpdateReservationRequestStatusToRefused(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.updateReservationRequestStatusToRefused(reservationRequestId);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}

	@RequestMapping(value = "/processUpdateReservationRequestStatusToEnded/{reservationRequestId}/{possesseurId}/{topoId}", method = RequestMethod.GET)
	public String processUpdateReservationRequestStatusToEnded(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId, 
			@PathVariable(value="topoId") int topoId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.setOverTopoReservation(reservationRequestId, new PossesseurTopo(topoId, possesseurId, null, null));
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}
	
	@RequestMapping(value = "/processUpdateReservationRequestStatusToCancelled/{reservationRequestId}/{possesseurId}", method = RequestMethod.GET)
	public String processUpdateReservationRequestStatusToCancelled(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.updateReservationRequestStatusToCancelled(reservationRequestId);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}

	@RequestMapping(value = "/processSetReservationVisibilityForOwnerToFalse/{reservationRequestId}/{possesseurId}", method = RequestMethod.GET)
	public String processSetReservationVisibilityToFalse(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.setReservationVisibilityForOwnerToFalse(reservationRequestId);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}
	
	@RequestMapping(value = "/processSetReservationVisibilityForRequesterToFalse/{reservationRequestId}/{possesseurId}", method = RequestMethod.GET)
	public String processSetReservationVisibilityForRequesterToFalse(Model model,
			@SessionAttribute(value = "sessionUtilisateur", required = false) Utilisateur sessionUtilisateur,
			@PathVariable(value = "reservationRequestId") int reservationRequestId,
			@PathVariable(value = "possesseurId") int possesseurId) {

		if (possesseurId != sessionUtilisateur.getId()) {
			return "redirect:/";
		} else {
			webContentManager.setReservationVisibilityForRequesterToFalse(reservationRequestId);
			return "redirect:/accountPage/" + sessionUtilisateur.getId();
		}
	}

}
