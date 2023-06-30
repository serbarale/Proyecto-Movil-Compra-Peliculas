package com.example.myconsumo_c1m.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myconsumo_c1m.Api.ServiceAPI;
import com.example.myconsumo_c1m.Conexion.ConnectionREST;
import com.example.myconsumo_c1m.Controllers.ProductoController;
import com.example.myconsumo_c1m.Models.Producto;
import com.example.myconsumo_c1m.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaVentana extends AppCompatActivity {

    private ServiceAPI serviceAPI;
    private ProductoController proController;
    private EditText etFechaInicio_;
    private EditText etFechaFinal_;
    private Button btnbuscarfecha_;
    private EditText etResultado2_;
    private Button btnAtras_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_ventana);

        etResultado2_ = (EditText) findViewById(R.id.etResultado2);
        /*Conexion*/
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        proController = new ProductoController(serviceAPI);
        load();

        etFechaInicio_ = (EditText) findViewById(R.id.etFechaInicio);
        etFechaFinal_ = (EditText) findViewById(R.id.etFechaFinal);
        btnbuscarfecha_ = (Button) findViewById(R.id.btnbuscarfecha);
        btnAtras_ = (Button) findViewById(R.id.btnAtras);

        btnbuscarfecha_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fechaii =etFechaInicio_.getText().toString();

                String[] partesInicial = fechaii.split("-");

                String añoInicial = partesInicial[0];
                int añoInicialT = Integer.parseInt(añoInicial);

                String mesInicial = partesInicial[1];
                int mesInicialT = Integer.parseInt(mesInicial);
                String diaInicial = partesInicial[2];
                int diaInicialT = Integer.parseInt(diaInicial);

                String fechaff= etFechaFinal_.getText().toString();
                String[] partesFinal = fechaff.split("-");

                String añoFinal = partesFinal[0];
                int añoFinalT = Integer.parseInt(añoFinal);
                String mesFinal = partesFinal[1];
                int mesFinalT = Integer.parseInt(mesFinal);
                String diaFinal = partesFinal[2];
                int diaFinalT = Integer.parseInt(diaFinal);

                //LIMPIA
                etResultado2_.setText("");

                ArrayList<Producto> arrayList = proController.getListaPeliculas();
                for(int i=0; i<arrayList.size();i++)
                {
                    String fechabb = arrayList.get(i).getfechaLanzamiento();
                    String[] partesBuscados = fechabb.split("-");

                    String añoBuscado = partesBuscados[0];
                    int añoBuscadoT = Integer.parseInt(añoBuscado);
                    String mesBuscado = partesBuscados[1];
                    int mesBuscadoT = Integer.parseInt(mesBuscado);
                    String diaBuscado = partesBuscados[2];
                    int diaBuscadoT = Integer.parseInt(diaBuscado);

                    //PRIMERO
                    if(añoInicialT<añoBuscadoT && añoBuscadoT<añoFinalT)
                    {
                        etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                    }
                    //SEGUNDO
                    if(añoInicialT==añoBuscadoT && añoBuscadoT<añoFinalT)
                    {
                        if(mesInicialT<mesBuscadoT)
                        {
                            etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                    arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                    +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                    arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                    "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                        }
                        else if (mesInicialT==mesBuscadoT)
                        {
                            if(diaInicialT<=diaBuscadoT)
                            {
                                etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                        arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                        +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                        arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                        "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                            }
                        }

                    }
                    //TERCERO
                    if(añoInicialT==añoBuscadoT && añoBuscadoT==añoFinalT)
                    {
                        if(mesInicialT<mesBuscadoT && mesBuscadoT<mesFinalT)
                        {
                            etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                    arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                    +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                    arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                    "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                        }
                        else if (mesInicialT==mesBuscadoT && mesBuscadoT<mesFinalT)
                        {
                            if(diaInicialT<=diaBuscadoT)
                            {
                                etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                        arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                        +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                        arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                        "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                            }
                        }
                        else if (mesInicialT==mesBuscadoT && mesBuscadoT==mesFinalT)
                        {
                            if(diaInicialT<=diaBuscadoT && diaBuscadoT<=diaFinalT)
                            {
                                etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                        arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                        +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                        arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                        "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                            }
                        }
                        else if(mesInicialT<mesBuscadoT && mesBuscadoT==mesFinalT)
                        {
                            if(diaBuscadoT<=diaFinalT)
                            {
                                etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                        arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                        +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                        arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                        "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                            }
                        }

                    }
                    //CUARTO
                    if(añoInicialT<añoBuscadoT && añoBuscadoT==añoFinalT)
                    {
                        if(mesBuscadoT<mesFinalT)
                        {
                            etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                    arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                    +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                    arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                    "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                        }
                        else if (mesBuscadoT==mesFinalT)
                        {
                            if(diaBuscadoT<=diaFinalT)
                            {
                                etResultado2_.append("ID: "+arrayList.get(i).getId()+"\n"+"GENERO ID: "+
                                        arrayList.get(i).getGenero_id()+"\n"+"DURACION: "+arrayList.get(i).getDuracion()+"\n"
                                        +"SINOPSIS: "+arrayList.get(i).getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+
                                        arrayList.get(i).getfechaLanzamiento()+"\n"+"PRECIO: "+arrayList.get(i).getPrecio()+"\n"+
                                        "URL: "+arrayList.get(i).getImagenURL()+"\n"+"\n");
                            }
                        }


                    }

                }
            }
        });

        btnAtras_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código para redireccionar a una nueva ventana
                Intent intent = new Intent(NuevaVentana.this, MainActivity.class);
                startActivity(intent);
            }
        });

     }
    public void load()
    {
        Call<List<Producto>> call = serviceAPI.listProduct();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.isSuccessful())
                {
                    etResultado2_.setText("");
                    List<Producto> lst = response.body();
                    etResultado2_.append("TODAS LAS PELICULAS"+"\n");
                    for(Producto x:lst)
                    {
                        etResultado2_.append("ID: "+x.getId()+"\n"+"GENERO ID: "+x.getGenero_id()+"\n"+"DURACION: "+x.getDuracion()+"\n"+"SINOPSIS: "+x.getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+x.getfechaLanzamiento()+"\n"+"PRECIO: "+x.getPrecio()+"\n"+"URL: "+x.getImagenURL()+"\n"+"\n");
                    }
                    // Actualizar la listaPeliculas con los datos obtenidos
                    proController.getListaPeliculas().clear();
                    proController.getListaPeliculas().addAll(lst);
                }
                else
                {
                    Toast.makeText(null,"Error",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(null,"Error Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}