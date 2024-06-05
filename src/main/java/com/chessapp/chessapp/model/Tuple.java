package com.chessapp.chessapp.model;

/**
 * Classe Tuple personnalisée qui stocke deux valeurs
 */
public class Tuple {

    private Number first;
    private Number second;

    /**
     * Constructeur du Tuple
     * @param first première valeur
     * @param second seconde valeur
     */
    public Tuple(Number first, Number second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Renvoie la première valeur
     * @return Number
     */
    public Number getFirst() {
        return first;
    }

    /**
     * Renvoie la seconde valeur
     * @return Number
     */
    public Number getSecond() {
        return second;
    }

    /**
     * Remplace la première valeur
     * @param first valeur de remplacement
     */
    public void setFirst(Number first) {
        this.first = first;
    }

    /**
     * Remplace la seconde valeur
     * @param second valeur de remplacement
     */
    public void setSecond(Number second) {
        this.second = second;
    }

    /**
     * Vérifie l'égalité entre deux tuples.
     * Vraie si et seulement si les deux valeurs sont égales
     * @param obj objet comparé
     * @return booléen
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Tuple compared = (Tuple) obj;
        return (this.first.equals(compared.first) && this.second.equals(compared.second));
    }

    /**
     * ToString basique
     * @return String
     */
    @Override
    public String toString() {
        return "Tuple [first=" + first + ", second=" + second + "]";
    }
}
