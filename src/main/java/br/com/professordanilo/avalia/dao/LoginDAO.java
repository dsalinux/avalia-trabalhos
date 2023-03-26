/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.EntityManagerProducer;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class LoginDAO implements Serializable{

    @Inject
    private EntityManager manager;
    
    public Aluno buscarAlunoPorLoginSenha(String login, String senha){
        Query query = manager.createQuery("from Aluno where login=:login and senha=:senha", Aluno.class);
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        Aluno aluno = (Aluno) query.getSingleResult();
        aluno.getVotosDados().size();
        return aluno;
    }
    public Aluno buscarPorId(Integer id){
        Aluno aluno = manager.find(Aluno.class, id);
        aluno.getVotosDados().size();
//        Conexao.fecharConexao();
        return aluno;
    }
}
