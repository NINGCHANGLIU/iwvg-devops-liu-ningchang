package es.upm.miw.devops.code;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;
    private String familyName;
    private List<Fraction> fractions;

    // 默认构造函数
    public User() {
        this.fractions = new ArrayList<>();
    }

    // 全参数构造函数（id, name, familyName, fractions）
    public User(String id, String name, String familyName, List<Fraction> fractions) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.fractions = fractions;
    }

    // 新增：3 参数构造函数（方便测试）
    public User(String id, String name, String familyName) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.fractions = new ArrayList<>();  // 默认给空集合
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Fraction> getFractions() {
        return fractions;
    }

    public void setFractions(List<Fraction> fractions) {
        this.fractions = fractions;
    }

    public void addFraction(Fraction fraction) {
        this.fractions.add(fraction);
    }

    // 辅助方法：返回全名
    public String fullName() {
        return this.name + " " + this.familyName;
    }

    // 辅助方法：返回首字母缩写
    public String initials() {
        return this.name.charAt(0) + ".";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", fractions=" + fractions +
                '}';
    }
}
