package lk.project.healthCareCenter.bo;

public interface PaymentBO {
    double checkProgramPayments(String therapistID);
    boolean savePayment(String date, String toPayAmount, String patientID, String programID);
    String checkAvailableWeeks(String patientID, String programID);
}
