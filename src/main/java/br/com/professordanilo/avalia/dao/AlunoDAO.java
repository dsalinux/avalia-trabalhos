/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.EntityManagerProducer;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Hibernate;

/**
 *
 * @author danilo
 */
public class AlunoDAO implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    public List<Aluno> listaAlunos(){
        Query query = manager.createQuery("from Aluno where url != ''");
        List<Aluno> alunos = query.getResultList();
        for(Aluno aluno : alunos) {
            aluno.getVotosRecebidos().size();
        }
//        Conexao.fecharConexao();
        return alunos;
    }
    
}
