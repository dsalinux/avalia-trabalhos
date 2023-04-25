/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.logic;

import br.com.professordanilo.avalia.dao.VotoDAO;
import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.entity.Voto;
import br.com.professordanilo.avalia.util.Transacional;
import br.com.professordanilo.avalia.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class VotoLogic implements Serializable{
    
    @Inject
    private VotoDAO dao;
    
    @Inject
    private LoginLogic loginLogic;
    
    @Transacional
    public void apoiar(Aluno alunoApoiado) throws ErroSistemaException{
        Aluno apoiador = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aluno");
        if(apoiador == null) {
            throw new ErroSistemaException("Ops, parece que você não está logado.");
        }
        if(apoiador.equals(alunoApoiado)) {
            throw new ErroSistemaException("Você não pode votar em si mesmo. Escolha outro colega.");
        }
        if(loginLogic.buscarPorId(apoiador.getId()).getVotosDados().size() > 1){
            throw new ErroSistemaException("Você chegou ao limite de votos.");
        }
        Voto voto = new Voto();
        voto.setAlunoQueVotou(apoiador);
        voto.setAlunoVotado(alunoApoiado);
        dao.salvarApoio(voto);
    }
    
    @Transacional
    public void cancelarApoio(Aluno alunoApoiado) throws ErroSistemaException{
        Aluno apoiador = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aluno");
        if(apoiador == null) {
            throw new ErroSistemaException("Ops, parece que você não está logado.");
        }
        Voto voto = null;
        for(Voto votoDado : apoiador.getVotosDados()) {
            if(votoDado.getAlunoVotado().equals(alunoApoiado)) {
                voto = votoDado;
                break;
            }
        }
        if(voto == null) {
            return;
        }
        dao.remover(voto);
        apoiador.getVotosDados().remove(voto);
    }

    public List<Voto> buscarVotosDados(Aluno aluno){
        return dao.buscarVotosDados(aluno);
    }
    
    
}
