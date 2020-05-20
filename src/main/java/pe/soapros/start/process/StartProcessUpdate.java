package pe.soapros.start.process;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.services.impl.UpdatingImpl;


public class StartProcessUpdate {
	private static final Log log = Log.getInstance(StartProcessUpdate.class);
	
	
	
	public static void main(String... args) {
		log.debug("Se inicia la carga del historico de Enotria");
		
		final String[] batchJobConfig = { "/spring/batch/config/context.xml", "/spring/batch/jobs/job-report.xml" };
		final ApplicationContext context = new ClassPathXmlApplicationContext(batchJobConfig);
		
		try {
			final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			
			final Job job = (Job) context.getBean("ingestaUpdateJob");
			
			final Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			
			final JobExecution jobExecution = jobLauncher.run(job, new JobParameters(parameters));
			
			log.info("Exit Status: " + jobExecution.getStatus());
			
			
		} catch (Exception e) {
			log.error("Error during execution batch", e);
			log.info("Exit code: ");
		} finally {
			// fecha recursos
			if (context != null) {
				((ConfigurableApplicationContext) context).close();
			}

			System.exit(0);
		}

		
	}
	
}
