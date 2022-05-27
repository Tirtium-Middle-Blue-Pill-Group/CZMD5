package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HiddenEffect;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.MusicSkill;

public class ToBeLate implements MusicSkill {
  @Override
  public String getName() {
    return "迟到";
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
  public EffectBase[] getEffects() {
    return new EffectBase[] { new HiddenEffect() };
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return source.getSubject() == Subject.Music;
  }

  @Override
  public String getMessage(People source, People target) {
    return "他/她不见了";
  }

}
