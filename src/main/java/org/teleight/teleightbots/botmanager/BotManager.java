package org.teleight.teleightbots.botmanager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teleight.teleightbots.bot.Bot;
import org.teleight.teleightbots.bot.BotSettings;
import org.teleight.teleightbots.bot.trait.TelegramBot;

import java.util.function.Consumer;

/**
 * Manages the bots and their settings.
 * <p>
 * This interface provides methods to register and manage bots.
 * </p>
 */
public sealed interface BotManager permits BotManagerImpl {

    /**
     * Registers a new bot with the given token and username.
     * <p>
     * This method registers a new bot with the given token and username.
     * The bot will be initialized with the default settings
     * </p>
     *
     * @param token            the bot's token
     * @param username         the bot's username
     * @param completeCallback the callback to be called when the bot has been registered
     */
    default void registerLongPolling(@NotNull String token, @NotNull String username, @Nullable Consumer<TelegramBot> completeCallback) {
        registerLongPolling(token, username, BotSettings.DEFAULT, completeCallback);
    }

    /**
     * Registers a new bot with the given token and username.
     * <p>
     * This method registers a new bot with the given token and username.
     * The bot will be initialized with custom settings provided by the given {@link BotSettings} object.
     * </p>
     *
     * @param token            the bot's token
     * @param username         the bot's username
     * @param botSettings      the bot's settings
     * @param completeCallback the callback to be called when the bot has been registered
     */
    void registerLongPolling(@NotNull String token, @NotNull String username, @Nullable BotSettings botSettings, @Nullable Consumer<TelegramBot> completeCallback);

    /**
     * Registers a new bot with the given token and username.
     * <p>
     * This method registers a new bot with the given token and username.
     * The bot will be initialized with custom settings provided by the given {@link BotSettings} object.
     * </p>
     *
     * @param token      the bot's token
     * @param username   the bot's username
     * @param botSettings the bot's settings
     */
    void registerLongPolling(@NotNull String token, @NotNull String username, @Nullable BotSettings botSettings);

    /**
     * Returns the bot provider.
     * @return the bot provider
     */
    BotProvider getBotProvider();

    /**
     * Sets the bot provider.
     * @param botProvider the bot provider
     */
    void setBotProvider(@NotNull BotProvider botProvider);

    /**
     * Returns the currently registered bots.
     * @return the currently registered bots
     */
    Bot[] getBots();

}
