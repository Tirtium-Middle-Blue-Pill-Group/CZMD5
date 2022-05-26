package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Util.LogUtil;
import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;
import tmbpg.czmd5.Util.LogUtil.TextColor;

public class GameRunner {
  private final Random random;
  private final List<People> peoples = new ArrayList<>();
  private int tick = 0;

  public GameRunner(long seed) {
    random = new Random(seed);
  }

  public void run() {
    peoples.add(new People("顾志刚", random.nextLong(), 100, Subject.Math, 8, 12));
    peoples.add(new People("王华", random.nextLong(), 100, Subject.English, 8, 12));
    peoples.add(new People("丁燕华", random.nextLong(), 100, Subject.IT, 8, 12));

    while (this.tick())
      ;
    LogUtil.log("游戏结束！");
    LogUtil.log(String.format("%s 胜利", this.getWinner().getName()), TextColor.GREEN);
  }

  /**
   * 主要的计算代码
   * 
   * @return 游戏是否仍在运行，false表示计算结束
   */
  private boolean tick() {
    tick++;
    // LogUtil.log("第" + tick + "回合");
    List<People> dead = new ArrayList<>();
    for (People p : peoples) {
      p.tickEffects();
      if (p.shouldAttack()) {
        People target = peoples.get(p.nextInt(peoples.size()));
        if (target.getName().equals(p.getName()))
          continue;
        int damage = p.getDamageAmount(target);
        target.damage(damage);
        LogUtil.log(
            String.format("第%d回合：%s 攻击了 %s ，血量%d->%d", tick, p.getName(), target.getName(), target.getHp() + damage,
                target.getHp()),
            TextColor.YELLOW);
        if (target.isDead()) {
          LogUtil.log(String.format("%s 死了", target.getName()), TextColor.RED);
          dead.add(target);
        }
      }
    }
    peoples.removeAll(dead);
    return peoples.size() > 1;
  }

  public People getWinner() {
    if (peoples.size() == 1)
      return peoples.get(0);
    throw new RuntimeException("游戏还没有结束，没有胜利者");
  }
}
