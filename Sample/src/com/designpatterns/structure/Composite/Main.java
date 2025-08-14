package com.designpatterns.structure.Composite;

public class Main {
    public static void main(String[] args) {
        Node root = new ElementNode("school");
        root.add(new ElementNode("classA")
                .add(new TextNode("Tom"))
                .add(new TextNode("Alice")));
        root.add(new ElementNode("classB")
                .add(new TextNode("Bob"))
                .add(new TextNode("Grace"))
                .add(new CommentNode("comment...")));
        System.out.println(root.toXml());

    }
}
/*
<school>
<classA>
Tom
Alice
</classA>

<classB>
Bob
Grace
<!-- comment... -->
</classB>

</school>
 */

/*
组合
将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具有一致性。

             ┌───────────┐
             │   Node    │
             └───────────┘
                   ▲
      ┌────────────┼────────────┐
      │            │            │
┌───────────┐┌───────────┐┌───────────┐
│ElementNode││ TextNode  ││CommentNode│
└───────────┘└───────────┘└───────────┘

 */