package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.SkillBase.ChineseSkill;
import tmbpg.czmd5.Util.Interface.SkillBase.EnglishSkill;

public class Dictation implements ChineseSkill, EnglishSkill {
  public static final int damage = 10;

  @Override
  public int getTriggerProb() {
    return 10;
  }

  @Override
  public String getName() {
    return "默写";
  }

  @Override
  public int execute(People source, People target) {
    target.damage(damage);
    source.addScore(damage);
    return damage;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return !Subject.isOneOf(target.getSubject(), Subject.Chinese, Subject.English);
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 喜迎Foolish，扣血 %d", target.getName(), damage);
  }
}
