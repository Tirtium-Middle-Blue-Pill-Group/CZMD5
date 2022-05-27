package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class AddHp implements SkillBase {
  public static final int damage = -10;

  @Override
  public String getName() {
    return "回血";
  }

  @Override
  public int getTriggerProb() {
    return 5;
  }

  @Override
  public int execute(People source, People target) {
    source.damage(damage);
    return damage;
  }

  @Override
  public EffectBase[] getEffects() {
    return EMPTY;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return lastDamage >= source.getAverageDamage();
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("回血 %d", -damage);
  }
}
