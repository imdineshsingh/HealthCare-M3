package com.dinesh_singh.healthcare.Model;

public class PatientsRecyclerViewData {
    private int profilePicId;
    private String patientNameId,patientDobId,patientMobileId;

    public PatientsRecyclerViewData(int profilePicId,String patientNameId,
                                    String patientBirthDayId,String patientMobileId) {
       this.profilePicId=profilePicId;
       this.patientNameId=patientNameId;
       this.patientDobId=patientBirthDayId;
       this.patientMobileId=patientMobileId;
    }

    public int getProfilePicId() {
        return profilePicId;
    }

    public String getPatientNameId() {
        return patientNameId;
    }

    public String getPatientDobId() {
        return patientDobId;
    }

    public String getPatientMobileId() {
        return patientMobileId;
    }
}
