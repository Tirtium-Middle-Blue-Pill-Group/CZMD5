package tmbpg.czmd5.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmbpg.czmd5.Util.People;

public class GameMap {
  private final List<People> peoples = new ArrayList<>();
  private final Random random;
  private int tick = 0;

  public GameMap(Random random) {
    this.random = random;
  }

  public void addPeople(People people) {
    peoples.add(people);
  }

  public void randomizePos() {
    for (People people : peoples) {
      people.setPos(random.nextInt(100));
    }
  }

  private void executeDamage(People p1, People p2) {
    System.out.println(String.format("\033[32m时间：%d\033[37m", tick));
    System.out.println(String.format("%s 和 %s 在 %d 处相遇了。", p1.getName(), p2.getName(), p1.getPos()));
    p1.damage(p2.getDamageAmount(p1));
    p2.damage(p1.getDamageAmount(p2));
    System.out.println(String.format("%s 受到了 %d 点伤害。", p1.getName(), p2.getDamageAmount(p1)));
    System.out.println(String.format("%s 受到了 %d 点伤害。", p2.getName(), p1.getDamageAmount(p2)));
    p1.sendKnockBack(p2);
    p2.sendKnockBack(p1);
    System.out.println(String.format("%s 被击飞至 %d", p1.getName(), p1.getPos()));
    System.out.println(String.format("%s 被击飞至 %d", p2.getName(), p2.getPos()));

    if (p1.isDead()) {
      peoples.remove(p1);
      System.out.println(String.format("\033[31m%s 已经死亡。\033[37m", p1.getName()));
    }
    if (p2.isDead()) {
      peoples.remove(p2);
      System.out.println(String.format("\033[31m%s 已经死亡。\033[37m", p2.getName()));
    }
  }

  /**
   * 主要的计算代码
   * 
   * @return 游戏是否仍在运行，false表示计算结束
   */
  public boolean tick() {
    tick++;
    for (People people : peoples) {
      people.executeEffect();
      people.moving(random.nextInt(people.getSpeedBase() * 2 + 1) - people.getSpeedBase());
    }
    for (int i = 0; i < peoples.size(); i++) {
      for (int j = i + 1; j < peoples.size(); j++) {
        if (peoples.get(i).getPos() == peoples.get(j).getPos())
          executeDamage(peoples.get(i), peoples.get(j));
      }
    }
    return peoples.size() > 1;
  }

  public People getWinner() {
    if (peoples.size() == 1)
      return peoples.get(0);
    throw new RuntimeException("游戏还没有结束，没有胜利者");
  }
}
