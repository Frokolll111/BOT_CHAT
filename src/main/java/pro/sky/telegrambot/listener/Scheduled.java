package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;

import java.util.List;


@Component
public class Scheduled {
    @Autowired
    private NotificationTaskRepository notificationTaskRepository;
    @Autowired
    private TelegramBot bot;

    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0/1 * * * *")
    public void runTask() {

        List<NotificationTask> tasks = notificationTaskRepository.findAllByLocalDateTimeLessThan(LocalDateTime.now());

        for (NotificationTask task : tasks) {
            bot.execute(new SendMessage(task.getChatId(), task.getLocalDateTime() + ": " + task.getMessageText()));
        }

        //     return  notificationTaskRepository.findAll().stream()
        //              .filter(f->f.getLocalDateTime().equals(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)))
        //             .collect(Collectors.toList());
    }
}