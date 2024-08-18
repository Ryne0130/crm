package cn.wolfcode.crm.domain;

import cn.wolfcode.crm.util.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//客户
public class Customer extends BaseDomain {
    //性别值的常量
    public static final Integer GENDER_WOMAN = 0; //女
    public static final Integer GENDER_MAN = 1; //男
    //状态的常量
    public static final Integer STATUS_NORMAL = 0; //潜在客户
    public static final Integer STATUS_POOL = 1; //客户池
    public static final Integer STATUS_FAIL = 2; //开发失败
    public static final Integer STATUS_LOST = 3; //流失

    private String name; //姓名

    private Integer age; //年龄

    private Integer gender = GENDER_WOMAN; //性别

    private String tel; //电话

    private String qq; //QQ

    //以下两个数据来源数据字典
    private SystemDictionaryItem job; //职业

    private SystemDictionaryItem source; //来源

    private Employee seller; //营销人员

    private Employee inputUser; //录入人

    private Date inputTime; //录入时间

    private Integer status = STATUS_NORMAL; //状态,默认潜在客户

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        map.put("gender", gender);
        map.put("tel", tel);
        map.put("qq", qq);
        map.put("jobId", job.getId());
        map.put("sourceId", source.getId());
        return JSONUtil.toJSONString(map);
    }

    public String getGenderName() {
        String msg = "女";
        if (gender.equals(GENDER_MAN)) {
            msg = "男";
        }
        return msg;
    }

    public String getStatusName() {
        String msg = "潜在客户";
        if (status.equals(STATUS_POOL)) {
            msg = "客户池";
        } else if (status.equals(STATUS_FAIL)) {
            msg = "开发失败";
        } else if (status.equals(STATUS_LOST)) {
            msg = "流失";
        }
        return msg;
    }

    public static Integer getGenderWoman() {
        return GENDER_WOMAN;
    }

    public static Integer getGenderMan() {
        return GENDER_MAN;
    }

    public static Integer getStatusNormal() {
        return STATUS_NORMAL;
    }

    public static Integer getStatusPool() {
        return STATUS_POOL;
    }

    public static Integer getStatusFail() {
        return STATUS_FAIL;
    }

    public static Integer getStatusLost() {
        return STATUS_LOST;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public SystemDictionaryItem getJob() {
        return job;
    }

    public void setJob(SystemDictionaryItem job) {
        this.job = job;
    }

    public SystemDictionaryItem getSource() {
        return source;
    }

    public void setSource(SystemDictionaryItem source) {
        this.source = source;
    }

    public Employee getSeller() {
        return seller;
    }

    public void setSeller(Employee seller) {
        this.seller = seller;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}