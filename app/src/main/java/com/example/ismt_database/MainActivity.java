package com.example.ismt_database;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txt_id,txt_name,txt_marks;
    Button btn_insertData,btn_viewData,btn_editData,btn_deleteData;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt_id=findViewById(R.id.eId);
        txt_name=findViewById(R.id.eName);
        txt_marks=findViewById(R.id.eMarks);
        btn_insertData=findViewById(R.id.btn_insert);
        btn_viewData=findViewById(R.id.btn_View);
        btn_editData=findViewById(R.id.btn_Edit);
        btn_deleteData=findViewById(R.id.btn_Delete);

        myDB=new DatabaseHelper(this);

        // insert Data Method
        btn_insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean inserted=myDB.insertData(
                txt_id.getText().toString(),
               txt_name.getText().toString(),
               txt_marks.getText().toString());
               if (inserted==true){
                   Toast.makeText(MainActivity.this, "Data has Inserted ", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Something error", Toast.LENGTH_SHORT).show();
               }

            }
        });


        btn_viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=myDB.getAllData();
                if (cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("Id"+cursor.getString(0)+"\n");
                    buffer.append("name"+cursor.getString(1)+"\n");
                    buffer.append("marks"+cursor.getString(2)+"\n");
                }
                show("Data",buffer.toString());
            }
        });

        btn_editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDB.updateData(
                        txt_id.getText().toString().trim(),
                        txt_name.getText().toString().trim(),
                        txt_marks.getText().toString().trim());
                if (isUpdate==true){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

      btn_deleteData.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Integer deleteData=myDB.deleteData(txt_id.getText().toString().trim());
              if (deleteData>0){
                  Toast.makeText(MainActivity.this, "Data Deleted ", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(MainActivity.this, "Deta has No Deleted", Toast.LENGTH_SHORT).show();
              }
          }
      });
    }
    public void show(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}