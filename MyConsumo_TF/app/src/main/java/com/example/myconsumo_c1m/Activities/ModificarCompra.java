package com.example.myconsumo_c1m.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myconsumo_c1m.Models.PeliAComprar;
import com.example.myconsumo_c1m.R;

import java.util.ArrayList;

public class ModificarCompra extends AppCompatActivity {

    private EditText txtNumeroAModifcar_;
    private Button btnModificarCompra_;
    private ArrayList<PeliAComprar> miArrayListModificar;
    private int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_compra);

        txtNumeroAModifcar_ = (EditText) findViewById(R.id.txtNumeroAModifcar);
        btnModificarCompra_ = (Button) findViewById(R.id.btnModificarCompra);
        // Obtener el ArrayList enviado a través del Intent
        miArrayListModificar = getIntent().getParcelableArrayListExtra("listaParaModificar");
        codigo = getIntent().getIntExtra("valorId", 0);
        btnModificarCompra_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for(int i=0;i<miArrayListModificar.size();i++)
                {
                    if(miArrayListModificar.get(i).getId() ==codigo)
                    {
                        int cantidad = Integer.parseInt(txtNumeroAModifcar_.getText().toString());
                        double precioU = miArrayListModificar.get(i).getPrecioUnitario();
                        Double precioT=precioU*cantidad;
                        miArrayListModificar.get(i).setCantidad(cantidad);
                        miArrayListModificar.get(i).setPrecioTotal(precioT);
                        Toast.makeText(ModificarCompra.this, "Se modificó correctamente", Toast.LENGTH_SHORT).show();
                        //Código para redireccionar a una nueva ventana
                        Intent intent = new Intent(ModificarCompra.this, CarritoDeCompras.class);
                        intent.putExtra("listaMoficada", miArrayListModificar);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });

    }
}