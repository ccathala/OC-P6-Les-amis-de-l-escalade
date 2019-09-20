package com.charles.lesamisdelescalade.business.authentification;

import com.charles.lesamisdelescalade.model.beans.Utilisateur;
import com.charles.lesamisdelescalade.model.dto.AuthResult;

public interface AuthentificationManager {

	public AuthResult login(Utilisateur utilisateur);
}
