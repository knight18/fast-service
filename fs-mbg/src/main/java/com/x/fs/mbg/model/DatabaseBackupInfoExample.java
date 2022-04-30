package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseBackupInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatabaseBackupInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDatabaseNameIsNull() {
            addCriterion("DATABASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNotNull() {
            addCriterion("DATABASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameEqualTo(String value) {
            addCriterion("DATABASE_NAME =", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotEqualTo(String value) {
            addCriterion("DATABASE_NAME <>", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThan(String value) {
            addCriterion("DATABASE_NAME >", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASE_NAME >=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThan(String value) {
            addCriterion("DATABASE_NAME <", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
            addCriterion("DATABASE_NAME <=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLike(String value) {
            addCriterion("DATABASE_NAME like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotLike(String value) {
            addCriterion("DATABASE_NAME not like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIn(List<String> values) {
            addCriterion("DATABASE_NAME in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotIn(List<String> values) {
            addCriterion("DATABASE_NAME not in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameBetween(String value1, String value2) {
            addCriterion("DATABASE_NAME between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotBetween(String value1, String value2) {
            addCriterion("DATABASE_NAME not between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIsNull() {
            addCriterion("DATABASE_IP is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIsNotNull() {
            addCriterion("DATABASE_IP is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpEqualTo(String value) {
            addCriterion("DATABASE_IP =", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotEqualTo(String value) {
            addCriterion("DATABASE_IP <>", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpGreaterThan(String value) {
            addCriterion("DATABASE_IP >", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASE_IP >=", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLessThan(String value) {
            addCriterion("DATABASE_IP <", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLessThanOrEqualTo(String value) {
            addCriterion("DATABASE_IP <=", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLike(String value) {
            addCriterion("DATABASE_IP like", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotLike(String value) {
            addCriterion("DATABASE_IP not like", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIn(List<String> values) {
            addCriterion("DATABASE_IP in", values, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotIn(List<String> values) {
            addCriterion("DATABASE_IP not in", values, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpBetween(String value1, String value2) {
            addCriterion("DATABASE_IP between", value1, value2, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotBetween(String value1, String value2) {
            addCriterion("DATABASE_IP not between", value1, value2, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabasePortIsNull() {
            addCriterion("DATABASE_PORT is null");
            return (Criteria) this;
        }

        public Criteria andDatabasePortIsNotNull() {
            addCriterion("DATABASE_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andDatabasePortEqualTo(String value) {
            addCriterion("DATABASE_PORT =", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortNotEqualTo(String value) {
            addCriterion("DATABASE_PORT <>", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortGreaterThan(String value) {
            addCriterion("DATABASE_PORT >", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASE_PORT >=", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortLessThan(String value) {
            addCriterion("DATABASE_PORT <", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortLessThanOrEqualTo(String value) {
            addCriterion("DATABASE_PORT <=", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortLike(String value) {
            addCriterion("DATABASE_PORT like", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortNotLike(String value) {
            addCriterion("DATABASE_PORT not like", value, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortIn(List<String> values) {
            addCriterion("DATABASE_PORT in", values, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortNotIn(List<String> values) {
            addCriterion("DATABASE_PORT not in", values, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortBetween(String value1, String value2) {
            addCriterion("DATABASE_PORT between", value1, value2, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabasePortNotBetween(String value1, String value2) {
            addCriterion("DATABASE_PORT not between", value1, value2, "databasePort");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdIsNull() {
            addCriterion("DATABASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdIsNotNull() {
            addCriterion("DATABASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdEqualTo(String value) {
            addCriterion("DATABASE_ID =", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdNotEqualTo(String value) {
            addCriterion("DATABASE_ID <>", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdGreaterThan(String value) {
            addCriterion("DATABASE_ID >", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("DATABASE_ID >=", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdLessThan(String value) {
            addCriterion("DATABASE_ID <", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdLessThanOrEqualTo(String value) {
            addCriterion("DATABASE_ID <=", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdLike(String value) {
            addCriterion("DATABASE_ID like", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdNotLike(String value) {
            addCriterion("DATABASE_ID not like", value, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdIn(List<String> values) {
            addCriterion("DATABASE_ID in", values, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdNotIn(List<String> values) {
            addCriterion("DATABASE_ID not in", values, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdBetween(String value1, String value2) {
            addCriterion("DATABASE_ID between", value1, value2, "databaseId");
            return (Criteria) this;
        }

        public Criteria andDatabaseIdNotBetween(String value1, String value2) {
            addCriterion("DATABASE_ID not between", value1, value2, "databaseId");
            return (Criteria) this;
        }

        public Criteria andBackupDirIsNull() {
            addCriterion("BACKUP_DIR is null");
            return (Criteria) this;
        }

        public Criteria andBackupDirIsNotNull() {
            addCriterion("BACKUP_DIR is not null");
            return (Criteria) this;
        }

        public Criteria andBackupDirEqualTo(String value) {
            addCriterion("BACKUP_DIR =", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirNotEqualTo(String value) {
            addCriterion("BACKUP_DIR <>", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirGreaterThan(String value) {
            addCriterion("BACKUP_DIR >", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirGreaterThanOrEqualTo(String value) {
            addCriterion("BACKUP_DIR >=", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirLessThan(String value) {
            addCriterion("BACKUP_DIR <", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirLessThanOrEqualTo(String value) {
            addCriterion("BACKUP_DIR <=", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirLike(String value) {
            addCriterion("BACKUP_DIR like", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirNotLike(String value) {
            addCriterion("BACKUP_DIR not like", value, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirIn(List<String> values) {
            addCriterion("BACKUP_DIR in", values, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirNotIn(List<String> values) {
            addCriterion("BACKUP_DIR not in", values, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirBetween(String value1, String value2) {
            addCriterion("BACKUP_DIR between", value1, value2, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupDirNotBetween(String value1, String value2) {
            addCriterion("BACKUP_DIR not between", value1, value2, "backupDir");
            return (Criteria) this;
        }

        public Criteria andBackupNameIsNull() {
            addCriterion("BACKUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBackupNameIsNotNull() {
            addCriterion("BACKUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBackupNameEqualTo(String value) {
            addCriterion("BACKUP_NAME =", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameNotEqualTo(String value) {
            addCriterion("BACKUP_NAME <>", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameGreaterThan(String value) {
            addCriterion("BACKUP_NAME >", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameGreaterThanOrEqualTo(String value) {
            addCriterion("BACKUP_NAME >=", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameLessThan(String value) {
            addCriterion("BACKUP_NAME <", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameLessThanOrEqualTo(String value) {
            addCriterion("BACKUP_NAME <=", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameLike(String value) {
            addCriterion("BACKUP_NAME like", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameNotLike(String value) {
            addCriterion("BACKUP_NAME not like", value, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameIn(List<String> values) {
            addCriterion("BACKUP_NAME in", values, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameNotIn(List<String> values) {
            addCriterion("BACKUP_NAME not in", values, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameBetween(String value1, String value2) {
            addCriterion("BACKUP_NAME between", value1, value2, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupNameNotBetween(String value1, String value2) {
            addCriterion("BACKUP_NAME not between", value1, value2, "backupName");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixIsNull() {
            addCriterion("BACKUP_FILE_SUFFIX is null");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixIsNotNull() {
            addCriterion("BACKUP_FILE_SUFFIX is not null");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixEqualTo(String value) {
            addCriterion("BACKUP_FILE_SUFFIX =", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixNotEqualTo(String value) {
            addCriterion("BACKUP_FILE_SUFFIX <>", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixGreaterThan(String value) {
            addCriterion("BACKUP_FILE_SUFFIX >", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("BACKUP_FILE_SUFFIX >=", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixLessThan(String value) {
            addCriterion("BACKUP_FILE_SUFFIX <", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixLessThanOrEqualTo(String value) {
            addCriterion("BACKUP_FILE_SUFFIX <=", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixLike(String value) {
            addCriterion("BACKUP_FILE_SUFFIX like", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixNotLike(String value) {
            addCriterion("BACKUP_FILE_SUFFIX not like", value, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixIn(List<String> values) {
            addCriterion("BACKUP_FILE_SUFFIX in", values, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixNotIn(List<String> values) {
            addCriterion("BACKUP_FILE_SUFFIX not in", values, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixBetween(String value1, String value2) {
            addCriterion("BACKUP_FILE_SUFFIX between", value1, value2, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andBackupFileSuffixNotBetween(String value1, String value2) {
            addCriterion("BACKUP_FILE_SUFFIX not between", value1, value2, "backupFileSuffix");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNull() {
            addCriterion("UPT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUptTimeIsNotNull() {
            addCriterion("UPT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUptTimeEqualTo(Date value) {
            addCriterion("UPT_TIME =", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotEqualTo(Date value) {
            addCriterion("UPT_TIME <>", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThan(Date value) {
            addCriterion("UPT_TIME >", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPT_TIME >=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThan(Date value) {
            addCriterion("UPT_TIME <", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPT_TIME <=", value, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeIn(List<Date> values) {
            addCriterion("UPT_TIME in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotIn(List<Date> values) {
            addCriterion("UPT_TIME not in", values, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeBetween(Date value1, Date value2) {
            addCriterion("UPT_TIME between", value1, value2, "uptTime");
            return (Criteria) this;
        }

        public Criteria andUptTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPT_TIME not between", value1, value2, "uptTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}