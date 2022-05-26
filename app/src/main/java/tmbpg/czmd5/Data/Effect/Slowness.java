package tmbpg.czmd5.Data.Effect;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;

public class Slowness extends EffectBase {
  @Override
  public int getTime() {
    return 10;
  }

  @Override
  public void apply(People people) {
    people.setSpeedBase(people.getSpeedBase() / 2);
  }

  @Override
  public void remove(People people) {
    people.setSpeedBase(people.getSpeedBase() * 2);
  }

  @Override
  public void execute(People people) {
  }
}
