/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.util.Conexao;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class LoginDAO implements Serializable{
    
    public Aluno buscarAlunoPorLoginSenha(String login, String senha){
        EntityManager em = Conexao.getConexao();
        Query query = em.createQuery("from Aluno where login=:login and senha=:senha", Aluno.class);
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        Aluno aluno = (Aluno) query.getSingleResult();
        aluno.getVotosDados().size();
        return aluno;
    }
    public Aluno buscarPorId(Integer id){
        EntityManager em = Conexao.getConexao();
        Aluno aluno = em.find(Aluno.class, id);
        aluno.getVotosDados().size();
        Conexao.fecharConexao();
        return aluno;
    }
}
