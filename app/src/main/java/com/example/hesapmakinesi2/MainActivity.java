package com.example.hesapmakinesi2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonminus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assingId(buttonDivide,R.id.button_divide);
        assingId(buttonMultiply,R.id.button_multiply);
        assingId(buttonPlus,R.id.button_plus);
        assingId(buttonminus,R.id.button_minus);
        assingId(buttonEquals,R.id.button_equals);
        assingId(buttonAC,R.id.button_ac);
        assingId(buttonDot,R.id.button_dot);

        assingId(button0,R.id.button0);
        assingId(button1,R.id.button1);
        assingId(button2,R.id.button2);
        assingId(button3,R.id.button3);
        assingId(button4,R.id.button4);
        assingId(button5,R.id.button5);
        assingId(button6,R.id.button6);
        assingId(button7,R.id.button7);
        assingId(button8,R.id.button8);
        assingId(button9,R.id.button9);
    }

    void assingId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        dataToCalculate = dataToCalculate+buttonText;
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e) {
            return "Err";
        }
    }
}