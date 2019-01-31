package mbortnic.unitfactory.swingy.model.Artifact;

import java.util.Random;

public class Artifact {

    private String artType;
    private static final String[] arts = {"WEAPON", "ARMOR", "HELM"};

    public static String randomArt() {
        Random rand = new Random();
        return (arts[rand.nextInt(3)]);
    }

    Artifact(String artType) {
        this.artType = artType;
    }

    public static String[] getArts() {
        return arts;
    }

    public String getArtType() {
        return this.artType;
    }

}
