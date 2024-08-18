package cn.wolfcode.crm.domain;


import cn.wolfcode.crm.constants.EmployeeConstants;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class Employee extends BaseDomain {
    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工密码
     */
    private String password;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工邮箱
     */
    private String email;

    /**
     * 是否为管理员
     */
    private boolean admin;

    /**
     * 关联的部门对象
     */
    private Department dept;

    /**
     * 关联的角色列表
     */
    private List<Role> roles = new ArrayList<>();

    /**
     * 员工工号
     */
    private String employeeNumber;

    /**
     * 员工微信号
     */
    private String wechatNumber;

    /**
     * 员工微信名
     */
    private String wechatName;

    private MultipartFile qrCodeImage; // 员工微信二维码收款图片



    /**
     * 员工电话号码
     */
    private String phoneNumber;

    /**
     * 员工企业微信号
     */
    private String enterpriseWechatName;

    /**
     * 员工身份
     */
    private String identity;

    /**
     * 员工权限
     */
    private String permission;

    /**
     * 是否已审核
     */
    private String isReviewed;

    /**
     * 员工评分
     */
    private Double employeeRating;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 是否具备 PPT 制作技能
     */
    private Boolean ppt;

    /**
     * 是否具备客服服务技能
     */
    private Boolean customer_service;

    /**
     * 是否具备海报设计技能
     */
    private Boolean poster;

    /**
     * 是否具备画册设计技能
     */
    private Boolean album;

    /**
     * 关于论文写作的相关描述或标识
     */
    private String thesis;

    /**
     * 是否具备文案写作技能
     */
    private Boolean copywriting;

    /**
     * 专业技能的相关描述或标识
     */
    private String professional_skill;

    /**
     * 是否具备店长管理技能
     */
    private Boolean store_manager;

    /**
     * PPT 技能的评分
     */
    private Double ppt_score;

    /**
     * 客服技能的评分
     */
    private Double customer_service_score;

    /**
     * 海报设计技能的评分
     */
    private Double poster_score;

    /**
     * 画册设计技能的评分
     */
    private Double album_score;

    /**
     * 论文写作技能的评分
     */
    private Double thesis_score;

    /**
     * 文案写作技能的评分
     */
    private Double copywriting_score;

    /**
     * 专业技能的评分
     */
    private Double professional_skill_score;

    /**
     * 店长技能的评分
     */
    private Double store_manager_score;

    public Employee() {
    }
    public Employee(String name, String password, Integer age, String email, boolean admin, Department dept,List<Role> roles) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.admin = admin;
        this.dept = dept;
        this.roles = roles;
    }

    public Employee(String name, String password, Integer age, String email, boolean admin, Department dept, List<Role> roles, String employeeNumber, String wechatNumber, String wechatName, MultipartFile qrCodeImage, String phoneNumber, String enterpriseWechatName, String identity, String permission, String isReviewed, Double employeeRating, String remarks, Boolean ppt, Boolean customer_service, Boolean poster, Boolean album, String thesis, Boolean copywriting, String professional_skill, Boolean store_manager, Double ppt_score, Double customer_service_score, Double poster_score, Double album_score, Double thesis_score, Double copywriting_score, Double professional_skill_score, Double store_manager_score) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.admin = admin;
        this.dept = dept;
        this.roles = roles;
        this.employeeNumber = employeeNumber;
        this.wechatNumber = wechatNumber;
        this.wechatName = wechatName;
        this.qrCodeImage = qrCodeImage;
        this.phoneNumber = phoneNumber;
        this.enterpriseWechatName = enterpriseWechatName;
        this.identity = identity;
        this.permission = permission;
        this.isReviewed = isReviewed;
        this.employeeRating = employeeRating;
        this.remarks = remarks;
        this.ppt = ppt;
        this.customer_service = customer_service;
        this.poster = poster;
        this.album = album;
        this.thesis = thesis;
        this.copywriting = copywriting;
        this.professional_skill = professional_skill;
        this.store_manager = store_manager;
        this.ppt_score = ppt_score;
        this.customer_service_score = customer_service_score;
        this.poster_score = poster_score;
        this.album_score = album_score;
        this.thesis_score = thesis_score;
        this.copywriting_score = copywriting_score;
        this.professional_skill_score = professional_skill_score;
        this.store_manager_score = store_manager_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(MultipartFile qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEnterpriseWechatName() {
        return enterpriseWechatName;
    }

    public void setEnterpriseWechatName(String enterpriseWechatName) {
        this.enterpriseWechatName = enterpriseWechatName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(String isReviewed) {
        this.isReviewed = isReviewed;
    }

    public Double getEmployeeRating() {
        return employeeRating;
    }

    public void setEmployeeRating(Double employeeRating) {
        this.employeeRating = employeeRating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getPpt() {
        return ppt;
    }

    public void setPpt(Boolean ppt) {
        this.ppt = ppt;
    }

    public Boolean getCustomer_service() {
        return customer_service;
    }

    public void setCustomer_service(Boolean customer_service) {
        this.customer_service = customer_service;
    }

    public Boolean getPoster() {
        return poster;
    }

    public void setPoster(Boolean poster) {
        this.poster = poster;
    }

    public Boolean getAlbum() {
        return album;
    }

    public void setAlbum(Boolean album) {
        this.album = album;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public Boolean getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(Boolean copywriting) {
        this.copywriting = copywriting;
    }

    public String getProfessional_skill() {
        return professional_skill;
    }

    public void setProfessional_skill(String professional_skill) {
        this.professional_skill = professional_skill;
    }

    public Boolean getStore_manager() {
        return store_manager;
    }

    public void setStore_manager(Boolean store_manager) {
        this.store_manager = store_manager;
    }

    public Double getPpt_score() {
        return ppt_score;
    }

    public void setPpt_score(Double ppt_score) {
        this.ppt_score = ppt_score;
    }

    public Double getCustomer_service_score() {
        return customer_service_score;
    }

    public void setCustomer_service_score(Double customer_service_score) {
        this.customer_service_score = customer_service_score;
    }

    public Double getPoster_score() {
        return poster_score;
    }

    public void setPoster_score(Double poster_score) {
        this.poster_score = poster_score;
    }

    public Double getAlbum_score() {
        return album_score;
    }

    public void setAlbum_score(Double album_score) {
        this.album_score = album_score;
    }

    public Double getThesis_score() {
        return thesis_score;
    }

    public void setThesis_score(Double thesis_score) {
        this.thesis_score = thesis_score;
    }

    public Double getCopywriting_score() {
        return copywriting_score;
    }

    public void setCopywriting_score(Double copywriting_score) {
        this.copywriting_score = copywriting_score;
    }

    public Double getProfessional_skill_score() {
        return professional_skill_score;
    }

    public void setProfessional_skill_score(Double professional_skill_score) {
        this.professional_skill_score = professional_skill_score;
    }

    public Double getStore_manager_score() {
        return store_manager_score;
    }

    public void setStore_manager_score(Double store_manager_score) {
        this.store_manager_score = store_manager_score;
    }


    public void setDefaultValues() {
        if (identity.equals(EmployeeConstants.DESIGNER)) {
            permission = EmployeeConstants.DESIGNER;
        } else if (identity.equals(EmployeeConstants.CUSTOMER_SERVICE)) {
            permission = EmployeeConstants.CUSTOMER_SERVICE;
        }
        isReviewed = EmployeeConstants.PENDING_REVIEW;
        employeeRating = 0.0;
        // 以下的分数字段根据您最新的类定义进行修改
        ppt = false;
        customer_service = false;
        poster = false;
        album = false;
        copywriting = false;
        store_manager = false;
        ppt_score = 0.0;
        customer_service_score = 0.0;
        poster_score = 0.0;
        album_score = 0.0;
        thesis_score = 0.0;
        copywriting_score = 0.0;
        professional_skill_score = 0.0;
        store_manager_score = 0.0;
    }


}