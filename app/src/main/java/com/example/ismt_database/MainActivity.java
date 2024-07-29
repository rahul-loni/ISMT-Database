package com.example.ismt_database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

    }
}