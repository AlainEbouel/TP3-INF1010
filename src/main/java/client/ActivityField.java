package client;

import java.util.ArrayList;

public enum ActivityField {

    INFORMATIQUE("Informatique"),
    ADMIN_AFFAIRES("Administration des affaires"),
    EDUC_PRESCOLAIRE("Education prescolaire"),
    ART_VISUEL("Art visuel"),
    BIOCHIMIE("Biochimie"),
    BIO_MEDICALE("Biologie medicale"),
    CHIMIE("Chimie"),
    COM_SOCIALE("Communication sociale"),
    ETUDES_FRANCAISES("Etudes francaise"),
    GENIE_ELEC("Genie electrique"),
    GENIE_INDUST("Genie industriel"),
    GENIE_MEDICALE("Genie medicale"),
    GENIE_MECA("Genie mecanique"),
    GEO_ENVIRONNEMENTALE("Geographie environnementale"),
    HISTOIRE("Histoire"),
    KINESIOLOGIE("Kinesiologie"),
    MATHEMATIQUES("Mathematiques"),
    PHILOSOPHIE("Philosophie"),
    PHYSIOTHERAPIE("Physiotherapie"),
    PHYSIQUE("Physique"),
    PSYCHOLOGIE("Psychologie"),
    SCIENCES_INFIRMIERES("Sciences infirmieres"),
    TRADUCTION("Traduction"),
    TRAVAIL_SOCIAL("Travail social");

    private final String activityField;

    public static ArrayList<ActivityField> getActivityFields() {
        ArrayList<ActivityField> activityFields = new ArrayList<ActivityField>();
        activityFields.add(ActivityField.INFORMATIQUE);
        activityFields.add(ActivityField.ADMIN_AFFAIRES);
        activityFields.add(ActivityField.EDUC_PRESCOLAIRE);
        activityFields.add(ActivityField.ART_VISUEL);
        activityFields.add(ActivityField.BIOCHIMIE);
        activityFields.add(ActivityField.BIO_MEDICALE);
        activityFields.add(ActivityField.CHIMIE);
        activityFields.add(ActivityField.GENIE_ELEC);
        activityFields.add(ActivityField.GENIE_INDUST);
        activityFields.add(ActivityField.GENIE_MEDICALE);
        activityFields.add(ActivityField.GENIE_MECA);
        activityFields.add(ActivityField.GEO_ENVIRONNEMENTALE);
        activityFields.add(ActivityField.HISTOIRE);
        activityFields.add(ActivityField.KINESIOLOGIE);
        activityFields.add(ActivityField.MATHEMATIQUES);
        activityFields.add(ActivityField.PHYSIQUE);
        activityFields.add(ActivityField.PSYCHOLOGIE);
        activityFields.add(ActivityField.PHYSIQUE);
        activityFields.add(ActivityField.PSYCHOLOGIE);
        activityFields.add(ActivityField.PHYSIQUE);
        activityFields.add(ActivityField.SCIENCES_INFIRMIERES);
        activityFields.add(ActivityField.TRADUCTION);

        return activityFields;
    }

    public String getField() { return activityField; }
    ActivityField(String activityField) {
        this.activityField = activityField;
    }
}
