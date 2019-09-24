package com.charles.lesamisdelescalade.business.authentification;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.LoginData;

public interface AuthentificationManager {

	public LoginData login(Utilisateur utilisateur);
}
