package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.Slowness;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChemistrySkill;
import tmbpg.czmd5.Util.Interface.SkillBase.MathSkill;

public class OneEyeMethod implements MathSkill, ChemistrySkill {
  @Override
  public int getDamage(Subject target) {
    return 0;
  }

  @Override
  public int getKnockback() {
    return 0;
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] { new Slowness() };
  }
}
