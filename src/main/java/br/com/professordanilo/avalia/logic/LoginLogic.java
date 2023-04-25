package br.com.professordanilo.avalia.logic;

import br.com.professordanilo.avalia.dao.LoginDAO;
import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.Transacional;
import br.com.professordanilo.avalia.util.exception.ErroNegocioException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class LoginLogic implements Serializable {
    
    @Inject
    private LoginDAO dao;
    
    public Aluno buscarAlunoPorLoginSenha(String nome, String senha) throws ErroNegocioException {
        Aluno aluno = dao.buscarAlunoPorLoginSenha(nome, senha);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aluno", aluno);
        return aluno;
    }
    
    public void atualizarAlunoLogado(Aluno aluno) {
        aluno = dao.buscarPorId(aluno.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aluno", aluno);
    }
    public Aluno buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
}
