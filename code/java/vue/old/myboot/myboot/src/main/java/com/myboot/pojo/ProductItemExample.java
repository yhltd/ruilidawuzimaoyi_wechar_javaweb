package com.myboot.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProductItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProductItemExample() {
        oredCriteria = new ArrayList<>();
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andGuigeIsNull() {
            addCriterion("guige is null");
            return (Criteria) this;
        }

        public Criteria andGuigeIsNotNull() {
            addCriterion("guige is not null");
            return (Criteria) this;
        }

        public Criteria andGuigeEqualTo(String value) {
            addCriterion("guige =", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeNotEqualTo(String value) {
            addCriterion("guige <>", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeGreaterThan(String value) {
            addCriterion("guige >", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeGreaterThanOrEqualTo(String value) {
            addCriterion("guige >=", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeLessThan(String value) {
            addCriterion("guige <", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeLessThanOrEqualTo(String value) {
            addCriterion("guige <=", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeLike(String value) {
            addCriterion("guige like", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeNotLike(String value) {
            addCriterion("guige not like", value, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeIn(List<String> values) {
            addCriterion("guige in", values, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeNotIn(List<String> values) {
            addCriterion("guige not in", values, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeBetween(String value1, String value2) {
            addCriterion("guige between", value1, value2, "guige");
            return (Criteria) this;
        }

        public Criteria andGuigeNotBetween(String value1, String value2) {
            addCriterion("guige not between", value1, value2, "guige");
            return (Criteria) this;
        }

        public Criteria andBianhaoIsNull() {
            addCriterion("bianhao is null");
            return (Criteria) this;
        }

        public Criteria andBianhaoIsNotNull() {
            addCriterion("bianhao is not null");
            return (Criteria) this;
        }

        public Criteria andBianhaoEqualTo(String value) {
            addCriterion("bianhao =", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotEqualTo(String value) {
            addCriterion("bianhao <>", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThan(String value) {
            addCriterion("bianhao >", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("bianhao >=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThan(String value) {
            addCriterion("bianhao <", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThanOrEqualTo(String value) {
            addCriterion("bianhao <=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLike(String value) {
            addCriterion("bianhao like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotLike(String value) {
            addCriterion("bianhao not like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoIn(List<String> values) {
            addCriterion("bianhao in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotIn(List<String> values) {
            addCriterion("bianhao not in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoBetween(String value1, String value2) {
            addCriterion("bianhao between", value1, value2, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotBetween(String value1, String value2) {
            addCriterion("bianhao not between", value1, value2, "bianhao");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceIsNull() {
            addCriterion("lingshou_price is null");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceIsNotNull() {
            addCriterion("lingshou_price is not null");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceEqualTo(String value) {
            addCriterion("lingshou_price =", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceNotEqualTo(String value) {
            addCriterion("lingshou_price <>", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceGreaterThan(String value) {
            addCriterion("lingshou_price >", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceGreaterThanOrEqualTo(String value) {
            addCriterion("lingshou_price >=", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceLessThan(String value) {
            addCriterion("lingshou_price <", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceLessThanOrEqualTo(String value) {
            addCriterion("lingshou_price <=", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceLike(String value) {
            addCriterion("lingshou_price like", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceNotLike(String value) {
            addCriterion("lingshou_price not like", value, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceIn(List<String> values) {
            addCriterion("lingshou_price in", values, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceNotIn(List<String> values) {
            addCriterion("lingshou_price not in", values, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceBetween(String value1, String value2) {
            addCriterion("lingshou_price between", value1, value2, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouPriceNotBetween(String value1, String value2) {
            addCriterion("lingshou_price not between", value1, value2, "lingshouPrice");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliIsNull() {
            addCriterion("lingshou_bili is null");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliIsNotNull() {
            addCriterion("lingshou_bili is not null");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliEqualTo(String value) {
            addCriterion("lingshou_bili =", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliNotEqualTo(String value) {
            addCriterion("lingshou_bili <>", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliGreaterThan(String value) {
            addCriterion("lingshou_bili >", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliGreaterThanOrEqualTo(String value) {
            addCriterion("lingshou_bili >=", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliLessThan(String value) {
            addCriterion("lingshou_bili <", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliLessThanOrEqualTo(String value) {
            addCriterion("lingshou_bili <=", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliLike(String value) {
            addCriterion("lingshou_bili like", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliNotLike(String value) {
            addCriterion("lingshou_bili not like", value, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliIn(List<String> values) {
            addCriterion("lingshou_bili in", values, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliNotIn(List<String> values) {
            addCriterion("lingshou_bili not in", values, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliBetween(String value1, String value2) {
            addCriterion("lingshou_bili between", value1, value2, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andLingshouBiliNotBetween(String value1, String value2) {
            addCriterion("lingshou_bili not between", value1, value2, "lingshouBili");
            return (Criteria) this;
        }

        public Criteria andPifaPriceIsNull() {
            addCriterion("pifa_price is null");
            return (Criteria) this;
        }

        public Criteria andPifaPriceIsNotNull() {
            addCriterion("pifa_price is not null");
            return (Criteria) this;
        }

        public Criteria andPifaPriceEqualTo(String value) {
            addCriterion("pifa_price =", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceNotEqualTo(String value) {
            addCriterion("pifa_price <>", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceGreaterThan(String value) {
            addCriterion("pifa_price >", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceGreaterThanOrEqualTo(String value) {
            addCriterion("pifa_price >=", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceLessThan(String value) {
            addCriterion("pifa_price <", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceLessThanOrEqualTo(String value) {
            addCriterion("pifa_price <=", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceLike(String value) {
            addCriterion("pifa_price like", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceNotLike(String value) {
            addCriterion("pifa_price not like", value, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceIn(List<String> values) {
            addCriterion("pifa_price in", values, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceNotIn(List<String> values) {
            addCriterion("pifa_price not in", values, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceBetween(String value1, String value2) {
            addCriterion("pifa_price between", value1, value2, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaPriceNotBetween(String value1, String value2) {
            addCriterion("pifa_price not between", value1, value2, "pifaPrice");
            return (Criteria) this;
        }

        public Criteria andPifaBiliIsNull() {
            addCriterion("pifa_bili is null");
            return (Criteria) this;
        }

        public Criteria andPifaBiliIsNotNull() {
            addCriterion("pifa_bili is not null");
            return (Criteria) this;
        }

        public Criteria andPifaBiliEqualTo(String value) {
            addCriterion("pifa_bili =", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliNotEqualTo(String value) {
            addCriterion("pifa_bili <>", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliGreaterThan(String value) {
            addCriterion("pifa_bili >", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliGreaterThanOrEqualTo(String value) {
            addCriterion("pifa_bili >=", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliLessThan(String value) {
            addCriterion("pifa_bili <", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliLessThanOrEqualTo(String value) {
            addCriterion("pifa_bili <=", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliLike(String value) {
            addCriterion("pifa_bili like", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliNotLike(String value) {
            addCriterion("pifa_bili not like", value, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliIn(List<String> values) {
            addCriterion("pifa_bili in", values, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliNotIn(List<String> values) {
            addCriterion("pifa_bili not in", values, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliBetween(String value1, String value2) {
            addCriterion("pifa_bili between", value1, value2, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andPifaBiliNotBetween(String value1, String value2) {
            addCriterion("pifa_bili not between", value1, value2, "pifaBili");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceIsNull() {
            addCriterion("dakehu_price is null");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceIsNotNull() {
            addCriterion("dakehu_price is not null");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceEqualTo(String value) {
            addCriterion("dakehu_price =", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceNotEqualTo(String value) {
            addCriterion("dakehu_price <>", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceGreaterThan(String value) {
            addCriterion("dakehu_price >", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceGreaterThanOrEqualTo(String value) {
            addCriterion("dakehu_price >=", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceLessThan(String value) {
            addCriterion("dakehu_price <", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceLessThanOrEqualTo(String value) {
            addCriterion("dakehu_price <=", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceLike(String value) {
            addCriterion("dakehu_price like", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceNotLike(String value) {
            addCriterion("dakehu_price not like", value, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceIn(List<String> values) {
            addCriterion("dakehu_price in", values, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceNotIn(List<String> values) {
            addCriterion("dakehu_price not in", values, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceBetween(String value1, String value2) {
            addCriterion("dakehu_price between", value1, value2, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuPriceNotBetween(String value1, String value2) {
            addCriterion("dakehu_price not between", value1, value2, "dakehuPrice");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliIsNull() {
            addCriterion("dakehu_bili is null");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliIsNotNull() {
            addCriterion("dakehu_bili is not null");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliEqualTo(String value) {
            addCriterion("dakehu_bili =", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliNotEqualTo(String value) {
            addCriterion("dakehu_bili <>", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliGreaterThan(String value) {
            addCriterion("dakehu_bili >", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliGreaterThanOrEqualTo(String value) {
            addCriterion("dakehu_bili >=", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliLessThan(String value) {
            addCriterion("dakehu_bili <", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliLessThanOrEqualTo(String value) {
            addCriterion("dakehu_bili <=", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliLike(String value) {
            addCriterion("dakehu_bili like", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliNotLike(String value) {
            addCriterion("dakehu_bili not like", value, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliIn(List<String> values) {
            addCriterion("dakehu_bili in", values, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliNotIn(List<String> values) {
            addCriterion("dakehu_bili not in", values, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliBetween(String value1, String value2) {
            addCriterion("dakehu_bili between", value1, value2, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andDakehuBiliNotBetween(String value1, String value2) {
            addCriterion("dakehu_bili not between", value1, value2, "dakehuBili");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceIsNull() {
            addCriterion("caigou_price is null");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceIsNotNull() {
            addCriterion("caigou_price is not null");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceEqualTo(String value) {
            addCriterion("caigou_price =", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceNotEqualTo(String value) {
            addCriterion("caigou_price <>", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceGreaterThan(String value) {
            addCriterion("caigou_price >", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceGreaterThanOrEqualTo(String value) {
            addCriterion("caigou_price >=", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceLessThan(String value) {
            addCriterion("caigou_price <", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceLessThanOrEqualTo(String value) {
            addCriterion("caigou_price <=", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceLike(String value) {
            addCriterion("caigou_price like", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceNotLike(String value) {
            addCriterion("caigou_price not like", value, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceIn(List<String> values) {
            addCriterion("caigou_price in", values, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceNotIn(List<String> values) {
            addCriterion("caigou_price not in", values, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceBetween(String value1, String value2) {
            addCriterion("caigou_price between", value1, value2, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andCaigouPriceNotBetween(String value1, String value2) {
            addCriterion("caigou_price not between", value1, value2, "caigouPrice");
            return (Criteria) this;
        }

        public Criteria andJinxiangIsNull() {
            addCriterion("jinxiang is null");
            return (Criteria) this;
        }

        public Criteria andJinxiangIsNotNull() {
            addCriterion("jinxiang is not null");
            return (Criteria) this;
        }

        public Criteria andJinxiangEqualTo(String value) {
            addCriterion("jinxiang =", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangNotEqualTo(String value) {
            addCriterion("jinxiang <>", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangGreaterThan(String value) {
            addCriterion("jinxiang >", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangGreaterThanOrEqualTo(String value) {
            addCriterion("jinxiang >=", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangLessThan(String value) {
            addCriterion("jinxiang <", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangLessThanOrEqualTo(String value) {
            addCriterion("jinxiang <=", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangLike(String value) {
            addCriterion("jinxiang like", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangNotLike(String value) {
            addCriterion("jinxiang not like", value, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangIn(List<String> values) {
            addCriterion("jinxiang in", values, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangNotIn(List<String> values) {
            addCriterion("jinxiang not in", values, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangBetween(String value1, String value2) {
            addCriterion("jinxiang between", value1, value2, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andJinxiangNotBetween(String value1, String value2) {
            addCriterion("jinxiang not between", value1, value2, "jinxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangIsNull() {
            addCriterion("xiaoxiang is null");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangIsNotNull() {
            addCriterion("xiaoxiang is not null");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangEqualTo(String value) {
            addCriterion("xiaoxiang =", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangNotEqualTo(String value) {
            addCriterion("xiaoxiang <>", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangGreaterThan(String value) {
            addCriterion("xiaoxiang >", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangGreaterThanOrEqualTo(String value) {
            addCriterion("xiaoxiang >=", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangLessThan(String value) {
            addCriterion("xiaoxiang <", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangLessThanOrEqualTo(String value) {
            addCriterion("xiaoxiang <=", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangLike(String value) {
            addCriterion("xiaoxiang like", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangNotLike(String value) {
            addCriterion("xiaoxiang not like", value, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangIn(List<String> values) {
            addCriterion("xiaoxiang in", values, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangNotIn(List<String> values) {
            addCriterion("xiaoxiang not in", values, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangBetween(String value1, String value2) {
            addCriterion("xiaoxiang between", value1, value2, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andXiaoxiangNotBetween(String value1, String value2) {
            addCriterion("xiaoxiang not between", value1, value2, "xiaoxiang");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("`enable` is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("`enable` is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(String value) {
            addCriterion("`enable` =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(String value) {
            addCriterion("`enable` <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(String value) {
            addCriterion("`enable` >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(String value) {
            addCriterion("`enable` >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(String value) {
            addCriterion("`enable` <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(String value) {
            addCriterion("`enable` <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLike(String value) {
            addCriterion("`enable` like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotLike(String value) {
            addCriterion("`enable` not like", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<String> values) {
            addCriterion("`enable` in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<String> values) {
            addCriterion("`enable` not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(String value1, String value2) {
            addCriterion("`enable` between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(String value1, String value2) {
            addCriterion("`enable` not between", value1, value2, "enable");
            return (Criteria) this;
        }
    }

    /**
     */
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