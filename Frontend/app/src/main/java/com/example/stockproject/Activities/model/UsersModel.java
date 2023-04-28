package com.example.stockproject.Activities.model;

/**
 * This class is a model for the RecyclerView to use.
 */
public class UsersModel {
    private String username, valuation, pos;
    private int image;

    /**
     * This constructor simply assigns the values of the user.
     * @param username The name of the user
     * @param pos The position of the user in the list
     * @param valuation The total value the user is
     * @param image The integer value of the picture based off of if they are increasing or decreasing
     */
    public UsersModel(String username, String pos, String valuation, int image) {
        this.username = username;
        this.pos = pos;
        this.valuation = valuation;
        this.image = image;
    }

    /**
     * This methods returns the username in this model.
     * @return a String username
     */
    public String getUsername() {
        return username;
    }
    /**
     * This methods returns the position in this model.
     * @return a String pos
     */
    public String getPos() { return pos; }
    /**
     * This methods returns the valuation in this model.
     * @return a String valuation
     */
    public String getValuation() { return valuation; }
    /**
     * This methods returns the image in this model.
     * @return an int image
     */
    public int getImage() { return image; }
}
