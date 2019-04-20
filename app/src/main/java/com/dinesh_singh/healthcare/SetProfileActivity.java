package com.dinesh_singh.healthcare;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.dinesh_singh.healthcare.Adapters.SpinnerAdapter;
import com.dinesh_singh.healthcare.databinding.ActivitySetProfileBinding;

import java.util.Calendar;
import java.util.Objects;

public class SetProfileActivity extends AppCompatActivity {

    private ActivitySetProfileBinding getBinding;

    private TextView mTitle;
    private ImageView mReturnBack;
    private TextView  mSkip;
    private Calendar mCalendar;
    private DatePickerDialog dpd;
    private Context mContext;
    private String[] mSpinnerData={"Speciality","Dentist","Cardiologist","Psychatrist"};

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        getBinding= DataBindingUtil.setContentView(this,R.layout.activity_set_profile);

        setToolbar();
        listeners();
        getBinding.spinner.setAdapter(new SpinnerAdapter(SetProfileActivity.this,R.layout.custom_spinner,mSpinnerData));

    }

    private void listeners() {
        getBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(SetProfileActivity.this,WorkAddressActivity.class));
            }
        });
        getBinding.tvDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                mCalendar = Calendar.getInstance();
                final int mYear = mCalendar.get(Calendar.YEAR);
                final int mMonth = mCalendar.get(Calendar.MONTH);
                final int mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                dpd =new DatePickerDialog(SetProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet( DatePicker view, int year, int month, int day ) {
                        getBinding.tvDob.setText(day+"/"+(month+1)+"/"+year);
                        getBinding.tvDob.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                },mYear,mMonth,mDay);
                dpd.show();
            }
        });
    }

    private void setToolbar() {
        mTitle=findViewById(R.id.title);
        mTitle.setText("Set your Profile");
        mReturnBack=findViewById(R.id.back_button);
        mSkip=findViewById(R.id.skip);
        mReturnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        });
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(SetProfileActivity.this,SearchPatientActivity.class));


            }
        });
    }
}
