package crm.model;

public class Utilisateur {

	private Long id;
	private String login;
	private String motDePasse;
	private String email;

	public Utilisateur() {
	}

	public Utilisateur(String login, String motDePasse, String email) {
		this.login = login;
		this.motDePasse = motDePasse;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", motDePasse=" + motDePasse + ", email=" + email + "]";
	}



}
