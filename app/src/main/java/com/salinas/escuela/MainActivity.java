package com.salinas.escuela;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.salinas.escuela.model.Alumno;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private RadioGroup nivelRadioGroup;
    private EditText promedioEditText;
    private EditText cantidadMateriasEditText;
    private TextView resultadosTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignar variables a los componentes de la interfaz
        nombreEditText = findViewById(R.id.nombre);
        nivelRadioGroup = findViewById(R.id.nivel);
        promedioEditText = findViewById(R.id.promedio);
        cantidadMateriasEditText = findViewById(R.id.cantidad_materias);
        calcularButton = findViewById(R.id.calcular_button);
// Asignar variables a los componentes de la interfaz
        nombreEditText = findViewById(R.id.nombre);
        nivelRadioGroup = findViewById(R.id.nivel);
        promedioEditText = findViewById(R.id.promedio);
        cantidadMateriasEditText = findViewById(R.id.cantidad_materias);
        calcularButton = findViewById(R.id.calcular_button);

// Agregar listener al botón de calcular
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String nombre = nombreEditText.getText().toString();

                int nivelRadioButtonId = nivelRadioGroup.getCheckedRadioButtonId();
                int maxMaterias;
                String nivel = "";
                switch (nivelRadioButtonId) {
                    case R.id.secundaria_radio_button:
                        nivel = "secundaria";
                        maxMaterias = 8; // máximo de 10 materias para secundaria
                        break;
                    case R.id.bachillerato_radio_button:
                        nivel = "bachillerato";
                        maxMaterias = 7; // máximo de 12 materias para bachillerato
                        break;
                    case R.id.universidad_radio_button:
                        nivel = "universidad";
                        maxMaterias = 6; // máximo de 20 materias para universidad
                        break;
                    default:
                        maxMaterias = 0;
                        break;
                }

                int cantidadMaterias = Integer.parseInt(cantidadMateriasEditText.getText().toString());

                if (cantidadMaterias > maxMaterias) {
                    // Mostrar un mensaje de error y salir de la función
                    Toast.makeText(getApplicationContext(), "Error: no se pueden inscribir más de " + maxMaterias + " materias para el nivel " + nivel, Toast.LENGTH_LONG).show();
                    return;
                }

                // Si la cantidad de materias es válida, continuar con el resto del código

                double promedio = Double.parseDouble(promedioEditText.getText().toString());

                // Crear objeto alumno
                Alumno alumno = new Alumno(nombre, nivel, promedio, cantidadMaterias);

                // Calcular valores y mostrar resultados
                double colegiatura = alumno.calcularColegiatura();
                double descuento = alumno.calcularDescuento();
                double iva = alumno.calcularIVA();
                double total = alumno.calcularTotal();

                String resultados = "Colegiatura: $" + String.format("%.2f", colegiatura) + "\n" +
                        "Descuento: $" + String.format("%.2f", descuento) + "\n" +
                        "IVA: $" + String.format("%.2f", iva) + "\n" +
                        "Total a pagar: $" + String.format("%.2f", total);

                Toast.makeText(getApplicationContext(), resultados, Toast.LENGTH_LONG).show();
            }
        });
    }
}