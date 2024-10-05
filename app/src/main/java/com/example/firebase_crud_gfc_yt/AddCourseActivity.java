package com.example.firebase_crud_gfc_yt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {

    private TextInputEditText courseNameEdt, coursePriceEdt, courseSuitedForEdt, courseImgEdt, courseLinkEdt, courseDescEdt;
    private Button addCourseBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String courseID=null;
    String courseName=null;
    String coursePrice=null;
    String suitedFor=null;
    String courseImg=null;
    String courseLink=null;
    String courseDesc=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseNameEdt=findViewById(R.id.idEdtCourseName);
        coursePriceEdt=findViewById(R.id.idEdtCoursePrice);
        courseSuitedForEdt=findViewById(R.id.idEdtCourseSuitedFor);
        courseImgEdt=findViewById(R.id.idEdtCourseImageLink);
        courseLinkEdt=findViewById(R.id.idEdtCourseLink);
        courseDescEdt=findViewById(R.id.idEdtCourseDesc);
        addCourseBtn=findViewById(R.id.idBtnAddCourse);
        loadingPB=findViewById(R.id.idPBLoading);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Courses");

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               courseName=courseNameEdt.getText().toString();
               coursePrice=coursePriceEdt.getText().toString();
               suitedFor=courseSuitedForEdt.getText().toString();
               courseImg=courseImgEdt.getText().toString();
               courseLink=courseLinkEdt.getText().toString();
               courseDesc=courseDescEdt.getText().toString();

               courseID=courseName;

               CourseRVModel courseRVModel=new CourseRVModel(courseName,coursePrice,suitedFor,courseImg,courseLink,courseDesc,courseID);
               databaseReference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(courseID).setValue(courseRVModel);
                       Toast.makeText(AddCourseActivity.this, "Course Added...", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(AddCourseActivity.this,MainActivity.class));
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       Toast.makeText(AddCourseActivity.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });

    }
}