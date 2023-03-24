/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professordanilo.avalia.dao;

import br.com.professordanilo.avalia.entity.Aluno;
import br.com.professordanilo.avalia.entity.Voto;
import br.com.professordanilo.avalia.util.Conexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danilo
 */
public class VotoDAO implements Serializable {
    
    public void salvarApoio(Voto voto){
        EntityManager em = Conexao.getConexao();
        em.getTransaction().begin();
        em.merge(voto);
        em.getTransaction().commit();
    }
    public void remover(Voto voto){
        EntityManager em = Conexao.getConexao();
        em.getTransaction().begin();
        voto = em.find(Voto.class, voto.getId());
        em.remove(voto);
        em.getTransaction().commit();
        Conexao.fecharConexao();
    }
}
