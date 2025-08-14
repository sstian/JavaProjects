package com.designpatterns.behavior.State;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BotContext bot = new BotContext();
        for (;;) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String output = bot.chat(input);
            System.out.println(output.isEmpty() ? "(no reply)" : "< " + output);
        }

    }
}
/*
> hello
< Hello, I'm Bob.
> how are you?
< Yes. how are you!
> good luck.
< good luck!
> bye
< Bye!
 */

/*
状态
允许一个对象在其内部状态改变时改变它的行为。对象看起来似乎修改了它的类。


 */