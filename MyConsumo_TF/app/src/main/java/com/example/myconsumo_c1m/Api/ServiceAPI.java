package com.example.myconsumo_c1m.Api;
import com.example.myconsumo_c1m.Models.Producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceAPI
{
    @GET("producto")
    public abstract Call<List<Producto>> listProduct();
    public abstract Call<ArrayList<Producto>> listaPeliculas();

    @POST("producto/agregar")
    public abstract Call<Producto> addProducto(@Body Producto obj);
    @PUT("producto/modificar")
    public abstract Call<Producto> modifyProducto(@Body Producto obj);
    @DELETE("producto/eliminar/{id}")
    public abstract Call<Producto> deleteProducto(@Path("id") int id);

}
