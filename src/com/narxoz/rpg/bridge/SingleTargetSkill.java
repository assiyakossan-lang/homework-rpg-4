package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class SingleTargetSkill extends Skill {

    public SingleTargetSkill(String name, int basePower, EffectImplementor effect) {
        super(name, basePower, effect);
    }

    @Override
    public void use(CombatNode target) {
        if (target == null || !target.isAlive()) {
            return;
        }

        target.takeDamage(getFinalPower());
    }
}
