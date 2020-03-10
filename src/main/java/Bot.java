import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if(message != null && message.hasText()){
             switch (message.getText()){
                 case "/Hi":
                     sendMsg(message, "Hello, my friend!");
                     break;
                 case "/Help":
                     sendMsg(message, "This is my first test bot, which uses weather api. You can write any city in English or Russian, and the bot will give you the current weather in this city");
                     break;
                 case "/Dev":
                     sendMsg(message, "Developed by: Aidyn Mamyrbayev, 2020");
                     break;
                 default:
                     try {
                         sendMsg(message, Weather.getWeather(message.getText(), model));
                     } catch (IOException e) {
                         sendMsg(message, "The city not found!");
                     }

             }
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add(new KeyboardButton("/Hi"));
        keyboardButtons.add(new KeyboardButton("/Help"));
        keyboardButtons.add(new KeyboardButton("/Dev"));
        keyboardRows.add(keyboardButtons);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    public String getBotUsername() {
        return "myTestRoot_bot";
    }

    public String getBotToken() {
        return "953380737:AAGmAlMipwAOgf1oJSSL7eAOjqeEBh-GetA";
    }
}
