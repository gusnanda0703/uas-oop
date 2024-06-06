package models;

import interfaces.WithId;

public class ReadList implements WithId{
    private static int autoIncrement = 0;

    private int id;
    private Pengguna pengguna;
    private Buku buku;

    public ReadList(Pengguna pengguna, Buku buku) {
        this.id = ++autoIncrement;
        this.pengguna = pengguna;
        this.buku = buku;
    }

    public int getId() {
        return id;
    }

    public User getPengguna() {
        return pengguna;
    }

    public Buku getBuku() {
        return buku;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReadList other = (ReadList) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReadList [id=" + id + ", pengguna=" + pengguna + ", buku=" + buku + "]";
    }

}
