package pe.soapros.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import pe.soapros.exception.ExecutionBatchException;
import pe.soapros.log.Log;
import pe.soapros.services.impl.UpdatingImpl;

public class UpdateTasklet implements Tasklet{

	
	private static final Log log = Log.getInstance(UpdateTasklet.class);
	
	@Autowired
	private UpdatingImpl updatingImpl;
	
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.debug("EXECUTE: " + chunkContext.getStepContext().getJobName());
		
		try {
			this.updatingImpl.createParallelProcess(chunkContext.getStepContext().getJobParameters());
		} catch (ExecutionBatchException e) {
			throw e;
		} catch (Exception e) {
			log.error("", e);
			throw e;
		}
		
		return RepeatStatus.FINISHED;
	}
}
