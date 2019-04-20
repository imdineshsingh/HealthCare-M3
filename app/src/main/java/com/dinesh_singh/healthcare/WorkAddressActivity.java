package com.dinesh_singh.healthcare;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinesh_singh.healthcare.Adapters.SpinnerAdapter;
import com.dinesh_singh.healthcare.databinding.ActivityWorkAddressBinding;

public class WorkAddressActivity extends AppCompatActivity {

    private ActivityWorkAddressBinding getBinding;
    private TextView mTitle;
    private ImageView mReturnBack;
    private TextView  mSkip;
    private String[] mSpinnerData={"State","Uttarakhand","Himachal","Kerala","Rajasthan","Bangal","Gujarat"};
   private static final int REQUEST_CODE=100;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
       getBinding= DataBindingUtil.setContentView(this,R.layout.activity_work_address);

        setToolbar();
        //Setting CustomAdapter on Spinner
        getBinding.spinner.setAdapter(new SpinnerAdapter(WorkAddressActivity.this,R.layout.custom_spinner,mSpinnerData));
        listeners();
    }

    private void listeners() {
        getBinding.btnSearchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                startActivity(new Intent(WorkAddressActivity.this,SelectLocation.class));
            }
        });
        getBinding.btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(WorkAddressActivity.this,TutorialScreenActivity.class));

            }
        });

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE)
        {
            if (resultCode==RESULT_OK)
            {
                /*Bundle bundle = getIntent().getExtras();
                assert bundle != null;
                String value = bundle.getString("address");
                getBinding.etLandmark.setText(value);*/
                String landmark= getIntent().getStringExtra("address");
                String city=getIntent().getStringExtra("city");
                String zip=getIntent().getStringExtra("zip");
                String country=getIntent().getStringExtra("country");

                getBinding.etLandmark.setText(landmark);
                getBinding.etCity.setText(city);
                getBinding.etZip.setText(zip);
                getBinding.etCountry.setText(country);



            }
        }


    }

    private void setToolbar() {

            mTitle=findViewById(R.id.title);
            mTitle.setText("Work Address");
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
                    startActivity(new Intent(WorkAddressActivity.this,SearchPatientActivity.class));

                }
            });
        }
}