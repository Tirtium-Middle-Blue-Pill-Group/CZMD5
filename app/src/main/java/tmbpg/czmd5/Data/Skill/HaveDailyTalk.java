package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.EnglishSkill;

public class HaveDailyTalk implements EnglishSkill {
  public static final int damage = 10;

  @Override
  public int getTriggerProb() {
    return 10;
  }

  @Override
  public String getName() {
    return "做DailyTalk";
  }

  @Override
  public int execute(People source, People target) {
    target.damage(damage);
    source.addScore(damage);
    return damage;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.English;
  }

  @Override
  public EffectBase[] getEffects() {
    return EMPTY;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("对 %s 造成额外伤害 %s", target.getName(), damage);
  }
}
