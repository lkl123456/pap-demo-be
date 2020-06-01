package com.yonyou.base.copy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yonyou.iuap.baseservice.entity.AbsDrModel;
import com.yonyou.iuap.baseservice.support.condition.Condition;
import com.yonyou.iuap.baseservice.support.generator.GeneratedValue;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * 业务实体
 * 
 * @date 2019年08月27日 上午10点46分38秒
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "btd_filed_business")
@Entity(name = "com.yonyou.iuap.permit.entity.BtdBusfiled")
public class BtdBusfiled extends AbsDrModel implements Serializable {
	@Id
	@GeneratedValue
	@Condition
	@Column(name = "id")
	@ApiModelProperty(value="主键",name="id",required=true)
	protected String id;

	@Override
	public void setId(Serializable id){
		this.id= id.toString();
		super.id = id;
	}

	@Override
	public String getId() {
		return id;
	}


	@ApiModelProperty(value="角色编号",name="roleId",required=true)
	@Column(name = "role_id")
	@Condition
	private String roleId;


	@ApiModelProperty(value="字段列名",name="columnName",required=true)
	@Column(name = "column_name")
	@Condition
	private String columnName;

	@ApiModelProperty(value="表名",name="tableName",required=true)
	@Column(name = "table_name")
	@Condition
	private String tableName;

	@ApiModelProperty(value="是否显示",name="display",required=true)
	@Column(name = "display")
	@Condition
	private String dispaly;

	@ApiModelProperty(value="状态",name="status",required=true)
	@Column(name = "status")
	@Condition
	private Integer status;

	@ApiModelProperty(value="自定义项1",name="def1",required=true)
	@Column(name = "def1")
	@Condition
	private String def1;

	@ApiModelProperty(value="自定义项2",name="def2",required=true)
	@Column(name = "def2")
	@Condition
	private String def2;

	@ApiModelProperty(value="自定义项3",name="def3",required=true)
	@Column(name = "def3")
	@Condition
	private String def3;

	@ApiModelProperty(value="自定义项4",name="def4",required=true)
	@Column(name = "def4")
	@Condition
	private String def4;

	@ApiModelProperty(value="自定义项5",name="def5",required=true)
	@Column(name = "def5")
	@Condition
	private String def5;

	@ApiModelProperty(value="编号",name="code",required=true)
	@Column(name = "code")
	@Condition
	private String code;

	@ApiModelProperty(value="姓名",name="name",required=true)
	@Column(name = "name")
	@Condition
	private String name;

	@ApiModelProperty(value="创建时间",name="createTime",required=true)
	@Column(name = "create_time")
	@Condition
	private String createTime;

	@ApiModelProperty(value="最后修改时间",name="lastModified",required=true)
	@Column(name = "last_modified")
	@Condition
	private String lastModified;

	@ApiModelProperty(value="最后修改人",name="lastModifyUser",required=true)
	@Column(name = "last_modify_user")
	@Condition
	private String lastModifyUser;

	@ApiModelProperty(value="修改人",name="modifier",required=true)
	@Column(name = "modifier")
	@Condition
	private String modifier;

	@ApiModelProperty(value="备注",name="remark",required=true)
	@Column(name = "remark")
	@Condition
	private String remark;

	@ApiModelProperty(value="所属集团",name="pkGroup",required=true)
	@Column(name = "pk_group")
	@Condition
	private String pkGroup;

	@ApiModelProperty(value="所属组织",name="pkOrg",required=true)
	@Column(name = "pk_org")
	@Condition
	private String pkOrg;

	@ApiModelProperty(value="创建人",name="createUser",required=true)
	@Column(name = "create_user")
	@Condition
	private String createUser;

	@ApiModelProperty(value="时间戳",name="ts",required=true)
	@Column(name = "ts")
	@Condition
	private String ts;

	@ApiModelProperty(value="状态标识",name="dr",required=true)
	@Column(name = "dr")
	@Condition
	private Integer dr;

	@ApiModelProperty(value="权限字符",name="dataStatus",required=true)
	@Column(name = "data_status")
	@Condition
	private String dataStatus;

	@ApiModelProperty(value="显示名称",name="displayName1",required=true)
	@Column(name = "display_name1")
	@Condition
	private String displayName1;

	@ApiModelProperty(value="业务对象",name="pkBusinessObj",required=true)
	@Column(name = "pk_business_obj")
	@Condition
	private String pkBusinessObj;

	@ApiModelProperty(value="显示名称2",name="displayName2",required=true)
	@Column(name = "display_name2")
	@Condition
	private String displayName2;

	@ApiModelProperty(value="业务字段名称1",name="bus_name1",required=true)
	@Column(name = "bus_name1")
	@Condition
	private String busName1;

	@ApiModelProperty(value="业务字段名称2",name="bus_name2",required=true)
	@Column(name = "bus_name2")
	@Condition
	private String busName2;

	@ApiModelProperty(value="业务字段名称3",name="bus_name3",required=true)
	@Column(name = "bus_name3")
	@Condition
	private String busName3;

	@ApiModelProperty(value="组件类型",name="default_type",required=true)
	@Column(name = "default_type")
	@Condition
	private Integer defaultType;


	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDispaly() {
		return dispaly;
	}

	public void setDispaly(String dispaly) {
		this.dispaly = dispaly;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDef1() {
		return def1;
	}

	public void setDef1(String def1) {
		this.def1 = def1;
	}

	public String getDef2() {
		return def2;
	}

	public void setDef2(String def2) {
		this.def2 = def2;
	}

	public String getDef3() {
		return def3;
	}

	public void setDef3(String def3) {
		this.def3 = def3;
	}

	public String getDef4() {
		return def4;
	}

	public void setDef4(String def4) {
		this.def4 = def4;
	}

	public String getDef5() {
		return def5;
	}

	public void setDef5(String def5) {
		this.def5 = def5;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String getLastModified() {
		return lastModified;
	}

	@Override
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String getLastModifyUser() {
		return lastModifyUser;
	}

	@Override
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPkGroup() {
		return pkGroup;
	}

	public void setPkGroup(String pkGroup) {
		this.pkGroup = pkGroup;
	}

	public String getPkOrg() {
		return pkOrg;
	}

	public void setPkOrg(String pkOrg) {
		this.pkOrg = pkOrg;
	}

	@Override
	public String getCreateUser() {
		return createUser;
	}

	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public String getTs() {
		return ts;
	}

	@Override
	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public Integer getDr() {
		return dr;
	}

	@Override
	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDisplayName1() {
		return displayName1;
	}

	public void setDisplayName1(String displayName1) {
		this.displayName1 = displayName1;
	}

	public String getPkBusinessObj() {
		return pkBusinessObj;
	}

	public void setPkBusinessObj(String pkBusinessObj) {
		this.pkBusinessObj = pkBusinessObj;
	}

	public String getDisplayName2() {
		return displayName2;
	}

	public void setDisplayName2(String displayName2) {
		this.displayName2 = displayName2;
	}

	public String getBusName1() {
		return busName1;
	}

	public void setBusName1(String busName1) {
		this.busName1 = busName1;
	}

	public String getBusName2() {
		return busName2;
	}

	public void setBusName2(String busName2) {
		this.busName2 = busName2;
	}

	public String getBusName3() {
		return busName3;
	}

	public void setBusName3(String busName3) {
		this.busName3 = busName3;
	}

	public Integer getDefaultType() {
		return defaultType;
	}

	public void setDefaultType(Integer defaultType) {
		this.defaultType = defaultType;
	}

	public BtdBusfiled(){}

	public BtdBusfiled(String columnName, String tableName, Integer status, String name, String code) {
		this.columnName = columnName;
		this.tableName = tableName;
		this.status = status;
		this.code = code;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
		if (o == null || getClass() != o.getClass()) {
            return false;
        }
		BtdBusfiled that = (BtdBusfiled) o;
		return Objects.equals(columnName, that.columnName) && Objects.equals(tableName, that.tableName);
	}

	@Override
	public int hashCode() {

		return Objects.hash(columnName, tableName);
	}
}
