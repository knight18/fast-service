package com.x.fs.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class FsTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FsTaskExample() {
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

        public Criteria andTasksIsNull() {
            addCriterion("TASKS is null");
            return (Criteria) this;
        }

        public Criteria andTasksIsNotNull() {
            addCriterion("TASKS is not null");
            return (Criteria) this;
        }

        public Criteria andTasksEqualTo(Integer value) {
            addCriterion("TASKS =", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksNotEqualTo(Integer value) {
            addCriterion("TASKS <>", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksGreaterThan(Integer value) {
            addCriterion("TASKS >", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASKS >=", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksLessThan(Integer value) {
            addCriterion("TASKS <", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksLessThanOrEqualTo(Integer value) {
            addCriterion("TASKS <=", value, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksIn(List<Integer> values) {
            addCriterion("TASKS in", values, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksNotIn(List<Integer> values) {
            addCriterion("TASKS not in", values, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksBetween(Integer value1, Integer value2) {
            addCriterion("TASKS between", value1, value2, "tasks");
            return (Criteria) this;
        }

        public Criteria andTasksNotBetween(Integer value1, Integer value2) {
            addCriterion("TASKS not between", value1, value2, "tasks");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIsNull() {
            addCriterion("TASK_PHASE is null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIsNotNull() {
            addCriterion("TASK_PHASE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseEqualTo(Integer value) {
            addCriterion("TASK_PHASE =", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotEqualTo(Integer value) {
            addCriterion("TASK_PHASE <>", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseGreaterThan(Integer value) {
            addCriterion("TASK_PHASE >", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("TASK_PHASE >=", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseLessThan(Integer value) {
            addCriterion("TASK_PHASE <", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseLessThanOrEqualTo(Integer value) {
            addCriterion("TASK_PHASE <=", value, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseIn(List<Integer> values) {
            addCriterion("TASK_PHASE in", values, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotIn(List<Integer> values) {
            addCriterion("TASK_PHASE not in", values, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseBetween(Integer value1, Integer value2) {
            addCriterion("TASK_PHASE between", value1, value2, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTaskPhaseNotBetween(Integer value1, Integer value2) {
            addCriterion("TASK_PHASE not between", value1, value2, "taskPhase");
            return (Criteria) this;
        }

        public Criteria andTasksNameIsNull() {
            addCriterion("TASKS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTasksNameIsNotNull() {
            addCriterion("TASKS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTasksNameEqualTo(String value) {
            addCriterion("TASKS_NAME =", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameNotEqualTo(String value) {
            addCriterion("TASKS_NAME <>", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameGreaterThan(String value) {
            addCriterion("TASKS_NAME >", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASKS_NAME >=", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameLessThan(String value) {
            addCriterion("TASKS_NAME <", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameLessThanOrEqualTo(String value) {
            addCriterion("TASKS_NAME <=", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameLike(String value) {
            addCriterion("TASKS_NAME like", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameNotLike(String value) {
            addCriterion("TASKS_NAME not like", value, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameIn(List<String> values) {
            addCriterion("TASKS_NAME in", values, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameNotIn(List<String> values) {
            addCriterion("TASKS_NAME not in", values, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameBetween(String value1, String value2) {
            addCriterion("TASKS_NAME between", value1, value2, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTasksNameNotBetween(String value1, String value2) {
            addCriterion("TASKS_NAME not between", value1, value2, "tasksName");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeIsNull() {
            addCriterion("TASK_JOB_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeIsNotNull() {
            addCriterion("TASK_JOB_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeEqualTo(String value) {
            addCriterion("TASK_JOB_TYPE =", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeNotEqualTo(String value) {
            addCriterion("TASK_JOB_TYPE <>", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeGreaterThan(String value) {
            addCriterion("TASK_JOB_TYPE >", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_JOB_TYPE >=", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeLessThan(String value) {
            addCriterion("TASK_JOB_TYPE <", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_JOB_TYPE <=", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeLike(String value) {
            addCriterion("TASK_JOB_TYPE like", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeNotLike(String value) {
            addCriterion("TASK_JOB_TYPE not like", value, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeIn(List<String> values) {
            addCriterion("TASK_JOB_TYPE in", values, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeNotIn(List<String> values) {
            addCriterion("TASK_JOB_TYPE not in", values, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeBetween(String value1, String value2) {
            addCriterion("TASK_JOB_TYPE between", value1, value2, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andTaskJobTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_JOB_TYPE not between", value1, value2, "taskJobType");
            return (Criteria) this;
        }

        public Criteria andMultithreadIsNull() {
            addCriterion("MULTITHREAD is null");
            return (Criteria) this;
        }

        public Criteria andMultithreadIsNotNull() {
            addCriterion("MULTITHREAD is not null");
            return (Criteria) this;
        }

        public Criteria andMultithreadEqualTo(String value) {
            addCriterion("MULTITHREAD =", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadNotEqualTo(String value) {
            addCriterion("MULTITHREAD <>", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadGreaterThan(String value) {
            addCriterion("MULTITHREAD >", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadGreaterThanOrEqualTo(String value) {
            addCriterion("MULTITHREAD >=", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadLessThan(String value) {
            addCriterion("MULTITHREAD <", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadLessThanOrEqualTo(String value) {
            addCriterion("MULTITHREAD <=", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadLike(String value) {
            addCriterion("MULTITHREAD like", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadNotLike(String value) {
            addCriterion("MULTITHREAD not like", value, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadIn(List<String> values) {
            addCriterion("MULTITHREAD in", values, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadNotIn(List<String> values) {
            addCriterion("MULTITHREAD not in", values, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadBetween(String value1, String value2) {
            addCriterion("MULTITHREAD between", value1, value2, "multithread");
            return (Criteria) this;
        }

        public Criteria andMultithreadNotBetween(String value1, String value2) {
            addCriterion("MULTITHREAD not between", value1, value2, "multithread");
            return (Criteria) this;
        }

        public Criteria andPreprocIsNull() {
            addCriterion("PREPROC is null");
            return (Criteria) this;
        }

        public Criteria andPreprocIsNotNull() {
            addCriterion("PREPROC is not null");
            return (Criteria) this;
        }

        public Criteria andPreprocEqualTo(String value) {
            addCriterion("PREPROC =", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocNotEqualTo(String value) {
            addCriterion("PREPROC <>", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocGreaterThan(String value) {
            addCriterion("PREPROC >", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocGreaterThanOrEqualTo(String value) {
            addCriterion("PREPROC >=", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocLessThan(String value) {
            addCriterion("PREPROC <", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocLessThanOrEqualTo(String value) {
            addCriterion("PREPROC <=", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocLike(String value) {
            addCriterion("PREPROC like", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocNotLike(String value) {
            addCriterion("PREPROC not like", value, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocIn(List<String> values) {
            addCriterion("PREPROC in", values, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocNotIn(List<String> values) {
            addCriterion("PREPROC not in", values, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocBetween(String value1, String value2) {
            addCriterion("PREPROC between", value1, value2, "preproc");
            return (Criteria) this;
        }

        public Criteria andPreprocNotBetween(String value1, String value2) {
            addCriterion("PREPROC not between", value1, value2, "preproc");
            return (Criteria) this;
        }

        public Criteria andMainprocIsNull() {
            addCriterion("MAINPROC is null");
            return (Criteria) this;
        }

        public Criteria andMainprocIsNotNull() {
            addCriterion("MAINPROC is not null");
            return (Criteria) this;
        }

        public Criteria andMainprocEqualTo(String value) {
            addCriterion("MAINPROC =", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocNotEqualTo(String value) {
            addCriterion("MAINPROC <>", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocGreaterThan(String value) {
            addCriterion("MAINPROC >", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocGreaterThanOrEqualTo(String value) {
            addCriterion("MAINPROC >=", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocLessThan(String value) {
            addCriterion("MAINPROC <", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocLessThanOrEqualTo(String value) {
            addCriterion("MAINPROC <=", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocLike(String value) {
            addCriterion("MAINPROC like", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocNotLike(String value) {
            addCriterion("MAINPROC not like", value, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocIn(List<String> values) {
            addCriterion("MAINPROC in", values, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocNotIn(List<String> values) {
            addCriterion("MAINPROC not in", values, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocBetween(String value1, String value2) {
            addCriterion("MAINPROC between", value1, value2, "mainproc");
            return (Criteria) this;
        }

        public Criteria andMainprocNotBetween(String value1, String value2) {
            addCriterion("MAINPROC not between", value1, value2, "mainproc");
            return (Criteria) this;
        }

        public Criteria andTailprocIsNull() {
            addCriterion("TAILPROC is null");
            return (Criteria) this;
        }

        public Criteria andTailprocIsNotNull() {
            addCriterion("TAILPROC is not null");
            return (Criteria) this;
        }

        public Criteria andTailprocEqualTo(String value) {
            addCriterion("TAILPROC =", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocNotEqualTo(String value) {
            addCriterion("TAILPROC <>", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocGreaterThan(String value) {
            addCriterion("TAILPROC >", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocGreaterThanOrEqualTo(String value) {
            addCriterion("TAILPROC >=", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocLessThan(String value) {
            addCriterion("TAILPROC <", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocLessThanOrEqualTo(String value) {
            addCriterion("TAILPROC <=", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocLike(String value) {
            addCriterion("TAILPROC like", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocNotLike(String value) {
            addCriterion("TAILPROC not like", value, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocIn(List<String> values) {
            addCriterion("TAILPROC in", values, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocNotIn(List<String> values) {
            addCriterion("TAILPROC not in", values, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocBetween(String value1, String value2) {
            addCriterion("TAILPROC between", value1, value2, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTailprocNotBetween(String value1, String value2) {
            addCriterion("TAILPROC not between", value1, value2, "tailproc");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("TIMEOUT is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("TIMEOUT is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(Integer value) {
            addCriterion("TIMEOUT =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(Integer value) {
            addCriterion("TIMEOUT <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(Integer value) {
            addCriterion("TIMEOUT >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIMEOUT >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(Integer value) {
            addCriterion("TIMEOUT <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("TIMEOUT <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<Integer> values) {
            addCriterion("TIMEOUT in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<Integer> values) {
            addCriterion("TIMEOUT not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("TIMEOUT between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("TIMEOUT not between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNull() {
            addCriterion("THREAD_NUM is null");
            return (Criteria) this;
        }

        public Criteria andThreadNumIsNotNull() {
            addCriterion("THREAD_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andThreadNumEqualTo(Integer value) {
            addCriterion("THREAD_NUM =", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotEqualTo(Integer value) {
            addCriterion("THREAD_NUM <>", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThan(Integer value) {
            addCriterion("THREAD_NUM >", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("THREAD_NUM >=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThan(Integer value) {
            addCriterion("THREAD_NUM <", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumLessThanOrEqualTo(Integer value) {
            addCriterion("THREAD_NUM <=", value, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumIn(List<Integer> values) {
            addCriterion("THREAD_NUM in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotIn(List<Integer> values) {
            addCriterion("THREAD_NUM not in", values, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_NUM between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_NUM not between", value1, value2, "threadNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumIsNull() {
            addCriterion("THREAD_PAR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andThreadParNumIsNotNull() {
            addCriterion("THREAD_PAR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andThreadParNumEqualTo(Integer value) {
            addCriterion("THREAD_PAR_NUM =", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumNotEqualTo(Integer value) {
            addCriterion("THREAD_PAR_NUM <>", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumGreaterThan(Integer value) {
            addCriterion("THREAD_PAR_NUM >", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("THREAD_PAR_NUM >=", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumLessThan(Integer value) {
            addCriterion("THREAD_PAR_NUM <", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumLessThanOrEqualTo(Integer value) {
            addCriterion("THREAD_PAR_NUM <=", value, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumIn(List<Integer> values) {
            addCriterion("THREAD_PAR_NUM in", values, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumNotIn(List<Integer> values) {
            addCriterion("THREAD_PAR_NUM not in", values, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_PAR_NUM between", value1, value2, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andThreadParNumNotBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_PAR_NUM not between", value1, value2, "threadParNum");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andExtParaIsNull() {
            addCriterion("EXT_PARA is null");
            return (Criteria) this;
        }

        public Criteria andExtParaIsNotNull() {
            addCriterion("EXT_PARA is not null");
            return (Criteria) this;
        }

        public Criteria andExtParaEqualTo(String value) {
            addCriterion("EXT_PARA =", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaNotEqualTo(String value) {
            addCriterion("EXT_PARA <>", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaGreaterThan(String value) {
            addCriterion("EXT_PARA >", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_PARA >=", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaLessThan(String value) {
            addCriterion("EXT_PARA <", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaLessThanOrEqualTo(String value) {
            addCriterion("EXT_PARA <=", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaLike(String value) {
            addCriterion("EXT_PARA like", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaNotLike(String value) {
            addCriterion("EXT_PARA not like", value, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaIn(List<String> values) {
            addCriterion("EXT_PARA in", values, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaNotIn(List<String> values) {
            addCriterion("EXT_PARA not in", values, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaBetween(String value1, String value2) {
            addCriterion("EXT_PARA between", value1, value2, "extPara");
            return (Criteria) this;
        }

        public Criteria andExtParaNotBetween(String value1, String value2) {
            addCriterion("EXT_PARA not between", value1, value2, "extPara");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameIsNull() {
            addCriterion("TASK_BEAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameIsNotNull() {
            addCriterion("TASK_BEAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameEqualTo(String value) {
            addCriterion("TASK_BEAN_NAME =", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameNotEqualTo(String value) {
            addCriterion("TASK_BEAN_NAME <>", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameGreaterThan(String value) {
            addCriterion("TASK_BEAN_NAME >", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_BEAN_NAME >=", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameLessThan(String value) {
            addCriterion("TASK_BEAN_NAME <", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_BEAN_NAME <=", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameLike(String value) {
            addCriterion("TASK_BEAN_NAME like", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameNotLike(String value) {
            addCriterion("TASK_BEAN_NAME not like", value, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameIn(List<String> values) {
            addCriterion("TASK_BEAN_NAME in", values, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameNotIn(List<String> values) {
            addCriterion("TASK_BEAN_NAME not in", values, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameBetween(String value1, String value2) {
            addCriterion("TASK_BEAN_NAME between", value1, value2, "taskBeanName");
            return (Criteria) this;
        }

        public Criteria andTaskBeanNameNotBetween(String value1, String value2) {
            addCriterion("TASK_BEAN_NAME not between", value1, value2, "taskBeanName");
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