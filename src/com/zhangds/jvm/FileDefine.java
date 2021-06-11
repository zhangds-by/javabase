package com.zhangds.jvm;

public class FileDefine {

    public static String WATCH_PACKAGE = System.getProperty("user.dir")+"\\out\\production\\javabase\\com\\zhangds\\jvm\\watchfile";

    private String fileName;

    private Long lastDefine;

    public FileDefine(String fileName, Long lastDefine) {
        this.fileName = fileName;
        this.lastDefine = lastDefine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getLastDefine() {
        return lastDefine;
    }

    public void setLastDefine(Long lastDefine) {
        this.lastDefine = lastDefine;
    }
}
