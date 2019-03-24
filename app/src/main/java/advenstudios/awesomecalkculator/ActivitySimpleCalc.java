package advenstudios.awesomecalkculator;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivitySimpleCalc extends AppCompatActivity {

    EditText editText;
    Button butt1;
    Button butt2;
    Button butt3;
    Button butt4;
    Button butt5;
    Button butt6;
    Button butt7;
    Button butt8;
    Button butt9;
    Button butt0;

    Button div;
    Button plus;
    Button minus;
    Button multiply;
    Button eq;

    Button c;
    Button bac;
    Button dot;

    ArrayList<Double> listOfNums;
    ArrayList<Character> listOfSigns;

    boolean isEmpty;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);


        editText= findViewById(R.id.editText);

        butt1 = findViewById(R.id.one);
        butt2 = findViewById(R.id.two);
        butt3 = findViewById(R.id.three);
        butt4 = findViewById(R.id.four);
        butt5 = findViewById(R.id.five);
        butt6 = findViewById(R.id.six);
        butt7 = findViewById(R.id.seven);
        butt8 = findViewById(R.id.eight);
        butt9 = findViewById(R.id.nine);
        butt0 = findViewById(R.id.zero);

        div = findViewById(R.id.div);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        multiply= findViewById(R.id.multiply);
        eq= findViewById(R.id.equals);

        c=findViewById(R.id.c);
        bac = findViewById(R.id.bksp);
        dot = findViewById(R.id.dot);

        listOfNums= new ArrayList<Double>();
        listOfSigns= new ArrayList<Character>();

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber()) {
                        editText.append(".");
                    }

                }
            }
        });

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    String str =editText.getText().toString();
                    str= str.substring(0, str.length() - 1);
                    editText.setText(str);

                }
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    editText.setText("");
                }
            }
        });

        ////////////////// S I G N S

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber() && listOfSigns.size()>0) {
                        String str =editText.getText().toString();
                        //str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));


                        editText.setText(performCalculations());
                        listOfNums.clear();
                        listOfSigns.clear();
                    }
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber()) {
                        editText.append("/");
                        listOfSigns.add('/');

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");
                    }
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber()) {
                        editText.append("+");
                        listOfSigns.add('+');

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLastTheNumber()) {
                    editText.append("-");
                    listOfSigns.add('-');

                    String str =editText.getText().toString();
                    str= str.substring(0, str.length() - 1);
                    listOfNums.add(Double.parseDouble(str));
                    editText.setText("");
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber()) {
                        editText.append("x");
                        listOfSigns.add('x');

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");
                    }
                }
            }
        });

        /////HERE ARE NUOMBERSS

        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("1");
            }
        });

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("2");

            }
        });

        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("3");
            }
        });

        butt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("4");
            }
        });

        butt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("5");
            }
        });

        butt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("6");
            }
        });

        butt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("7");
            }
        });

        butt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("8");
            }
        });

        butt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("9");
            }
        });

        butt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.append("0");
            }
        });

    }
    boolean checkIfEmpty(){

        boolean isEmptyy;
        if(editText.getText().toString().length() >0){
            isEmptyy=false;
        }
        else {
            isEmptyy=true;
        }

        return isEmptyy;
    }

    boolean isLastTheNumber(){

        boolean nm;
        char tmp =editText.getText().toString().charAt(editText.getText().toString().length() -1);
        if(tmp=='+' || tmp=='x' || tmp=='-' || tmp=='/'){
            nm=false;
        }
        else {
            nm=true;
        }

        return nm;
    }

    String performCalculations(){

        double licz=listOfNums.get(0);

        for(int i=0; i< listOfSigns.size(); i++){
          //  if(i==0) {
                switch (listOfSigns.get(i)) {

                    case '+':
                        licz += listOfNums.get(i + 1);
                        break;
                    case '-':
                        licz -= listOfNums.get(i + 1);
                        break;
                    case 'x':
                        licz *= listOfNums.get(i + 1);
                        break;
                    case '/':
                        licz /= listOfNums.get(i + 1);
                        break;

                }
        }

        Log.d("tag","Toje lista liczb: "+String.valueOf(listOfNums).toString());
        Log.d("tag","to je lista znakuf: "+String.valueOf(listOfSigns).toString());
        Log.d("tag","to jej rozmiar znakuf: "+String.valueOf(listOfSigns.size()).toString());
        Log.d("tag","-----------------");
        Log.d("tag","to je znak 0: "+String.valueOf(listOfNums.get(0)).toString());
        Log.d("tag","to je znak 1: "+String.valueOf(listOfNums.get(1)).toString());

        String wynik;
        if(licz%1 == 0){
            int tmp =(int)licz;
            wynik=String.valueOf(tmp);
        }
        else {
            licz *= 10000;
            licz = Math.round(licz);
            licz/=10000;

            wynik=String.valueOf(licz);
        }

        return wynik;

    }
}
