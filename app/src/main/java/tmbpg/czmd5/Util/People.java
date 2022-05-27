package tmbpg.czmd5.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Data.GlobalSettings;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;
import tmbpg.czmd5.Util.LogUtil.TextColor;

public class People {
  private final String name;
  private final Random random;
  private int hp, maxHp;
  private final Subject subject;
  private final int damageBaseMin, damageBaseMax;
  private final List<SkillBase> skills = new ArrayList<>();
  private final List<Pair<EffectBase, People>> effects = new ArrayList<>();
  private int score = 0, damageMul = 1, skipTurns = 0;

  public People(String name, long seed, int hp, Subject subject, int damageBaseMin, int damageBaseMax,
      SkillBase... skills) {
    this.name = name;
    this.random = new Random(seed);
    this.hp = hp;
    this.subject = subject;
    this.damageBaseMin = damageBaseMin;
    this.damageBaseMax = damageBaseMax;
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  public int getAverageDamage() {
    return (damageBaseMin + damageBaseMax) / 2;
  }

  public boolean shouldAttack() {
    return random.nextInt(GlobalSettings.attackProbability) == 0;
  }

  public String getName() {
    return name;
  }

  public int getDamageAmount(People target) {
    if (target.getSubject() == subject)
      return 0;
    return (random.nextInt(damageBaseMax - damageBaseMin + 1) + damageBaseMin) * damageMul;
  }

  public int getSkipTurns() {
    return skipTurns;
  }

  public void tickEffects() {
    if (skipTurns > 0) {
      skipTurns--;
      return;
    }
    List<Pair<EffectBase, People>> timeout = new ArrayList<>();
    for (Pair<EffectBase, People> effect : effects) {
      effect.getFirst().execute(this, effect.getSecond());
      effect.getFirst().tick();
      if (effect.getFirst().isTimeOut()) {
        LogUtil.log(String.format("%s 的效果 %s 已失效", this.name, effect.getFirst().getName()), TextColor.MAGENTA);
        timeout.add(effect);
      }
    }
    effects.removeAll(timeout);
  }

  public void addEffect(EffectBase effect, People source) {
    for (Pair<EffectBase, People> e : effects)
      if (e.getFirst().getClass().equals(effect.getClass())) {
        e.getFirst().addTime();
        LogUtil.log(String.format("效果 %s 叠加至 %d 回合", e.getFirst().getName(), e.getFirst().getTimeRemain()),
            TextColor.BLUE);
        return;
      }
    effects.add(Pair.of(effect, source));
  }

  public int getHp() {
    return hp;
  }

  public void damage(int damage) {
    hp -= damage;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public Subject getSubject() {
    return subject;
  }

  public List<SkillBase> getSkills() {
    return skills;
  }

  public boolean isDead() {
    return hp <= 0;
  }

  public int nextInt(int size) {
    return random.nextInt(size);
  }

  public int getScore() {
    return this.score;
  }

  public void addScore(int score) {
    this.score += score;
  }

  public void addSkillls(SkillBase... skills) {
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  public void setDamageMul(int damageMul) {
    this.damageMul = damageMul;
  }

  public void setSkipTurns(int skipTurns) {
    this.skipTurns = skipTurns;
  }

  public int getDamageMul() {
    return damageMul;
  }
}
