package br.com.fib.Biblioteca.beans;

import java.util.Date;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo {
	
	@Id
        @GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
        @Column(name = "DATA_EMPRESTIMO")
	private Date dataEmprestimo;
	
	@Temporal(TemporalType.DATE)
        @Column(name = "DATA_DEVOLUCAO")
	private Date dataDevolucao;
	
	@ManyToOne
	private Livro livro;
        
        @ManyToOne
	private Usuario usuario;
        
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}