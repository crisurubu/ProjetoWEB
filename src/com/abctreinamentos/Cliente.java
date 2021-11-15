package com.abctreinamentos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENTE")
public class Cliente implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private long cpf;
	private String nome;
	private String email;

	public Cliente() {
	}

	public Cliente(long cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public Cliente(long cpf, String nome, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}

	@Id
	@Column(name = "CPF", unique = true, nullable = false, precision = 22, scale = 0)
	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Column(name = "NOME", nullable = false, length = 80)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Cliente [cpf="+cpf+" nome="+nome+" email="+email+"]";
	}
}
