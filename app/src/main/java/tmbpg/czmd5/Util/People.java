package tmbpg.czmd5.Util;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class People {
  private int hp, maxHp;
  private final Subject subject;
  private int damageBase;
  private int knockbackBase;
  private float speedBase = 2;
  private final List<SkillBase> skills = new ArrayList<>();
  private final List<EffectBase> effects = new ArrayList<>();

  public People(int hp, Subject subject, int damageBase, int knockbackBase, SkillBase... skills) {
    this.hp = hp;
    this.maxHp = hp;
    this.subject = subject;
    this.damageBase = damageBase;
    this.knockbackBase = knockbackBase;
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  public void executeEffect() {
    for (EffectBase effect : effects) {
      effect.execute(this);
      effect.tick();
      if (effect.isTimeOut())
        effects.remove(effect);
    }
  }

  public void addEffect(EffectBase effect) {
    effects.add(effect);
  }

  public int getHp() {
    return hp;
  }

  public float getHpPercent() {
    return (float) hp / maxHp;
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

  public int getDamageBase() {
    return damageBase;
  }

  public int setDamageBase(int damageBase) {
    return this.damageBase = damageBase;
  }

  public int getKnockbackBase() {
    return knockbackBase;
  }

  public int setKnockbackBase(int knockbackBase) {
    return this.knockbackBase = knockbackBase;
  }

  public float getSpeedBase() {
    return speedBase;
  }

  public void setSpeedBase(float speedBase) {
    this.speedBase = speedBase;
  }

  public List<SkillBase> getSkills() {
    return skills;
  }

}
