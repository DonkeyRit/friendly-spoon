package com.github.telegrambotstepfather.botinteractions.extensions;

import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

import java.util.Arrays;
import java.util.List;

public class PageExtensions {

    private static final double DEFAULT_TIMEOUT = 15000;

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

    public static ElementHandle waitForSelectorsAsync(Page page, String... selectors) {

        List<CompletableFuture<ElementHandle>> futures = Arrays.stream(selectors)
                .map(selector -> waitForSelectorAsync(page, selector, DEFAULT_TIMEOUT))
                .toList();

        try {
            return (ElementHandle) CompletableFuture.anyOf(futures.toArray(CompletableFuture[]::new)).get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        throw new RuntimeException(String.format("Element not found with either selector - [%s]", String.join(" ", selectors)));
    }

    private static CompletableFuture<ElementHandle> waitForSelectorAsync(Page page, String selector, double timeout) {
        return CompletableFuture
                .supplyAsync(
                        () -> page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeout)));
    }
}
