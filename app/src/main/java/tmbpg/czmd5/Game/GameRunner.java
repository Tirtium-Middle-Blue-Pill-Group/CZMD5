package tmbpg.czmd5.Game;

import java.util.Random;

import tmbpg.czmd5.Util.People;
import tmbpg.czmd5.Util.Enum.Subject;

public class GameRunner {
  private final Random random;
  private final GameMap gameMap;

  public GameRunner(long seed) {
    random = new Random(seed);
    gameMap = new GameMap(random);
  }

  public void run() {
    gameMap.addPeople(new People("顾志刚", 100, Subject.Math, 10, 2));
    gameMap.addPeople(new People("王华", 100, Subject.English, 10, 2));
    gameMap.addPeople(new People("丁燕华", 100, Subject.IT, 10, 2));

    gameMap.randomizePos();

    while (gameMap.tick())
      ;
    System.out.println("游戏结束！");
    System.out.println("胜利者是：" + gameMap.getWinner().getName() + "\033[37m");
  }
}
