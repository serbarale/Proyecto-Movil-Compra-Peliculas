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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etResultado;
    private EditText etCodigo_;
    private EditText etUrl_;
    private EditText etTitulo_;
    private EditText etPrecio_;
    private EditText etGeneroId_;
    private EditText etFechaL_;
    private EditText etDuracion_;
    private EditText etSinopsis_;
    private Button btnNuevo_;
    private Button btnGrabar_;
    private Button btnBuscar_;
    private Button btnModificar_;
    private Button btnEliminar_;
    private Button btnLimpiar_;
    private Button btnopcion_;
    private Button btnComprar_;
    private ServiceAPI serviceAPI;
    private ProductoController proController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etResultado = (EditText) findViewById(R.id.etResultado_);
        /*Conexion*/
        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        proController = new ProductoController(serviceAPI);
        load();

        /*Metodos de agregar-eliminar-modificar*/
        etCodigo_ = (EditText) findViewById(R.id.etCodigo);
        etTitulo_ = (EditText) findViewById(R.id.etTitulo);
        etGeneroId_ = (EditText) findViewById(R.id.etGeneroId);
        etDuracion_ = (EditText) findViewById(R.id.etDuracion);
        etSinopsis_ = (EditText) findViewById(R.id.etSinopsis);
        etFechaL_ = (EditText) findViewById(R.id.etFechaL);
        etPrecio_ = (EditText) findViewById(R.id.etPrecio);
        etUrl_ = (EditText) findViewById(R.id.etdireccionimagen);
        btnopcion_ = (Button) findViewById(R.id.btnopcion);
        btnLimpiar_ = (Button) findViewById(R.id.btnLimpiar);
        btnNuevo_ = (Button) findViewById(R.id.btnNuevo);
        btnGrabar_ = (Button) findViewById(R.id.btnGrabar);
        btnBuscar_ = (Button) findViewById(R.id.btnBuscar);
        btnModificar_ = (Button) findViewById(R.id.btnModificar);
        btnEliminar_ = (Button) findViewById(R.id.btnEliminar);
        btnComprar_ = (Button) findViewById(R.id.btnComprar);


        btnNuevo_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w){

                etCodigo_.setText(""+proController.getCorrelativo());
                etTitulo_.requestFocus();
            }

        });
        btnBuscar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w){
                int codigo = Integer.parseInt(etCodigo_.getText().toString());
                Producto productazo = proController.get(codigo);
                if (productazo != null) {
                    etTitulo_.setText(productazo.getTitulo());
                    etGeneroId_.setText(String.valueOf(productazo.getGenero_id()));
                    etDuracion_.setText(String.valueOf(productazo.getDuracion()));
                    etSinopsis_.setText(productazo.getSinopsis());
                    etFechaL_.setText(productazo.getfechaLanzamiento());
                    etPrecio_.setText(String.valueOf(productazo.getPrecio()));
                    etUrl_.setText(productazo.getImagenURL());
                } else {
                    Toast.makeText(MainActivity.this, "No se encontró el producto con el código " + codigo, Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnopcion_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código para redireccionar a una nueva ventana
                Intent intent = new Intent(MainActivity.this, NuevaVentana.class);
                startActivity(intent);
            }
        });

        btnComprar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código para redireccionar a una nueva ventana
                Intent intent = new Intent(MainActivity.this, Comprar.class);
                startActivity(intent);
            }
        });

        btnLimpiar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                etCodigo_.setText("");
                etTitulo_.setText("");
                etGeneroId_.setText("");
                etDuracion_.setText("");
                etSinopsis_.setText("");
                etFechaL_.setText("");
                etPrecio_.setText("");
                etUrl_.setText("");
            }
        });
        btnGrabar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final CharSequence[]        opciones={"SI","NO"};
                final AlertDialog.Builder   alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("¿Deseas Grabar el Registro?");
                alert.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(opciones[which].equals("SI"))
                        {

                            Producto pObj = new Producto(Integer.parseInt(etCodigo_.getText().toString()),
                                    etTitulo_.getText().toString(),Integer.parseInt(etGeneroId_.getText().toString()),
                                    Integer.parseInt(etDuracion_.getText().toString()),etSinopsis_.getText().toString(),
                                    etFechaL_.getText().toString(),Double.parseDouble(etPrecio_.getText().toString()),
                                    etUrl_.getText().toString()
                            );
                            addProducto(pObj);

                        }
                        if(opciones[which].equals("NO"))
                        {
                            dialog.dismiss();
                        }
                    }
                });
                alert.show();
            }
        });
        btnModificar_.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final CharSequence[]        opciones={"SI","NO"};
                final AlertDialog.Builder   alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("¿Estas Seguro de Modificar el Registro?");
                alert.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(opciones[which].equals("SI"))
                        {
                            Producto pObj = new Producto(Integer.parseInt(etCodigo_.getText().toString()),
                                    etTitulo_.getText().toString(),Integer.parseInt(etGeneroId_.getText().toString()),
                                    Integer.parseInt(etDuracion_.getText().toString()),etSinopsis_.getText().toString(),
                                    etFechaL_.getText().toString(),Double.parseDouble(etPrecio_.getText().toString()),
                                    etUrl_.getText().toString()
                            );
                            modifyProducto(pObj);
                        }
                        if(opciones[which].equals("NO"))
                        {
                            dialog.dismiss();
                        }
                    }
                });
                alert.show();
            }
        });
        btnEliminar_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final CharSequence[]        opciones={"SI","NO"};
                final AlertDialog.Builder   alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("¿Estas Seguro de Eliminar el Registro?");
                alert.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(opciones[which].equals("SI"))
                        {
                            eliminarProducto(Integer.parseInt(etCodigo_.getText().toString()));
                        }
                        if(opciones[which].equals("NO"))
                        {
                            dialog.dismiss();
                        }
                    }
                });
                alert.show();
            }
        });
    }
    public void addProducto(Producto pObj)
    {
        Call<Producto> call = serviceAPI.addProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    Producto pro = response.body();
                    mensaje("Registro grabado satisfactoriamente!");

                    etCodigo_.setText("");
                    etTitulo_.setText("");
                    etGeneroId_.setText("");
                    etDuracion_.setText("");
                    etSinopsis_.setText("");
                    etFechaL_.setText("");
                    etPrecio_.setText("");
                    etUrl_.setText("");
                    load();
                }
                else
                {
                    mensaje("Ocurrio un error al grabar los datos!");
                }
            }
            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error desconocido!" + t.getMessage());
            }
        });
    }
    private void modifyProducto(Producto pObj) {
        Call<Producto> call = serviceAPI.modifyProducto(pObj);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    Producto pro = response.body();

                    mensaje("Los datos se modificaron satisfactoriamente!!!");
                    load();
                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
            }
        });
    }
    private void eliminarProducto(int parseInt) {
        Call<Producto> call = serviceAPI.deleteProducto(parseInt);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful())
                {
                    mensaje("Los datos se eliminaron satisfactoriamente!!!");
                    load();

                }
                else
                {
                    mensaje("Ocurrio un error desconocido!!!");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mensaje("Ocurrio un error!!!" + t.getMessage());
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
                    etResultado.setText("");
                    List<Producto> lst = response.body();
                    etResultado.append("TODAS LAS PELICULAS"+"\n");
                    for(Producto x:lst)
                    {
                        etResultado.append("ID: "+x.getId()+"\n"+"TITULO: "+x.getTitulo()+"\n"+"GENERO ID: "+x.getGenero_id()+"\n"+"DURACION: "+x.getDuracion()+"\n"+"SINOPSIS: "+x.getSinopsis()+"\n"+"FECHA DE LANZAMIENTO: "+x.getfechaLanzamiento()+"\n"+"PRECIO: "+x.getPrecio()+"\n"+"URL: "+x.getImagenURL()+"\n"+"\n");
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

    public void mensaje(String msg)
    {
        android.app.AlertDialog.Builder alerta = new android.app.AlertDialog.Builder(this);
        alerta.setMessage(msg);
        alerta.show();
    }


}