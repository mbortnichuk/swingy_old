package mbortnic.unitfactory.swingy.model.Artifact;

public class Weapon extends Artifact {

    private int attackType = 80;

    public Weapon(String attackType) {
        super(attackType);
    }

    public int getAttackType() {
        return this.attackType;
    }

}
