package com.bhsoft.musiconline.model;

public  class AudioEvent {
    private int type;
    private String audio;

    public AudioEvent() {
    }

    public AudioEvent(int type, String audio) {
        this.type = type;
        this.audio = audio;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
