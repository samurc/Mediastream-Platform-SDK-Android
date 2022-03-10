package com.example.mediastreamplatformsdkandroidsamples;

public class ItemList {
    int Image;
    String Title;
    String Description;
    String id;
    String accountID;
    String type;
    Boolean isLive;
    Boolean isService;
    Boolean isDVR;
    Boolean isLocal;

    public Boolean getLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public ItemList() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

    public Boolean getService() {
        return isService;
    }

    public void setService(Boolean service) {
        isService = service;
    }

    public Boolean getDVR() {
        return isDVR;
    }

    public void setDVR(Boolean DVR) {
        isDVR = DVR;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



}
