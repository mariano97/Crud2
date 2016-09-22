package com.usco.mariano.crud2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //se declaran las variables que se vayan a implementar
    EditText et_nomre, et_apelldo, et_barrio;
    Button bt_guardar, bt_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referenciamos las variables creadas con los edittext en la activity
        et_nomre = (EditText) findViewById(R.id.et_nombre);
        et_apelldo=(EditText) findViewById(R.id.et_apellido);
        et_barrio=(EditText) findViewById(R.id.et_barrio);

        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);
        //se hace el llamado del boton, para que se ejecute la accion corespndiente como el onClick y ademas
        //capture los datos que han sido ingresados en la activity
        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(et_nomre.getText().toString(), et_apelldo.getText().toString(), et_barrio.getText().toString());

            }
        });
        //se hace el llamado del boton, para que se ejecute la accion corespndiente como el onClick y ademas
        //capture los datos que han sido ingresados en la activity
        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, listado.class));
            }
        });

    }
    //se genera el metodo para que los datos ingresados en la base de datos y que ademas muestre un mensaje
    //advirtiendo de un error o que se han ingresado los datos correctamente
    private void guardar (String Nombre, String Apellido, String Barrio){
        BaseHeper helper = new BaseHeper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("nombre",Nombre);
            c.put("apellido", Apellido);
            c.put("barrio", Barrio);

            db.insert("personas",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
