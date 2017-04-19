package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

public class MultiShot extends Ability implements Ability.IPreAttack{

    public static class P extends Ability.AblityPrototype{
        private int bonusTarget;

        public P(int bonusTarget) {
            super("MultiShot");
            this.bonusTarget = bonusTarget;
        }

        @Override
        public Ability getAbility() {
            return new MultiShot(this);
        }

        @Override
        public String getTooltip() {
            return "Can shot to " + bonusTarget + " bonus targets";
        }
    }

    private int bonusTarget;
    private Array <Mob> targets;

    public MultiShot(P prototype) {
        this.bonusTarget = prototype.bonusTarget;
        targets = new Array<Mob>(/*bonusTarget*/);
    }

    @Override
    public void use(Mob target) {
        for (int i = 0; i < bonusTarget; i++){
//            adding:
            for (Mob m: Wave.mobs){
                if(owner.isInRange(m.getCenter())){
                    if(m != target){
                        if(!targets.contains(m, false)) {
                            targets.add(m);
                            break; //adding;
                        }
                    }
                }
            }
        }
        shot();
        dispose();
    }

    private void shot(){
        for (Mob m: targets){
            Map.projectiles.add(new Projectile(owner, owner.getCenter(), m));
        }
    }
    private void dispose(){
        targets.clear();
    }

    @Override
    protected void init() {

    }


}
