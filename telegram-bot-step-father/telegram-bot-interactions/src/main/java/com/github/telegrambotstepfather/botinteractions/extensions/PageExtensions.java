package com.github.telegrambotstepfather.botinteractions.extensions;

import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

public class PageExtensions {

    private static final double DEFAULT_TIMEOUT = 10000;

    public static ElementHandle waitForElement(Page page, String selector, String alternativeSelector)
            throws PlaywrightException {
        try {
            // TODO: Add logging
            return page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(DEFAULT_TIMEOUT));
        } catch (PlaywrightException ex) {
            // TODO: Add logging
            return page.waitForSelector(alternativeSelector,
                    new Page.WaitForSelectorOptions().setTimeout(DEFAULT_TIMEOUT));
        }
    }

    public static ElementHandle waitForSelectorsAsync(Page page, String selector1, String selector2) {

        CompletableFuture<ElementHandle> combinedFuture = CompletableFuture.anyOf(
                waitForSelectorAsync(page, selector1, DEFAULT_TIMEOUT),
                waitForSelectorAsync(page, selector2, DEFAULT_TIMEOUT))
            .thenApply(result -> (ElementHandle) result);

        try {
            ElementHandle locatedElement = combinedFuture.get(); // Wait for the first non-null selector or timeout
            if (locatedElement != null) {
                return locatedElement;
            } else {
                throw new RuntimeException("Element not found with either selector");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static CompletableFuture<ElementHandle> waitForSelectorAsync(Page page, String selector, double timeout) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout));
            } catch (PlaywrightException e) {
                return null;
            }
        });
    }
}
