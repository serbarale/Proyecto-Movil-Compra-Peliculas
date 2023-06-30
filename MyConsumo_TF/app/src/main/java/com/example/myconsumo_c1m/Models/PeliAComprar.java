package com.example.myconsumo_c1m.Models;
import android.os.Parcel;
import android.os.Parcelable;

public class PeliAComprar implements Parcelable {
    private int id;
    private String titulo;
    private double precioUnitario;
    private int cantidad;
    private double precioTotal;
    private String imagenURL;

    public PeliAComprar() {

    }

    public PeliAComprar(int id, String titulo, double precioUnitario, int cantidad, String imagenURL, double precioTotal) {
        this.id = id;
        this.titulo = titulo;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.imagenURL = imagenURL;
        this.precioTotal = precioTotal;
    }

    protected PeliAComprar(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        precioUnitario = in.readDouble();
        cantidad = in.readInt();
        precioTotal = in.readDouble();
        imagenURL = in.readString();
    }

    public static final Creator<PeliAComprar> CREATOR = new Creator<PeliAComprar>() {
        @Override
        public PeliAComprar createFromParcel(Parcel in) {
            return new PeliAComprar(in);
        }

        @Override
        public PeliAComprar[] newArray(int size) {
            return new PeliAComprar[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeDouble(precioUnitario);
        dest.writeInt(cantidad);
        dest.writeDouble(precioTotal);
        dest.writeString(imagenURL);
    }
}
