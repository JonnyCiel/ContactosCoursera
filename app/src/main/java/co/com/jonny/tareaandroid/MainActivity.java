package co.com.jonny.tareaandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DialogInterface.OnCancelListener, View.OnClickListener{


    public static final String NOMBRE = "Nombre";
    public static final String FECHA = "Fecha";
    public static final String TELEFONO = "Telefono";
    public static final String EMAIL = "Email";
    public static final String DESCRIPCION = "Descripcion";

    private EditText mEditTextFecha;
    private EditText mEditTextNombre;
    private EditText mEditTextTelefono;
    private EditText mEditTextEmail;
    private EditText mEditTextDescripcion;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextFecha = (EditText) findViewById(R.id.FECHAEDIT);
        mEditTextNombre = (EditText) findViewById(R.id.editText_Nombre);
        mEditTextTelefono = (EditText) findViewById(R.id.editText_Telefono);
        mEditTextEmail = (EditText) findViewById(R.id.editText_Email);
        mEditTextDescripcion = (EditText) findViewById(R.id.editText_Descripcion);
        mButton = (Button) findViewById(R.id.Boton_Enviar);
        mButton.setOnClickListener(this);


        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.NOMBRE)){
            mEditTextNombre.setText(intent.getStringExtra(MainActivity.NOMBRE));
        }
        if (intent.hasExtra(MainActivity.FECHA)){
            mEditTextFecha.setText(intent.getStringExtra(MainActivity.FECHA));
        }

        if (intent.hasExtra(MainActivity.TELEFONO)){
            mEditTextTelefono.setText(intent.getStringExtra(MainActivity.TELEFONO));
        }

        if (intent.hasExtra(MainActivity.EMAIL)){
            mEditTextEmail.setText(intent.getStringExtra(MainActivity.EMAIL));
        }

        if (intent.hasExtra(MainActivity.DESCRIPCION)){
            mEditTextDescripcion.setText(intent.getStringExtra(MainActivity.DESCRIPCION));
        }

        mEditTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(v);
                fechamostrar(v);
            }
        });
    }

    private int year, month, day;


    private void fechamostrar(View v) {
        initDateData();
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(year, month, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)

        );

        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.show(getFragmentManager(), "datePickerDialog");
    }

    private void initDateData(){
        if(year ==0 ){
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            this.year = year;
            month = monthOfYear;
            day = dayOfMonth;

        mEditTextFecha.setText((day < 10 ? "0" + day: day) + "/" +
                (month+1 < 10 ? "0" +(month+1): month-1) + "/" +
                this.year);

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        year = month = day =0;
        mEditTextFecha.setText("");
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        String Nombre = "";
        String Fecha = "";
        String Telefono = "";
        String Email = "";
        String Descripcion = "";

        if (mEditTextNombre.getText().toString().isEmpty()){
            mEditTextNombre.setError(getString(R.string.errorCampo));
        }else {
            Nombre  = mEditTextNombre.getText().toString();
        }

        if(mEditTextFecha.getText().toString().isEmpty()){
            mEditTextFecha.setError(getString(R.string.errorCampo));
        }else {
            Fecha = mEditTextFecha.getText().toString();
        }

        if(mEditTextTelefono.getText().toString().isEmpty()){
            mEditTextTelefono.setError(getString(R.string.errorCampo));
        }else {
            Telefono = mEditTextTelefono.getText().toString();
        }

        if(mEditTextEmail.getText().toString().isEmpty()){
            mEditTextEmail.setError(getString(R.string.errorCampo));
        }else {
            Email = mEditTextEmail.getText().toString();
        }

        if(mEditTextDescripcion.getText().toString().isEmpty()){
            mEditTextDescripcion.setError(getString(R.string.errorCampo));
        }else {
            Descripcion = mEditTextDescripcion.getText().toString();
        }


        if(mEditTextNombre.getText().toString().isEmpty() || mEditTextFecha.getText().toString().isEmpty()
                || mEditTextTelefono.getText().toString().isEmpty() || mEditTextEmail.getText().toString().isEmpty()
                || mEditTextDescripcion.getText().toString().isEmpty()){
            Snackbar.make(findViewById(android.R.id.content), "No dejes ningún campo sin llenar", Snackbar.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, Detalles.class);
            intent.putExtra(NOMBRE, Nombre);
            intent.putExtra(FECHA, Fecha);
            intent.putExtra(TELEFONO, Telefono);
            intent.putExtra(EMAIL, Email);
            intent.putExtra(DESCRIPCION, Descripcion);
            startActivity(intent);
        }


    }
}
