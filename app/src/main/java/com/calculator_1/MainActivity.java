package com.calculator_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    private StringBuilder firstNumber = new StringBuilder();
    private StringBuilder nextNumber=new StringBuilder();
    private StringBuilder operator = new StringBuilder();
    private StringBuilder result = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        final TextView text = (TextView)findViewById(R.id.show);
        Button [] buttons =new Button[18];
        buttons[0]=(Button)findViewById(R.id.b0);
        buttons[1]=(Button)findViewById(R.id.b1);
        buttons[2]=(Button)findViewById(R.id.b2);
        buttons[3]=(Button)findViewById(R.id.b3);
        buttons[4]=(Button)findViewById(R.id.b4);
        buttons[5]=(Button)findViewById(R.id.b5);
        buttons[6]=(Button)findViewById(R.id.b6);
        buttons[7]=(Button)findViewById(R.id.b7);
        buttons[8]=(Button)findViewById(R.id.b8);
        buttons[9]=(Button)findViewById(R.id.b9);
        buttons[10]=(Button)findViewById(R.id.dot);
        buttons[11]=(Button)findViewById(R.id.add);
        buttons[12]=(Button)findViewById(R.id.minus);
        buttons[13]=(Button)findViewById(R.id.Multiply);
        buttons[14]=(Button)findViewById(R.id.division);
        buttons[15]=(Button)findViewById(R.id.AC);
        buttons[16]=(Button)findViewById(R.id.CE);
        buttons[17]=(Button)findViewById(R.id.equal);


        for(int i=0;i<=10;i++){

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button =(Button)findViewById(v.getId());
                    String s =button.getText().toString();
                    if(!(result.toString().equals(""))){
                        clear();
                    }
                    if(operator.toString().equals("")){
                        add(s,firstNumber);
                        text.setText(firstNumber.toString());
                    }else{
                        add(s,nextNumber);
                        text.setText(firstNumber.toString()+operator.toString()+nextNumber.toString());

                    }

                }
            });
        }

        for (int i = 11;i<=14;i++){

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button =(Button)findViewById(v.getId());
                    String s =button.getText().toString();
                    if(result.toString().equals("")) {
                        add(s, operator);
                        text.setText(firstNumber.toString() + operator.toString());
                    }else{
                        String res = result.toString();
                        clear();
                        add(res,firstNumber);
                        add(s,operator);
                        text.setText(firstNumber.toString() + operator.toString());

                    }

                }
            });
        }

        buttons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstNumber.toString().equals("")||operator.toString().equals("")||
                        nextNumber.toString().equals("")){
                    Toast.makeText(MainActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                }else {
                    getResult(firstNumber,operator,nextNumber);
                    text.setText(result.toString());

                }
            }
        });

        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                text.setText("0");
            }
        });

        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(result.toString().equals(""))){
                    clear();
                    text.setText("0");

                }else if (!(nextNumber.toString().equals(""))){
                  nextNumber.delete(nextNumber.length() - 1, nextNumber.length());
                  text.setText(firstNumber.toString()+operator.toString()+nextNumber.toString());

                }else if(!(operator.toString().equals(""))){
                    operator.setLength(0);
                    text.setText(firstNumber.toString());
                }else if(!(firstNumber.toString().equals(""))){
                    firstNumber.delete(firstNumber.length() - 1, firstNumber.length());
                     text.setText(firstNumber.toString());
                }
            }
        });

    }

    public void add (String s1 ,StringBuilder s2){
        if(s1.equals("0")) {
            if (!(s2.toString().equals("0"))) {
                s2.append("0");
            }
        }
            else if(s2.equals(operator)){
                if(s2.toString().equals("")){
                    s2.append(s1);
                }else {
                    s2.setLength(0);
                    s2.append(s1);

                }

            }
            else {
                s2.append(s1);
            }

    }

    public void getResult(StringBuilder firstNumber ,StringBuilder operator,
                          StringBuilder nextNumber){
        if (result.toString().equals("")) {
            try {
                double first = Double.parseDouble(firstNumber.toString());
                double next = Double.parseDouble(nextNumber.toString());
                double result_show = 0;
                switch (operator.toString()) {
                    case "+":
                        result_show = first + next;
                        break;
                    case "-":
                        result_show = first - next;
                        break;
                    case "*":
                        result_show = first * next;
                        break;
                    case "/":
                        result_show = first / next;
                        break;
                    default:
                        break;

                }
                int a =(int)result_show;
                if(a==result_show){
                    result.append(String.valueOf(a));
                }else {
                    result_show=Math.round(result_show*10000000)/10000000.0;
                    result.append(String.valueOf(result_show));

                }
            } catch (Exception e) {
                clear();
                Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();

            }
        }



    }
    public void clear(){
        firstNumber.setLength(0);
        nextNumber.setLength(0);
        operator.setLength(0);
        result.setLength(0);
    }
}
