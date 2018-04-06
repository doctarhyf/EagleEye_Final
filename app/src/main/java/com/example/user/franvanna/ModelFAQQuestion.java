package com.example.user.franvanna;


class ModelFAQQuestion {


    private int id;
    private String question;
    private String reponse;

    public ModelFAQQuestion(){

    }

    public ModelFAQQuestion(int id, String question, String reponse){
        this.id = id;
        this.question = question;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
