package br.com.professordanilo.avalia.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "aluno")
@Data
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
}
