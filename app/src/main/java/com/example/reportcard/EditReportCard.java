package com.example.reportcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reportcard.databinding.ActivityEditReportCardBinding;
import com.example.reportcard.models.ReportCard;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditReportCard extends AppCompatActivity {
    String admno;
    FirebaseDatabase database;
    ActivityEditReportCardBinding binding;
    ReportCard r;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_report_card);
        binding=ActivityEditReportCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        admno=getIntent().getStringExtra("admno");
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Getting loaded");
        database=FirebaseDatabase.getInstance("https://reportcard-a40aa-default-rtdb.firebaseio.com/");
        progressDialog.show();
        database.getReference().child("ReportCard").child(admno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                r=snapshot.getValue(ReportCard.class);
                if(r!=null){
                    setReportCard();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.editSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setName(binding.TextName.getText().toString());
                r.setRollno(binding.rollNo.getText().toString());
                r.setAdmissionNo(binding.admissionNumber.getText().toString());
                r.setClassAndSec(binding.ClassAndSec.getText().toString());
                r.setEnglish(binding.ENGLISHTEXT.getText().toString());
                r.setGrammer(binding.Grammer.getText().toString());
                r.setDictation(binding.Dictation.getText().toString());
                r.setHindi(binding.Hindi.getText().toString());
                r.setKannada(binding.KANNADA.getText().toString());
                r.setMaths(binding.Maths.getText().toString());
                r.setTables(binding.TABLES.getText().toString());
                r.setScience(binding.SCIENCE.getText().toString());
                r.setComputerScience(binding.COMPUTERSCIENCE.getText().toString());
                r.setSocialScience(binding.SOCIALSTUDIES.getText().toString());
                r.setReading(binding.READING.getText().toString());
                r.setWritting(binding.WRITING.getText().toString());
                r.setDrawing(binding.DRAWING.getText().toString());
                r.setSpemps(binding.SPEMPS.getText().toString());
                r.setAcademicPotetial(binding.ACADAMICPOTENTIAL.getText().toString());
                r.setAcademicAchievement(binding.ACADAMICACHIEVEMENT.getText().toString());
                r.setAttendance(binding.ATTENDANCE.getText().toString());
                r.setRemarks(binding.REMARKS.getText().toString());
                database.getReference().child("ReportCard").child(admno).setValue(r);
                Toast.makeText(EditReportCard.this,"Your Pdf is edited",Toast.LENGTH_SHORT).show();

                generatePDF(r.getName(),r.getClassAndSec(),r.getRollno(),r.getAdmissionNo(),r.getEnglish(),r.getGrammer(),r.getDictation(),r.getHindi(),r.getKannada(),
                        r.getMaths(),r.getTables(),r.getScience(),r.getComputerScience(),r.getSocialScience(),r.getReading(),r.getWritting(),r.getDrawing()
                ,r.getSpemps(),r.getAcademicPotetial(),r.getAcademicAchievement(),r.getAttendance(),r.getRemarks());




            }
        });


    }

    void setReportCard(){
        binding.TextName.setText(r.getName());
        binding.ClassAndSec.setText(r.getClassAndSec());
        binding.rollNo.setText(r.getRollno());
        binding.admissionNumber.setText(r.getAdmissionNo());
        binding.ENGLISHTEXT.setText(r.getEnglish());
        binding.Grammer.setText(r.getGrammer());
        binding.Dictation.setText(r.getDictation());
        binding.Hindi.setText(r.getHindi());
        binding.KANNADA.setText(r.getKannada());
        binding.Maths.setText(r.getMaths());
        binding.TABLES.setText(r.getTables());
        binding.SCIENCE.setText(r.getScience());
        binding.COMPUTERSCIENCE.setText(r.getComputerScience());
        binding.SOCIALSTUDIES.setText(r.getSocialScience());
        binding.READING.setText(r.getReading());
        binding.WRITING.setText(r.getWritting());
        binding.DRAWING.setText(r.getDrawing());
        binding.SPEMPS.setText(r.getSpemps());
        binding.ACADAMICPOTENTIAL.setText(r.getAcademicPotetial());
        binding.ACADAMICACHIEVEMENT.setText(r.getAcademicAchievement());
        binding.ATTENDANCE.setText(r.getAttendance());
        binding.REMARKS.setText(r.getRemarks());

        progressDialog.hide();
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

                        Toast.makeText(EditReportCard.this,"Your pdf is genrated\nLocation:",Toast.LENGTH_SHORT).show();
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