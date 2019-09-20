package com.charles.lesamisdelescalade.model.dto;

public class AddVoiePageData extends AddWebContentPageData{
	
	private int departementIdVoie;
	private int siteIdVoie;
	private int secteurIdVoie;
	private String messageErrorVoie;
	private String messageSuccessVoie;
	private String collapseClassVoie;
	private Boolean collapseAriaVoie;
	
	public AddVoiePageData() {
		super();
	}

	public AddVoiePageData(int departementIdVoie, int siteIdVoie, int secteurIdVoie, String messageErrorVoie,
			String messageSuccessVoie, String collapseClassVoie, Boolean collapseAriaVoie) {
		super();
		this.departementIdVoie = departementIdVoie;
		this.siteIdVoie = siteIdVoie;
		this.secteurIdVoie = secteurIdVoie;
		this.messageErrorVoie = messageErrorVoie;
		this.messageSuccessVoie = messageSuccessVoie;
		this.collapseClassVoie = collapseClassVoie;
		this.collapseAriaVoie = collapseAriaVoie;
	}

	public int getDepartementIdVoie() {
		return departementIdVoie;
	}

	public void setDepartementIdVoie(int departementIdVoie) {
		this.departementIdVoie = departementIdVoie;
	}

	public int getSiteIdVoie() {
		return siteIdVoie;
	}

	public void setSiteIdVoie(int siteIdVoie) {
		this.siteIdVoie = siteIdVoie;
	}

	public int getSecteurIdVoie() {
		return secteurIdVoie;
	}

	public void setSecteurIdVoie(int secteurIdVoie) {
		this.secteurIdVoie = secteurIdVoie;
	}

	public String getMessageErrorVoie() {
		return messageErrorVoie;
	}

	public void setMessageErrorVoie(String messageErrorVoie) {
		this.messageErrorVoie = messageErrorVoie;
	}

	public String getMessageSuccessVoie() {
		return messageSuccessVoie;
	}

	public void setMessageSuccessVoie(String messageSuccessVoie) {
		this.messageSuccessVoie = messageSuccessVoie;
	}

	public String getCollapseClassVoie() {
		return collapseClassVoie;
	}

	public void setCollapseClassVoie(String collapseClassVoie) {
		this.collapseClassVoie = collapseClassVoie;
	}

	public Boolean getCollapseAriaVoie() {
		return collapseAriaVoie;
	}

	public void setCollapseAriaVoie(Boolean collapseAriaVoie) {
		this.collapseAriaVoie = collapseAriaVoie;
	}

	@Override
	public String toString() {
		return "AddVoiePageData [departementIdVoie=" + departementIdVoie + ", siteIdVoie=" + siteIdVoie
				+ ", secteurIdVoie=" + secteurIdVoie + ", messageErrorVoie=" + messageErrorVoie
				+ ", messageSuccessVoie=" + messageSuccessVoie + ", collapseClassVoie=" + collapseClassVoie
				+ ", collapseAriaVoie=" + collapseAriaVoie + "]";
	}
	
	
	

}
