package org.jeffrey.oauth.base;

import org.jeffrey.oauth.util.DateUtils;
import org.jeffrey.oauth.util.GuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 定义抽象的Domain父类，将公共属性放于此
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:35
 **/
public abstract class AbstractDomain implements Serializable{

    private static final long serialVersionUID = 6569365774429340632L;

    protected int id;

    /** 用于实现逻辑删除 **/
    protected boolean archived;

    /** 业务ID **/
    protected String guid = GuidGenerator.generate();

    /** The domain create time **/
    protected LocalDateTime createTime = DateUtils.now();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean getArchived(){
        return archived;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public AbstractDomain archived(boolean archived){
        this.archived = archived;
        return this;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof AbstractDomain)){
            return false;
        }
        AbstractDomain abstractDomain = (AbstractDomain)o;
        return guid.equals(abstractDomain.guid);
    }

    @Override
    public int hashCode(){
        return guid.hashCode();
    }

    public void saveOrUpdate(){}

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{id=").append(id);
        stringBuilder.append(",archived=").append(archived);
        stringBuilder.append(",guid='").append(guid).append("\'");
        stringBuilder.append(",createTime=").append(createTime);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
