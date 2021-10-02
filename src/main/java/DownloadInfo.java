public class DownloadInfo {
    private int rate;

    private String fileName;

    public DownloadInfo(int rate, String fileName) {
        this.rate = rate;
        this.fileName = fileName;
    }

    public int getRate() {
        return rate;
    }

    public String getFileName() {
        return fileName;
    }
}
