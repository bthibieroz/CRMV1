package crm.model;

public class Client {
	
	private Long id;
	private String nom;
	private String prenom;
	private String entreprise;
	private String email;
	private String telephone;
	private String mobile;
	private boolean actif;
	
	public Client() {	
	}

	public Client(String nom, String prenom, String entreprise, String email, String telephone, String mobile,
			boolean actif) {
		this.nom = nom;
		this.prenom = prenom;
		this.entreprise = entreprise;
		this.email = email;
		this.telephone = telephone;
		this.mobile = mobile;
		this.actif = actif;
	}

	public Client(String nom, String prenom, String entreprise, String email, String telephone, boolean actif) {
		this.nom = nom;
		this.prenom = prenom;
		this.entreprise = entreprise;
		this.email = email;
		this.telephone = telephone;
		this.actif = actif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	
	@Override
	public String toString() {
		return "Client [nom=" + getNom() + ", prenom=" + prenom + ", entreprise=" + entreprise + ", email=" + email
				+ ", telephone=" + telephone + ", mobile=" + this.mobile + ", actif=" + actif + "]";
	}

	
}
