package models;

public class Client {
    private String nom;

    public Client(String nom) {
        this.nom = nom;
    }

    int numeroUnique;

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                '}';
    }
}