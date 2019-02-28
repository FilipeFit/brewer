package com.insightsoftware.brewer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "estilo")
public class Estilo implements Serializable {

  private static final long serialVersionUID = 5356299631703687154L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @NotEmpty(message = "O nome do estilo é obrigatório")
  private String nome;

  @OneToMany(mappedBy = "estilo")
  @Size(min = 1, max = 50, message = "A nome deve ter entre 1 e 50 caracteres")
  private List<Cerveja> cervejas;

  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) { return true; }
    if (obj == null) { return false; }
    if (getClass() != obj.getClass()) { return false; }
    Estilo other = (Estilo) obj;
    if (codigo == null) {
      if (other.codigo != null) { return false; }
    } else if (!codigo.equals(other.codigo)) { return false; }
    return true;
  }

}
