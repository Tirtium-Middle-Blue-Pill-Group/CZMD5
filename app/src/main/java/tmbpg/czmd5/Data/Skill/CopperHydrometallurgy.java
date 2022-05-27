package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChemistrySkill;

public class CopperHydrometallurgy implements ChemistrySkill {
  @Override
  public String getName() {
    return "湿法炼铜";
  }

  @Override
  public int getTriggerProb() {
    return 20;
  }

  @Override
  public int getDamage(People target) {
    return 8;
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] {};
  }

  @Override
  public boolean shouldDamage(People target) {
    return target.getSubject() != Subject.Chemistry;
  }
}
