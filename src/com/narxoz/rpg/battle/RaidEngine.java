package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.AreaSkill;
import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaidEngine {
    private Random random = new Random();

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, Skill skillA, CombatNode teamB, Skill skillB) {
        RaidResult result = new RaidResult();
        int round = 0;

        result.addLog("=== RAID START ===");
        result.addLog(teamA.getName() + " vs " + teamB.getName());

        while (teamA.isAlive() && teamB.isAlive()) {
            round++;
            result.addLog("---- Round " + round + " ----");

            if (teamA.isAlive()) {
                CombatNode targetForA = chooseTarget(teamB, skillA);
                if (targetForA != null) {
                    result.addLog(teamA.getName() + " uses " + skillA.getName()
                            + " (" + skillA.getEffectName() + ") on " + targetForA.getName());
                    skillA.use(targetForA);
                    result.addLog(teamB.getName() + " total attack now: " + teamB.getAttackPower());
                }
            }

            if (!teamB.isAlive()) {
                break;
            }

            if (teamB.isAlive()) {
                CombatNode targetForB = chooseTarget(teamA, skillB);
                if (targetForB != null) {
                    result.addLog(teamB.getName() + " uses " + skillB.getName()
                            + " (" + skillB.getEffectName() + ") on " + targetForB.getName());
                    skillB.use(targetForB);
                    result.addLog(teamA.getName() + " total attack now: " + teamA.getAttackPower());
                }
            }
        }

        result.setRounds(round);

        if (teamA.isAlive()) {
            result.setWinner(teamA.getName());
        } else if (teamB.isAlive()) {
            result.setWinner(teamB.getName());
        } else {
            result.setWinner("Draw");
        }

        result.addLog("=== RAID END ===");
        result.addLog("Winner: " + result.getWinner());
        result.addLog("Rounds: " + result.getRounds());

        return result;
    }

    private CombatNode chooseTarget(CombatNode enemyTeam, Skill skill) {
        if (skill instanceof AreaSkill) {
            return enemyTeam;
        }
        return chooseRandomAliveLeaf(enemyTeam);
    }

    private CombatNode chooseRandomAliveLeaf(CombatNode root) {
        List<CombatNode> aliveLeaves = new ArrayList<>();
        collectAliveLeaves(root, aliveLeaves);

        if (aliveLeaves.isEmpty()) {
            return null;
        }

        int index = random.nextInt(aliveLeaves.size());
        return aliveLeaves.get(index);
    }

    private void collectAliveLeaves(CombatNode node, List<CombatNode> aliveLeaves) {
        if (node == null || !node.isAlive()) {
            return;
        }

        if (node.isLeaf()) {
            aliveLeaves.add(node);
            return;
        }

        for (CombatNode child : node.getChildren()) {
            collectAliveLeaves(child, aliveLeaves);
        }
    }
}