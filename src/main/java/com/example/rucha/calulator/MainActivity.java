package com.example.rucha.calulator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView number;

    private String operation;
    private float op1;
    private String num;
    float n,n_raise;
    private float result;
    Intent intent;
    float t = 1;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (TextView)findViewById(R.id.number);

        int ids[] = {R.id.add,R.id.mul,R.id.sub,R.id.div,R.id.decimal,R.id.eq,R.id.del,
                R.id.one,R.id.two,R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven,
                R.id.eight,R.id.nine,R.id.zero};
        for(int id: ids){

            View v = (View)findViewById(id);
            v.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.del:
                number.setText("0");
                break;
            case R.id.add:
                Log.i("MainAct", "in add ");
                operand("+");
                break;
            case R.id.mul:
                Log.i("MainAct", "in mul ");
                operand("*");
                break;
            case R.id.div:
                Log.i("MainAct", "in div ");
                operand("/");
                break;
            case R.id.sub:
                Log.i("MainAct", "in sub ");
                operand("-");
                break;
            case R.id.eq:
                Log.i("MainAct", "in eq ");
                Result();
                break;
            default:
                num = ((Button) v).getText().toString();
                typeDigits(num);
                break;
        }

}

    private void typeDigits(String num) {

        String onScreenNum = number.getText().toString();
        Log.i("MainAct","number on screen is :"+onScreenNum);


        if (onScreenNum.equals("0")){
            Log.i("MainAct","if 0 then num clicked and displayed is :"+num);
            number.setText(num);
        }
        else{
            onScreenNum = onScreenNum.concat(num);
            Log.i("MainAct","if onscreen number is not 0 concat number is :"+onScreenNum);
            number.setText(onScreenNum);
          }

    }

    private void operand(String s) {
        op1 = Float.parseFloat(number.getText().toString());
        operation = s;
        Log.i("MainAct","op1 is:"+op1);
        number.setText("0");

    }


    private void Result() {
        n = Float.parseFloat(number.getText().toString());

        b= new Bundle();
        b = getIntent().getExtras();
        if(b != null) {

            operation = b.getString("operation");
            Log.i("MainAct", "operation in intent is :" + operation);

            n_raise = b.getFloat("n_raise", n_raise);
            Log.i("MainAct","n_raised in intent after switching keypads : "+n_raise);

        }

        if(operation.equals("+")){
            result = n + op1;
        }
        if(operation.equals("-")){
            result =op1-n;
        }
        if(operation.equals("*")){
            result = n * op1;
        }
        if(operation.equals("/")){
            result = op1/n;
        }
        if(operation.equals("sin")){
            result  = (float) Math.sin(Math.toRadians(n));;
            Log.i("MainAct","sin result is : "+result);
        }
        if(operation.equals("cos")){
            result  = (float) Math.cos(Math.toRadians(n));;
            Log.i("MainAct","cos result is : "+result);
        }
        if(operation.equals("tan")){
            result  = (float) Math.tan(Math.toRadians(n));;
            Log.i("MainAct","tan result is : "+result);
        }
        if(operation.equals("√")){
            result  = (float) Math.sqrt(n);
            Log.i("MainAct","√ result is : "+result);
        }
        if(operation.equals("ln")){
            result  = (float) Math.log(n);
            Log.i("MainAct","ln result is : "+result);
        }
        if(operation.equals("log")){
            result  = (float) Math.log10(n);
            Log.i("MainAct","log result is : "+result);
        }
        if(operation.equals("^")){
            Log.i("MainAct","in raised to !!!!!!!!!!!!");

            n = Float.parseFloat(number.getText().toString());
            Log.i("MainAct","n_raise=base"+n_raise+"  and n = exponent is : "+n);
            for(int i = 1;i <= n; i++)
            {
                t=t*n_raise;
                //result = t;
            }
            Log.i("MainAct","^ result is : "+t);
            result = t;

        }
        if(operation.equals("!")){
            Log.i("MainAct","in ! ######");
            n = Float.parseFloat(number.getText().toString());
            Log.i("MainAct","n = "+n);

            for (int i = 1; i <= n; i++) {
                t = t * i;
            }
            result = t;
            Log.i("MainAct",n+"! is "+result);
        }
        if(operation.equals("%")){
            n = Float.parseFloat(number.getText().toString());
            result = n/100;
            Log.i("MainAct",n+"% is :"+result);
        }
        if(operation.equals("π")){
            result =(float) Math.PI;
        }
        if(operation.equals("e")){
            n = Float.parseFloat(number.getText().toString());
            result =(float) Math.exp(n);
        }
        number.setText(String.valueOf(result));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_switch) {

            n_raise = Float.parseFloat(number.getText().toString());
            Log.i("MainAct","n_raise stored: "+n_raise);

            intent = new Intent(MainActivity.this,SciActivity.class);
            b = new Bundle();
            b.putFloat("n_raise",n_raise);

            intent.putExtras(b);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
