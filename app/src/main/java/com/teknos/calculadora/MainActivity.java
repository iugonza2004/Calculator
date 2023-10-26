package com.teknos.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private String operationResult;
    private TextView textResult;
    private TextView textOperation;
    private boolean lastOneOperator = false;


    private String getButtonText(View view) {
        Button button = (Button) view;

        return button.getText().toString();
    }

    public void addNumber(View view) {
        String buttonText = getButtonText(view);
        if (this.textResult.getText() == "0") {
            this.textResult.setText(buttonText);
        } else {
            String textToAdd = this.textResult.getText() + buttonText;
            this.textResult.setText(textToAdd);
        }
        this.operationResult += buttonText;
        this.textOperation.setText(this.operationResult);

        this.lastOneOperator = false;
    }

    public void reset(View view) {
        this.operationResult = "";
        this.textResult.setText("0");
        this.textOperation.setText(this.operationResult);

        this.lastOneOperator = false;
    }

    public void addOperator(View view) {
        String buttonText = getButtonText(view);

        if (this.lastOneOperator) {
            this.operationResult = this.operationResult.substring(0, this.operationResult.length() - 2);
            this.operationResult += buttonText + " ";
        } else {
            this.operationResult += " " + buttonText + " ";
        }
        this.textOperation.setText(operationResult);
        this.lastOneOperator = true;
        this.textResult.setText("0");
    }

    public void equals(View view) {

        if (this.lastOneOperator) {
            String textToAdd = textOperation.getText().toString().substring(0, textOperation.getText().toString().length() - 2) + "= ";
            this.textOperation.setText(textToAdd);
            this.operationResult = this.operationResult.substring(0, this.operationResult.length() - 2);
        } else {
            String textToAdd = operationResult + " = ";
            this.textOperation.setText(textToAdd);
        }

        String expresion = this.operationResult;
        String resultCalculated = Double.toString(Calculator.evaluateExpression(expresion));

        this.operationResult = resultCalculated;
        this.textResult.setText(resultCalculated);

        this.lastOneOperator = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Calculator", "onCreate");

        this.textResult = findViewById(R.id.result);
        this.textResult.setText("0");
        this.textOperation = findViewById(R.id.operation);
        this.operationResult = "";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Calculator", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Calculator", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Calculator", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Calculator", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Calculator", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Calculator", "onRestart");
    }
}