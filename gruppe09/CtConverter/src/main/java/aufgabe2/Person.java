package aufgabe2;

import java.time.LocalDate;
import java.util.*;

/**
 * The class Person stores the name, birthdate, weight, height and special identification number of a Patient,
 * along with an image path and data for the ct.
 */
public class Person {
    private String name;
    private LocalDate birth;
    private int weight;
    private int height;
    private String iz;
    private String imagePath;
    private Integer[] dimensionSize;
    private Short[][][] ctData;
    private HashMap<Integer, String> comments;

    public   HashMap<Integer, String> getComments() {
        return comments;
    }

    public Person(String name, LocalDate birth, int weight, int height, String iz, int xDimensionSize, int yDimensionSize, int zDimensionSize){
        setName(name);
        setBirth(birth);
        setWeight(weight);
        setHeight(height);
        setIz(iz);
        setDimensionSize(new Integer[]{xDimensionSize, yDimensionSize, zDimensionSize});
    }

    public Person(){}

    /**
     * The method setComments changes the HashMap containing the comments in a ct file.
     *
     * @param comments The HashMap with the line number and comment.
     */
    protected void setComments(HashMap<Integer, String> comments) {
        this.comments = comments;
    }

    public Short[][][] getCtData() {
        return ctData;
    }

    /**
     * The method setCtData changes the gray numbers in a ct.
     *
     * @param ctData The ct numbers splittet in width, length and height.
     */
    public void setCtData(Short[][][] ctData) {
        this.ctData = ctData;
    }

    public Integer[] getDimensionSize() {
        return dimensionSize;
    }

    /**
     * The method setDimensionSize changes the lengths of the x, y and z coordinates that make up the total ct size.
     *
     * @param dimensionSize The x, y and z lengths of a ct.
     */
    protected void setDimensionSize(Integer[] dimensionSize) {
        this.dimensionSize = dimensionSize;
    }

    public String getName() {
        return name;
    }

    /**
     * The method setName changes the name of the person.
     *
     * @param name The prename(s) and last name(s) of the person {@code not null}.
     */
    protected void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    /**
     * The method setBirth changes the birthday of the person.
     *
     * @param birth The day, month and year of the person in the format "dd.mm.yyyy" {@code not null}.
     */
    protected void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * The method setWeight changes the persons weight.
     *
     * @param weight The weight of the person in kg {@code not null}.
     */
    protected void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    /**
     * The method setHeight changes the height of the person.
     *
     * @param height The height of the person in cm.
     */
    protected void setHeight(int height) {
        this.height = height;
    }

    public String getIz() {
        return iz;
    }

    /**
     * The method setIz changes the identification number of the person.
     *
     * @param iz The uniq identification number.
     */
    protected void setIz(String iz) {
        this.iz = iz;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * The method setImagePath changes the path to an image of a ct.
     *
     * @param imagePath The path to a ct image.
     */
    protected void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}