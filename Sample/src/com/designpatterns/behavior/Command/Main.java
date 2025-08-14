package com.designpatterns.behavior.Command;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.add("Command pattern in text editor.\n");
        // 执行一个CopyCommand:
        Command copy = new CopyCommand(editor);
        copy.execute();
        editor.add("----\n");
        // 执行一个PasteCommand:
        Command paste = new PasteCommand(editor);
        paste.execute();
        System.out.println(editor.getState());

    }
}
/*
Command pattern in text editor.
----
 */

/*
命令
将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化，对请求排队或记录请求日志，以及支持可撤销的操作。

┌──────┐      ┌───────┐
│Client│─ ─ ─▶│Command│
└──────┘      └───────┘
                  │  ┌──────────────┐
                  ├─▶│ CopyCommand  │
                  │  ├──────────────┤
                  │  │editor.copy() │─ ┐
                  │  └──────────────┘
                  │                    │  ┌────────────┐
                  │  ┌──────────────┐   ─▶│ TextEditor │
                  └─▶│ PasteCommand │  │  └────────────┘
                     ├──────────────┤
                     │editor.paste()│─ ┘
                     └──────────────┘
 */