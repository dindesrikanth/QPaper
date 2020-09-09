package com.example.questionpaper.Model;


public class ParticipantModel {
    private String participant_id;
    private String participant_name;

    public ParticipantModel(String participant_id, String participant_name) {
        this.participant_id = participant_id;
        this.participant_name = participant_name;
    }

    public String getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(String participant_id) {
        this.participant_id = participant_id;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }
}
