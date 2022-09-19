package util;

import model.Patient;

public interface LoadPatientEvent {
    void loadPatient(Patient patient);
}
