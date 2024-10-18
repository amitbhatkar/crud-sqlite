package com.example.data;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_roll, et_DOB, et_contact;
    Button btn_insert;
    Button btn_view;
    Button btn_update;
    Button btn_delete;
    DB_Helper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=findViewById(R.id.et_name);
        et_roll=findViewById(R.id.et_roll);
        et_DOB=findViewById(R.id.et_DOB);
        et_contact=findViewById(R.id.et_contact);

        btn_insert=findViewById(R.id.btn_insert);
        btn_view=findViewById(R.id.btn_view);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);

        dbHelper= new DB_Helper(getApplicationContext());

    }


    // ------------------------------ insert ------------------------------

    public void btnInsertValues(View view){

        String name=et_name.getText().toString();
        String roll=et_roll.getText().toString();
        String DOB1=et_DOB.getText().toString();
        String contact=et_contact.getText().toString();

        dbHelper.getInsertTable(name, roll, DOB1, contact);

        Toast.makeText(this,"User Insert Successfully...", Toast.LENGTH_LONG).show();

        et_name.setText("");
        et_roll.setText("");
        et_DOB.setText("");
        et_contact.setText("");

    }


    // ------------------------------ view ------------------------------
    public void btnShow(View view)
    {
        Cursor res = dbHelper.getData();
        if(res.getCount()==0)
        {
            Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext())
        {
            buffer.append("Name :  "   +res.getString(0)+"\n");
            buffer.append("Roll_no:  " +res.getString(1)+"\n");
            buffer.append("DOB :  "    +res.getString(2)+"\n");
            buffer.append("Contact:  " +res.getString(3)+"\n--------------------------------------------------\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(" User Register");
        builder.setMessage(buffer.toString());
        builder.show();
    }


    //-------------------------------------UPDATE------------------------------------
    public void getUpdate(View view)
    {
        String name=et_name.getText().toString();
        String roll=et_roll.getText().toString();
        String DOB1=et_DOB.getText().toString();
        String contact=et_contact.getText().toString();

        Boolean checkdata=dbHelper.getUpdateData(name,roll, DOB1,contact);

        if (checkdata==true)
            Toast.makeText(this,"Updated Successfully!", Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, "NOT DATA FOUND",Toast.LENGTH_SHORT).show();

    }


    //-------------------------------------DELETE DATA------------------------------------
    public void getDelete(View view)
    {
        String name=et_name.getText().toString();

        Boolean checkdel=dbHelper.getdeleteData(name);

        if (checkdel==true)
            Toast.makeText(this,"Deleted Data Successfully..", Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, "NOT DELETED TRY AGAIN!",Toast.LENGTH_SHORT).show();

    }
}