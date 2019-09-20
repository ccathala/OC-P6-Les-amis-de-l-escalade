package com.charles.lesamisdelescalade.model.dto;

public class AddSecteurPageData extends AddWebContentPageData {
	
	private int departementIdSecteur;
	private int siteIdSecteur;
	private String messageErrorSecteur;
	private String messageSuccessSecteur;
	private String collapseClassSecteur;
	private Boolean collapseAriaSecteur;
	
	public AddSecteurPageData() {
		super();
	}

	public AddSecteurPageData(int departementIdSecteur, int siteIdSecteur, String messageErrorSecteur,
			String messageSuccessSecteur, String collapseClassSecteur, Boolean collapseAriaSecteur) {
		super();
		this.departementIdSecteur = departementIdSecteur;
		this.siteIdSecteur = siteIdSecteur;
		this.messageErrorSecteur = messageErrorSecteur;
		this.messageSuccessSecteur = messageSuccessSecteur;
		this.collapseClassSecteur = collapseClassSecteur;
		this.collapseAriaSecteur = collapseAriaSecteur;
	}

	public int getDepartementIdSecteur() {
		return departementIdSecteur;
	}

	public void setDepartementIdSecteur(int departementIdSecteur) {
		this.departementIdSecteur = departementIdSecteur;
	}

	public int getSiteIdSecteur() {
		return siteIdSecteur;
	}

	public void setSiteIdSecteur(int siteIdSecteur) {
		this.siteIdSecteur = siteIdSecteur;
	}

	public String getMessageErrorSecteur() {
		return messageErrorSecteur;
	}

	public void setMessageErrorSecteur(String messageErrorSecteur) {
		this.messageErrorSecteur = messageErrorSecteur;
	}

	public String getMessageSuccessSecteur() {
		return messageSuccessSecteur;
	}

	public void setMessageSuccessSecteur(String messageSuccessSecteur) {
		this.messageSuccessSecteur = messageSuccessSecteur;
	}

	public String getCollapseClassSecteur() {
		return collapseClassSecteur;
	}

	public void setCollapseClassSecteur(String collapseClassSecteur) {
		this.collapseClassSecteur = collapseClassSecteur;
	}

	public Boolean getCollapseAriaSecteur() {
		return collapseAriaSecteur;
	}

	public void setCollapseAriaSecteur(Boolean collapseAriaSecteur) {
		this.collapseAriaSecteur = collapseAriaSecteur;
	}

	@Override
	public String toString() {
		return "AddSecteurPageData [departementIdSecteur=" + departementIdSecteur + ", siteIdSecteur=" + siteIdSecteur
				+ ", messageErrorSecteur=" + messageErrorSecteur + ", messageSuccessSecteur=" + messageSuccessSecteur
				+ ", collapseClassSecteur=" + collapseClassSecteur + ", collapseAriaSecteur=" + collapseAriaSecteur
				+ "]";
	}
	
	
	

}
