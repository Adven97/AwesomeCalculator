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

import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

public class AdvancedActivity extends AppCompatActivity {

    TextView editText;
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
    Button plusmin;

    Button sin;
    Button cos;
    Button tan;
    Button log;
    Button ln;
    Button sqrt;

    Button x2;
    Button xy;

    ArrayList<Double> listOfNums;
    ArrayList<String> listOfSigns;

    boolean extended;
    boolean extended2;
    static boolean error;
    boolean isDot;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);

        extended=false;
        extended2=false;
        error =false;
        isDot=false;

        editText= findViewById(R.id.editText2);

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
        plusmin = findViewById(R.id.plusmin);

        sin= findViewById(R.id.sin);
        cos= findViewById(R.id.cos);
        tan= findViewById(R.id.tan);
        log= findViewById(R.id.log);
        ln= findViewById(R.id.ln);
        sqrt= findViewById(R.id.sqrt);

        x2= findViewById(R.id.xs);
        xy = findViewById(R.id.xy);

        listOfNums= new ArrayList<Double>();
        listOfSigns= new ArrayList<String>();
        ////////////////////////////////////////////////////////////////////////////

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber()) {
                        if(!isDot) {
                            editText.append(".");
                            isDot=true;
                        }
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

        ///////////////// ADVANCED

        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                    editText.append("sqrt(");
                    listOfSigns.add("sqrt(");
                    extended=true;
                }
            }
        });

        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                    editText.append("ln(");
                    listOfSigns.add("ln(");
                    extended=true;
                }
            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                    editText.append("log(");
                    listOfSigns.add("log(");
                    extended=true;
                }
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                    editText.append("tan(");
                    listOfSigns.add("tan(");
                    extended=true;
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                        editText.append("cos(");
                        listOfSigns.add("cos(");
                        extended=true;
                    }
                }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()) {
                    editText.append("sin(");
                    listOfSigns.add("sin(");
                    extended=true;
                }
            }
        });

        x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIfEmpty() && !extended && !checkIfError()) {
                    if (isLastTheNumber() ) {
                        editText.append("^2");
                        listOfSigns.add("^2");
                        extended2 = true;
                    }
                }
            }
        });

        xy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty() && !extended && !checkIfError()) {
                    if(isLastTheNumber()) {
                        editText.append("^");
                        listOfSigns.add("^");

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");
                        //extended=true;
                    }
                }
            }
        });


        ////////////////// S I G N S

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if( !extended && !extended2 && isLastTheNumber() && listOfSigns.size()>0) {
                        String str =editText.getText().toString();
                        listOfNums.add(Double.parseDouble(str));

                        editText.setText(performCalculations());
                        listOfNums.clear();
                        listOfSigns.clear();
                    }
                    else if(extended){
                        int lengt = listOfSigns.get(0).length();
                        String str =editText.getText().toString();
                        str= str.substring(lengt, str.length() );
                        listOfNums.add(Double.parseDouble(str));

                        editText.setText(performCalculations());
                        listOfNums.clear();
                        listOfSigns.clear();
                        extended= false;
                    }
                    else if(extended2){
                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() -2 );
                        listOfNums.add(Double.parseDouble(str));

                        editText.setText(performCalculations());
                        listOfNums.clear();
                        listOfSigns.clear();
                        extended2= false;
                    }
                    isDot=false;
                }
            }
        });


        plusmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber() && !extended && !checkIfError()) {
                        if(editText.getText().toString().charAt(0) == '-'){
                            String str = editText.getText().toString();
                            str = str.substring(1, str.length());
                            editText.setText(str);
                        }
                        else {
                            String str2 = '-'+editText.getText().toString();
                            editText.setText(str2);
                        }
                        Log.d("tag", "podano zminusowana liczba cxd ");
                    }
                }
            }
        });


        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty() && !extended && !checkIfError()) {
                    if(isLastTheNumber()) {
                        editText.append("/");
                        listOfSigns.add("/");

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");

                        isDot=false;
                    }
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty() && !extended && !checkIfError()) {
                    if(isLastTheNumber()) {
                        editText.append("+");
                        listOfSigns.add("+");

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");

                        isDot=false;
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLastTheNumber() && !extended && !checkIfError()) {
                    editText.append("-");
                    listOfSigns.add("-");

                    String str =editText.getText().toString();
                    str= str.substring(0, str.length() - 1);
                    listOfNums.add(Double.parseDouble(str));
                    editText.setText("");

                    isDot=false;
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfEmpty()) {
                    if(isLastTheNumber() && !extended && !checkIfError()) {
                        editText.append("x");
                        listOfSigns.add("x");

                        String str =editText.getText().toString();
                        str= str.substring(0, str.length() - 1);
                        listOfNums.add(Double.parseDouble(str));
                        editText.setText("");

                        isDot=false;
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

    boolean checkIfError(){

        boolean b;
        if(editText.getText().toString().contains("E")){
            return true;
        }
        else
            return false;
    }

    String performCalculations(){

        boolean error =false;
        double licz=listOfNums.get(0);

        for(int i=0; i< listOfSigns.size(); i++){
            //  if(i==0) {
            switch (listOfSigns.get(i)) {

                case "+":
                    licz += listOfNums.get(i + 1);
                    break;
                case "-":
                    licz -= listOfNums.get(i + 1);
                    break;
                case "x":
                    licz *= listOfNums.get(i + 1);
                    break;
                case "/":
                    double temp = listOfNums.get(i + 1);
                    if(temp != 0)
                        licz /= temp;
                    else
                        error=true;

                    break;

                case "sin(":
                    licz = sin(licz);
                    break;
                case "cos(":
                    licz = cos(licz);
                    break;
                case "tan(":
                    licz = tan(licz);
                    break;
                case "log(":
                    licz = log10(licz);
                    break;
                case "ln(":
                    licz = log(licz);
                    break;
                case "sqrt(":
                    licz =sqrt(licz);
                    break;
                case "^2":
                    licz =licz*licz;
                    break;
                case "^":
                    int t = (int)licz;
                    double tem = listOfNums.get(i + 1);
                    licz= pow(t, (int)tem);
                    break;

            }
        }

        Log.d("tag","Toje lista liczb: "+String.valueOf(listOfNums).toString());
        Log.d("tag","to je lista znakuf: "+String.valueOf(listOfSigns).toString());
        Log.d("tag","to jej rozmiar znakuf: "+String.valueOf(listOfSigns.size()).toString());
        Log.d("tag","-----------------");
        Log.d("tag","to je znak 0: "+String.valueOf(listOfNums.get(0)).toString());
        //Log.d("tag","to je znak 1: "+String.valueOf(listOfNums.get(1)).toString());

        String wynik;

        if(error){
            wynik="Error!";
        }
        else {
            if (licz % 1 == 0) {
                int tmp = (int) licz;
                wynik = String.valueOf(tmp);
            } else if (extended) {
                licz *= 10000000;
                licz = Math.round(licz);
                licz /= 10000000;

                wynik = String.valueOf(licz);
            } else {
                licz *= 10000;
                licz = Math.round(licz);
                licz /= 10000;

                wynik = String.valueOf(licz);
            }
        }

        return wynik;

    }
}
