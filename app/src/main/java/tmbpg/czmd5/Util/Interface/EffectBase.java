package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;

public abstract class EffectBase {
  protected int timeLast = 0;

  public int getTimeLast(Subject subject) {
    return this.timeLast;
  }

  public abstract String getName();

  public void tick() {
    timeLast++;
  }

  public boolean isTimeOut() {
    return timeLast >= getTime();
  }

  public abstract int getTime();

  public abstract void apply(People people);

  public abstract void remove(People people);

  public abstract void execute(People people);
}
