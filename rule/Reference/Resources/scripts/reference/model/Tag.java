package reference.model;

import administrator.dao.LanguageDAO;
import administrator.model.Language;
import com.exponentus.common.model.SimpleReferenceEntity;
import com.exponentus.scripting._Session;
import com.exponentus.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = {"parent", "name"}))
@NamedQuery(name = "Tag.findAll", query = "SELECT m FROM Tag AS m WHERE m.parent IS NULL ORDER BY m.name")
public class Tag extends SimpleReferenceEntity {

    @Column(length = 7)
    private String color;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Tag parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Tag> children;

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public List<Tag> getChildren() {
        return children;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getParentId() {
        if (parent == null) {
            return null;
        }
        return parent.id;
    }

    public void setParentId(UUID id) {
        if (id == null) {
            setParent(null);
            return;
        }

        Tag parent = new Tag();
        parent.setId(id);
        setParent(parent);
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateFormat.format(regDate) + "</regdate>");
        chunk.append("<name>" + getName() + "</name>");
        chunk.append("<color>" + color + "</color>");
        chunk.append("<localizednames>");
        LanguageDAO lDao = new LanguageDAO(ses);
        List<Language> list = lDao.findAll();
        for (Language l : list) {
            chunk.append("<entry id=\"" + l.getCode() + "\">" + getLocalizedName(l.getCode()) + "</entry>");
        }
        chunk.append("</localizednames>");
        return chunk.toString();
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        return "<name>" + getLocalizedName(ses.getLang()) + "</name><color>" + color + "</color>";
    }

    @Override
    public String toString() {
        return "Tag[" + id + ", " + getLocalizedName() + ", " + color + ", " + parent + ", " + getAuthor() + ", " + getRegDate() + "]";
    }
}
