package lk.project.healthCareCenter.bo;

import lk.project.healthCareCenter.entity.Therapist;

public interface TherapistManageBO {
    boolean saveTherapist(Therapist therapist);
    boolean updateTherapist(Therapist therapist);
    boolean deleteTherapist(Therapist therapist);
}
