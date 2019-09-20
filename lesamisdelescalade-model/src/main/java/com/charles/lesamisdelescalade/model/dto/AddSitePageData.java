package com.charles.lesamisdelescalade.model.dto;

public class AddSitePageData extends AddWebContentPageData{

	private int departementIdSite;
	private String collapseClassSite;
	private Boolean collapseAriaSite;
	private String messageErrorSite;
	private String messageSuccessSite;
	
	public AddSitePageData() {
		super();
		
	}

	public AddSitePageData(int departementIdSite, String collapseClassSite, Boolean collapseAriaSite,
			String messageErrorSite, String messageSuccessSite) {
		super();
		this.departementIdSite = departementIdSite;
		this.collapseClassSite = collapseClassSite;
		this.collapseAriaSite = collapseAriaSite;
		this.messageErrorSite = messageErrorSite;
		this.messageSuccessSite = messageSuccessSite;
	}

	public int getDepartementIdSite() {
		return departementIdSite;
	}

	public void setDepartementIdSite(int departementIdSite) {
		this.departementIdSite = departementIdSite;
	}

	public String getCollapseClassSite() {
		return collapseClassSite;
	}

	public void setCollapseClassSite(String collapseClassSite) {
		this.collapseClassSite = collapseClassSite;
	}

	public Boolean getCollapseAriaSite() {
		return collapseAriaSite;
	}

	public void setCollapseAriaSite(Boolean collapseAriaSite) {
		this.collapseAriaSite = collapseAriaSite;
	}

	public String getMessageErrorSite() {
		return messageErrorSite;
	}

	public void setMessageErrorSite(String messageErrorSite) {
		this.messageErrorSite = messageErrorSite;
	}

	public String getMessageSuccessSite() {
		return messageSuccessSite;
	}

	public void setMessageSuccessSite(String messageSuccessSite) {
		this.messageSuccessSite = messageSuccessSite;
	}

	@Override
	public String toString() {
		return "AddSitePageData [departementIdSite=" + departementIdSite + ", collapseClassSite=" + collapseClassSite
				+ ", collapseAriaSite=" + collapseAriaSite + ", messageErrorSite=" + messageErrorSite
				+ ", messageSuccessSite=" + messageSuccessSite + "]";
	}
	
//	
	
}
