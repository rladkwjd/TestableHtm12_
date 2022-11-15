package com.cleancode.fitnesse.testablehtml;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class TestableHtml {

    public static final String INCLUDE_SETUP = "!include -setup .";
    public static final String INCLUDE_TEARDOWN = "!include -teardown .";

    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBuffer Pagebuffer = new StringBuffer();

        if (pageData.hasAttribute("Test")) {
            if (includeSuiteSetup) {
                WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage);
                if (suiteSetup != null) {
                    renderextracted(wikiPage, suiteSetup, Pagebuffer, INCLUDE_SETUP);
                }
            }
            WikiPage setup = PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
            if (setup != null) {
                renderextracted(wikiPage, setup, Pagebuffer, INCLUDE_SETUP);
            }
        }

        Pagebuffer.append(pageData.getContent());
        if (pageData.hasAttribute("Test")) {
            WikiPage teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
            if (teardown != null) {
                renderextracted(wikiPage, teardown, Pagebuffer, INCLUDE_TEARDOWN);
            }
            if (includeSuiteSetup) {
                WikiPage suiteTeardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage);
                if (suiteTeardown != null) {
                    renderextracted(wikiPage, suiteTeardown, Pagebuffer, INCLUDE_TEARDOWN);
                }
            }
        }

        pageData.setContent(Pagebuffer.toString());
        return pageData.getHtml();
    }

    private static void renderextracted(WikiPage wikiPage, WikiPage suiteSetup, StringBuffer Pagebuffer, String str) throws Exception {
        WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(suiteSetup);
        String pagePathName = PathParser.render(pagePath);
        Pagebuffer.append(str).append(pagePathName).append("\n");
    }
}