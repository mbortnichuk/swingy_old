package mbortnic.unitfactory.swingy.view;

import mbortnic.unitfactory.swingy.controller.Heros;
import mbortnic.unitfactory.swingy.model.Artifact.Armor;
import mbortnic.unitfactory.swingy.model.Artifact.Artifact;
import mbortnic.unitfactory.swingy.model.Artifact.Helm;
import mbortnic.unitfactory.swingy.model.Artifact.Weapon;
import mbortnic.unitfactory.swingy.model.Hero.HeroStatistics;
import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.writer.WriteToFile;

/**
 * Created by mbortnic on 2/7/19.
 */
public class SwingyView {

    private static String art;
    private static String statistics;

    private static Player DBPlayer = new Player();
    private static Player newPlayer = new Player();

    private static int lvl;
    private static int attack;
    private static int protection;
    private static int hitp;
    private static int exp;

    public static Player addPlayer(String type, String player) {
        art = Artifact.randomArt();
        if (art.equals("ARMOR")) {
            Armor ar = new Armor("Armor");
            lvl = 1;
            attack = 100;
            protection = ar.getProtection() + 100;
            exp = 1000;
            hitp = 100;
            HeroStatistics heroStatistics = new HeroStatistics(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStatistics, ar);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        } else if (art.equals("WEAPON")) {
            Weapon w  = new Weapon("Weapon");
            lvl = 1;
            attack = w.getAttackType() + 100;
            protection = 100;
            exp = 1000;
            hitp = 100;
            HeroStatistics heroStatistics = new HeroStatistics(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStatistics, w);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        } else if (art.equals("HELM")) {
            Helm h = new Helm("Helm");
            lvl = 1;
            attack = h.getHitPointAmount() + 100;
            protection = 100;
            exp = 1000;
            hitp = 100;
            HeroStatistics heroStatistics = new HeroStatistics(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStatistics, h);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        }
        WriteToFile.write(statistics);
        return newPlayer;
    }

}
