/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tmbpg.czmd5;

import java.util.Scanner;

import tmbpg.czmd5.Game.GameRunner;

public class Main {
  public static void main(String[] args) {
    System.out.println("请输入随机种子：");
    Scanner scanner = new Scanner(System.in);
    long seed = scanner.nextLong();
    scanner.close();
    GameRunner gameRunner = new GameRunner(seed);
    gameRunner.run();
  }
}
