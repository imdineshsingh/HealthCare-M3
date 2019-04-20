package com.dinesh_singh.healthcare;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dinesh_singh.healthcare.Adapters.PatientsRecyclerViewAdapter;
import com.dinesh_singh.healthcare.Model.PatientsRecyclerViewData;
import com.dinesh_singh.healthcare.databinding.ActivitySearchPatientBinding;

import java.util.ArrayList;

public class SearchPatientActivity extends AppCompatActivity {

    private ActivitySearchPatientBinding getBinding;
    private Context mContext;
    private TextView mTitle;
    private TextView mSkip;
    private ImageView mBackButton;

    PatientsRecyclerViewAdapter patientsRecyclerViewAdapter;
    private ArrayList<PatientsRecyclerViewData> mExampleList;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        getBinding= DataBindingUtil.setContentView(this,R.layout.activity_search_patient);

        setToolbar();
        buildRecyclerView();

/*

        list= new ArrayList<>();
        list.add();
*/

/*
        getBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit( String query ) {
                if(list.contains(query)){
                    patientsRecyclerViewAdapter.getFilter().filter(query);
                }else{
                    Toast.makeText(SearchData.this, "No Match found",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange( String newText ) {
                return false;
            }
        });
*/
    /*
       getBinding.etSearch.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
           }
           @Override
           public void onTextChanged( CharSequence s, int start, int before, int count ) {
           }
           @Override
           public void afterTextChanged( Editable s ) {
               filter(s.toString());
           }
       });
    */

        //ADDING SEARCH FOR RECYCLER VIEW
        getBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit( String query ) {
                return false;
            }
            @Override
            public boolean onQueryTextChange( String newText ) {
                filter(newText.toString());
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<PatientsRecyclerViewData> filteredList = new ArrayList<>();
        for (PatientsRecyclerViewData item : mExampleList) {
            if (item.getPatientNameId().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        patientsRecyclerViewAdapter.filterList(filteredList);
    }


    //SETTING TOOLBAR
    private void setToolbar() {
        mTitle=findViewById(R.id.title);
        mSkip=findViewById(R.id.skip);
        mTitle.setText("Search Patient");
        mSkip.setVisibility(View.GONE);
        mBackButton=findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(SearchPatientActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    //ADDING RECYCLERVIEW
    private void buildRecyclerView() {
        ArrayList<PatientsRecyclerViewData> data=new ArrayList<>();
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient1),
                getResources().getString(R.string.dob_patient1),getResources().getString(R.string.mob_patient1)));
        data.add(new PatientsRecyclerViewData(R.mipmap.patient_icon,getResources().getString(R.string.name_patient2),
                getResources().getString(R.string.dob_patient2),getResources().getString(R.string.mob_patient2)));

        getBinding.patientsRecyclerView.setHasFixedSize(true);
        getBinding.patientsRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        patientsRecyclerViewAdapter=new PatientsRecyclerViewAdapter(data);
        getBinding.patientsRecyclerView.setAdapter(patientsRecyclerViewAdapter);


    }

}