package backendDietiEstates.backendDietiEstates.Entity;

public class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	
	//Getter and Setter for nome
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Getter and Setter for cognome
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	//Getter and Setter for email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Getter and Setter for password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
