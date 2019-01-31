package mbortnic.unitfactory.swingy.model.Artifact;

public class Helm extends Artifact {

    private int hitPointAmount = 80;

    public Helm(String hitPointAmount) {
        super(hitPointAmount);
    }

    public int getHitPointAmount() {
        return this.hitPointAmount;
    }

}
