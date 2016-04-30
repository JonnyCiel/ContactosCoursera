package co.com.jonny.tareaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detalles extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextViewNombre;
    private TextView mTextViewFecha;
    private TextView mTextViewTelefono;
    private TextView mTextViewEmail;
    private TextView mTextViewDescripcion;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        mTextViewNombre = (TextView) findViewById(R.id.textView_Nombre);
        mTextViewFecha = (TextView) findViewById(R.id.textView_Fecha);
        mTextViewTelefono = (TextView) findViewById(R.id.textView_Telefono);
        mTextViewEmail = (TextView) findViewById(R.id.textView_Email);
        mTextViewDescripcion = (TextView) findViewById(R.id.textView_Descripcion);

        mButton = (Button) findViewById(R.id.boton_EditarDatos);
        mButton.setOnClickListener(this);

        Intent intent = getIntent();

        if (intent !=null){
            if (intent.hasExtra(MainActivity.NOMBRE)){
                mTextViewNombre.setText(intent.getStringExtra(MainActivity.NOMBRE));
            }else mTextViewNombre.setText(R.string.errorVacio);

            if (intent.hasExtra(MainActivity.FECHA)){
                mTextViewFecha.setText(intent.getStringExtra(MainActivity.FECHA));
            }else mTextViewNombre.setText(R.string.errorVacio);

            if (intent.hasExtra(MainActivity.TELEFONO)){
                mTextViewTelefono.setText(intent.getStringExtra(MainActivity.TELEFONO));
            }else mTextViewTelefono.setText(R.string.errorVacio);

            if (intent.hasExtra(MainActivity.EMAIL)){
                mTextViewEmail.setText(intent.getStringExtra(MainActivity.EMAIL));
            }else mTextViewEmail.setText(R.string.errorVacio);

            if (intent.hasExtra(MainActivity.DESCRIPCION)){
                mTextViewDescripcion.setText(intent.getStringExtra(MainActivity.DESCRIPCION));
            }else mTextViewDescripcion.setText(R.string.errorVacio);

        }

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.NOMBRE, mTextViewNombre.getText());
        intent.putExtra(MainActivity.FECHA, mTextViewFecha.getText());
        intent.putExtra(MainActivity.TELEFONO, mTextViewTelefono.getText());
        intent.putExtra(MainActivity.EMAIL, mTextViewEmail.getText());
        intent.putExtra(MainActivity.DESCRIPCION, mTextViewDescripcion.getText());
        startActivity(intent);
    }
}
