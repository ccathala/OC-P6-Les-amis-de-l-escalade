package com.charles.lesamisdelescalade.model.dto;

import java.util.List;

import com.charles.lesamisdelescalade.model.beans.Longueur;
import com.charles.lesamisdelescalade.model.beans.Secteur;
import com.charles.lesamisdelescalade.model.beans.Site;
import com.charles.lesamisdelescalade.model.beans.Voie;

public class SitePageData {
	
	private List<Secteur> secteurs;
	private List<Voie> voies;
	private List<Longueur> Longueurs;
	private Site site;
	
	
	
	public SitePageData() {
		super();
		
	}
	
	public List<Secteur> getSecteurs() {
		return secteurs;
	}
	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}
	public List<Voie> getVoies() {
		return voies;
	}
	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}
	public List<Longueur> getLongueurs() {
		return Longueurs;
	}
	public void setLongueurs(List<Longueur> longueurs) {
		Longueurs = longueurs;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "SiteRequest [secteurs=" + secteurs + ", voies=" + voies + ", Longueurs=" + Longueurs + ", site=" + site
				+ "]";
	}
	
	

	
}
