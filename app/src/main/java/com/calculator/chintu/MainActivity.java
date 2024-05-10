package com.calculator.chintu;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton btnC, btnBracketOpen, btnBracketClose;
    MaterialButton btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    MaterialButton btnAC, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(btnC, R.id.btn_c);
        assignId(btnBracketOpen, R.id.btn_open_bracket);
        assignId(btnBracketClose, R.id.btn_close_bracket);
        assignId(btnDivide, R.id.btn_divide);
        assignId(btnMultiply, R.id.btn_multiply);
        assignId(btnPlus, R.id.btn_plus);
        assignId(btnMinus, R.id.btn_minus);
        assignId(btnEquals, R.id.btn_equals);
        assignId(btn0, R.id.btn_0);
        assignId(btn1, R.id.btn_1);
        assignId(btn2, R.id.btn_2);
        assignId(btn3, R.id.btn_3);
        assignId(btn4, R.id.btn_4);
        assignId(btn5, R.id.btn_5);
        assignId(btn6, R.id.btn_6);
        assignId(btn7, R.id.btn_7);
        assignId(btn8, R.id.btn_8);
        assignId(btn9, R.id.btn_9);
        assignId(btnDot, R.id.btn_dot);
        assignId(btnAC, R.id.btn_ac);



    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton btn =(MaterialButton) view;
        String btnText = btn.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (btnText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (btnText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (btnText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+btnText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}