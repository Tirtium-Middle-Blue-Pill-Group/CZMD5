package tmbpg.czmd5.Data.Skill;

import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.SkillBase;

public class HaveDailyTalk implements SkillBase {
  @Override
  public Subject[] getOwner() {
    return new Subject[] { Subject.English };
  }

  @Override
  public int getDamage(Subject target) {
    return 0;
  }

  @Override
  public int getKnockback() {
    return 0;
  }
}
