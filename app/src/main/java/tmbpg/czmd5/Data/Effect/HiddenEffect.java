package tmbpg.czmd5.Data.Effect;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Interface.EffectBase;

public class HiddenEffect extends EffectBase {
  private final int time;

  public HiddenEffect(People source, People target, int time) {
    super(source, target);
    this.timeLast = this.time = time;
  }

  @Override
  public String getName() {
    return "隐身";
  }

  @Override
  public int getTime() {
    return time;
  }

  @Override
  public void apply(People people) {
    people.setHidden(true);
  }

  @Override
  public void remove(People people) {
    people.setHidden(false);
  }

  @Override
  public void execute(People people, People source) {
  }

}
