package models;

import enums.Status;
import interfaces.WithId;

public class Buku implements WithId {
    private static int autoIncrement = 0;

    private int id;
    private String judul;
    private Penulis penulis;
    private Penerbit penerbit;
    private int tahunTerbit;
    private Status status;

    public Buku(String judul, Penulis penulis) {
        this.id = ++autoIncrement;
        this.judul = judul;
        this.penulis = penulis;
        this.status = Status.DRAFTED;
    }

    public Buku(String judul, Penulis penulis, Penerbit penerbit, int tahunTerbit) {
        this.id = ++autoIncrement;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.status = Status.PUBLISHED;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public Penulis getPenulis() {
        return penulis;
    }

    public Penerbit getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(Penerbit penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        Buku other = (Buku) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Buku [id=" + id + ", judul=" + judul + ", penulis=" + penulis + ", penerbit=" + penerbit
                + ", tahunTerbit=" + tahunTerbit + ", status=" + status + "]";
    }

}
