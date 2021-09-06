package com.example.reportcard;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reportcard.models.ReportCard;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity  {
    EditText editTextName,editClassAndSec,editrollno,editadmno,editengText,editGrammerGrade,editDictationGrade,editHindiGrade,editkannada,editMathsGrade,edittables,editscience,editcompScience,editsocialStudies,editreading,editwriting,editdrawing,editsmpeps,editacaPot,editacaAch,editattendance,editremarks;
    Button submitBtn,editBtn,logoutBtn;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editBtn=findViewById(R.id.edit);
        database=FirebaseDatabase.getInstance("https://reportcard-a40aa-default-rtdb.firebaseio.com/");
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Your report card is getting generated");
        editTextName=findViewById(R.id.TextName);
        editClassAndSec=findViewById(R.id.ClassAndSec);
        editrollno=findViewById(R.id.rollNo);
        editadmno=findViewById(R.id.admissionNumber);
        editengText=findViewById(R.id.ENGLISHTEXT);
        editkannada=findViewById(R.id.KANNADA);
        edittables=findViewById(R.id.TABLES);
        editscience=findViewById(R.id.SCIENCE);
        editcompScience=findViewById(R.id.COMPUTERSCIENCE);
        editsocialStudies=findViewById(R.id.SOCIALSTUDIES);
        editreading=findViewById(R.id.READING);
        editwriting=findViewById(R.id.WRITING);
        editdrawing=findViewById(R.id.DRAWING);
        editsmpeps=findViewById(R.id.SPEMPS);
        editacaPot=findViewById(R.id.ACADAMICPOTENTIAL);
        editacaAch=findViewById(R.id.ACADAMICACHIEVEMENT);
        editattendance=findViewById(R.id.ATTENDANCE);
        editremarks=findViewById(R.id.REMARKS);
        editGrammerGrade=findViewById(R.id.Grammer);
        editDictationGrade=findViewById(R.id.Dictation);
        editHindiGrade=findViewById(R.id.Hindi);
        editMathsGrade=findViewById(R.id.Maths);
        submitBtn=findViewById(R.id.Submit);
        logoutBtn=findViewById(R.id.logout);
        submitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty())
                {
                    progressDialog.show();
                    ReportCard newReportCard=new ReportCard(editTextName.getText().toString(),
                            editClassAndSec.getText().toString(),
                            editrollno.getText().toString(),
                            editadmno.getText().toString(),
                            editengText.getText().toString(),
                            editGrammerGrade.getText().toString(),
                            editDictationGrade.getText().toString(),
                            editHindiGrade.getText().toString(),
                            editkannada.getText().toString(),
                            editMathsGrade.getText().toString(),
                            edittables.getText().toString(),
                            editscience.getText().toString(),
                            editcompScience.getText().toString(),
                            editsocialStudies.getText().toString(),
                            editreading.getText().toString(),
                            editwriting.getText().toString(),
                            editdrawing.getText().toString(),
                            editsmpeps.getText().toString(),
                            editacaPot.getText().toString(),
                            editacaAch.getText().toString(),
                            editattendance.getText().toString(),
                            editremarks.getText().toString());
                    database.getReference().child("ReportCard").child(editadmno.getText().toString()).setValue(newReportCard).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.hide();
                            generatePDF(editTextName.getText().toString(),
                                    editClassAndSec.getText().toString(),
                                    editrollno.getText().toString(),
                                    editadmno.getText().toString(),
                                    editengText.getText().toString(),
                                    editGrammerGrade.getText().toString(),
                                    editDictationGrade.getText().toString(),
                                    editHindiGrade.getText().toString(),
                                    editkannada.getText().toString(),
                                    editMathsGrade.getText().toString(),
                                    edittables.getText().toString(),
                                    editscience.getText().toString(),
                                    editcompScience.getText().toString(),
                                    editsocialStudies.getText().toString(),
                                    editreading.getText().toString(),
                                    editwriting.getText().toString(),
                                    editdrawing.getText().toString(),
                                    editsmpeps.getText().toString(),
                                    editacaPot.getText().toString(),
                                    editacaAch.getText().toString(),
                                    editattendance.getText().toString(),
                                    editremarks.getText().toString());
                        }
                    });
                }
            }
        });

        editBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editadmno.getText().toString().isEmpty())return;
                Intent intent=new Intent(MainActivity.this,EditReportCard.class);
                intent.putExtra("admno",editadmno.getText().toString());
                startActivity(intent);
            }
        });
        logoutBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        }



    public boolean checkEmpty(){
        if(editTextName.getText().toString().isEmpty()) { editTextName.setError("Name is required"); return false;}
        if(editClassAndSec.getText().toString().isEmpty()){editClassAndSec.setError("Class and section required"); return false;}
        if(editrollno.getText().toString().isEmpty()) {editrollno.setError("Rollno is required"); return false;}
        if(editadmno.getText().toString().isEmpty()) {editadmno.setError("admission number Required"); return false;}
        if(editengText.getText().toString().isEmpty()) {editengText.setError("Required"); return false;}
        if(editGrammerGrade.getText().toString().isEmpty()) {editGrammerGrade.setError("Required"); return false;}
        if(editDictationGrade.getText().toString().isEmpty()) {editDictationGrade.setError("Required"); return false;}
        if(editHindiGrade.getText().toString().isEmpty()) {editHindiGrade.setError("Required"); return false;}
        if(editkannada.getText().toString().isEmpty()) {editkannada.setError("Required"); return false;}
        if(editMathsGrade.getText().toString().isEmpty()) {editMathsGrade.setError("Required"); return false;}
        if(edittables.getText().toString().isEmpty()) {edittables.setError("Required"); return false;}
        if(editscience.getText().toString().isEmpty()) {editscience.setError("Required"); return false;}
        if(editcompScience.getText().toString().isEmpty()) {editcompScience.setError("Required"); return false;}
        if(editsocialStudies.getText().toString().isEmpty()) {editsocialStudies.setError("Required"); return false;}
        if(editreading.getText().toString().isEmpty()) {editreading.setError("Required"); return false;}
        if(editwriting.getText().toString().isEmpty()) {editwriting.setError("Required"); return false;}
        if(editdrawing.getText().toString().isEmpty()) {editdrawing.setError("Required"); return false;}
        if(editsmpeps.getText().toString().isEmpty()) {editsmpeps.setError("Required"); return false;}
        if(editacaPot.getText().toString().isEmpty()) {editacaPot.setError("Required"); return false;}
        if(editacaAch.getText().toString().isEmpty()) {editacaAch.setError("Required"); return false;}
        if(editattendance.getText().toString().isEmpty()) {editattendance.setError("Required"); return false;}
        if(editremarks.getText().toString().isEmpty()) {editremarks.setError("Required"); return false;}
       return true;


    }
      public void generatePDF(String name, String classandsec, String rollno, String admno, String engtext, String grammer, String dictation, String hindi,String kannada, String maths, String tables, String science, String compscience,String socialStudies, String reading, String writing, String drawing, String smpeps, String acadamicPotential, String acadamicAchievement, String attendance, String remarks) {
        @SuppressLint("InflateParams") View view=getLayoutInflater().inflate(R.layout.pdf_design,null);
          LinearLayout linearLayout=view.findViewById(R.id.root);
        TextView textView=(TextView) linearLayout.findViewById(R.id.nameValue);
        textView.setText(name);
         textView=(TextView) linearLayout.findViewById(R.id.classValue);
        textView.setText(classandsec);
          textView=(TextView) linearLayout.findViewById(R.id.rollvalue);
          textView.setText(rollno);
          textView=(TextView) linearLayout.findViewById(R.id.admValue);
          textView.setText(admno);
          textView=(TextView) linearLayout.findViewById(R.id.engTextValue);
          textView.setText(engtext);
        textView=(TextView) linearLayout.findViewById(R.id.grammerMarks);
        textView.setText(grammer);
        textView=(TextView) linearLayout.findViewById(R.id.dictationMarks);
        textView.setText(dictation);
        textView=(TextView) linearLayout.findViewById(R.id.hindiMarks);
        textView.setText(hindi);
          textView=(TextView) linearLayout.findViewById(R.id.kannadaValue);
          textView.setText(kannada);
        textView=(TextView) linearLayout.findViewById(R.id.mathsMarks);
        textView.setText(maths);
          textView=(TextView) linearLayout.findViewById(R.id.tablesValue);
          textView.setText(tables);
          textView=(TextView) linearLayout.findViewById(R.id.scienceValue);
          textView.setText(science);
          textView=(TextView) linearLayout.findViewById(R.id.computerScienceValue);
          textView.setText(compscience);
          textView=(TextView) linearLayout.findViewById(R.id.socialStudiesValues);
          textView.setText(socialStudies);
          textView=(TextView) linearLayout.findViewById(R.id.ReadingValue);
          textView.setText(reading);
          textView=(TextView) linearLayout.findViewById(R.id.writingValue);
          textView.setText(writing);
          textView=(TextView) linearLayout.findViewById(R.id.drawingValue);
          textView.setText(drawing);
          textView=(TextView) linearLayout.findViewById(R.id.spemsValues);
          textView.setText(smpeps);
          textView=(TextView) linearLayout.findViewById(R.id.acadamicPotentialValue);
          textView.setText(acadamicPotential);
          textView=(TextView) linearLayout.findViewById(R.id.AcademicAchievementValue);
          textView.setText(acadamicAchievement);
          textView=(TextView) linearLayout.findViewById(R.id.attendanceValue);
          textView.setText(attendance);
          textView=(TextView) linearLayout.findViewById(R.id.remarksValue);
          textView.setText(remarks);

          PdfGenerator.getBuilder()
                  .setContext(this)
                  .fromViewSource()
                  .fromView(view)
                  .setFileName(name)
                  .setFolderName("Report cards")
                  .build(new PdfGeneratorListener() {
                      @Override
                      public void onStartPDFGeneration() {

                      }

                      @Override
                      public void onFinishPDFGeneration() {

                          Toast.makeText(MainActivity.this,"Your pdf is genrated\nLocation:",Toast.LENGTH_SHORT).show();
                      }
                      @Override
                      public void onSuccess(SuccessResponse response) {
                          super.onSuccess(response);
                          System.out.println(response.getFile().getPath());
                          /* If PDF is generated successfully then you will find SuccessResponse
                           * which holds the PdfDocument,File and path (where generated pdf is stored)*/



                      }
                  });

      }






  }





