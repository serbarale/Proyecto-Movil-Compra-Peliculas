package com.example.myconsumo_c1m.Controllers;
import com.example.myconsumo_c1m.Api.ServiceAPI;
import com.example.myconsumo_c1m.Models.Producto;
import java.util.ArrayList;

public class ProductoController
{
    private ServiceAPI serviceAPI;
    private ArrayList<Producto> listaPeliculas;

    public ProductoController(ServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
        this.listaPeliculas = new ArrayList<>();
    }

    public ArrayList<Producto> getListaPeliculas() {
        return listaPeliculas;
    }

    public void add(Producto producto)
    {
        listaPeliculas.add(producto);
    }
    public Producto get(int codigo) {
        for (Producto producto : listaPeliculas) {
            if (producto.getId() == codigo) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto con el código dado
    }
    public int size()
    {
        return listaPeliculas.size();
    }
    public void remove(Producto producto)
    {
        listaPeliculas.remove(producto);
    }
    public int getCorrelativo()
    {
        if(listaPeliculas.size()==0)
        {
            return 1;
        }

        else
        {
            return listaPeliculas.get(size()-1).getId() +1;
        }

    }
}


