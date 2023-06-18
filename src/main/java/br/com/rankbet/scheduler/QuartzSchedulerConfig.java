package br.com.rankbet.scheduler;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@Singleton
@Named
@FacesConfig
public class QuartzSchedulerConfig {

    @PostConstruct
    public void initialize(String[] args) {
        SchedulerFactory shedFact = new StdSchedulerFactory();
        try {
            Scheduler scheduler = shedFact.getScheduler();
            scheduler.start();

            JobDetail job = JobBuilder.newJob(FinalizeExpiredAccountPlansJob.class)
                    .withIdentity("finalizeExpiredAccountPlansJob", "grupo01")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("finalizeExpiredAccountPlansTRIGGER","grupo01")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                    .build();

            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
