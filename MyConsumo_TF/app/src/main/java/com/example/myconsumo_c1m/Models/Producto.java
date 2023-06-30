package com.example.myconsumo_c1m.Models;
public class Producto
{
    private int     id;
    private String  titulo;
    private int genero_id;
    private int duracion;
    private String sinopsis;
    private String fechaLanzamiento;
    private double precio;
    private String imagenURL;

    public Producto(int id, String titulo, int genero_id, int duracion, String sinopsis, String fechaLanzamiento, double precio, String imagenURL) {
        this.id = id;
        this.titulo = titulo;
        this.genero_id = genero_id;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
        this.imagenURL = imagenURL;
    }
    public Producto()
    {

    }

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

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getfechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setfechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}
