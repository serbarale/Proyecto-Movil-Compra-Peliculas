package com.example.myconsumo_c1m.Controllers;
import com.example.myconsumo_c1m.Models.PeliAComprar;

import java.util.ArrayList;

public class PeliAComprarController {
    private ArrayList<PeliAComprar> agregarArray;

    public PeliAComprarController(){
        agregarArray = new ArrayList<>();
    }
    public void add(PeliAComprar peliAC){
        agregarArray.add(peliAC);
    }
    public ArrayList<PeliAComprar> getListaPeliculasAComprar() {
        return agregarArray;
    }
    public int size(){
        return agregarArray.size();
    }

    public void eliminarC(int codigoA){
        for (int i = 0; i < agregarArray.size(); i++) {
            if (agregarArray.get(i).getId() == codigoA) {
                agregarArray.remove(i);
                break; // Terminar el bucle una vez que se elimine el elemento
            }
        }
    }

    public void modificar(int codigo, int cantidad, double precioU)
    {
        double precioT = cantidad*precioU;
        for(int i=0; i<agregarArray.size();i++)
        {
            if(agregarArray.get(i).getId()==codigo)
            {
                PeliAComprar peliAComprar = new PeliAComprar();
                peliAComprar.setCantidad(cantidad);
                peliAComprar.setPrecioUnitario(precioU);
                peliAComprar.setPrecioTotal(precioT);
                agregarArray.set(i, peliAComprar);
            }
        }
    }

    public PeliAComprar get(int codigoC) {
        for (PeliAComprar producto : agregarArray) {
            if (producto.getId() == codigoC) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto con el código dado
    }


}
