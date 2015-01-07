package io.github.seleniumquery.by.xpath.component;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.filter.ElementFilterList;
import io.github.seleniumquery.by.xpath.CssCombinationType;

import java.util.List;

public class ConditionalAppliedToAllComponent extends XPathComponent {

    public ConditionalAppliedToAllComponent(String xPathExpression) {
        super(xPathExpression, ComponentUtils.toElementFilterList(ElementFilter.FILTER_NOTHING), CssCombinationType.CONDITIONAL_TO_ALL);
    }

    ConditionalAppliedToAllComponent(String xPathExpression, List<XPathComponent> combinatedComponents, ElementFilterList elementFilterList) {
        super(xPathExpression, combinatedComponents, elementFilterList, CssCombinationType.CONDITIONAL_TO_ALL);
    }

    @Override
    public String mergeExpression(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_TO_ALL.merge(sourceXPathExpression, null, this.xPathExpression);
    }

    @Override
    public String mergeExpressionAsCondition(String sourceXPathExpression) {
        return CssCombinationType.CONDITIONAL_TO_ALL.mergeAsCondition(sourceXPathExpression, null, this.xPathExpression);
    }

}