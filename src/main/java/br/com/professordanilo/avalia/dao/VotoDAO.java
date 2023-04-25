/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.entity.Voto;
import br.com.professordanilo.avalia.util.EntityManagerProducer;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class VotoDAO implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    public void salvarApoio(Voto voto){
        manager.merge(voto);
    }
    public void remover(Voto voto){
        voto = manager.find(Voto.class, voto.getId());
        manager.remove(voto);
    }
    
        
    public List<Voto> buscarVotosDados(Aluno aluno) {
        Query query = manager.createQuery("from Voto where alunoQueVotou=:aluno", Voto.class);
        query.setParameter("aluno", aluno);
        return query.getResultList();
    }
}
