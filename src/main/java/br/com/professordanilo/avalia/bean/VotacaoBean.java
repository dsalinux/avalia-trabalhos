package br.com.professordanilo.avalia.bean;

import br.com.professordanilo.avalia.dao.LoginDAO;
import br.com.professordanilo.avalia.logic.LoginLogic;
import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.entity.Voto;
import br.com.professordanilo.avalia.logic.AlunoLogic;
import br.com.professordanilo.avalia.logic.VotoLogic;
import br.com.professordanilo.avalia.util.JSFUtil;
import br.com.professordanilo.avalia.util.exception.ErroNegocioException;
import br.com.professordanilo.avalia.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class VotacaoBean extends JSFUtil{

	@Getter @Setter
	private String login;
	@Getter @Setter
	private String senha;
	
	@Getter @Setter
        private Aluno alunoSelecionado;
        
        @Inject
        private VotoLogic votoLogic;
        @Inject
        private AlunoLogic alunoLogic;
        
        @Inject
        private LoginLogic loginLogic;
        
        private Long ultimaAtualizacao = 0L;
        private List<Aluno> alunos;
        
	public List<Aluno> getListaAlunos(){
            try {
                if(ultimaAtualizacao + 5000 < new Date().getTime()){
                    ultimaAtualizacao = new Date().getTime();
                    alunos = alunoLogic.listarAlunosComURL();
                }
                return alunos;
            } catch (ErroNegocioException ex) {
                Logger.getLogger(VotacaoBean.class.getName()).log(Level.SEVERE, null, ex);
                addAviso(ex);
            }
            return null;
        }
	
        public void visualizarAluno(Aluno aluno) {
            this.alunoSelecionado = aluno;
        }
        
        public void cancelarSelecao(){
            this.alunoSelecionado = null;
        }
        
        public void apoiar(Aluno aluno){
            try {
                votoLogic.apoiar(aluno);
            } catch (ErroSistemaException ex) {
                Logger.getLogger(VotacaoBean.class.getName()).log(Level.SEVERE, null, ex);
                addErro(ex);
            }
            Aluno apoiador = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aluno");
            loginLogic.atualizarAlunoLogado(apoiador);
        }
        public void cancelarApoio(Aluno aluno){
            try {
                votoLogic.cancelarApoio(aluno);
            } catch (ErroSistemaException ex) {
                Logger.getLogger(VotacaoBean.class.getName()).log(Level.SEVERE, null, ex);
                addErro(ex);
            }
        }
        public boolean jaVotou(Aluno alunoParaApoio) {
            Aluno alunoLogado = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aluno");
            boolean votoDado = false;
            List<Voto> votosDados = votoLogic.buscarVotosDados(alunoLogado);
            for(Voto voto : votosDados) {
                votoDado = voto.getAlunoVotado().equals(alunoParaApoio);
                if(votoDado) return votoDado;
            }
            return votoDado;
        }
}
