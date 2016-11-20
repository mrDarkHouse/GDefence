package com.darkhouse.gdefence.Level.Ability;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.AttackLogic;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

public class MultiShot extends Ability{
    private int bonusTarget;
    private Array <Mob> targets;

    public MultiShot(int bonusTarget) {
        super(UseType.preattack);
        this.bonusTarget = bonusTarget;
        targets = new Array<Mob>(/*bonusTarget*/);
    }

    @Override
    public void use(Mob target) {
        for (int i = 0; i < bonusTarget; i++){
           // adding:
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
            Map.projectiles.add(new Projectile(owner, m));
        }
    }
    private void dispose(){
        targets.clear();
    }


}
