package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.Enum.Subject;

public interface EffectBase {
  public int getTime();

  public int getDamagePerTime(Subject subject);
}
