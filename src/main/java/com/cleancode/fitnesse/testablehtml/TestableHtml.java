package com.cleancode.fitnesse.testablehtml;

import fitnesse.wiki.*;

public class TestableHtml {
    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        boolean isTest = pageData.hasAttribute("Test");
        if(isTest){
            WikiPage testPage = pageData.getWikiPage();
            StringBuffer newPageContent = new StringBuffer();
            includeSetup(testPage, newPageContent, includeSuiteSetup);
            newPageContent.append(pageData.getContent());
            includeTeardown(testPage, newPageContent, includeSuiteSetup);
            pageData.setContent(newPageContent.toString());
        }
        return pageData.getHtml();
    }

    private void includeTeardown(WikiPage testPage, StringBuffer newPageContent, boolean includeSuiteSetup) {
    }
    private void includeSetup(WikiPage testPage, StringBuffer newPageContent, boolean includeSuiteSetup) {
    }
}