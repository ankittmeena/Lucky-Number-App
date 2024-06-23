package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView txtview, num;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn=findViewById(R.id.button1);
        num=findViewById(R.id.number);
        txtview=findViewById(R.id.textView2);

        Intent i=getIntent();
        String userName=i.getStringExtra("name");

        int number=randomnumber();
        num.setText(" "+randomnumber());

        // share
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedata(userName, number);
            }
        });

    }
    public int randomnumber(){
        Random random = new Random();
        int upper_limit=1000;
        int randomnum=random.nextInt(upper_limit);
        return randomnum;
    }
    public void sharedata(String username, int num){
        // Implicit Intent
        Intent i= new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT,username+" got luck today !!!" );
        i.putExtra(Intent.EXTRA_TEXT, username+"'s Lucky number is: "+num);
        startActivity(Intent.createChooser(i, "Choose the platform"));
    }
}