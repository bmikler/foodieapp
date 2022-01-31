package pl.javastart.foodieapp.entity;

public enum OrderStatus {
    NEW("New"), IN_PROGRESS("In progress"), COMPLETE("Complete");

    private String description;

    OrderStatus(String name) {
        this.description = name;
    }

    public String getDescription() {
        return description;
    }
}
