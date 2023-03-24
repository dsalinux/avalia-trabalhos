package br.com.professordanilo.avalia.logic;

import br.com.professordanilo.avalia.dao.AlunoDAO;
import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.exception.ErroNegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author danilo
 */
public class AlunoLogic implements Serializable {
    
    @Inject
    private AlunoDAO dao;
    
    public List<Aluno> listarAlunosComURL() throws ErroNegocioException {
        List<Aluno> alunos =  dao.listaAlunos();
        return alunos;
    }
    
}
