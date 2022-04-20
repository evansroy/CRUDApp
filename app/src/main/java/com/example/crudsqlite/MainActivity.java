package com.example.crudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare the variables
    EditText admissionNo,fullName,contact,email,dob,course,department;
    Button insert,update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Edit Text
        admissionNo = findViewById(R.id.etAdmNo);
        fullName = findViewById(R.id.etFullName);
        contact = findViewById(R.id.etContact);
        email = findViewById(R.id.etEmail);
        dob = findViewById(R.id.etDOB);
        course = findViewById(R.id.etCourse);
        department =findViewById(R.id.etDepartment);

        //Initialize the Buttons
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete =findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        //Implement a click listener on insert button
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String admissionNoTXT = admissionNo.getText().toString();
                String fullNameTXT = fullName.getText().toString();
                String contactTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();
                String dobTXT =dob.getText().toString();
                String courseTXT = course.getText().toString();
                String departmentTXT = department.getText().toString();

                Boolean checkInsertData = DB.insertUserData(admissionNoTXT, fullNameTXT,contactTXT,emailTXT,dobTXT,courseTXT,departmentTXT);
                if (checkInsertData == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        //Implement  a click listener on update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String admissionNoTXT = admissionNo.getText().toString();
                String fullNameTXT = fullName.getText().toString();
                String contactTXT = contact.getText().toString();
                String emailTXT = email.getText().toString();
                String dobTXT =dob.getText().toString();
                String courseTXT = course.getText().toString();
                String departmentTXT = department.getText().toString();

                Boolean checkUpdateData = DB.updateUserData(admissionNoTXT, fullNameTXT,contactTXT,emailTXT,dobTXT,courseTXT,departmentTXT);
                if (checkUpdateData == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        //Implement a click listener on delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String admissionNoTXT = admissionNo.getText().toString();


                Boolean checkDeleteData = DB.deleteData(admissionNoTXT);
                if (checkDeleteData == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        //Implement a Click listener on view button
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("AdmissionNo:" + res.getString(0) + "\n");
                    buffer.append("Full Name:" + res.getString(1) + "\n");
                    buffer.append("Contact:" + res.getString(2) + "\n");
                    buffer.append("Email:" + res.getString(3) + "\n");
                    buffer.append("Date of Birth:" + res.getString(4) + "\n");
                    buffer.append("Course:" + res.getString(5) + "\n");
                    buffer.append("Department:" + res.getString(6) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}