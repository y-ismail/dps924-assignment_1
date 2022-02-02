package com.dps924.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private TextView displayTextView;
    private TextView historyTextView;
    private Calculator calculatorObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.calculatorObj = new Calculator();

        // initializing button click listeners
        this.initButtons();

        // Getting and setting default display values
        this.displayTextView = findViewById(R.id.display);
        this.historyTextView = findViewById(R.id.history);
        this.displayTextView.setText("");
        this.historyTextView.setText("");


    }

    protected void display(String content, Resources res) {

        if (this.displayTextView.getText().length() > 0) {
            String displayContent = String.format(res.getString(R.string.display_window), this.displayTextView.getText(), content);
            this.displayTextView.setText(displayContent);
        }
        else {
            this.displayTextView.setText(content);
        }
    }

    protected void initButtons() {

        Button btn;

        int[] idList = {
                R.id.btn_0,
                R.id.btn_1,
                R.id.btn_2,
                R.id.btn_3,
                R.id.btn_4,
                R.id.btn_5,
                R.id.btn_6,
                R.id.btn_7,
                R.id.btn_8,
                R.id.btn_9,
                R.id.op_add,
                R.id.op_multiply,
                R.id.op_div,
                R.id.op_sub,
                R.id.op_equals,
                R.id.clear,
                R.id.advanced,
                R.id.op_power,
                R.id.op_modulo,
                R.id.op_max,
                R.id.op_min
        };

        for (Integer i: idList) {
            btn = findViewById(i);
            btn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String buttonContent = ((Button)view).getText().toString();
        Resources res = getResources();

        if (id == R.id.clear) {
            this.historyTextView.setText("");
            this.displayTextView.setText("");
            this.calculatorObj.numberOperations.clear();
        }
        else if (id == R.id.op_equals) {

            String historyText = String.format(res.getString(R.string.history_display), this.displayTextView.getText());
            this.historyTextView.setText(historyText);

            String result;

            try {
                result = String.valueOf(this.calculatorObj.calculate());
            }catch(Exception e) {
                result = e.getMessage();
            }

            this.displayTextView.setText(result);

        }
        else if (id == R.id.advanced) {
            View advancedBar = findViewById(R.id.advanced_bar);
            Button btn_advanced = findViewById(R.id.advanced);

            advancedBar.setVisibility(advancedBar.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            btn_advanced.setText(advancedBar.getVisibility() == View.VISIBLE ? "Standard" : "Advanced");
        }
        else {
            this.calculatorObj.push(buttonContent);
            this.display(buttonContent, res);
        }

    }

}