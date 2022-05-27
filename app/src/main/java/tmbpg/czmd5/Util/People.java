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
  private int hp;
  private final Subject subject;
  private final int damageBaseMin, damageBaseMax;
  private final List<SkillBase> skills = new ArrayList<>();
  private final List<EffectBase> effects = new ArrayList<>();

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

  public boolean shouldAttack() {
    return random.nextInt(GlobalSettings.attackProbability) == 0;
  }

  public String getName() {
    return name;
  }

  public int getDamageAmount(People target) {
    if (target.getSubject() == subject)
      return 0;
    return random.nextInt(damageBaseMax - damageBaseMin + 1) + damageBaseMin;
  }

  public void tickEffects() {
    List<EffectBase> timeout = new ArrayList<>();
    for (EffectBase effect : effects) {
      effect.execute(this);
      effect.tick();
      if (effect.isTimeOut()) {
        LogUtil.log(String.format("%s 的效果 %s 已失效", this.name, effect.getName()), TextColor.MAGENTA);
        timeout.add(effect);
      }
    }
    effects.removeAll(timeout);
  }

  public void addEffect(EffectBase effect) {
    for (EffectBase e : effects)
      if (e.getClass().equals(effect.getClass())) {
        e.addTime();
        LogUtil.log(String.format("效果 %s 叠加至 %d 回合", e.getName(), e.getTimeRemain()), TextColor.BLUE);
        return;
      }
    effects.add(effect);
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
}
