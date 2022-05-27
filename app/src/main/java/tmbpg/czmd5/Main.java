package tmbpg.czmd5;

import java.util.Scanner;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import tmbpg.czmd5.Game.GameRunner;

public class Main {
  public static void main(String[] args) {
    System.out.println("请输入随机种子：");
    Scanner scanner = new Scanner(System.in);
    String seedString = scanner.nextLine();
    long seed = Hashing.sha512().hashString(seedString, Charsets.UTF_8).asLong();
    scanner.close();
    GameRunner gameRunner = new GameRunner(seed);
    gameRunner.run();
  }
}
