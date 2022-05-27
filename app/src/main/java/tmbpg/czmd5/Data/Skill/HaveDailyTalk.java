package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.EnglishSkill;

public class HaveDailyTalk implements EnglishSkill {
  @Override
  public int getDamage(People target) {
    return 10;
  }

  @Override
  public EffectBase[] getEffects() {
    return new EffectBase[] {};
  }

  @Override
  public int getTriggerProb() {
    return 10;
  }

  @Override
  public String getName() {
    return "做DailyTalk";
  }

  @Override
  public boolean shouldDamage(People target) {
    return target.getSubject() != Subject.English;
  }
}
