package mbortnic.unitfactory.swingy.model.Hero;

import com.sun.istack.internal.NotNull;
import mbortnic.unitfactory.swingy.model.Artifact.Artifact;

public class Player {

    public Player() {

    }

    @NotNull
    private Artifact art;

    @NotNull
    private HeroStatistics heroStatistics = new HeroStatistics();

    @NotNull
    private String newPlayer;


    protected Player(String newPlayer, HeroStatistics heroStatistics, Artifact art) {
        this.newPlayer = newPlayer;
        this.heroStatistics = heroStatistics;
        this.art = art;
    }

    public String getNewPlayer() {
        return this.newPlayer;
    }

    public HeroStatistics getHeroStatistics() {
        return this.heroStatistics = heroStatistics;
    }

    public Artifact getArt() {
        return this.art = art;
    }

    public void setArt(Artifact art) {
        this.art = art;
    }


}
