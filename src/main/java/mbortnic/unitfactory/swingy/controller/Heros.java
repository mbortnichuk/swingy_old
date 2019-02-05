package mbortnic.unitfactory.swingy.controller;

import mbortnic.unitfactory.swingy.model.Artifact.Armor;
import mbortnic.unitfactory.swingy.model.Artifact.Artifact;
import mbortnic.unitfactory.swingy.model.Artifact.Helm;
import mbortnic.unitfactory.swingy.model.Artifact.Weapon;
import mbortnic.unitfactory.swingy.model.Hero.HeroStatistics;
import mbortnic.unitfactory.swingy.model.Hero.Human;
import mbortnic.unitfactory.swingy.model.Hero.Player;
import mbortnic.unitfactory.swingy.model.Hero.Undead;
import mbortnic.unitfactory.swingy.model.Villian.DarkElf;
import mbortnic.unitfactory.swingy.model.Villian.Orc;
import mbortnic.unitfactory.swingy.model.Villian.Villian;

import java.util.Random;

public class Heros {

    public static Player newHero(String hero, String player, HeroStatistics heroStatistics, Artifact art) {
        if (hero.equals("Human")) {
            return new Human(player, heroStatistics, art);
        } else if (hero.equals("Undead")) {
            return new Undead(player, heroStatistics, art);
        } else {
            return null;
        }
    }

    public static Villian newVillian(Player player) {
        Random rand = new Random();
        int enemy = rand.nextInt(2) + 1;
        String artif = Artifact.randomArt();
        int lvl = 0;
        int attack = 0;
        int protection = 0;
        int exp = 0;
        int hitp = 0;

        if (enemy == 1) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStatistics().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Orc(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Weapon");
                lvl = player.getHeroStatistics().getLvl();
                attack = 100 + w.getAttackType();
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Orc(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStatistics().getLvl();
                attack = 100;
                protection = armor.getProtection();
                hitp = 100;
                exp = 0;
                return (new Orc(lvl, attack, protection, hitp, exp, armor));
            }
        } else if (enemy == 2) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStatistics().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new DarkElf(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Wwapon");
                lvl = player.getHeroStatistics().getLvl();
                attack = w.getAttackType() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new DarkElf(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStatistics().getLvl();
                attack = 100;
                protection = armor.getProtection() + 100;
                hitp = 100;
                exp = 0;
                return (new DarkElf(lvl, attack, protection, hitp, exp, armor));
            }
        }
        return null;
    }

}

