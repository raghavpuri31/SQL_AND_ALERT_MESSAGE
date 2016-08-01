package com.example.raghav_dell.sqlalert;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4;
    Database_Helper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new Database_Helper(this);
     show();
    }


    public void show()
    {
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is = mydb.add(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                if (is == true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor res = mydb.getdata();
                if (res.getCount() == 0)
                {
                    showmessage("ERROR", "NOTHING FOUND");

                } else
                {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext())
                    {
                        buffer.append(" ID : " + res.getString(0) + "\n");
                        buffer.append(" NAME: " + res.getString(1) + "\n");
                        buffer.append(" SURNAME: " + res.getString(2) + "\n");
                        buffer.append(" MARKS: " + res.getString(3) + "\n\n");


                    }
                    showmessage("DATA", buffer.toString());
                }
            }

        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean supdate= mydb.updatedata(e1.getText().toString(), e2.getText().toString(), e4.getText().toString(), e3.getText().toString());
                if(supdate== true)
                    Toast.makeText(MainActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete =  mydb.deletedata(e4.getText().toString());
                if(delete>0)
                {
                    Toast.makeText(MainActivity.this, "DATA DELETED", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "DATA NOT DELETED", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showmessage(String TITLE,String MESSAGE)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        alert.setTitle(TITLE);
        alert.setMessage(MESSAGE);
        alert.show();
    }


}
