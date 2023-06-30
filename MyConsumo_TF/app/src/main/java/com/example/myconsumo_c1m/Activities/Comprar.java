package com.example.myconsumo_c1m.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myconsumo_c1m.Api.ServiceAPI;
import com.example.myconsumo_c1m.Conexion.ConnectionREST;
import com.example.myconsumo_c1m.Controllers.PeliAComprarController;
import com.example.myconsumo_c1m.Controllers.ProductoController;
import com.example.myconsumo_c1m.Models.PeliAComprar;
import com.example.myconsumo_c1m.Models.Producto;
import com.example.myconsumo_c1m.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comprar extends AppCompatActivity {

    private TableLayout tbtTabla_;
    private ServiceAPI serviceAPI;
    private Button btnatraasC_;

    private ProductoController proController;
    private PeliAComprarController peliAComprarController;
    private ArrayList<PeliAComprar> miArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        tbtTabla_ = findViewById(R.id.tbtTablaCarrito);

        btnatraasC_ = (Button) findViewById(R.id.btnatraasC);
        peliAComprarController = new PeliAComprarController();
        btnatraasC_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código para redireccionar a una nueva ventana
                Intent intent = new Intent(Comprar.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Crear la primera fila (encabezado)
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView header1 = new TextView(this);
        header1.setText("ID PELICULA");
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        header1.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header1);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header2 = new TextView(this);
        header2.setText("TITULO");
        header2.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header2);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header3 = new TextView(this);
        header3.setText("PRECIO");
        header3.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header3);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header4 = new TextView(this);
        header4.setText("OPCION");
        header4.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header4);

        tbtTabla_.addView(headerRow);

        serviceAPI = ConnectionREST.getConnection().create(ServiceAPI.class);
        proController = new ProductoController(serviceAPI);
        load();
    }

        public void load() {
            Call<List<Producto>> call = serviceAPI.listProduct();
            call.enqueue(new Callback<List<Producto>>() {
                @Override
                public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                    if (response.isSuccessful()) {
                        List<Producto> lst = response.body();
                        proController.getListaPeliculas().clear();
                        proController.getListaPeliculas().addAll(lst);
                        createTableRows();
                    } else {
                        Toast.makeText(Comprar.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Producto>> call, Throwable t) {
                    Toast.makeText(Comprar.this, "Error Failure", Toast.LENGTH_SHORT).show();
                }
        });
    }

    public void createTableRows() {
            ArrayList<Producto> arrayList = proController.getListaPeliculas();
            for (int i = 0; i < arrayList.size(); i++) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                TextView cell1 = new TextView(this);
                cell1.setText(String.valueOf(arrayList.get(i).getId()));
                //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
                cell1.setLayoutParams(new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                row.addView(cell1);

                TextView cell2 = new TextView(this);
                cell2.setText(String.valueOf(arrayList.get(i).getTitulo()));
                //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
                cell2.setLayoutParams(new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                row.addView(cell2);

                TextView cell3 = new TextView(this);
                cell3.setText(String.valueOf(arrayList.get(i).getPrecio()));
                //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
                cell3.setLayoutParams(new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                row.addView(cell3);

                Button cell4 = new Button(this);
                cell4.setText("COMPRAR");
                //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
                cell4.setLayoutParams(new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

                int idd=arrayList.get(i).getId();
                String titulo =arrayList.get(i).getTitulo();
                Double preciazo =arrayList.get(i).getPrecio();
                String urll=arrayList.get(i).getImagenURL();
                int ca=1;
                Double tot=preciazo*ca;
                cell4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isExisting = false;
                        for (int i = 0; i < peliAComprarController.getListaPeliculasAComprar().size(); i++) {
                            if(peliAComprarController.getListaPeliculasAComprar().get(i).getId() == idd)
                            {
                                int can2=ca+peliAComprarController.getListaPeliculasAComprar().get(i).getCantidad();
                                Double precioT=preciazo*can2;
                                peliAComprarController.getListaPeliculasAComprar().get(i).setCantidad(can2);
                                peliAComprarController.getListaPeliculasAComprar().get(i).setPrecioTotal(precioT);
                                isExisting = true;
                                break;
                            }
                        }
                            if(!isExisting)
                            {
                                PeliAComprar peliAComprar = new PeliAComprar();

                                peliAComprar.setId(idd);
                                peliAComprar.setTitulo(titulo);
                                peliAComprar.setPrecioUnitario(preciazo);
                                peliAComprar.setCantidad(ca);
                                peliAComprar.setPrecioTotal(tot);
                                peliAComprar.setImagenURL(urll);

                                peliAComprarController.add(peliAComprar);
                            }



                        // Código para redireccionar a una nueva ventana
                        Intent intent = new Intent(Comprar.this, CarritoDeCompras.class);
                        intent.putExtra("listaPeliculasAComprar", peliAComprarController.getListaPeliculasAComprar());
                        startActivityForResult(intent, 1);
                    }
                });
                row.addView(cell4);
                tbtTabla_.addView(row);
            }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<PeliAComprar> listaPeliculasCompradas = data.getParcelableArrayListExtra("listaPeliculasCompradas");
            // Actualiza el ArrayList con los datos devueltos
            peliAComprarController.getListaPeliculasAComprar().clear();
            peliAComprarController.getListaPeliculasAComprar().addAll(listaPeliculasCompradas);
            // Vuelve a crear las filas de la tabla con los nuevos datos
            createTableRows();
        }
    }

}