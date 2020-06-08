package com.ygy.liberal.arithmetic.demo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

    //定义顶点信息
    private static class Vertex {

        private int indegree;  //入度

        private String name;  //顶点信息

        //广度优先搜索新增字段
        private int deepPath = Integer.MAX_VALUE;  //路径深度 初始化为最小

        private Boolean isKnown = false;   //路口是否已探  初始化为false

        private Vertex preShortVertex;

        public boolean isKnown() {
            return isKnown;
        }

        public Vertex(String name) {
            this.name = name;
            indegree = 0;   //初始入度为0
        }

        public int getIndegree() {
            return indegree;
        }

        public void setIndegree(int indegree) {
            this.indegree = indegree;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDeepPath() {
            return deepPath;
        }

        public void setDeepPath(int deepPath) {
            this.deepPath = deepPath;
        }

        public Boolean getKnown() {
            return isKnown;
        }

        public void setKnown(Boolean known) {
            isKnown = known;
        }

        public Vertex getPreShortVertex() {
            return preShortVertex;
        }

        public void setPreShortVertex(Vertex preShortVertex) {
            this.preShortVertex = preShortVertex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Vertex vertex = (Vertex) o;

            return name.equals(vertex.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    //定义拓扑关系图
    private static class TopoGraph {

        //dijkstra   修改关系为map 带入权重
        public Map<Vertex, Map<Vertex, Integer>> relMap = new HashMap<>();  //顶点与节点关系

        public Set<Vertex> vertices = new HashSet<>();  //所有节点信息

        //添加顶点关系图
        public boolean addRelVertex(Vertex start, Vertex end, Integer weight) {

            //根据name判断重复
            vertices.add(start);
            vertices.add(end);

            Map<Vertex, Integer> adjcents = relMap.get(start);  //相邻节点信息
            if (CollectionUtils.isEmpty(adjcents)) {
                adjcents = new HashMap<>();
            }

            if (adjcents.containsKey(end)) {
                return false;
            }

            adjcents.put(end, weight);
            int indegree = end.getIndegree();
            end.setIndegree(++indegree);    //入度+1
            relMap.put(start, adjcents);
            return true;
        }
    }

    public static List<Vertex> dijkstra(TopoGraph topoGraph, Vertex root, Vertex target) {

        PriorityQueue<Vertex> knownQueue = new PriorityQueue<>(
                (x, y) -> x.getDeepPath() - y.getDeepPath() > 0 ? 1 : -1
        );
        List<Vertex> resultList = new ArrayList<>();
        root.setKnown(true);
        root.setDeepPath(0);
        knownQueue.add(root);
        //最小堆结构,构造最近数据

        while (!knownQueue.isEmpty()) {
            //移出最小deep节点
            Vertex minVertex = knownQueue.remove();
            //标记为已知
            minVertex.setKnown(true);
            resultList.add(minVertex);
            Map<Vertex, Integer> relAdj = topoGraph.relMap.get(minVertex);

            if (!CollectionUtils.isEmpty(relAdj)) {
                for (Vertex vertex : relAdj.keySet()) {
                    if (!vertex.isKnown()) {
                        Integer weight = relAdj.get(vertex);
                        //比较已有的deep值，找出最小路线
                        if (minVertex.getDeepPath() + weight < vertex.getDeepPath()) {
                            knownQueue.remove(vertex);   //移出已有的
                            vertex.setDeepPath((weight + minVertex.getDeepPath()));
                            knownQueue.add(vertex);
                            vertex.preShortVertex = minVertex;
                        }
                    }
                }
            }
        }
        return resultList;
    }

    public static void printShortPath(List<Vertex> list) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (Vertex vertex : list) {
            int count = 0;
            while (vertex != null) {
                if (count != 0) {
                    System.out.print("<----");
                }
                System.out.print(vertex.getName() + ":" + vertex.getDeepPath());
                vertex = vertex.preShortVertex;
                count++;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        TopoGraph topoGraph = new TopoGraph();
        topoGraph.addRelVertex(vertexA, vertexB, 5);
        topoGraph.addRelVertex(vertexA, vertexC, 2);
        topoGraph.addRelVertex(vertexC, vertexD, 3);
        topoGraph.addRelVertex(vertexC, vertexB, 2);
        topoGraph.addRelVertex(vertexB, vertexD, 2);
        topoGraph.addRelVertex(vertexB, vertexE, 15);
        topoGraph.addRelVertex(vertexD, vertexE, 4);
        printShortPath(dijkstra(topoGraph, vertexA, vertexE));


    }

}