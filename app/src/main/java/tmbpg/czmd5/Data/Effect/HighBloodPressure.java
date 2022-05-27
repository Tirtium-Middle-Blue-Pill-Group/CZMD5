package tmbpg.czmd5.Data.Effect;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;

public class HighBloodPressure extends EffectBase {
  @Override
  public int getTime() {
    return 10;
  }

  @Override
  public void apply(People people) {

  }

  @Override
  public void remove(People people) {

  }

  @Override
  public void execute(People people, People source) {
    people.damage(10);
    source.addScore(10);
  }

  @Override
  public String getName() {
    return "高血压";
  }
}
