package br.com.professordanilo.avalia.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String url;
        
        @OneToMany(mappedBy = "alunoVotado")
        private List<Voto> votosRecebidos;
	
        @OneToMany(mappedBy = "alunoQueVotou")
        private List<Voto> votosDados;
        
        @Transient
        public Integer getVotosDisponiveis() {
            int disponivel = 2;
            if(votosDados != null) {
                disponivel = 2 - votosDados.size();
            }
            return disponivel;
        }
        
}
