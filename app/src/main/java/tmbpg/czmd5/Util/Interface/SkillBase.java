package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.Enum.Subject;

public interface SkillBase {
  public Subject[] getOwner();

  public int getDamage(Subject target);

  public int getKnockback();
}
