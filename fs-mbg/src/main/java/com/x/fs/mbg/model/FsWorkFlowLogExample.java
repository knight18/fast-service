package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FsWorkFlowLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FsWorkFlowLogExample() {
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

        public Criteria andWfLogIdIsNull() {
            addCriterion("WF_LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andWfLogIdIsNotNull() {
            addCriterion("WF_LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWfLogIdEqualTo(Long value) {
            addCriterion("WF_LOG_ID =", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdNotEqualTo(Long value) {
            addCriterion("WF_LOG_ID <>", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdGreaterThan(Long value) {
            addCriterion("WF_LOG_ID >", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("WF_LOG_ID >=", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdLessThan(Long value) {
            addCriterion("WF_LOG_ID <", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdLessThanOrEqualTo(Long value) {
            addCriterion("WF_LOG_ID <=", value, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdIn(List<Long> values) {
            addCriterion("WF_LOG_ID in", values, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdNotIn(List<Long> values) {
            addCriterion("WF_LOG_ID not in", values, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdBetween(Long value1, Long value2) {
            addCriterion("WF_LOG_ID between", value1, value2, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfLogIdNotBetween(Long value1, Long value2) {
            addCriterion("WF_LOG_ID not between", value1, value2, "wfLogId");
            return (Criteria) this;
        }

        public Criteria andWfGuidIsNull() {
            addCriterion("WF_GUID is null");
            return (Criteria) this;
        }

        public Criteria andWfGuidIsNotNull() {
            addCriterion("WF_GUID is not null");
            return (Criteria) this;
        }

        public Criteria andWfGuidEqualTo(String value) {
            addCriterion("WF_GUID =", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotEqualTo(String value) {
            addCriterion("WF_GUID <>", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidGreaterThan(String value) {
            addCriterion("WF_GUID >", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidGreaterThanOrEqualTo(String value) {
            addCriterion("WF_GUID >=", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLessThan(String value) {
            addCriterion("WF_GUID <", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLessThanOrEqualTo(String value) {
            addCriterion("WF_GUID <=", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidLike(String value) {
            addCriterion("WF_GUID like", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotLike(String value) {
            addCriterion("WF_GUID not like", value, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidIn(List<String> values) {
            addCriterion("WF_GUID in", values, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotIn(List<String> values) {
            addCriterion("WF_GUID not in", values, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidBetween(String value1, String value2) {
            addCriterion("WF_GUID between", value1, value2, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfGuidNotBetween(String value1, String value2) {
            addCriterion("WF_GUID not between", value1, value2, "wfGuid");
            return (Criteria) this;
        }

        public Criteria andWfTimeIsNull() {
            addCriterion("WF_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWfTimeIsNotNull() {
            addCriterion("WF_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWfTimeEqualTo(Date value) {
            addCriterion("WF_TIME =", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeNotEqualTo(Date value) {
            addCriterion("WF_TIME <>", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeGreaterThan(Date value) {
            addCriterion("WF_TIME >", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("WF_TIME >=", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeLessThan(Date value) {
            addCriterion("WF_TIME <", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeLessThanOrEqualTo(Date value) {
            addCriterion("WF_TIME <=", value, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeIn(List<Date> values) {
            addCriterion("WF_TIME in", values, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeNotIn(List<Date> values) {
            addCriterion("WF_TIME not in", values, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeBetween(Date value1, Date value2) {
            addCriterion("WF_TIME between", value1, value2, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfTimeNotBetween(Date value1, Date value2) {
            addCriterion("WF_TIME not between", value1, value2, "wfTime");
            return (Criteria) this;
        }

        public Criteria andWfLogTextIsNull() {
            addCriterion("WF_LOG_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andWfLogTextIsNotNull() {
            addCriterion("WF_LOG_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andWfLogTextEqualTo(String value) {
            addCriterion("WF_LOG_TEXT =", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextNotEqualTo(String value) {
            addCriterion("WF_LOG_TEXT <>", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextGreaterThan(String value) {
            addCriterion("WF_LOG_TEXT >", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextGreaterThanOrEqualTo(String value) {
            addCriterion("WF_LOG_TEXT >=", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextLessThan(String value) {
            addCriterion("WF_LOG_TEXT <", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextLessThanOrEqualTo(String value) {
            addCriterion("WF_LOG_TEXT <=", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextLike(String value) {
            addCriterion("WF_LOG_TEXT like", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextNotLike(String value) {
            addCriterion("WF_LOG_TEXT not like", value, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextIn(List<String> values) {
            addCriterion("WF_LOG_TEXT in", values, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextNotIn(List<String> values) {
            addCriterion("WF_LOG_TEXT not in", values, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextBetween(String value1, String value2) {
            addCriterion("WF_LOG_TEXT between", value1, value2, "wfLogText");
            return (Criteria) this;
        }

        public Criteria andWfLogTextNotBetween(String value1, String value2) {
            addCriterion("WF_LOG_TEXT not between", value1, value2, "wfLogText");
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