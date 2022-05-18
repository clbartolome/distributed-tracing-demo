package com.redhat.foo.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Delay {

  public static void execute() {
    try {
      int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
      TimeUnit.SECONDS.sleep(randomNum);
    } catch (InterruptedException e) {
      System.out.println("Error while executing the delay");
    }
  }

}
