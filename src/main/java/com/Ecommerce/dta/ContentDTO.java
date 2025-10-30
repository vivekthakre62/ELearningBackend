package com.Ecommerce.dta;

public class ContentDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath; // server path (optional)
    private String fileUrl;  // accessible URL for frontend

    public ContentDTO() {}

    public ContentDTO(Long id, String fileName, String fileType, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileUrl = "http://localhost:8080/api/files/download/" + fileName;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
