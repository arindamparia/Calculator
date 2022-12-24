package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public boolean isSign(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    public boolean isValid(String s) {
        if(s.length()==0)return true;
        if (s.charAt(0) == '*' || s.charAt(0) == '/') return false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (isSign(s.charAt(i)) && isSign(s.charAt(i + 1))) return false;
            if (s.charAt(i) == '.' && (s.charAt(i + 1) == '.' || isSign(s.charAt(i + 1))))
                return false;
        }
        if (s.charAt(s.length() - 1) == '.') return false;
        if (s.length() == 2) return !(notDigit(s.charAt(0)) && notDigit(s.charAt(1)));
        for (int i = 0; i < s.length() - 2; i++) {
            if (notDigit(s.charAt(i)) && notDigit(s.charAt(i + 1)) && notDigit(s.charAt(i + 2)))
                return false;
        }
        if (s.length() == 1 && s.charAt(0) == '.') return false;
        return true;
    }

    public boolean notDigit(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '.');
    }

    public String calculate(String s) {
        if (s.length() == 0) return "";
        if (!isValid(s)){
            return "Invalid Equation";
        }
        s = s.trim().replaceAll(" +", "");
        int length = s.length();
        double res = 0;
        double preVal = 0; // initial preVal is 0
        char sign = '+'; // initial sign is +
        int i = 0;
        if (s.charAt(0) == '+') i++;
        if (s.charAt(0) == '-') {
            i++;
            sign = '-';
        }
        while (i < length) {
            double curVal = 0;
            int start = i;
            while (i < length && ((int) s.charAt(i) <= 57 && (int) s.charAt(i) >= 48 || s.charAt(i) == '.')) { // int
                i++;
            }
            String temp = s.substring(start, i);
            curVal = Double.parseDouble(temp);
            if (sign == '+') {
                res += preVal;  // update res
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;  // update res
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
            } else if (sign == '/') {
                preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
            }
            if (i < length) { // getting new sign
                sign = s.charAt(i);
                i++;
            }
        }
        res += preVal;
        double temp = res;
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        String ans = df.format(res);
        int intplace = 0;
        for (int j = 0; j < ans.length(); j++) {
            if (ans.charAt(j) == '.') {
                break;
            }
            intplace++;
        }
        return intplace > 20 ? Double.toString(temp) : ans;
    }

    Button one, two, three, four, five, six, seven, eight, nine, zero, equal, plus, minus, multiply, divide, delete, sqroot, ac, dot, copyright;
    TextView result;
    EditText typing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        result = findViewById(R.id.result);
        typing = findViewById(R.id.typing);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        equal = findViewById(R.id.equal);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        delete = findViewById(R.id.delete);
        sqroot = findViewById(R.id.sqroot);
        ac = findViewById(R.id.ac);
        dot = findViewById(R.id.dot);
        copyright = findViewById(R.id.copyright);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This App is developed by Arindam", Toast.LENGTH_SHORT).show();
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "1");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in one block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "2");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in two block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "3");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in three block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "4");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in four block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "5");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in five block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "6");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in six block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "7");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in seven block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "8");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in eight block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "9");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in nine block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, "0");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in zero block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    if (start != 0 && notDigit(typing.getText().toString().charAt(start - 1))) {
                        StringBuffer sb = new StringBuffer(typing.getText().toString());
                        sb.replace(start - 1, start, "+");
                        typing.setText(sb.toString());
                        typing.setSelection(start);
                    } else {
                        typing.getText().insert(start, "+");
                        typing.setSelection(start + 1);
                    }
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in plus block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    if (start != 0 && notDigit(typing.getText().toString().charAt(start - 1))) {
                        StringBuffer sb = new StringBuffer(typing.getText().toString());
                        sb.replace(start - 1, start, "-");
                        typing.setText(sb.toString());
                        typing.setSelection(start);
                    } else {
                        typing.getText().insert(start, "-");
                        typing.setSelection(start + 1);
                    }
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in minus block", Toast.LENGTH_SHORT).show();
                }
            }

        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    if (start != 0 && notDigit(typing.getText().toString().charAt(start - 1))) {
                        StringBuffer sb = new StringBuffer(typing.getText().toString());
                        sb.replace(start - 1, start, "*");
                        typing.setText(sb.toString());
                        typing.setSelection(start);
                    } else {
                        typing.getText().insert(start, "*");
                        typing.setSelection(start + 1);
                    }
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in multiply block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    if (start != 0 && notDigit(typing.getText().toString().charAt(start - 1))) {
                        StringBuffer sb = new StringBuffer(typing.getText().toString());
                        sb.replace(start - 1, start, "/");
                        typing.setText(sb.toString());
                        typing.setSelection(start);
                    } else {
                        typing.getText().insert(start, "/");
                        typing.setSelection(start + 1);
                    }
                    result.setText("");
                    result.setHint(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in divide block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = typing.getText().toString();
                    if (str.length() == 0) {
                        result.setHint("Result");
                        result.setText("");
                        Toast.makeText(MainActivity.this, "You didn't type anything", Toast.LENGTH_SHORT).show();
                    } else {
                        int start = typing.getSelectionStart();
                        if (start != 0) {
                            StringBuffer sb = new StringBuffer(str);
                            sb.delete(start - 1, start);
                            typing.setText(sb.toString());
                            typing.setSelection(start - 1);
                        }
                        if (typing.getText().toString().length() == 0) {
                            result.setHint("Result");
                            result.setText("");
                        } else {
                            result.setText("");
                            result.setHint(calculate(typing.getText().toString()));
                        }
                    }
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in delete block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isValid(typing.getText().toString())) {
                        typing.setText(calculate(typing.getText().toString()));
                    } else result.setHint(calculate(typing.getText().toString()));
                    typing.setSelection(typing.getText().toString().length());
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in equal block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    typing.setText("");
                    result.setText("");
                    result.setHint("Result");
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in ac block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sqroot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                try {
                    if (typing.getText().toString().length() == 0 || !isValid(typing.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    } else {
                        String ans = String.format("%.2f", (Math.sqrt(Double.parseDouble(result.getHint().toString()))));
                        result.setHint(ans);
                        typing.setText(ans);
                        typing.setSelection(typing.getText().toString().length());
                    }
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in sqroot block", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int start = typing.getSelectionStart();
                    typing.getText().insert(start, ".");
                    typing.setSelection(start + 1);
                    result.setText("");
                    result.setText(calculate(typing.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "some error occurred in dot block", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
