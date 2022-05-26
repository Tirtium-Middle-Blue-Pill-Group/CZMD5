package tmbpg.czmd5.Util;

import java.util.ArrayList;
import java.util.List;

import tmbpg.czmd5.Util.Enum.Direction;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class People {
  private final String name;
  private int hp, maxHp;
  private final Subject subject;
  private int damageBase;
  private int knockbackBase;
  private int speedBase = 2;
  private final List<SkillBase> skills = new ArrayList<>();
  private final List<EffectBase> effects = new ArrayList<>();
  private int pos;
  private Direction lastDir;

  public People(String name, int hp, Subject subject, int damageBase, int knockbackBase, SkillBase... skills) {
    this.name = name;
    this.hp = hp;
    this.maxHp = hp;
    this.subject = subject;
    this.damageBase = damageBase;
    this.knockbackBase = knockbackBase;
    for (SkillBase skill : skills)
      this.skills.add(skill);
  }

  public String getName() {
    return name;
  }

  public int getDamageAmount(People target) {
    if (target.getSubject() == subject)
      return 0;
    return damageBase;
  }

  public int getPos() {
    return pos;
  }

  public void moving(int delta) {
    this.pos += delta;
    lastDir = delta > 0 ? Direction.Forward : delta < 0 ? Direction.Backward : lastDir;
  }

  public Direction getLastDirection() {
    return lastDir;
  }

  public void setPos(int pos) {
    this.pos = pos;
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

  public int getSpeedBase() {
    return speedBase;
  }

  public void setSpeedBase(int speedBase) {
    this.speedBase = speedBase;
  }

  public List<SkillBase> getSkills() {
    return skills;
  }

  public void sendKnockBack(People source) {
    int knockback = source.getKnockbackBase();
    if (source.lastDir == Direction.Backward)
      knockback *= -1;
    this.pos += knockback;
  }

  public boolean isDead() {
    return hp <= 0;
  }
}
