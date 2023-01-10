package test_data.computer;

public class ComputerData {


    // tên attribute phải đăt trùng với key của file json, tùy k bão lỗi gì những sẽ lấy data ko chish xác
    private String processorType;
    private String ram;
    private String os;
    private String hdd;
    private String software;

    public ComputerData(String processorType, String ram, String os, String hdd, String software) {
        this.processorType = processorType;
        this.ram = ram;
        this.os = os;
        this.hdd = hdd;
        this.software = software;
    }

    public String getProcessorType() {
        return processorType;
    }

    public String getRam() {
        return ram;
    }

    public String getOs() {
        return os;
    }

    public String getHdd() {
        return hdd;
    }

    public String getSoftware() {
        return software;
    }
}
