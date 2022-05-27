package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HighBloodPressure;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChemistrySkill;
import tmbpg.czmd5.Util.Interface.SkillBase.MathSkill;

public class OneEyeMethod implements MathSkill, ChemistrySkill {
  public static final int damage = 10;

  @Override
  public int getTriggerProb() {
    return 10;
  }

  @Override
  public String getName() {
    return "一眼法";
  }

  @Override
  public int execute(People source, People target) {
    target.damage(damage);
    source.addScore(damage);
    return damage;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return !Subject.isOneOf(target.getSubject(), Subject.Math, Subject.Chemistry);
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] { new HighBloodPressure() };
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 无法容忍如此马虎的做题，被气的高血压了，掉血 %d", target.getName(), damage);
  }
}