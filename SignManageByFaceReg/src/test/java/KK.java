public class KK {
    private int student_id;
    private int major_id;
    private int class_id;

    public KK(int student_id, int major_id, int class_id) {
        this.student_id = student_id;
        this.major_id = major_id;
        this.class_id = class_id;
    }

    public KK() {
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + student_id;
        result = 31 * result + major_id;
        result = 31 * result + class_id;
        return result;
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
        return pn.student_id == student_id && pn.major_id == major_id && pn.class_id == class_id;
    }

    @Override
    public String toString() {
        return "KK{" +
                "student_id=" + student_id +
                ", major_id=" + major_id +
                ", class_id=" + class_id +
                '}';
    }
}
