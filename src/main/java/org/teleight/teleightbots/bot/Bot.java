package org.teleight.teleightbots.bot;

import org.jetbrains.annotations.NotNull;
import org.teleight.teleightbots.TeleightBots;
import org.teleight.teleightbots.api.ApiMethod;
import org.teleight.teleightbots.api.pagination.*;
import org.teleight.teleightbots.bot.trait.TelegramBot;
import org.teleight.teleightbots.event.EventManager;
import org.teleight.teleightbots.event.EventManagerImpl;
import org.teleight.teleightbots.event.bot.MethodSendEvent;
import org.teleight.teleightbots.exception.exceptions.TelegramRequestException;
import org.teleight.teleightbots.scheduler.Scheduler;
import org.teleight.teleightbots.updateprocessor.UpdateProcessor;

import java.util.concurrent.CompletableFuture;

public class Bot implements TelegramBot {

    private final String token;
    private final String username;

    //Settings
    private final BotSettings botSettings;

    //Updates
    private final UpdateProcessor updateProcessor;

    //Scheduler
    private final Scheduler scheduler = Scheduler.newScheduler();

    //Events
    private final EventManager eventManager = new EventManagerImpl();

    //Pagination
    private final PaginationManager paginationManager = new PaginationManagerImpl();

    public Bot(String token, String username, UpdateProcessor updateProcessor, BotSettings botSettings) {
        this.token = token;
        this.username = username;
        this.botSettings = botSettings;
        this.updateProcessor = updateProcessor;
    }

    @Override
    public @NotNull String getBotToken() {
        return token;
    }

    @Override
    public @NotNull String getBotUsername() {
        return username;
    }

    @Override
    public @NotNull Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public @NotNull UpdateProcessor getUpdateProcessor() {
        return updateProcessor;
    }

    @Override
    public @NotNull BotSettings getBotSettings() {
        return botSettings;
    }

    @Override
    public @NotNull EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public @NotNull PaginationManager getPaginationManager() {
        return paginationManager;
    }

    @Override
    public @NotNull Menu createMenu(Menu.@NotNull Builder builder) {
        final MenuBuilder.MenuBuilderImpl menuBuilder = new MenuBuilder.MenuBuilderImpl();
        final Menu rootMenu = menuBuilder.createMenu("root");
        builder.create(menuBuilder, rootMenu);

        for (final MenuImpl subMenu : menuBuilder.getAllMenus()) {
            subMenu.createKeyboard();

            paginationManager.registerMenu(subMenu);
        }

        return rootMenu;
    }

    @Override
    public void connect() {
        updateProcessor.start();
    }

    public void close() {
        try {
            scheduler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            updateProcessor.terminate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <R> @NotNull CompletableFuture<R> execute(@NotNull ApiMethod<R> method) {
        final CompletableFuture<String> responseFuture = updateProcessor.executeMethod(method);
        return responseFuture.thenApplyAsync(responseJson -> {
            final R result;
            try {
                result = method.deserializeResponse(responseJson);
            } catch (Exception e) {
                TeleightBots.getExceptionManager().handleException(e);
                throw new TelegramRequestException(e);
            }
            eventManager.call(new MethodSendEvent<>(Bot.this, method, result));
            return result;
        });
    }

}
