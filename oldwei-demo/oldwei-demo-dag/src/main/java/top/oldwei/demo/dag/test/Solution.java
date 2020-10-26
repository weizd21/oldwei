package top.oldwei.demo.dag.test;

import java.util.*;

class Solution {

    private static final long MAIN_METHOD_ID = 0L;

    static class Method {
        long id;
        Method parent;
        Set<Method> children;
        // TODO 其它属性

        public Method(long id) {
            this.id = id;
            children = new HashSet<>(0);
        }

        @Override
        public String toString() {
            return "Method{" +
                    "id=" + id +
                    '}';
        }
    }

    static class Edge {
        long start;
        long end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static Method buildGraph(Edge[] edges) {
        // 空图
        if (edges == null || edges.length == 0) {
            return null;
        }

        // ID 到Method的映射
        Map<Long, Method> id2Method = new HashMap<>();

        for (Edge edge: edges) {

            long start = edge.start;
            long end = edge.end;

            Method startMethod = id2Method.get(start);

            // 不存在则创建
            if (startMethod == null) {
                startMethod = new Method(start);
                id2Method.put(start, startMethod);
            }

            Method endMethod = id2Method.get(end);

            // 不存在则创建
            if (endMethod == null) {
                endMethod = new Method(end);
                id2Method.put(end, endMethod);
            }

            // 将endMethod放入startMethod的children中
            startMethod.children.add(endMethod);
            // 设置依赖的方法
            endMethod.parent = startMethod;
        }

        return id2Method.get(MAIN_METHOD_ID);

    }

    private static void execute(Method method) {
        System.out.println("执行" + method + "从" + method.parent + "获取入参");
    }

    // 从main方法开始遍历图
    private static void start(Method main) {

        Queue<Method> queue = new LinkedList<>();
        queue.add(main);

        while (! queue.isEmpty()) {

            Method method = queue.poll();

            execute(method);

            for (Method child: method.children) {
                queue.offer(child);
            }
        }
    }


    public static void main(String[] args) {

        Edge[] edges = {
                new Edge(0, 1),
                new Edge(0, 2),
                new Edge(1, 3),
                new Edge(3, 4),
                new Edge(2, 5),
                new Edge(2, 6),
                new Edge(2, 7),
                new Edge(5, 8),
                new Edge(5, 9),
                new Edge(6, 10),
                new Edge(7, 11),
        };
        Method mainMethod = buildGraph(edges);
        start(mainMethod);
    }
}