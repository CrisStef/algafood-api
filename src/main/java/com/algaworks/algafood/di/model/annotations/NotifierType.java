package com.algaworks.algafood.di.model.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface NotifierType {
	LevelUrgency value();
}