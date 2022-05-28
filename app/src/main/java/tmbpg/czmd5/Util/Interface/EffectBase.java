package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.People;

public abstract class EffectBase {
  protected int timeLast;
  private final People source, target;

  public EffectBase(People source, People target) {
    this.source = source;
    this.target = target;
    timeLast = this.getTime();
  }

  public int getTimeLast() {
    return this.timeLast;
  }

  public void addTime() {
    this.timeLast += this.getTime();
  }

  public void tick() {
    timeLast--;
  }

  public boolean isTimeOut() {
    return timeLast < 0;
  }

  public People getSource() {
    return source;
  }

  public People getTarget() {
    return target;
  }

  public abstract String getName();

  public abstract int getTime();

  public abstract void apply(People people);

  public abstract void remove(People people);

  public abstract void execute(People people, People source);
}
