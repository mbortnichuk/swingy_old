package mbortnic.unitfactory.swingy.model.Villian;

import mbortnic.unitfactory.swingy.model.Artifact.Artifact;

public class DarkElf extends Villian{

    public DarkElf(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        super(lvl, attack, protection, hitp, exp, art);
        int idType = 2;
        super.setIdType(idType);
    }

}
