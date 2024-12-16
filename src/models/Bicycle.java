package models;

public class Bicycle {
    private String name;
    private int year;
    private float weight;
    private String condition;
    private int hourlyRate;
    private String location;
    private int userId;

    public Bicycle(String name, int year, float weight, String rating, int hourlyRate, String location, int userId) {
        this.name = name;
        this.year = year;
        this.weight = weight;
        this.condition = rating;
        this.hourlyRate = hourlyRate;
        this.location = location;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String rating) {
        this.condition = rating;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
