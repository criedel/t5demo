package com.cupsoftware.carsharing.pages;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.apache.tapestry5.test.TapestryTestCase;
import org.testng.annotations.Test;


public class IndexPageTest extends TapestryTestCase {

    @Test
    public void render_headline() {

        final PageTester pageTester = new PageTester("com.cupsoftware.carsharing", "carsharing");

        final Document document = pageTester.renderPage("Index");

        assertEquals(document.find("html/body/div/div/h1").getChildMarkup(), "Please rent a car.");

        assertEquals(document.getElementById("headline").getChildMarkup(), "Please rent a car.");
    }
}
