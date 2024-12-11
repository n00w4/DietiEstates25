package dietiEstates.backendDietiEstates.Data;

public abstract class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	
	public Utente(String nome, String cognome, String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	
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
