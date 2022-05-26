package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.EnglishSkill;

public class HaveDailyTalk implements EnglishSkill {
  @Override
  public int getDamage(Subject target) {
    return 10;
  }

  @Override
  public int getKnockback() {
    return 0;
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] {};
  }
}
