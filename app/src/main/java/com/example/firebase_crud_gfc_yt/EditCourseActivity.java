package com.example.firebase_crud_gfc_yt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCourseActivity extends AppCompatActivity {
    private TextInputEditText courseNameEdt, coursePriceEdt, courseSuitedForEdt, courseImgEdt, courseLinkEdt, courseDescEdt;
    private Button updateCourseBtn,deleteCourseBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String courseID=null;

    private CourseRVModel courseRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Courses").child(courseID);
        courseNameEdt=findViewById(R.id.idEdtCourseName);
        coursePriceEdt=findViewById(R.id.idEdtCoursePrice);
        courseSuitedForEdt=findViewById(R.id.idEdtCourseSuitedFor);
        courseImgEdt=findViewById(R.id.idEdtCourseImageLink);
        courseLinkEdt=findViewById(R.id.idEdtCourseLink);
        courseDescEdt=findViewById(R.id.idEdtCourseDesc);
        updateCourseBtn=findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn=findViewById(R.id.idBtnDeleteCourse);
        loadingPB=findViewById(R.id.idPBLoading);

    }
}