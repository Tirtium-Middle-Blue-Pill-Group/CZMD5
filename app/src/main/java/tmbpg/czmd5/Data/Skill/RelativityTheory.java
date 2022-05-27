package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class RelativityTheory implements SkillBase {
  @Override
  public String getName() {
    return "相对论";
  }

  @Override
  public int getTriggerProb() {
    return 20;
  }

  @Override
  public int execute(People source, People target) {
    target.setSkipTurns(5);
    return 0;
  }

  @Override
  public EffectBase[] getEffects() {
    return EMPTY;
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.Physics;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 去遨游太空了，跳过 %d 回合", target.getName(), 5);
  }
}
