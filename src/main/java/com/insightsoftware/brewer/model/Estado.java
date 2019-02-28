package com.insightsoftware.brewer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

  private static final long serialVersionUID = -5176203340659081379L;

  @Id
  private Long codigo;

  @NotEmpty(message = "O nome do estado é obrigatório")
  private String nome;

  @Size(min = 2, max = 2, message = "A sigla de um estado deve ter somente 2 caracteres")
  private String sigla;

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

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
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
    Estado other = (Estado) obj;
    if (codigo == null) {
      if (other.codigo != null) { return false; }
    } else if (!codigo.equals(other.codigo)) { return false; }
    return true;
  }

}
