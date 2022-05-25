package tmbpg.czmd5.Data.Effect;

import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.Interface.EffectBase;

public class HighBloodPressure implements EffectBase {
  @Override
  public int getTime() {
    return 10;
  }

  @Override
  public int getDamagePerTime(Subject subject) {
    return 10;
  }
}
