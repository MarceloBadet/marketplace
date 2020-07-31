package com.badet.marketplace.api.job.calculoscore;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CalculoScoreAgendador {
	
	private static final String TODOS_OS_DIAS_DUAS_HORAS_E_QUATRO_MINUTOS_AM = "0 0 2 * * *";
	//private static final String TESTE = "*/15 * * * * *";
	
	
	@Scheduled(cron = TODOS_OS_DIAS_DUAS_HORAS_E_QUATRO_MINUTOS_AM)
	protected void TODOS_OS_DIAS_DUAS_HORAS_E_QUATRO_MINUTOS_AM() {

	
	}
	
}
