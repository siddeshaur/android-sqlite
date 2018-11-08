package com.example.chandu.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText e1,e2,e3;
    Button b,b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=mydb.insertData(e3.getText().toString(),e2.getText().toString(),e1.getText().toString());
                if(b)
                    Toast.makeText(MainActivity.this,"insetted",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

            }
        });
        b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              Cursor res= mydb.getdata();
              if(res.getCount()==0)
              {
                  Toast.makeText(MainActivity.this,"no data",Toast.LENGTH_SHORT).show();
                  showM("mark","noting found");
              }
              else
              {
                  StringBuffer buffer=new StringBuffer();
                  while(res.moveToNext()) {
                      buffer.append("ID:" + res.getString(0) + "\n");
                      buffer.append("name:" + res.getString(1) + "\n");
                      buffer.append("surnsme:" + res.getString(2) + "\n");
                      buffer.append("mark:" + res.getString(3) + "\n\n");
                  }
                  showM("data",buffer.toString());
              }
            }
        });


    }
    public  void showM(String t,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(t);
        builder.setMessage(Message);
        builder.show();
    }
}
