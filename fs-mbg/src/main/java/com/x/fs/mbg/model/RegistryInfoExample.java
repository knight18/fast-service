package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistryInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegistryInfoExample() {
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

        public Criteria andRegistryIdIsNull() {
            addCriterion("REGISTRY_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegistryIdIsNotNull() {
            addCriterion("REGISTRY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryIdEqualTo(String value) {
            addCriterion("REGISTRY_ID =", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdNotEqualTo(String value) {
            addCriterion("REGISTRY_ID <>", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdGreaterThan(String value) {
            addCriterion("REGISTRY_ID >", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTRY_ID >=", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdLessThan(String value) {
            addCriterion("REGISTRY_ID <", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdLessThanOrEqualTo(String value) {
            addCriterion("REGISTRY_ID <=", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdLike(String value) {
            addCriterion("REGISTRY_ID like", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdNotLike(String value) {
            addCriterion("REGISTRY_ID not like", value, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdIn(List<String> values) {
            addCriterion("REGISTRY_ID in", values, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdNotIn(List<String> values) {
            addCriterion("REGISTRY_ID not in", values, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdBetween(String value1, String value2) {
            addCriterion("REGISTRY_ID between", value1, value2, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegistryIdNotBetween(String value1, String value2) {
            addCriterion("REGISTRY_ID not between", value1, value2, "registryId");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameIsNull() {
            addCriterion("REGKEY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameIsNotNull() {
            addCriterion("REGKEY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameEqualTo(String value) {
            addCriterion("REGKEY_NAME =", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameNotEqualTo(String value) {
            addCriterion("REGKEY_NAME <>", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameGreaterThan(String value) {
            addCriterion("REGKEY_NAME >", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGKEY_NAME >=", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameLessThan(String value) {
            addCriterion("REGKEY_NAME <", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameLessThanOrEqualTo(String value) {
            addCriterion("REGKEY_NAME <=", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameLike(String value) {
            addCriterion("REGKEY_NAME like", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameNotLike(String value) {
            addCriterion("REGKEY_NAME not like", value, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameIn(List<String> values) {
            addCriterion("REGKEY_NAME in", values, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameNotIn(List<String> values) {
            addCriterion("REGKEY_NAME not in", values, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameBetween(String value1, String value2) {
            addCriterion("REGKEY_NAME between", value1, value2, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyNameNotBetween(String value1, String value2) {
            addCriterion("REGKEY_NAME not between", value1, value2, "regkeyName");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueIsNull() {
            addCriterion("REGKEY_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueIsNotNull() {
            addCriterion("REGKEY_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueEqualTo(String value) {
            addCriterion("REGKEY_VALUE =", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueNotEqualTo(String value) {
            addCriterion("REGKEY_VALUE <>", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueGreaterThan(String value) {
            addCriterion("REGKEY_VALUE >", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueGreaterThanOrEqualTo(String value) {
            addCriterion("REGKEY_VALUE >=", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueLessThan(String value) {
            addCriterion("REGKEY_VALUE <", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueLessThanOrEqualTo(String value) {
            addCriterion("REGKEY_VALUE <=", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueLike(String value) {
            addCriterion("REGKEY_VALUE like", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueNotLike(String value) {
            addCriterion("REGKEY_VALUE not like", value, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueIn(List<String> values) {
            addCriterion("REGKEY_VALUE in", values, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueNotIn(List<String> values) {
            addCriterion("REGKEY_VALUE not in", values, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueBetween(String value1, String value2) {
            addCriterion("REGKEY_VALUE between", value1, value2, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyValueNotBetween(String value1, String value2) {
            addCriterion("REGKEY_VALUE not between", value1, value2, "regkeyValue");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusIsNull() {
            addCriterion("REGKEY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusIsNotNull() {
            addCriterion("REGKEY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusEqualTo(String value) {
            addCriterion("REGKEY_STATUS =", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusNotEqualTo(String value) {
            addCriterion("REGKEY_STATUS <>", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusGreaterThan(String value) {
            addCriterion("REGKEY_STATUS >", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusGreaterThanOrEqualTo(String value) {
            addCriterion("REGKEY_STATUS >=", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusLessThan(String value) {
            addCriterion("REGKEY_STATUS <", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusLessThanOrEqualTo(String value) {
            addCriterion("REGKEY_STATUS <=", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusLike(String value) {
            addCriterion("REGKEY_STATUS like", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusNotLike(String value) {
            addCriterion("REGKEY_STATUS not like", value, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusIn(List<String> values) {
            addCriterion("REGKEY_STATUS in", values, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusNotIn(List<String> values) {
            addCriterion("REGKEY_STATUS not in", values, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusBetween(String value1, String value2) {
            addCriterion("REGKEY_STATUS between", value1, value2, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andRegkeyStatusNotBetween(String value1, String value2) {
            addCriterion("REGKEY_STATUS not between", value1, value2, "regkeyStatus");
            return (Criteria) this;
        }

        public Criteria andValueDescIsNull() {
            addCriterion("VALUE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andValueDescIsNotNull() {
            addCriterion("VALUE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andValueDescEqualTo(String value) {
            addCriterion("VALUE_DESC =", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescNotEqualTo(String value) {
            addCriterion("VALUE_DESC <>", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescGreaterThan(String value) {
            addCriterion("VALUE_DESC >", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_DESC >=", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescLessThan(String value) {
            addCriterion("VALUE_DESC <", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescLessThanOrEqualTo(String value) {
            addCriterion("VALUE_DESC <=", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescLike(String value) {
            addCriterion("VALUE_DESC like", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescNotLike(String value) {
            addCriterion("VALUE_DESC not like", value, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescIn(List<String> values) {
            addCriterion("VALUE_DESC in", values, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescNotIn(List<String> values) {
            addCriterion("VALUE_DESC not in", values, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescBetween(String value1, String value2) {
            addCriterion("VALUE_DESC between", value1, value2, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andValueDescNotBetween(String value1, String value2) {
            addCriterion("VALUE_DESC not between", value1, value2, "valueDesc");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNull() {
            addCriterion("CREATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIsNotNull() {
            addCriterion("CREATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreationTimeEqualTo(Date value) {
            addCriterion("CREATION_TIME =", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotEqualTo(Date value) {
            addCriterion("CREATION_TIME <>", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThan(Date value) {
            addCriterion("CREATION_TIME >", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATION_TIME >=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThan(Date value) {
            addCriterion("CREATION_TIME <", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATION_TIME <=", value, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeIn(List<Date> values) {
            addCriterion("CREATION_TIME in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotIn(List<Date> values) {
            addCriterion("CREATION_TIME not in", values, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeBetween(Date value1, Date value2) {
            addCriterion("CREATION_TIME between", value1, value2, "creationTime");
            return (Criteria) this;
        }

        public Criteria andCreationTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATION_TIME not between", value1, value2, "creationTime");
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