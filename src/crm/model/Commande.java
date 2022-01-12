package crm.model;

public class Commande {

	private Long id;
	private String label;
	private float tjmHT;
	private float dureeJours;
	private float TVA;
	private Statut statut;
	private TypeCommande typeCommande;
	private String notes;
	private Client client;

	public Commande() {
	}

	public Commande(String label, float tjmHT, float dureeJours, float tVA, Statut statut, TypeCommande typeCommande,
			Client client) {
		this.label = label;
		this.tjmHT = tjmHT;
		this.dureeJours = dureeJours;
		TVA = tVA;
		this.statut = statut;
		this.typeCommande = typeCommande;
		this.client = client;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getTjmHT() {
		return tjmHT;
	}

	public void setTjmHT(float tjmHT) {
		this.tjmHT = tjmHT;
	}

	public float getDureeJours() {
		return dureeJours;
	}

	public void setDureeJours(float dureeJours) {
		this.dureeJours = dureeJours;
	}

	public float getTVA() {
		return TVA;
	}

	public void setTVA(float tVA) {
		TVA = tVA;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public TypeCommande getTypeCommande() {
		return typeCommande;
	}

	public void setTypeCommande(TypeCommande typeCommande) {
		this.typeCommande = typeCommande;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", label=" + label + ", tjmHT=" + tjmHT + ", dureeJours=" + dureeJours + ", TVA="
				+ TVA + ", statut=" + statut + ", typeCommande=" + typeCommande + ", notes=" + notes + ", client="
				+ client + "]";
	}

}
