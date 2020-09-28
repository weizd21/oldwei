package top.oldwei.demo.dag.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author weizd
 */
@Data
public class Dag implements Serializable {
    private long id;

    private String name;

    private List<Vertice> vertices = new ArrayList<>();

    private List<Edge> edges = new ArrayList<>();

    /**
     * 通过顶点类型获取dag中 顶点
     * @param type
     * @return
     */
    public Vertice getVerticeByType(String type){
        Iterator var2 = this.vertices.iterator();
        Vertice v;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            v = (Vertice)var2.next();
        } while(!v.getType().equals(type));
        return v;
    }

    /**
     * 通过顶点的id获取 顶点的下级顶点列表
     * @return
     */
    public List<Vertice> getNextVerticesById(long id){
        List<Vertice> vertices = new ArrayList<>();

        Iterator var4 = this.edges.iterator();

        while(var4.hasNext()) {
            Edge e = (Edge)var4.next();
            Long source = e.getSource() == null ? e.getStart() : e.getSource();
            if (source.equals(id)) {
                Long target = e.getTarget() == null ? e.getEnd() : e.getTarget();
                Vertice v = this.getVerticeById(target);
                vertices.add(v);
            }
        }
        return vertices;
    }

    /**
     * 通过顶点的id获取 顶点的上级顶点列表
     * @param id
     * @return
     */
    public List<Vertice> getPreVerticesById(long id){
        List<Vertice> vertices = new ArrayList<>();
        Iterator var4 = this.edges.iterator();
        while(var4.hasNext()) {
            Edge e = (Edge)var4.next();
            Long target = e.getTarget() == null ? e.getEnd() : e.getTarget();
            if (target.equals(id)) {
                Long source = e.getSource() == null ? e.getStart() : e.getSource();
                Vertice v = this.getVerticeById(source);
                vertices.add(v);
            }
        }
        return vertices;
    }


    /**
     * 通过id 获取dag中一个顶点
     * @param id
     * @return
     */
    public Vertice getVerticeById(long id){
        Iterator var = this.vertices.iterator();
        Vertice v;
        do {
            if (!var.hasNext()) {
                return null;
            }
            v = (Vertice)var.next();
        } while(v.getId() != id);
        return v;
    }
}
