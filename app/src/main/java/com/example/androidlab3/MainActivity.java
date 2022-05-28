package com.example.androidlab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CalculatorState state;
    private TextView expressionView;
    private TextView resultView;
    private Button[] numericButtons;

    private static final String EXPRESSION_KEY = "expression";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionView = findViewById(R.id.expression_text);
        resultView = findViewById(R.id.result_text);

        state = new CalculatorState();
        numericButtons = new Button[10];
        expressionView.setText(state.getExpression());
        resultView.setText(state.getResult());

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
        outState.putString(EXPRESSION_KEY, expressionView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = new CalculatorState(savedInstanceState.getString(EXPRESSION_KEY));
        expressionView.setText(state.getExpression());
        resultView.setText(state.getResult());
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
            state.backspace();
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setDotButtonHandler() {
        Button dotButton = findViewById(R.id.button_dot);
        dotButton.setOnClickListener(v -> {
            state.appendExpression('.');
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setEqualsButtonHandler() {
        Button equalsButton = findViewById(R.id.button_equals);
        equalsButton.setOnClickListener(v -> {
            String res = state.getResult();

            state = new CalculatorState(res);
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        });
    }

    private void setOperationButtonHandlers() {
        Button plusButton = findViewById(R.id.button_add);
        plusButton.setOnClickListener(getClickListener('+'));

        Button minusButton = findViewById(R.id.button_subtract);
        minusButton.setOnClickListener(getClickListener('-'));

        Button multiplyButton = findViewById(R.id.button_multiply);
        multiplyButton.setOnClickListener(getClickListener('*'));

        Button divideButton = findViewById(R.id.button_divide);
        divideButton.setOnClickListener(getClickListener('/'));
    }

    private View.OnClickListener getClickListener(final char op) {
        return v -> {
            state.appendExpression(op);
            expressionView.setText(state.getExpression());
            resultView.setText(state.getResult());
        };
    }
}