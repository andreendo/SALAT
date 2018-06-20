/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.andreendo.salat;

/**
 *
 * @author renil
 */
public class TimeStopCondition implements StopCondition{

    private long inicio = System.currentTimeMillis();
    private long tempo;

    public TimeStopCondition(int tempo, Enum<Unidade> unidadeEnum) {
        if(unidadeEnum.equals(Unidade.MILLISECONDS)){
            this.tempo = tempo;
        }
        if(unidadeEnum.equals(Unidade.SECONDS)){
            this.tempo = tempo * 1000;
        }
        else if(unidadeEnum.equals(Unidade.MINUTES)){
            this.tempo = tempo * 60000;
        }
        else if(unidadeEnum.equals(Unidade.HOURS)){
            this.tempo = tempo * 3600000;
        }
    }
    
    
    
    @Override
    public boolean hasReached() {
        long tempoAtual = System.currentTimeMillis() - inicio;
        return tempoAtual > tempo;
    }

    @Override
    public void update() {
        
    }
    
}
