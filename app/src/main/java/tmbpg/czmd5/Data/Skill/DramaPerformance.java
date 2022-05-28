package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HaveTwoMinds;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.MathSkill;

public class DramaPerformance implements MathSkill {
  public static final int damage = 5;

  @Override
  public String getName() {
    return "话剧表演";
  }

  @Override
  public int getTriggerProb() {
    return 15;
  }

  @Override
  public int execute(People source, People target) {
    target.damage(damage);
    source.addScore(damage);
    return 0;
  }

  @Override
  public EffectBase[] getEffectsForTarget(People source,People target) {
    return new EffectBase[] { new HaveTwoMinds(source, target) };
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.Math;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 只顾着看话剧表演了", target.getName());
  }
}
