package guru.qa.model;

public class CatModel {
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public boolean isLoved() {
        return isLoved;
    }

    public String[] getDiet() {
        return diet;
    }

    public String getFood(int index) {
        return diet[index];
    }

    public String name;
    public String owner;
    public String breed;
    public int age;
    public boolean isLoved;
    public String[] diet;
}
