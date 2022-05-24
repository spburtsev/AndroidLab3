package com.example.androidlab3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CalculatorState state;
    private TextView expressionView;
    private TextView resultView;
    private Button[] numericButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialText();
        state = new CalculatorState();
        numericButtons = new Button[10];
        setNumericButtonHandlers();
        setClearButtonHandler();
        setBackspaceButtonHandler();
        setOperationButtonHandlers();
        setDotButtonHandler();
        setEqualsButtonHandler();
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
                state.appendExpression(((Button) v).getText().charAt(0));
                expressionView.setText(state.getExpression());
                resultView.setText(state.getResult());
            });
        }
    }

    private void setClearButtonHandler() {
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> {
            state.clear();
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setBackspaceButtonHandler() {
        Button backspaceButton = findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);

            TextView resultView = findViewById(R.id.result_text);
            state.backspace();
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setDotButtonHandler() {
        Button dotButton = findViewById(R.id.button_dot);
        dotButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            state.appendExpression('.');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setEqualsButtonHandler() {
        Button equalsButton = findViewById(R.id.button_equals);
        equalsButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            String res = state.getResult();

            state = new CalculatorState(res);
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setOperationButtonHandlers() {
        Button plusButton = findViewById(R.id.button_add);
        plusButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            state.appendExpression('+');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
        Button minusButton = findViewById(R.id.button_subtract);
        minusButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            state.appendExpression('-');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
        Button multiplyButton = findViewById(R.id.button_multiply);
        multiplyButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            state.appendExpression('*');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
        Button divideButton = findViewById(R.id.button_divide);
        divideButton.setOnClickListener(v -> {
            TextView expressionView = findViewById(R.id.expression_text);
            TextView resultView = findViewById(R.id.result_text);
            state.appendExpression('/');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }
}