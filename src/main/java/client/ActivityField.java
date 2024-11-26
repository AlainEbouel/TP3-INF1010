package client;

public enum ActivityField {

    INFORMATIQUE("Informatique"),
    ADMIN_AFFAIRES("Administration des affaires"),
    EDUC_PRESCOLAIRE("Education prescolaire"),
    ART_VISUEL("Art visuel"),
    BIOCHIMIE("Biochimie"),
    BIO_MEDICALE("Biologie_medicale"),
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
    public String getField() { return activityField; }
    ActivityField(String activityField) {
        this.activityField = activityField;
    }
}
