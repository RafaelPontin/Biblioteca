package br.com.fib.Biblioteca.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
        @NotNull
        @Size(min = 4, max = 30)
	private String username;
	
        @NotNull()
        @Size(min = 5, max = 30)
	private String password;
	
	@OneToMany(mappedBy="usuario")
	private List<Review> reviews = new ArrayList<>();
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passsword) {
		this.password = passsword;
	}
	
	
	
	
	
	

}
