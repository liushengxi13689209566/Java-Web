public class KK {
    private String major_id;
    private String class_id;

    public KK(String major_id, String class_id) {
        this.major_id = major_id;
        this.class_id = class_id;
    }

    public KK() {
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public int hashCode() {
        return major_id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KK)) {
            return false;
        }
        KK pn = (KK) obj;
        return pn.major_id == major_id || pn.class_id == class_id;
    }

    @Override
    public String toString() {
        return "KK{" +
                "major_id=" + major_id +
                ", class_id=" + class_id +
                '}';
    }
}
