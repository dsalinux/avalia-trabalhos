package br.com.professordanilo.avalia.bean;

import br.com.professordanilo.avalia.dao.LoginDAO;
import br.com.professordanilo.avalia.logic.LoginLogic;
import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.JSFUtil;
import br.com.professordanilo.avalia.util.exception.ErroNegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class LoginBean extends JSFUtil{

	@Getter @Setter
	private String login;
	@Getter @Setter
	private String senha;
	
        @Inject
        private LoginLogic logic;
        
	public void logar() {
            try {
                Aluno aluno = logic.buscarAlunoPorLoginSenha(login, senha);
            } catch (ErroNegocioException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                addAviso(ex);
            }
	}
	
        public void logout(){
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        
        public void redirecionaLogin(){
            try {
                if(!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("aluno")){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
	
}
