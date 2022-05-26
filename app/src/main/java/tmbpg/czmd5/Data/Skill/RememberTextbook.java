package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChineseSkill;
import tmbpg.czmd5.Util.Interface.SkillBase.EnglishSkill;

public class RememberTextbook implements ChineseSkill, EnglishSkill {
  @Override
  public int getDamage(People target) {
    return Subject.isOneOf(target.getSubject(), Subject.Chinese, Subject.English) ? 0 : 10;
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
    return "全篇背诵";
  }
}
