package tmbpg.czmd5.Data.Effect;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;

public class HaveTwoMinds extends EffectBase {
  public HaveTwoMinds(People source, People target) {
    super(source, target);
  }

  @Override
  public String getName() {
    return "三心二意";
  }

  @Override
  public int getTime() {
    return 10;
  }

  @Override
  public void apply(People people) {
    people.setDamageMul(people.getDamageMul() / 2);
  }

  @Override
  public void remove(People people) {
    people.setDamageMul(people.getDamageMul() * 2);
  }

  @Override
  public void execute(People people, People source) {

  }

}
