package com.dinesh_singh.healthcare.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinesh_singh.healthcare.Model.PatientsRecyclerViewData;
import com.dinesh_singh.healthcare.R;
import java.util.ArrayList;
public class PatientsRecyclerViewAdapter extends RecyclerView.Adapter<PatientsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<PatientsRecyclerViewData> data;
    public PatientsRecyclerViewAdapter(ArrayList<PatientsRecyclerViewData> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup viewGroup, int i ) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder( @NonNull ViewHolder viewHolder, int position ) {
        PatientsRecyclerViewData patientsRecyclerViewData=data.get(position);
        viewHolder.mProfilePic.setImageResource(patientsRecyclerViewData.getProfilePicId());
        viewHolder.mPatientsName.setText(patientsRecyclerViewData.getPatientNameId());
        viewHolder.mPatientDob.setText(patientsRecyclerViewData.getPatientDobId());
        viewHolder.mPatientMobile.setText(patientsRecyclerViewData.getPatientMobileId());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mProfilePic;
        private TextView mPatientsName,mPatientDob,mPatientMobile;
        ViewHolder( @NonNull View itemView ) {
            super(itemView);
            this.mProfilePic=itemView.findViewById(R.id.iv_profile_pic);
            this.mPatientsName=itemView.findViewById(R.id.tv_patient_name);
            this.mPatientDob=itemView.findViewById(R.id.tv_patient_dob);
            this.mPatientMobile=itemView.findViewById(R.id.tv_mobile_number);
        }
    }

    public void filterList(ArrayList<PatientsRecyclerViewData> filteredList) {
        data= filteredList;
        notifyDataSetChanged();
    }

}


