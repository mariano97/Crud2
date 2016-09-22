package com.usco.mariano.crud2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    //se declaran las variables que se vayan a implementar
    EditText et_nomre, et_apelldo, et_barrio;
    Button bt_modificar, bt_borrar;
    int id;
    String nombre, apellido, barrio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b = getIntent().getExtras();
        if(b!=null){

            id = b.getInt("id");
            nombre = b.getString("nombre");
            apellido = b.getString("apellido");
            barrio = b.getString("barrio");



        }
        //Referenciamos las variables creadas con los edittext en la activity
        et_nomre = (EditText) findViewById(R.id.et_nombre);
        et_apelldo=(EditText) findViewById(R.id.et_apellido);
        et_barrio=(EditText) findViewById(R.id.et_barrio);


        et_nomre.setText(nombre);
        et_apelldo.setText(apellido);
        et_barrio.setText(barrio);
        //Referenciamos las variables creadas con los edittext en la activity
        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_borrar = (Button) findViewById(R.id.bt_borrar);

        //se toma de la bd el id para poder eliminar los datos ingresados y una vez realizada la oeracin
        //regrese a la activit anterior
        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });

        //se hace el llamado del boton, para que se ejecute la accion corespndiente como el onClick y ademas
        //capture los datos que han sido ingresados en la activity y una vez realizada la oeracin
        //regrese a la activit anterior
        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id,et_nomre.getText().toString(),et_apelldo.getText().toString(),et_barrio.getText().toString());
                onBackPressed();
            }
        });

    }

    //Se genera el metodo que tomara los datos ingresados en la activity y para que por medio del id
    //busque los datos en la db y los actualice
    private void Modificar(int Id, String Nombre, String Apellido, String Barrio){
        BaseHeper helper = new BaseHeper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql="update personas set nombre='"+Nombre+"', apellido='"+Apellido+"', barrio='"+Barrio+"' where id="+Id;
        db.execSQL(sql);
        db.close();
    }
    //Se genera el metodo que permitira por medio del id buscar el db el id y la elimnara
    private void Eliminar(int Id){
        BaseHeper helper = new BaseHeper(this,"demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql="delete from personas where id="+Id;
        db.execSQL(sql);
        db.close();
    }

}
