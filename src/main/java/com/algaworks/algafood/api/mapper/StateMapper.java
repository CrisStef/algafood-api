package com.algaworks.algafood.api.mapper;

import java.util.List;

import com.algaworks.algafood.api.model.request.StateRequest;
import com.algaworks.algafood.api.model.response.StateResponse;
import com.algaworks.algafood.domain.model.State;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
	@Autowired
	private ModelMapper modelMapper;

	public State stateRequestForState(StateRequest stateRequest) {
		return modelMapper.map(stateRequest, State.class);
	}

	public void copyStateForCurrentState(State state, State currentState) {
		modelMapper.map(state, currentState);
	}

	public StateResponse stateForStateResponse(State state) {
		return modelMapper.map(state, StateResponse.class);
	}

	public List<StateResponse> stateListForStateListResponse(List<State> state) {
		return modelMapper.map(state, new TypeToken<List<StateResponse>>(){}.getType());
	}
}