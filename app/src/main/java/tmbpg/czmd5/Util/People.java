package tmbpg.czmd5.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
  private final List<EffectBase> effects = new ArrayList<>();
  private int score = 0;
  private float damageMul = 1;
  private boolean hidden = false;

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

  public String getName() {
    return name;
  }

  public int getDamageAmount(People target) {
    if (target.getSubject() == subject)
      return 0;
    return (int) ((random.nextInt(damageBaseMax - damageBaseMin + 1) + damageBaseMin) * damageMul);
  }

  public void tickEffects() {
    List<EffectBase> timeout = new ArrayList<>();
    for (EffectBase effect : effects) {
      effect.execute(this, effect.getSource());
      effect.tick();
      if (effect.isTimeOut()) {
        LogUtil.formatLog("%s 的效果 %s 已失效", TextColor.MAGENTA, this.name, effect.getName());
        timeout.add(effect);
        effect.remove(this);
      }
      if (this.isDead()) {
        LogUtil.formatLog("%s 被 %s 施加的 %s 效果杀死了", TextColor.RED, this.name, effect.getSource().getName(),
            effect.getName());
        return;
      }
    }
    effects.removeAll(timeout);
  }

  public void addEffect(EffectBase effect) {
    LogUtil.formatLog("%s 获得效果： %s ，持续 %d 回合", TextColor.MAGENTA, this.getName(), effect.getName(), effect.getTime());
    for (EffectBase e : effects)
      if (e.getClass().equals(effect.getClass())) {
        e.addTime();
        LogUtil.formatLog("效果 %s 叠加至 %d 回合", TextColor.BLUE, e.getName(), e.getTimeLast());
        return;
      }
    effects.add(effect);
    effect.apply(this);
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

  public void setDamageMul(float damageMul) {
    this.damageMul = damageMul;
  }

  public float getDamageMul() {
    return damageMul;
  }

  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }

  public boolean isHidden() {
    return hidden;
  }
}
