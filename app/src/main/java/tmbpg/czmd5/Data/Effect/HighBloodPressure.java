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
    people.setDamageBase(people.getDamageBase() - 2);
  }

  @Override
  public void remove(People people) {
    people.setDamageBase(people.getDamageBase() + 2);
  }

  @Override
  public void execute(People people) {
    people.damage(10);
  }
}