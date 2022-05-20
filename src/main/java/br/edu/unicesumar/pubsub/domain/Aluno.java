package br.edu.unicesumar.pubsub.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
<<<<<<< HEAD
public class Aluno implements Serializable {
=======
public class Aluno implements Serializable{
>>>>>>> a6ca68deb75465eccafd39a69217ad806e2220d2

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String matricula;

    @NotBlank
    private String nome;

    // private LocalDate dataNascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id")
    private List<Prova> provas = new ArrayList<>();

}