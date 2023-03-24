/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.Conexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Hibernate;

/**
 *
 * @author danilo
 */
public class AlunoDAO implements Serializable {
    
    public List<Aluno> listaAlunos(){
        EntityManager em = Conexao.getConexao();
        Query query = em.createQuery("from Aluno where url != ''");
        List<Aluno> alunos = query.getResultList();
        for(Aluno aluno : alunos) {
            aluno.getVotosRecebidos().size();
        }
        Conexao.fecharConexao();
        return alunos;
    }
    
}
