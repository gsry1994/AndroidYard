package com.example.abdul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText tf;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tf = (EditText)this.findViewById(R.id.txf);
        this.result = (TextView)this.findViewById(R.id.resText);
    }

    public void calculate (View v)
    {
        ShuntingYard syObj = new ShuntingYard(this.tf.getText().toString());
        String answer = syObj.runner();
        this.result.setText(answer);
    }

}
