package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class AreaSkill extends Skill {

    public AreaSkill(String name, int basePower, EffectImplementor effect) {
        super(name, basePower, effect);
    }

    @Override
    public void use(CombatNode target) {
        if (target == null || !target.isAlive()) {
            return;
        }

        applyToAliveLeaves(target);
    }

    private void applyToAliveLeaves(CombatNode node) {
        if (node == null || !node.isAlive()) {
            return;
        }

        if (node.isLeaf()) {
            node.takeDamage(getFinalPower());
            return;
        }

        for (CombatNode child : node.getChildren()) {
            applyToAliveLeaves(child);
        }
    }
}
