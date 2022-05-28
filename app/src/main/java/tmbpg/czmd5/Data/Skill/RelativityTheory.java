package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HiddenEffect;
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
    return 0;
  }

  @Override
  public EffectBase[] getEffectsForTarget(People source, People target) {
    return new EffectBase[] { new HiddenEffect(source, target, 5) };
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.Physics;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 去遨游太空了", target.getName());
  }
}
