package com.example.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 文龙 on 2020/2/17.
 */
@Entity     //表示此类归greendao管理，和表进行关联
public class Bean {
    @Id
    private Long id;//一定是long类型
    private String data;
    @Unique //唯一性约束，数据不能重复
    private String name;
    private int step;
    @Generated(hash = 830187939)
    public Bean(Long id, String data, String name, int step) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.step = step;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStep() {
        return this.step;
    }
    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", name='" + name + '\'' +
                ", step=" + step +
                '}';
    }
}
