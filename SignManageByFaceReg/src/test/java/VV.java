public class VV {
    private int late_count; //迟到人数
    private int truancy_count; //旷课人数
    private int success_count; //出勤人数

    public VV(int late_count, int truancy_count, int success_count) {
        this.late_count = late_count;
        this.truancy_count = truancy_count;
        this.success_count = success_count;
    }

    public VV() {
    }

    public int getLate_count() {
        return late_count;
    }

    public void setLate_count(int late_count) {
        this.late_count = late_count;
    }

    public int getTruancy_count() {
        return truancy_count;
    }

    public void setTruancy_count(int truancy_count) {
        this.truancy_count = truancy_count;
    }

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    @Override
    public String toString() {
        return "VV{" +
                "late_count=" + late_count +
                ", truancy_count=" + truancy_count +
                ", success_count=" + success_count +
                '}';
    }
}
