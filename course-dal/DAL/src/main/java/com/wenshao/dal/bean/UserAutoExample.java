package com.wenshao.dal.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAutoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAutoExample() {
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

        public Criteria andToIdIsNull() {
            addCriterion("to_id is null");
            return (Criteria) this;
        }

        public Criteria andToIdIsNotNull() {
            addCriterion("to_id is not null");
            return (Criteria) this;
        }

        public Criteria andToIdEqualTo(Integer value) {
            addCriterion("to_id =", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotEqualTo(Integer value) {
            addCriterion("to_id <>", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThan(Integer value) {
            addCriterion("to_id >", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_id >=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThan(Integer value) {
            addCriterion("to_id <", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_id <=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdIn(List<Integer> values) {
            addCriterion("to_id in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotIn(List<Integer> values) {
            addCriterion("to_id not in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdBetween(Integer value1, Integer value2) {
            addCriterion("to_id between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_id not between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToContextIsNull() {
            addCriterion("to_context is null");
            return (Criteria) this;
        }

        public Criteria andToContextIsNotNull() {
            addCriterion("to_context is not null");
            return (Criteria) this;
        }

        public Criteria andToContextEqualTo(String value) {
            addCriterion("to_context =", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextNotEqualTo(String value) {
            addCriterion("to_context <>", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextGreaterThan(String value) {
            addCriterion("to_context >", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextGreaterThanOrEqualTo(String value) {
            addCriterion("to_context >=", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextLessThan(String value) {
            addCriterion("to_context <", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextLessThanOrEqualTo(String value) {
            addCriterion("to_context <=", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextLike(String value) {
            addCriterion("to_context like", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextNotLike(String value) {
            addCriterion("to_context not like", value, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextIn(List<String> values) {
            addCriterion("to_context in", values, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextNotIn(List<String> values) {
            addCriterion("to_context not in", values, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextBetween(String value1, String value2) {
            addCriterion("to_context between", value1, value2, "toContext");
            return (Criteria) this;
        }

        public Criteria andToContextNotBetween(String value1, String value2) {
            addCriterion("to_context not between", value1, value2, "toContext");
            return (Criteria) this;
        }

        public Criteria andToTopicIdIsNull() {
            addCriterion("to_topic_id is null");
            return (Criteria) this;
        }

        public Criteria andToTopicIdIsNotNull() {
            addCriterion("to_topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andToTopicIdEqualTo(Integer value) {
            addCriterion("to_topic_id =", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdNotEqualTo(Integer value) {
            addCriterion("to_topic_id <>", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdGreaterThan(Integer value) {
            addCriterion("to_topic_id >", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_topic_id >=", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdLessThan(Integer value) {
            addCriterion("to_topic_id <", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_topic_id <=", value, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdIn(List<Integer> values) {
            addCriterion("to_topic_id in", values, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdNotIn(List<Integer> values) {
            addCriterion("to_topic_id not in", values, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("to_topic_id between", value1, value2, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_topic_id not between", value1, value2, "toTopicId");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeIsNull() {
            addCriterion("to_create_time is null");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeIsNotNull() {
            addCriterion("to_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeEqualTo(Date value) {
            addCriterion("to_create_time =", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeNotEqualTo(Date value) {
            addCriterion("to_create_time <>", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeGreaterThan(Date value) {
            addCriterion("to_create_time >", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("to_create_time >=", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeLessThan(Date value) {
            addCriterion("to_create_time <", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("to_create_time <=", value, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeIn(List<Date> values) {
            addCriterion("to_create_time in", values, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeNotIn(List<Date> values) {
            addCriterion("to_create_time not in", values, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeBetween(Date value1, Date value2) {
            addCriterion("to_create_time between", value1, value2, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("to_create_time not between", value1, value2, "toCreateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeIsNull() {
            addCriterion("to_update_time is null");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeIsNotNull() {
            addCriterion("to_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeEqualTo(Date value) {
            addCriterion("to_update_time =", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeNotEqualTo(Date value) {
            addCriterion("to_update_time <>", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeGreaterThan(Date value) {
            addCriterion("to_update_time >", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("to_update_time >=", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeLessThan(Date value) {
            addCriterion("to_update_time <", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("to_update_time <=", value, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeIn(List<Date> values) {
            addCriterion("to_update_time in", values, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeNotIn(List<Date> values) {
            addCriterion("to_update_time not in", values, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("to_update_time between", value1, value2, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("to_update_time not between", value1, value2, "toUpdateTime");
            return (Criteria) this;
        }

        public Criteria andToContextLikeInsensitive(String value) {
            addCriterion("upper(to_context) like", value.toUpperCase(), "toContext");
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