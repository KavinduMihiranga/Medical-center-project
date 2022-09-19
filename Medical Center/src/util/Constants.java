package util;

public class Constants {
    public static enum DoctorSpeciality{
        ENT,CARDIO,PHYSICIAN,NEURO;

        public static String getDoctorSpecialityString(DoctorSpeciality doctorSpeciality){
            switch (doctorSpeciality){
                case ENT:
                    return "ENT";
                case PHYSICIAN:
                    return "PHYSICIAN";
                case NEURO:
                    return "NURO";
                case CARDIO:
                    return "CARDIO";

            }
            return "MBBS";
        }

    }
    public static final double MEDICAL_CENTER_CHARGE=600.00;
}
