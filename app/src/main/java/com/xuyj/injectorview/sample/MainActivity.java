package com.xuyj.injectorview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xuyj.injectorview.annotation.InjectView;
import com.xuyj.injectorview.annotation.InjectorView;

public class MainActivity extends AppCompatActivity
{

    @InjectorView(R.id.text)
    TextView textView;
    @InjectorView((R.id.text))
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectView.inject(this);
        textView.setText("Binding success!");
    }
}
