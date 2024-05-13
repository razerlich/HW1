package com.example.hw1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText operand1EditText;
    EditText operand2EditText;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        operand1EditText = findViewById(R.id.operand1EditText);
        operand2EditText = findViewById(R.id.operand2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void calculate(View view){
        try {
            int operand1 = Integer.parseInt(operand1EditText.getText().toString());
            int operand2 = Integer.parseInt(operand2EditText.getText().toString());
            String operator = spinner.getSelectedItem().toString();
            int result = 0;

            switch (operator){
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "\u00F7":
                    if (operand2 == 0){
                        throw new ArithmeticException("Division by zero");
                    }
                    result = operand1 / operand2;
                    break;
                case "^":
                    result = (int) Math.pow(operand1,operand2);
                    break;

            }
            resultTextView.setText("Result: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid operands", Toast.LENGTH_SHORT).show();
        } catch (ArithmeticException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}