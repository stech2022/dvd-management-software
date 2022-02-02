package com.example.dvdmanagementsoftware.dvd;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DVD {
    private int id;
    private String title;
    private String actors;
    private String director;
    private String produceDate;
    private int duration;
    private String languages;
    private String subtitles;
    private String category;
    private double price;
    private int units;

    public DVD() {}

    public DVD(
            int id,
            String title,
            String actors,
            String director,
            String produceDate,
            int duration,
            String languages,
            String subtitles,
            String category,
            double price,
            int units
    ) {
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.director = director;
        this.produceDate = produceDate;
        this.duration = duration;
        this.languages = languages;
        this.subtitles = subtitles;
        this.category = category;
        this.price = price;
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
