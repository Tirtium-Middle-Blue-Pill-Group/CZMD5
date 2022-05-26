package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HighBloodPressure;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChemistrySkill;
import tmbpg.czmd5.Util.Interface.SkillBase.MathSkill;

public class OneEyeMethod implements MathSkill, ChemistrySkill {
  @Override
  public int getDamage(People target) {
    return Subject.isOneOf(target.getSubject(), Subject.Math, Subject.Chemistry) ? 0 : 10;
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] { new HighBloodPressure() };
  }

  @Override
  public int getTriggerProb() {
    return 10;
  }

  @Override
  public String getName() {
    return "一眼法";
  }
}