package br.com.professordanilo.avalia.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "votos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Votos implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@EqualsAndHashCode.Include
		private Integer id;
	
		@ManyToOne
		@JoinColumn(referencedColumnName = "aluno_votado_id")
		private Aluno alunoVotado;
		
		@ManyToOne
		@JoinColumn(referencedColumnName = "aluno_que_votou_id")
		private Aluno alunoQueVotou;
}
