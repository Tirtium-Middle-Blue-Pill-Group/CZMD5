package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.SkillBase.ChemistrySkill;

public class CopperHydrometallurgy implements ChemistrySkill {
  public static final int damage = 8;

  @Override
  public String getName() {
    return "湿法炼铜";
  }

  @Override
  public int getTriggerProb() {
    return 20;
  }

  @Override
  public int execute(People source, People target) {
    target.damage(damage);
    source.addScore(damage);
    return damage;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.Chemistry;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("对 %s 造成额外伤害 %s", target.getName(), damage);
  }
}
