package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Data.Effect.HiddenEffect;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;
import tmbpg.czmd5.Util.Interface.SkillBase.ChineseSkill;

public class GuoQinLun implements ChineseSkill {
  public static final int damage = 15;

  @Override
  public String getName() {
    return "过秦论";
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
  public EffectBase[] getEffectsForTarget(People source, People target) {
    return new EffectBase[] { new HiddenEffect(source, target, 2) };
  }

  @Override
  public boolean shouldExecute(People source, People target, int lastDamage) {
    return target.getSubject() != Subject.Chinese;
  }

  @Override
  public String getMessage(People source, People target) {
    return String.format("%s 背诵过秦论背崩溃了，脑子里一团浆糊，不得不休息%d回合", target.getName(), 2);
  }

}
