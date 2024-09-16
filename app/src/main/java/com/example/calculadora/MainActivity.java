
package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_suma;
    private Button btn_resta;
    private Button btn_multiplicacion;
    private Button btn_division;
    private Button btn_exit; // Añadido para el botón de salir

    private TextView text_respuesta;
    private EditText edit_numero_uno;
    private EditText edit_numero_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_respuesta = findViewById(R.id.respuesta);
        edit_numero_uno = findViewById(R.id.num1);
        edit_numero_dos = findViewById(R.id.num2);

        btn_suma = findViewById(R.id.button_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation(Operation.SUMA);
            }
        });

        btn_resta = findViewById(R.id.button_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation(Operation.RESTA);
            }
        });

        btn_multiplicacion = findViewById(R.id.button_multiplicacion);
        btn_multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation(Operation.MULTIPLICACION);
            }
        });

        btn_division = findViewById(R.id.button_division);
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation(Operation.DIVISION);
            }
        });

        // Configura el botón de salir
        btn_exit = findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finaliza la actividad actual y cierra la aplicación
                finish();
                System.exit(0);
            }
        });
    }

    private void performOperation(Operation operation) {
        String inputUno = edit_numero_uno.getText().toString();
        String inputDos = edit_numero_dos.getText().toString();

        if (inputUno.isEmpty() || inputDos.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        int num1 = Integer.parseInt(inputUno);
        int num2 = Integer.parseInt(inputDos);

        double resultado = 0;
        boolean validOperation = true;

        switch (operation) {
            case SUMA:
                resultado = suma(num1, num2);
                break;
            case RESTA:
                resultado = resta(num1, num2);
                break;
            case MULTIPLICACION:
                resultado = multiplicacion(num1, num2);
                break;
            case DIVISION:
                if (num2 == 0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    validOperation = false;
                } else {
                    resultado = division(num1, num2);
                }
                break;
        }

        if (validOperation) {
            text_respuesta.setText(String.valueOf(resultado));
        }
    }

    public double suma(int a, int b) {
        return a + b;
    }

    public double resta(int a, int b) {
        return a - b;
    }

    public double multiplicacion(int a, int b) {
        return a * b;
    }

    public double division(int a, int b) {
        return (double) a / b; // Cambio aquí para obtener un resultado decimal.
    }

    // Enum para operaciones
    private enum Operation {
        SUMA, RESTA, MULTIPLICACION, DIVISION
    }
}
