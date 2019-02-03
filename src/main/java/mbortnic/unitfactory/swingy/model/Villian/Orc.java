package mbortnic.unitfactory.swingy.model.Villian;

import mbortnic.unitfactory.swingy.model.Artifact.Artifact;

public class Orc extends Villian{

    public Orc(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
       super(lvl, attack, protection, hitp, exp, art);
       int idType = 2;
       super.setIdType(idType);
    }

}
