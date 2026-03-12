package com.narxoz.rpg.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaidGroup implements CombatNode {
    private final String name;
    private final List<CombatNode> children = new ArrayList<>();

    public RaidGroup(String name) {
        this.name = name;
    }

    public void add(CombatNode node) {
        if (node != null) {
            children.add(node);
        }
    }

    public void remove(CombatNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAttackPower() {
        int total = 0;
        for (CombatNode child : children) {
            if (child.isAlive()) {
                total += child.getAttackPower();
            }
        }
        return total;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) {
            return;
        }

        List<CombatNode> aliveChildren = new ArrayList<>();
        for (CombatNode child : children) {
            if (child.isAlive()) {
                aliveChildren.add(child);
            }
        }

        if (aliveChildren.isEmpty()) {
            return;
        }

        int perChildDamage = amount / aliveChildren.size();
        int remainder = amount % aliveChildren.size();

        for (int i = 0; i < aliveChildren.size(); i++) {
            int currentDamage = perChildDamage;

            if (i < remainder) {
                currentDamage++;
            }

            aliveChildren.get(i).takeDamage(currentDamage);
        }
    }

    @Override
    public boolean isAlive() {
        for (CombatNode child : children) {
            if (child.isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printTree(String indent) {
        System.out.println(indent + "+ " + name
                + " [RaidGroup, totalATK=" + getAttackPower()
                + ", alive=" + isAlive() + "]");

        for (CombatNode child : children) {
            child.printTree(indent + "   ");
        }
    }

    @Override
    public List<CombatNode> getChildren() {
        return Collections.unmodifiableList(children);
    }
}