package com.chessapp.chessapp.model;

public class Tuple {

    private Number first;
    private Number second;

    public Tuple(Number first, Number second) {
        this.first = first;
        this.second = second;
    }

    public Number getFirst() {
        return first;
    }

    public Number getSecond() {
        return second;
    }

    public void setFirst(Number first) {
        this.first = first;
    }

    public void setSecond(Number second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Tuple compared = (Tuple) obj;
        return (this.first == compared.first && this.second == compared.second);
    }
}
