package com.example.myconsumo_c1m.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myconsumo_c1m.Controllers.PeliAComprarController;
import com.example.myconsumo_c1m.Models.PeliAComprar;
import com.example.myconsumo_c1m.Models.Producto;
import com.example.myconsumo_c1m.R;

import java.util.ArrayList;

public class CarritoDeCompras extends AppCompatActivity {
    private Button btnatraasCarrito_;
    private ArrayList<PeliAComprar> miArrayList;
    private TableLayout tbtTablaCarrito_;
    private EditText etTotalSuma_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_de_compras);

        tbtTablaCarrito_ = findViewById(R.id.tbtTablaCarrito2);
        btnatraasCarrito_= (Button) findViewById(R.id.btnatraasCarrito);
        etTotalSuma_ = (EditText) findViewById(R.id.etTotalSuma);
        // Obtener el ArrayList enviado a través del Intent
        miArrayList = getIntent().getParcelableArrayListExtra("listaPeliculasAComprar");

        btnatraasCarrito_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código para redireccionar a una nueva ventana
                Intent intent = new Intent(CarritoDeCompras.this, Comprar.class);
                intent.putExtra("listaPeliculasCompradas", miArrayList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Crear la primera fila (encabezado)
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView header1 = new TextView(this);
        header1.setText("ID P");
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        header1.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header1);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header2 = new TextView(this);
        header2.setText("PRECIO U.");
        header2.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header2);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header3 = new TextView(this);
        header3.setText("PRECIO TOTAL");
        header3.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header3);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header4 = new TextView(this);
        header4.setText("CANTIDAD");
        header4.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header4);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header5 = new TextView(this);
        header5.setText("OP1");
        header5.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header5);
        //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
        TextView header6 = new TextView(this);
        header6.setText("OP2");
        header6.setLayoutParams(new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f // Peso igual para cada columna
        ));
        headerRow.addView(header6);

        tbtTablaCarrito_.addView(headerRow);
        createTableRows2();

    }
    public void createTableRows2() {
        // Eliminar todas las filas existentes antes de agregar las nuevas filas
        tbtTablaCarrito_.removeAllViews();

        for (int i = 0; i < miArrayList.size(); i++) {
            final int index = i;
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            TextView cell1 = new TextView(this);
            cell1.setText(String.valueOf(miArrayList.get(i).getId()));
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell1.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell1);

            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf(miArrayList.get(i).getPrecioUnitario()));
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell2.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell2);

            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf(miArrayList.get(i).getPrecioTotal()));
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell3.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell3);

            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf(miArrayList.get(i).getCantidad()));
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell4.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell4);

            Button cell5 = new Button(this);
            cell5.setText("MO");
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell5.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell5);

            //pasar el la cod
            int codigoPelicula = miArrayList.get(index).getId();
            cell5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CharSequence[]        opciones={"SI","NO"};
                    final AlertDialog.Builder   alert=new AlertDialog.Builder(CarritoDeCompras.this);
                    alert.setTitle("¿Deseas Modificar esta Compra?");
                    alert.setItems(opciones, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(opciones[which].equals("SI"))
                            {
                                //Código para redireccionar a una nueva ventana
                                Intent intent = new Intent(CarritoDeCompras.this, ModificarCompra.class);
                                intent.putExtra("listaParaModificar", miArrayList);
                                intent.putExtra("valorId",codigoPelicula);
                                startActivityForResult(intent, 1);
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

            Button cell6 = new Button(this);
            cell6.setText("EL");
            //CAMBIAR EL TAMAÑO DE LAS COLUMNAS
            cell6.setLayoutParams(new TableRow.LayoutParams(
                    0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(cell6);
            cell6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CharSequence[]        opciones={"SI","NO"};
                    final AlertDialog.Builder   alert=new AlertDialog.Builder(CarritoDeCompras.this);
                    alert.setTitle("¿Deseas Eliminar esta Compra?");
                    alert.setItems(opciones, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(opciones[which].equals("SI"))
                            {
                                PeliAComprar peliAEliminar = miArrayList.get(index);
                                miArrayList.remove(peliAEliminar);
                                createTableRows2();
                                //ACTUALIZAR LA SUMA DE LAS PELIS
                                Double sumaTodasCompras=0.00;
                                for(int i = 0; i < miArrayList.size(); i++)
                                {
                                    sumaTodasCompras+=miArrayList.get(i).getPrecioTotal();
                                }
                                etTotalSuma_.setText(String.valueOf(sumaTodasCompras));
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

            tbtTablaCarrito_.addView(row);
        }
        Double sumaTodasCompras=0.00;
        for(int i = 0; i < miArrayList.size(); i++)
        {
            sumaTodasCompras+=miArrayList.get(i).getPrecioTotal();
        }
        etTotalSuma_.setText(String.valueOf(sumaTodasCompras));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ArrayList<PeliAComprar> listaPeliculasCompradas = data.getParcelableArrayListExtra("listaMoficada");
            // Actualiza el ArrayList con los datos devueltos
            miArrayList.clear();
            miArrayList.addAll(listaPeliculasCompradas);
            // Vuelve a crear las filas de la tabla con los nuevos datos
            createTableRows2();
        }
    }

}