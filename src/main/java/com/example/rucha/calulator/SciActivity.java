package com.example.rucha.calulator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SciActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView number;

    private String operation;
    private String sci_num;
    private float n_raise;
    private Intent intent;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scientific);

        number = (TextView)findViewById(R.id.number);

        int ids[] = {R.id.sin,R.id.cos,R.id.tan,R.id.imaginary,R.id.naturallog,R.id.log,R.id.del,
                R.id.percent,R.id.fact,R.id.sqrt,R.id.exp,R.id.opbrac,R.id.closebrac,R.id.pi,
                R.id.eulers};
        for(int id: ids){

            View v = (View)findViewById(id);

            v.setOnClickListener(this);
        }

        b = new Bundle();
        b=getIntent().getExtras();
        n_raise = b.getFloat("n_raise");
        Log.i("MainAct","in Sci... on create.. n_raise = "+n_raise);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.del:
                number.setText("0");
                break;

            default:
                sci_num = ((Button) v).getText().toString();
                typeDigits(sci_num);
                break;
        }

    }


    private void operand(String s) {
        Log.i("MainAct","in operand() n_raise = "+n_raise);
        operation = s;
        intent = new Intent(SciActivity.this,MainActivity.class);
        b = new Bundle();

        b.putString("operation",operation);
        b.putFloat("n_raise",n_raise);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }

    private void typeDigits(String num) {

        String onScreenNum = number.getText().toString();
        Log.i("MainAct","number on screen is :"+onScreenNum);

        if (onScreenNum.equals("0")){
            Log.i("MainAct","if 0 then num clicked and displayed is :"+num);
            number.setText(num);
            operand(sci_num);
        }
        else{
            onScreenNum = onScreenNum.concat(num);
            Log.i("MainAct","if onscreen number is not 0 concat number is :"+onScreenNum);
            number.setText(onScreenNum);
            operand(sci_num);
        }

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
            startActivity(new Intent(this,MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


}
