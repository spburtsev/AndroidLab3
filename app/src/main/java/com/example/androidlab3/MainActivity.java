package com.example.androidlab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private  TextView expressionView;
    private TextView resultView;
    private Button[] numericButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialText();

        numericButtons = new Button[10];
        setNumericButtonHandlers();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setInitialText() {
        expressionView = findViewById(R.id.expression_text);
        resultView = findViewById(R.id.result_text);
        expressionView.setText("0");
        resultView.setText("0");
    }

    private void setNumericButtonHandlers() {
        for (int i = 0; i < numericButtons.length; ++i) {
            numericButtons[i] = findViewById(getResources().getIdentifier(
                    "button_" + i,
                    "id",
                    getPackageName()));
            numericButtons[i].setOnClickListener(v -> {
                TextView expressionView = findViewById(R.id.expression_text);
                TextView resultView = findViewById(R.id.result_text);
                String expression = expressionView.getText().toString();
                String result = resultView.getText().toString();
                if (expression.equals("0")) {
                    expression = "";
                }
                if (result.equals("0")) {
                    result = "";
                }
                expression += result + ((Button) v).getText();
                expressionView.setText(expression);
                resultView.setText("0");
            });
        }
    }
}