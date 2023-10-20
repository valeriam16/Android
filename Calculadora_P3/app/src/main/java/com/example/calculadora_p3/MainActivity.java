package com.example.calculadora_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextResult;
    private Button buttonC, buttonDelete, buttonMultiply, buttonDivide, buttonPlus, buttonMinus, buttonPoint, buttonEquals,
            button9, button8, button7, button6, button5, button4, button3, button2, button1, button0;
    private StringBuilder currentInput;
    private double result;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResult = findViewById(R.id.editTextResult);

        buttonC = findViewById(R.id.buttonC);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonEquals = findViewById(R.id.buttonEquals);
        button9 = findViewById(R.id.button9);
        button8 = findViewById(R.id.button8);
        button7 = findViewById(R.id.button7);
        button6 = findViewById(R.id.button6);
        button5 = findViewById(R.id.button5);
        button4 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        button1 = findViewById(R.id.button1);
        button0 = findViewById(R.id.button0);

        currentInput = new StringBuilder();
        result = 0;
        operator = "";

        buttonC.setOnClickListener(v -> clearText());
        buttonDelete.setOnClickListener(v -> onDeleteClick());
        buttonMultiply.setOnClickListener(v -> onButtonClick("x"));
        buttonDivide.setOnClickListener(v -> onButtonClick("÷"));
        buttonPlus.setOnClickListener(v -> onButtonClick("+"));
        buttonMinus.setOnClickListener(v -> onButtonClick("-"));
        buttonPoint.setOnClickListener(v -> onButtonClick("."));
        buttonEquals.setOnClickListener(v -> onEqualsClick());
        button9.setOnClickListener(v -> onButtonClick("9"));
        button8.setOnClickListener(v -> onButtonClick("8"));
        button7.setOnClickListener(v -> onButtonClick("7"));
        button6.setOnClickListener(v -> onButtonClick("6"));
        button5.setOnClickListener(v -> onButtonClick("5"));
        button4.setOnClickListener(v -> onButtonClick("4"));
        button3.setOnClickListener(v -> onButtonClick("3"));
        button2.setOnClickListener(v -> onButtonClick("2"));
        button1.setOnClickListener(v -> onButtonClick("1"));
        button0.setOnClickListener(v -> onButtonClick("0"));
    }

    private void clearText() {
        currentInput.setLength(0);
        result = 0;
        operator = "";
        editTextResult.setText("");
    }

    private void onButtonClick(String input) {
        currentInput.append(input);
        editTextResult.setText(currentInput.toString());
    }

    private void onDeleteClick() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            editTextResult.setText(currentInput.toString());
        }
    }

    private void performOperation() {
        String[] tokens = currentInput.toString().split("(?<=[+\\-x÷])|(?=[+\\-x÷])");

        if (tokens.length < 3) {
            // No hay suficientes operandos y operadores para realizar una operación
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        double operand1 = Double.parseDouble(tokens[0]);
        String currentOperator = "";

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].matches("[+\\-x÷]")) {
                currentOperator = tokens[i];
            } else {
                double operand2 = Double.parseDouble(tokens[i]);

                switch (currentOperator) {
                    case "+":
                        operand1 += operand2;
                        break;
                    case "-":
                        operand1 -= operand2;
                        break;
                    case "x":
                        operand1 *= operand2;
                        break;
                    case "÷":
                        if (operand2 != 0) {
                            operand1 /= operand2;
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }
            }
        }

        result = operand1;
        currentInput.setLength(0);
        currentInput.append(result);
        editTextResult.setText(currentInput.toString());
    }

    private void onEqualsClick() {
        performOperation();
        operator = "";
    }
}

